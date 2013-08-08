package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AManyIdArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class ManyIdArgumentListCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		AManyIdArgumentList node = (AManyIdArgumentList) aNode;

		IntegerConnection conn = context.getCurrentConnectionFor(node
				.getIdentifier().getText());
		context.pushConnection(conn);
	}

	@Override
	public void outNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		// does not need implementation.
	}
}
