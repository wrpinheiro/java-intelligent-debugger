package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.MyInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsOfANodeInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.BlockComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.ConditionalBranchComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.patterns.AlternativeSelection;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * Command para if com express�o.
 * 
 * @author wrp
 * 
 *         Apr 2, 2009
 */
public class IdentifierIfThenElseStatementCommand implements
    ProductionInterpreterCommand {

  /**
   * Log of the class.
   */
  private static final Logger LOG = Logger
      .getLogger(IdentifierIfThenElseStatementCommand.class);

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node)
   */
  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierIfThenElseStatement node = (AIdentifierIfThenElseStatement) aNode;

    LOG.debug("Starting if-then-else model construction.");

    String name = context.getNextComponentId();
    AlternativeSelection c = new AlternativeSelection(context.getSystem(),
        name, DefaultSets.toSet(node.getIf().getLine()), ParserUtils
            .getInfo(aNode));
    
    // usado somente para gerar a linha da express�o.
    // isso � uma gambi que deve ser removida depois....
    new NullComponent(c, "", DefaultSets.toSet(node.getIf().getLine()), ParserUtils
        .getInfo(aNode));

    IntegerConnection idConnection = context.getCurrentConnectionFor(node
        .getIdentifier().getText());
    idConnection.addPort(c.getCondResult());

    BlockComponent c_then = new ConditionalBranchComponent(c, c.getName()
        + "_then", DefaultSets.toSet(node.getIf().getLine()), ParserUtils
        .getInfo(node.getStatementNoShortIf()));
    BlockComponent c_else = new ConditionalBranchComponent(c, c.getName()
        + "_else", DefaultSets.toSet(node.getElse().getLine()), ParserUtils
        .getInfo(node.getStatement()));
    c.setThenSystem(c_then);
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
    // variáveis com o mesmo nome declaradas dentro do condicional.

    // TODO descobrir uma forma de disponibilizar as variáveis do contexto
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

      IntegerPort p_else = c_else.addInputPort(var);
      IntegerConnection elseConnVar = elseInterpreter.getContext()
          .createConnection(var,
              "(i)_" + elseInterpreter.getContext().getSystem().getName());
      elseConnVar.addPort(p_else);
      elseConnVar.setIsBoolean(conn.isBoolean());
    }

    node.getStatementNoShortIf().apply(thenInterpreter);
    node.getStatement().apply(elseInterpreter);

    for (String var : outputVars) {
      IntegerPort p = c.addOutputPort(var);
      String index = c.getName().substring(1);
      IntegerConnection conn = context.createConnection(var, index);
      conn.addPort(p);

      IntegerPort p_then = c_then.addOutputPort(var);
      IntegerConnection currentConn = thenInterpreter.getContext()
          .getCurrentConnectionFor(var);
      currentConn.addPort(p_then);
      currentConn.setIsBoolean(conn.isBoolean());

      IntegerPort p_else = c_else.addOutputPort(var);
      currentConn = elseInterpreter.getContext().getCurrentConnectionFor(var);
      currentConn.addPort(p_else);
      currentConn.setIsBoolean(conn.isBoolean());
    }

    LOG.debug("Finishing if-then-else model construction.");
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void outNode(final InterpretationContext interpreter, final Node node,
      final BaseInterpreter analysisAdapter) {
    // does not need implementation.
  }
}
