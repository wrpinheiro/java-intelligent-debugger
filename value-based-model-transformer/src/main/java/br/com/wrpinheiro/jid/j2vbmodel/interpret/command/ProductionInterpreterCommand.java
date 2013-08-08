package br.com.wrpinheiro.jid.j2vbmodel.interpret.command;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.Node;

/**
 * Commands used to interpret the nodes derived from the productions of the
 * grammar.
 * 
 * @author wrp
 * 
 *         03/07/2009
 */
public interface ProductionInterpreterCommand {
	/**
	 * Process values when entering the node.
	 * 
	 * @param context
	 *            of the interpreter used to manipulate the system.
	 * @param aNode
	 *            the node to be interpreted.
	 * @param analysisAdapter
	 *            the source interpreter.
	 */
	public void inNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter);

	/**
	 * Process values when exiting the node.
	 * 
	 * @param context
	 *            of the interpreter used to manipulate the system.
	 * @param aNode
	 *            the node to be interpreted.
	 * @param analysisAdapter
	 *            the source interpreter.
	 */
	public void outNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter);
}
