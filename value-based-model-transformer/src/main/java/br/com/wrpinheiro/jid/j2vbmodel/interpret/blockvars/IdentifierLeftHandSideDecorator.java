package br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars;

import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierLeftHandSide;

/**
 * @author wrp
 * 
 *         May 13, 2009
 */
public class IdentifierLeftHandSideDecorator extends
		BaseVarsInterpreterDecorator {

	/**
	 * Create an instance of the decorator.
	 * 
	 * @param decorated
	 *            the decorated object.
	 */
	public IdentifierLeftHandSideDecorator(final VarsInterpreter decorated) {
		super(decorated);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierLeftHandSide(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierLeftHandSide)
	 */
	@Override
	public void inAIdentifierLeftHandSide(AIdentifierLeftHandSide node) {
		addOutputVar(node.getIdentifier().getText());
	}
}
