package br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars;

import java.util.Set;

/**
 * Basic functionality to treat input and output variables of a block.
 * 
 * @author wrp
 * 
 *         May 13, 2009
 */
public interface VarsInterpreter {

	/**
	 * Add var as a new input variable.
	 * 
	 * @param var
	 *            the variable.
	 */
	public abstract void addInputVar(final String var);

	/**
	 * Add var as a new output variable.
	 * 
	 * @param var
	 *            the variable.
	 */
	public abstract void addOutputVar(final String var);

	/**
	 * Add the literals found in the block.
	 * 
	 * @param literal
	 */
	public abstract void addLiteral(final String literal);

	/**
	 * Return the input variables.
	 * 
	 * @return a set of input variables.
	 */
	public abstract Set<String> getInputVars();

	/**
	 * Return the output variables.
	 * 
	 * @return a set of output variables.
	 */
	public abstract Set<String> getOutputVars();

}