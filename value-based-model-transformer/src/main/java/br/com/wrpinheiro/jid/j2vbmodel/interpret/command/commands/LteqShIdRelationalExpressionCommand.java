package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ALteqShIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerLessOrEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 */
public class LteqShIdRelationalExpressionCommand implements
    ProductionInterpreterCommand {

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    ALteqShIdRelationalExpression node = (ALteqShIdRelationalExpression) aNode;

    String name = context.getNextComponentId();
    IntegerLessOrEqual c = new IntegerLessOrEqual(context.getSystem(), name,
        node.getLteq().getLine(), ParserUtils.getInfo(aNode));

    context.pushComponent(c);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void outNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    ALteqShIdRelationalExpression node = (ALteqShIdRelationalExpression) aNode;

    IntegerLessOrEqual c = (IntegerLessOrEqual) context.popComponent();

    IntegerConnection conn1 = context.popConnection();
    IntegerConnection conn2 = context.getCurrentConnectionFor(node
        .getIdentifier().getText());

    conn1.addPort(c.getIn1());
    conn2.addPort(c.getIn2());

    String index = c.getName().substring(1);

    IntegerConnection outConn = context.createAuxiliaryConnection(index);
    outConn.setIsBoolean(true);
    outConn.addPort(c.getResult());
    context.pushConnection(outConn);
  }
}
