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
