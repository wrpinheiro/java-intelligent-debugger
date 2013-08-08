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


import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

public abstract class AbstractAssignCommand implements AssignmentCommand {

	protected abstract Class<? extends PAssignmentOperator> getCommandAssignmentOperatorClass();
	protected abstract Class<? extends IntegerComponent> getComponentClass();

	protected void checkType(final PAssignmentOperator op) {
		if (!getCommandAssignmentOperatorClass().isInstance(op))
			throw new RuntimeException(
					"Command for " + this.getCommandAssignmentOperatorClass() + " cannot be used for "
							+ op.getClass() + " to parser node: " + op);
	}

	protected void checkType(final IntegerComponent comp) {
		if (!getComponentClass().isInstance(comp))
			throw new RuntimeException(
					"Command for " + this.getCommandAssignmentOperatorClass() + " cannot be used with a component "
							+ " with the type " + comp.getClass());
	}
}
