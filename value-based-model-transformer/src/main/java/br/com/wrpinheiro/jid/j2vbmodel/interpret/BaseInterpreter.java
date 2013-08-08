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

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.CommandException;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.CommandFactory;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.Start;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

public abstract class BaseInterpreter extends DepthFirstAdapter {
	private static Logger LOG = Logger.getLogger(BaseInterpreter.class);

	private IntegerAbstractComponent system;
	private InterpretationContext context;

	public BaseInterpreter(final IntegerAbstractComponent system,
			final InterpretationContext ctx) {
		this.system = system;
		this.context = new InterpretationContext(this.system);

		if (ctx != null)
			this.context.restartContextWith(ctx);
	}

	protected IntegerAbstractComponent getSystem() {
		return this.system;
	}

	/**
	 * Return the interpretation context.
	 * @return the interpretation context.
	 */
	public InterpretationContext getContext() {
		return this.context;
	}

	private ProductionInterpreterCommand getProductionCommand(final Node node) {
		String simpleName = node.getClass().getSimpleName();
		String commandName = simpleName.substring(1, simpleName.length())
				+ "Command";

		try {
		return CommandFactory.getInstance().getCommand(commandName);
		} catch (CommandException ex) {
			LOG.debug(node);
			throw ex;
		}
	}
	
	/**
	 * Log the parsing element.
	 * @param strParam the parsing element.
	 */
	protected void logParsing(final String strParam) {
		LOG.debug("parsing " + strParam);
	}

	protected void in(final Node node) {
		if (ExclusionClasses.contains(node.getClass()))
			return;

		logParsing("[in] - " + node);
		getProductionCommand(node).inNode(getContext(), node, this);
	}

	protected void out(final Node node) {
		if (node.getClass().equals(Start.class))
			System.out.println(node);
		
		if (ExclusionClasses.contains(node.getClass()))
			return;

		logParsing("[out] - " + node);
		getProductionCommand(node).outNode(getContext(), node, this);
	}
}