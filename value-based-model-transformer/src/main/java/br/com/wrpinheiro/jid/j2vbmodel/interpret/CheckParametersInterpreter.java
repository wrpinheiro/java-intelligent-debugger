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
package br.com.wrpinheiro.jid.j2vbmodel.interpret;

import java.util.LinkedHashSet;
import java.util.Set;


import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;
import br.com.wrpinheiro.jid.javacompiler.node.AManyIdArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusIdentifierUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AOneIdArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveFormalParameter;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceFormalParameter;
import br.com.wrpinheiro.jid.javacompiler.node.Node;

/**
 * Check the parameters of a method.
 * 
 * @author wrp
 */
public class CheckParametersInterpreter extends DepthFirstAdapter {
	/**
	 * The set of parameters.
	 */
	private Set<String> params = new LinkedHashSet<String>();

	/**
	 * Return the set of parameters.
	 * 
	 * @return the parameters.
	 */
	public Set<String> getParams() {
		return this.params;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#
	 *      inAPrimitiveFormalParameter (br.com.wrpinheiro.jid.javacompiler.node.
	 *      APrimitiveFormalParameter )
	 */
	@Override
	public void inAPrimitiveFormalParameter(final APrimitiveFormalParameter node) {
		this.params.add(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAReferenceFormalParameter(br.com.wrpinheiro.jid.javacompiler.node.AReferenceFormalParameter)
	 */
	@Override
	public void inAReferenceFormalParameter(final AReferenceFormalParameter node) {
		this.params.add(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAManyIdArgumentList(br.com.wrpinheiro.jid.javacompiler.node.AManyIdArgumentList)
	 */
	@Override
	public void inAManyIdArgumentList(final AManyIdArgumentList node) {
		// the out method must be used!!! Otherwise, the sequence of parameters will be reversed.
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#outAManyIdArgumentList(br.com.wrpinheiro.jid.javacompiler.node.AManyIdArgumentList)
	 */
	@Override
	public void outAManyIdArgumentList(final AManyIdArgumentList node) {
		this.params.add(node.getIdentifier().getText());
	}
	

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAOneIdArgumentList(br.com.wrpinheiro.jid.javacompiler.node.AOneIdArgumentList)
	 */
	@Override
	public void inAOneIdArgumentList(final AOneIdArgumentList node) {
		this.params.add(node.getIdentifier().getText());
	}
	
	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAMinusIdentifierUnaryExpression(br.com.wrpinheiro.jid.javacompiler.node.AMinusIdentifierUnaryExpression)
	 */
	@Override
	public void inAMinusIdentifierUnaryExpression(final AMinusIdentifierUnaryExpression node) {
		this.params.add(node.getIdentifier().getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sablecc.grammars.java_1_5.analysis.DepthFirstAdapter#defaultIn(org
	 * .sablecc.grammars.java_1_5.node.Node)
	 */
	@Override
	public void defaultIn(final Node node) {
		if (!ExclusionClasses.contains(node.getClass()))
			throw new RuntimeException("Not implemented parser for: "
					+ node.getClass());
	}
}
