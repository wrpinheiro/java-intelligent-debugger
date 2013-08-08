package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPreDecrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APreDecrementUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerDecrement;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class IdentifierPreDecrementExpressionCommand implements
    ProductionInterpreterCommand {

  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierPreDecrementExpression node = (AIdentifierPreDecrementExpression) aNode;

    String name = context.getNextComponentId();

    IntegerDecrement c = new IntegerDecrement(context.getSystem(), name, node
        .getMinusMinus().getLine(), ParserUtils.getInfo(aNode));
    context.pushComponent(c);
  }

  @Override
  public void outNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierPreDecrementExpression node = (AIdentifierPreDecrementExpression) aNode;

    IntegerDecrement c = (IntegerDecrement) context.popComponent();

    String id = node.getIdentifier().getText();

    IntegerConnection in = context.getCurrentConnectionFor(id);
    in.addPort(c.getIn());

    String index = c.getName().substring(1);

    IntegerConnection out = context.createConnection(id, index);
    out.addPort(c.getOut());

    if (node.parent() instanceof APreDecrementUnaryExpression)
      context.pushConnection(out);
  }
}
