package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.APercentIdIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerPercent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for production: <id> % <unary_expression>
 * 
 * @author wrp
 */
public class PercentIdIdMultiplicativeExpressionCommand implements
    ProductionInterpreterCommand {

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    APercentIdIdMultiplicativeExpression node = (APercentIdIdMultiplicativeExpression) aNode;

    String name = context.getNextComponentId();
    IntegerPercent c = new IntegerPercent(context.getSystem(), name, node
        .getIdentifier1().getLine(), ParserUtils.getInfo(node));

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

    APercentIdIdMultiplicativeExpression node = (APercentIdIdMultiplicativeExpression) aNode;

    IntegerPercent c = (IntegerPercent) context.popComponent();

    IntegerConnection conn1 = context.getCurrentConnectionFor(node
        .getIdentifier1().getText());
    IntegerConnection conn2 = context.getCurrentConnectionFor(node
        .getIdentifier2().getText());

    conn1.addPort(c.getIn1());
    conn2.addPort(c.getIn2());

    String index = c.getName().substring(1);

    IntegerConnection outConn = context.createAuxiliaryConnection(index);

    outConn.addPort(c.getResult());
    context.pushConnection(outConn);
  }
}
