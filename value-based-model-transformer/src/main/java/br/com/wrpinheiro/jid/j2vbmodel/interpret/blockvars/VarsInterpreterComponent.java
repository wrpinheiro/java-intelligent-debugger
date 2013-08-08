package br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;

/**
 * Check the input and output variables, and the literals of a program or a
 * block.
 * 
 * @author wrp
 */
public class VarsInterpreterComponent extends DepthFirstAdapter implements VarsInterpreter {
	/**
	 * Logger of the class.
	 */
	private static final Logger LOG = Logger
			.getLogger(VarsInterpreterComponent.class);

	/**
	 * The output variables (variables that have their values changed in the
	 * program).
	 */
	private Set<String> outputVars = new HashSet<String>();

	/**
	 * The input variables (variables used in this program).
	 */
	private Set<String> inputVars = new HashSet<String>();

	/**
	 * The literals of the program.
	 */
	private Set<String> literals = new HashSet<String>();

	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#addInputVar(java.lang.String)
	 */
	public void addInputVar(final String var) {
		// If an expression uses a variable V, we need to check if v initialized
		// internally of the condicional or if it was declared inside the
		// conditional. In both cases, we don't need the external value of the
		// variable. We can check if an input variable has a value assigned
		// before being used, and if it is the case, the we don't need the input
		// value of the variable.
		if (!this.outputVars.contains(var))
			this.inputVars.add(var);
		else
			LOG.debug("Input variable: " + var
					+ " ignored in this conditional.");
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#addOutputVar(java.lang.String)
	 */
	public void addOutputVar(final String var) {
		this.outputVars.add(var);
	}
	
	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#addLiterals(java.lang.String)
	 */
	public void addLiteral(final String literal) {
		this.literals.add(literal);
	}

	/** 
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#getInputVars()
	 */
	public Set<String> getInputVars() {
		return this.inputVars;
	}

	/** 
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars.VarsInterpreter#getOutputVars()
	 */
	public Set<String> getOutputVars() {
		return this.outputVars;
	}
}
