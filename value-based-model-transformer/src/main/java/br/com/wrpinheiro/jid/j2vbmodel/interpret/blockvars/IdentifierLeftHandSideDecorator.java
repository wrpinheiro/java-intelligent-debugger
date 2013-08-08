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
