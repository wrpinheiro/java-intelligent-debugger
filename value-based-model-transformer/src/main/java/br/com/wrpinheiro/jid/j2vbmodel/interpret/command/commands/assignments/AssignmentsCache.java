package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments;

import java.util.HashMap;
import java.util.Map;

import br.com.wrpinheiro.jid.javacompiler.node.AAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.APercentAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.APlusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AStarAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;

public class AssignmentsCache {
	private static AssignmentsCache instance;

	private Map<Class<? extends PAssignmentOperator>, AssignmentCommand> cache = 
		new HashMap<Class<? extends PAssignmentOperator>, AssignmentCommand>();

	private AssignmentsCache() {
		this.cache.put(AAssignAssignmentOperator.class, new AssignCommand());
		this.cache.put(APlusAssignAssignmentOperator.class, new PlusAssignCommand());
		this.cache.put(AMinusAssignAssignmentOperator.class, new MinusAssignCommand());
		this.cache.put(APercentAssignAssignmentOperator.class, new PercentAssignCommand());
		this.cache.put(ASlashAssignAssignmentOperator.class, new SlashAssignCommand());
		this.cache.put(AStarAssignAssignmentOperator.class, new StarAssignCommand());
	}

	public static AssignmentsCache getInstance() {
		if (instance == null) {
			instance = new AssignmentsCache();
		}
		return instance;
	}

	public AssignmentCommand getAssignmentCommand(final PAssignmentOperator op) {
		AssignmentCommand comm = this.cache.get(op.getClass());
		return comm;
	}

}
