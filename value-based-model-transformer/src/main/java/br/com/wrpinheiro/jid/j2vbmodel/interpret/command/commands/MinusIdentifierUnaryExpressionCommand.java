package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusIdentifierUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMinus;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class MinusIdentifierUnaryExpressionCommand implements
    ProductionInterpreterCommand {

  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    AMinusIdentifierUnaryExpression node = (AMinusIdentifierUnaryExpression) aNode;

    String name = context.getNextComponentId();
    IntegerMinus c = new IntegerMinus(context.getSystem(), name, node
        .getMinus().getLine(), ParserUtils.getInfo(aNode));

    IntegerConnection input = context.getCurrentConnectionFor(node
        .getIdentifier().getText());
    input.addPort(c.getIn());

    String index = c.getName().substring(1);
    IntegerConnection output = context.createAuxiliaryConnection(index);
    output.addPort(c.getOut());

    context.pushConnection(output);
  }

  @Override
  public void outNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {
    // does not need implementation.
  }
}
