package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.BraceMatcher;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.MyInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsOfANodeInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIifThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComponentManager;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.BlockComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.ConditionalBranchComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.patterns.LoopIteration;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * Parser for "if(<expr>) <sentence>"
 * 
 * @author wrp
 */
public class ExpressionIifThenStatementCommand implements
    ProductionInterpreterCommand {

  /**
   * Log of the class.
   */
  private static final Logger LOG = Logger
      .getLogger(ExpressionIifThenStatementCommand.class);

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    AExpressionIifThenStatement node = (AExpressionIifThenStatement) aNode;

    LOG.debug("Starting if-then model construction.");

    String name = context.getNextComponentId();

    int iteration = 0;

    if (context.getSystem() instanceof ConditionalBranchComponent) {
      ConditionalBranchComponent branch = ((ConditionalBranchComponent) context
          .getSystem());
      if (branch.getParent() instanceof LoopIteration) {
        iteration = ((LoopIteration) branch.getParent()).getIteration() + 1;
      }
    }

    LoopIteration c = new LoopIteration(context.getSystem(), name, DefaultSets.toSet(node.getIif().getLine()),
        iteration, ParserUtils.getInfo(aNode));

    // MyInterpreter exprInterpreter = new MyInterpreter(c, context);

    // gera modelo para a express�o do condicional.
    ComponentManager.getInstance().mark();

    node.getExpression().apply(analysisAdapter);

    List<IntegerComponent> newComps = ComponentManager.getInstance().release();
    for (IntegerComponent comp : newComps) {
      comp.setConditionalExpression(true);
      comp.replaceParent(c);
      // // aqui!
    }

    context.popConnection().addPort(c.getCondResult());

    BlockComponent c_then = new ConditionalBranchComponent(c, c.getName()
        + "_then", DefaultSets.toSet(node.getIif().getLine()), ParserUtils
        .getInfo(node.getStatement()));
    c.setThenSystem(c_then);

    // If a sentence is a conditional without else block, at least we need
    // an else
    // block to propagate the current values (before the conditional).
    BlockComponent c_else = new ConditionalBranchComponent(c, c.getName()
        + "_else", DefaultSets.toSet(node.getIif().getLine()), ParserUtils
        .getInfo(node.getStatement()));
    c.setElseSystem(c_else);

    MyInterpreter thenInterpreter = new MyInterpreter(c_then, context);
    MyInterpreter elseInterpreter = new MyInterpreter(c_else, context);

    VarsOfANodeInterpreter varsInsideTheConditional = new VarsOfANodeInterpreter();
    aNode.apply(varsInsideTheConditional);

    Set<String> inputVars = varsInsideTheConditional.getInputVars();
    Set<String> outputVars = varsInsideTheConditional.getOutputVars();

    LOG.debug("Input vars [bef]: ." + inputVars);
    LOG.debug("Output vars [bef]: ." + outputVars);

    // This treatment is required to make direct connection between input
    // and output
    // variables in the fictitious else branch.
    for (String varName : outputVars) {
      if (context.hasCurrentConnection(varName) && !inputVars.contains(varName))
        inputVars.add(varName);
    }

    LOG.debug("Input vars [aft]: ." + inputVars);
    LOG.debug("Output vars [aft]: ." + outputVars);

    // TODO como diferenciar vari�veis que est�o num contexto externo de
    // vari�veis com o mesmo nome declaradas dentro do condicional.

    // TODO descobrir uma forma de disponibilizar as vari�veis do contexto
    // fora do condicional para o contexto dentro do condicional.

    for (String var : inputVars) {
      IntegerPort p = c.addInputPort(var);
      IntegerConnection conn = context.getCurrentConnectionFor(var);
      if (conn == null)
        throw new RuntimeException("Could not find current connection " + var
            + " in the contextfor the interpreter: " + analysisAdapter);
      conn.addPort(p);

      IntegerPort p_then = c_then.addInputPort(var);
      IntegerConnection thenConnVar = thenInterpreter.getContext()
          .createConnection(var,
              "(i)_" + thenInterpreter.getContext().getSystem().getName());
      thenConnVar.addPort(p_then);
      thenConnVar.setIsBoolean(conn.isBoolean());

      // As the else branch does not have any component the connections
      // aren't created.
      c_else.addInputPort(var);
    }

    node.getStatement().apply(thenInterpreter);

    for (String var : outputVars) {
      IntegerPort p = c.addOutputPort(var);
      String index = c.getName().substring(1);
      IntegerConnection conn = context.createConnection(var, index);
      conn.addPort(p);

      IntegerPort p_then_output = c_then.addOutputPort(var);
      IntegerConnection currentConn = thenInterpreter.getContext()
          .getCurrentConnectionFor(var);
      currentConn.addPort(p_then_output);
      currentConn.setIsBoolean(conn.isBoolean());

      // Create the output connection and if there is an input connection
      // for the same variables, creates a connection to make a direct
      // connection
      // between input and output.
      IntegerPort p_else_output = c_else.addOutputPort(var);
      if (inputVars.contains(var)) {
        IntegerPort p_else_input = c_else.getInputPort(var);
        IntegerConnection directConn = elseInterpreter.getContext()
            .createConnection(var,
                "(i)_" + elseInterpreter.getContext().getSystem().getName());

        directConn.addPort(p_else_input);
        directConn.addPort(p_else_output);
        directConn.setIsBoolean(conn.isBoolean());
      }
    }

    BraceMatcher bMatcher = new BraceMatcher();
    node.apply(bMatcher);
    
    List<Integer> rBraces = bMatcher.getRBraces();
    if (rBraces.size() > 0) {
      new NullComponent(c, "", DefaultSets.toSet(rBraces.get(rBraces.size()-1)), ParserUtils
          .getInfo(aNode));
    }
    
    LOG.debug("Finishing if-then model construction.");
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node, BaseInterpreter)
   */
  @Override
  public void outNode(InterpretationContext context, Node node,
      BaseInterpreter analysisAdapter) {
    // does not need implementation.
  }
}
