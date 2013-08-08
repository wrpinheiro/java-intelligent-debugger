package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashIdIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerDivisor;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class SlashIdIdMultiplicativeExpressionCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		ASlashIdIdMultiplicativeExpression node = (ASlashIdIdMultiplicativeExpression) aNode;

		String name = context.getNextComponentId();

		IntegerDivisor c = new IntegerDivisor(context.getSystem(), name, node
				.getSlash().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	@Override
	public void outNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		ASlashIdIdMultiplicativeExpression node = (ASlashIdIdMultiplicativeExpression) aNode;

		IntegerDivisor c = (IntegerDivisor) context.popComponent();

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
