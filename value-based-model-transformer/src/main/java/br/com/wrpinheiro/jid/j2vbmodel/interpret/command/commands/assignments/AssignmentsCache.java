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
