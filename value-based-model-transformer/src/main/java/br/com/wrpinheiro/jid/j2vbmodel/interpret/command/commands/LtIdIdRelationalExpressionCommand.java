package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ALtIdIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerLess;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Interpretador de relações do tipo: <id> < <id>
 * 
 * @author wrp
 * 
 *         Apr 2, 2009
 */
public class LtIdIdRelationalExpressionCommand implements
    ProductionInterpreterCommand {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand
   * #
   * inNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext
   * , org.sablecc.grammars.java_1_5.node.Node,
   * org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    ALtIdIdRelationalExpression node = (ALtIdIdRelationalExpression) aNode;
    String name = context.getNextComponentId();
    IntegerLess c = new IntegerLess(context.getSystem(), name, node.getLt()
        .getLine(), ParserUtils.getInfo(aNode));

    context.pushComponent(c);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand
   * #
   * outNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext
   * , org.sablecc.grammars.java_1_5.node.Node,
   * org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void outNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    ALtIdIdRelationalExpression node = (ALtIdIdRelationalExpression) aNode;

    IntegerLess c = (IntegerLess) context.popComponent();

    IntegerConnection conn1 = context.getCurrentConnectionFor(node
        .getIdentifier1().getText());
    IntegerConnection conn2 = context.getCurrentConnectionFor(node
        .getIdentifier2().getText());

    conn1.addPort(c.getIn1());
    conn2.addPort(c.getIn2());

    String index = c.getName().substring(1);

    IntegerConnection outConn = context.createAuxiliaryConnection(index);
    outConn.setIsBoolean(true);
    outConn.addPort(c.getResult());
    context.pushConnection(outConn);
  }
}
