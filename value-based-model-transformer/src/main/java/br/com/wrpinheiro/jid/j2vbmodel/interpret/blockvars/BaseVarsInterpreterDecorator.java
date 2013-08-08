package br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars;

import java.util.Set;

import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;

/**
 * A basic implementation for the VarsInterpreter.
 * 
 * @author wrp
 */
public class BaseVarsInterpreterDecorator extends DepthFirstAdapter implements
		VarsInterpreter {

	/**
	 * Reference for the decorated object.
	 */
	private VarsInterpreter decorated;

	/**
	 * Decorates the
	 * 
	 * @param decorated
	 */
	public BaseVarsInterpreterDecorator(final VarsInterpreter decorated) {
		this.decorated = decorated;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#addInputVar(java.lang.String)
	 */
	public void addInputVar(final String var) {
		this.decorated.addInputVar(var);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#addOutputVar(java.lang.String)
	 */
	public void addOutputVar(final String var) {
		this.decorated.addOutputVar(var);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#addLiteral(java.lang.String)
	 */
	public void addLiteral(final String literal) {
		this.decorated.addLiteral(literal);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#getInputVars()
	 */
	public Set<String> getInputVars() {
		return this.decorated.getInputVars();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#getOutputVars()
	 */
	public Set<String> getOutputVars() {
		return this.decorated.getOutputVars();
	}
}
