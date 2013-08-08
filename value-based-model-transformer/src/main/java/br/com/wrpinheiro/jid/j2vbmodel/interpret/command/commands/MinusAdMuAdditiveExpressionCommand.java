package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusAdMuAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerSubtractor;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class MinusAdMuAdditiveExpressionCommand implements
    ProductionInterpreterCommand {

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    AMinusAdMuAdditiveExpression node = (AMinusAdMuAdditiveExpression) aNode;

    String name = context.getNextComponentId();
    IntegerSubtractor c = new IntegerSubtractor(context.getSystem(), name, node
        .getMinus().getLine(), ParserUtils.getInfo(node));

    context.pushComponent(c);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void outNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    IntegerSubtractor adderComponent = (IntegerSubtractor) context
        .popComponent();

    IntegerConnection conn2 = context.popConnection();
    IntegerConnection conn1 = context.popConnection();

    conn1.addPort(adderComponent.getIn1());
    conn2.addPort(adderComponent.getIn2());

    String index = adderComponent.getName().substring(1);

    IntegerConnection outConn = context.createAuxiliaryConnection(index);
    outConn.addPort(adderComponent.getResult());
    context.pushConnection(outConn);
  }
}
