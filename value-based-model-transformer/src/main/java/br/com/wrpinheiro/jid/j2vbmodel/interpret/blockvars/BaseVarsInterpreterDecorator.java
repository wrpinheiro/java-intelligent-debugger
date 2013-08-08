/*
 * Copyright 2006-2013 Wellington Ricardo Pinheiro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
