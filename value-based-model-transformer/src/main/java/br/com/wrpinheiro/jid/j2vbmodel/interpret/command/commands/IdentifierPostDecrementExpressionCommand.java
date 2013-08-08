package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPostDecrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APostDecrementPostfixExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerDecrement;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class IdentifierPostDecrementExpressionCommand implements
    ProductionInterpreterCommand {

  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierPostDecrementExpression node = (AIdentifierPostDecrementExpression) aNode;

    String name = context.getNextComponentId();

    IntegerDecrement c = new IntegerDecrement(context.getSystem(), name, node
        .getMinusMinus().getLine(), ParserUtils.getInfo(aNode));
    context.pushComponent(c);
  }

  @Override
  public void outNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierPostDecrementExpression node = (AIdentifierPostDecrementExpression) aNode;

    IntegerDecrement c = (IntegerDecrement) context.popComponent();

    String id = node.getIdentifier().getText();

    IntegerConnection in = context.getCurrentConnectionFor(id);
    in.addPort(c.getIn());

    String index = c.getName().substring(1);

    IntegerConnection out = context.createConnection(id, index);
    out.addPort(c.getOut());

    if (node.parent() instanceof APostDecrementPostfixExpression)
      context.pushConnection(in);
  }
}
