package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public interface AssignmentCommand {
	int getLineOfTheOperator(PAssignmentOperator op);
	IntegerComponent getComponent(IntegerAbstractComponent system, String name, PAssignmentOperator op, Node n);
	void connect(IntegerComponent comp, String leftSideId, IntegerConnection rightSideExpr, InterpretationContext ctx);
}
