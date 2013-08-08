package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.Node;

public class NormalClassDeclarationCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {
		// does not need implementation.
	}

	@Override
	public void outNode(final InterpretationContext interpreter,
			final Node aNode, final BaseInterpreter analysisAdapter) {
		// does not need implementation.
	}
}
