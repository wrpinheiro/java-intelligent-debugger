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


import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AStarAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class StarAssignCommand extends AbstractAssignCommand {

	@Override
	protected Class<? extends PAssignmentOperator> getCommandAssignmentOperatorClass() {
		return AStarAssignAssignmentOperator.class;
	}

	@Override
	protected Class<? extends IntegerComponent> getComponentClass() {
		return IntegerMultiplier.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#getLineOfTheOperator(br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator)
	 */
	@Override
	public int getLineOfTheOperator(final PAssignmentOperator op) {
		checkType(op);
		return ((AStarAssignAssignmentOperator) op).getStarAssign().getLine();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#getComponent(br.com.jdebugger.programdiagnosis.IntegerSystem,
	 *      java.lang.String,
	 *      br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator)
	 */
	@Override
	public IntegerComponent getComponent(IntegerAbstractComponent system, String name,
			PAssignmentOperator op, Node node) {
		checkType(op);
		return new IntegerMultiplier(system, name, this
				.getLineOfTheOperator(op), ParserUtils.getInfo(node));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#connect(br.com.jdebugger.programdiagnosis.components.IntegerComponent,
	 *      java.lang.String,
	 *      br.com.jdebugger.programdiagnosis.connectors.IntegerConnection)
	 */
	@Override
	public void connect(IntegerComponent comp, String leftSideId,
			IntegerConnection rightSideExpr, InterpretationContext ctx) {
		checkType(comp);

		IntegerMultiplier c = (IntegerMultiplier) comp;

		IntegerConnection leftSideConn = ctx
				.getCurrentConnectionFor(leftSideId);
		leftSideConn.addPort(c.getIn1());
		rightSideExpr.addPort(c.getIn2());

		String index = c.getName().substring(1);

		IntegerConnection out = ctx.createConnection(leftSideId, index);
		out.addPort(c.getResult());
	}
}
