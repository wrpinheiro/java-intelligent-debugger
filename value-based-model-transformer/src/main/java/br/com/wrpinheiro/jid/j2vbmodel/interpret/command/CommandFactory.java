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

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	public static final String COMMANDS_PACKAGE = CommandFactory.class.getPackage().getName() + ".commands";
	
	public final Map<String, ProductionInterpreterCommand> cache = 
		new HashMap<String, ProductionInterpreterCommand>();

	public static final CommandFactory instance = new CommandFactory();

	public static CommandFactory getInstance() {
		return instance;
	}

	@SuppressWarnings(value={"all"})
	private ProductionInterpreterCommand loadCommand(String className) {
		String commandName = COMMANDS_PACKAGE + "." + className;

		try {
			Class<? extends ProductionInterpreterCommand> commandClass = (Class<? extends ProductionInterpreterCommand>) Class.forName(commandName);
			ProductionInterpreterCommand command = commandClass.newInstance();
			return command;
		} catch (Exception e) {
			throw new CommandException("could not load command: " + commandName);
		}
	}

	public ProductionInterpreterCommand getCommand(String className) {
		ProductionInterpreterCommand command = this.cache.get(className);

		if (command == null) {
			command = this.loadCommand(className);
			this.cache.put(className, command);
		}

		return command;
	}
}
