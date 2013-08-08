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

import java.lang.reflect.Constructor;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

public abstract class AbstractProductionInterpreterCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(InterpretationContext context, Node node,
			BaseInterpreter analysisAdapter) {
		try {
			String name = context.getNextComponentId();

			Class<? extends IntegerComponent> clazz = this
					.getComponentClass(node);
			Constructor<? extends IntegerComponent> constructor = clazz
					.getConstructor(IntegerSystem.class, String.class,
							int.class);

			IntegerComponent c = constructor.newInstance(context, name,
					getLine(node));

			context.pushComponent(c);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void outNode(InterpretationContext context, Node node,
			BaseInterpreter analysisAdapter) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get the component used in the parse of this production.
	 * 
	 * @return
	 */
	public abstract Class<? extends IntegerComponent> getComponentClass(
			Node node);

	/**
	 * Return the line number that the component created will represent.
	 * 
	 * @param node
	 *            the node to parse.
	 * @return
	 */
	public abstract int getLine(Node node);
}
