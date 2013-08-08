package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AStarIdUnMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class StarIdUnMultiplicativeExpressionCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		AStarIdUnMultiplicativeExpression node = (AStarIdUnMultiplicativeExpression) aNode;
		
		String name = context.getNextComponentId();

		IntegerMultiplier c = new IntegerMultiplier(context.getSystem(), name, node
				.getIdentifier().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	@Override
	public void outNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		AStarIdUnMultiplicativeExpression node = (AStarIdUnMultiplicativeExpression) aNode;

		IntegerMultiplier c = (IntegerMultiplier) context.popComponent();

		IntegerConnection conn1 = context.getCurrentConnectionFor(node
				.getIdentifier().getText());
		IntegerConnection conn2 = context.popConnection();

		conn1.addPort(c.getIn1());
		conn2.addPort(c.getIn2());

		// String id = node.getIdentifier().getText();
		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}
}
