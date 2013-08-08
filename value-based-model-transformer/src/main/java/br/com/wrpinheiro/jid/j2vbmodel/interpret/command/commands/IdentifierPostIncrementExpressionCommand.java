package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPostIncrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APostIncrementPostfixExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerIncrement;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class IdentifierPostIncrementExpressionCommand implements
    ProductionInterpreterCommand {

  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierPostIncrementExpression node = (AIdentifierPostIncrementExpression) aNode;

    String name = context.getNextComponentId();

    IntegerIncrement c = new IntegerIncrement(context.getSystem(), name, node
        .getPlusPlus().getLine(), ParserUtils.getInfo(aNode));
    context.pushComponent(c);
  }

  @Override
  public void outNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierPostIncrementExpression node = (AIdentifierPostIncrementExpression) aNode;

    IntegerIncrement c = (IntegerIncrement) context.popComponent();

    String id = node.getIdentifier().getText();

    IntegerConnection in = context.getCurrentConnectionFor(id);
    in.addPort(c.getIn());

    String index = c.getName().substring(1);

    IntegerConnection out = context.createConnection(id, index);
    out.addPort(c.getOut());

    if (node.parent() instanceof APostIncrementPostfixExpression)
      context.pushConnection(in);
  }
}
