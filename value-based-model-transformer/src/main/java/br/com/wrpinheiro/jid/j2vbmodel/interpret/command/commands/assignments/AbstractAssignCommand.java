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
