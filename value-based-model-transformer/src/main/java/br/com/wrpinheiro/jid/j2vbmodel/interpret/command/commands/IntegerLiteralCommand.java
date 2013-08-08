package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIntegerLiteral;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class IntegerLiteralCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		AIntegerLiteral node = (AIntegerLiteral) aNode;

		String literal = node.getIntegerLiteral().getText();
		IntegerConnection conn = context.getConstantConnectionFor(literal);

		context.pushConnection(conn);
	}

	@Override
	public void outNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		// does not need implementation.
	}
}
