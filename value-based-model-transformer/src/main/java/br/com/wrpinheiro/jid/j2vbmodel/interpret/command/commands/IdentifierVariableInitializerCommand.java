package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierVariableInitializer;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for the rightside <id> of: <type> <id> = <id>
 * 
 * @author wrp
 */
public class IdentifierVariableInitializerCommand implements
		ProductionInterpreterCommand {

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void inNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {

		AIdentifierVariableInitializer node = (AIdentifierVariableInitializer) aNode;

		IntegerConnection conn = context.getCurrentConnectionFor(node.getIdentifier().getText());
		context.pushConnection(conn);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void outNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {
		// does not need an implementation.
	}

}
