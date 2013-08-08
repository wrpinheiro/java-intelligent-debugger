package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AOneIdArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class OneIdArgumentListCommand implements ProductionInterpreterCommand {

	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		AOneIdArgumentList node = (AOneIdArgumentList) aNode;

		IntegerConnection conn = context.getCurrentConnectionFor(node
				.getIdentifier().getText());
		
		context.pushConnection(conn);
	}

	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		// does not neet implementation
	}

}
