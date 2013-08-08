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
