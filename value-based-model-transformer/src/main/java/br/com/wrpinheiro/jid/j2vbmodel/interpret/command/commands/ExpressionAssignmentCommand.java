package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentsCache;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionAssignment;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class ExpressionAssignmentCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {

		AExpressionAssignment node = (AExpressionAssignment)aNode;

		String name = context.getNextComponentId();

		AssignmentCommand comm = AssignmentsCache.getInstance().getAssignmentCommand(node.getAssignmentOperator());
		
		IntegerComponent c = comm.getComponent(context.getSystem(), name, node.getAssignmentOperator(), node);
		context.pushComponent(c);
	}

	@Override
	public void outNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {

		AExpressionAssignment node = (AExpressionAssignment)aNode;

		IntegerComponent c = context.popComponent();
		AssignmentCommand comm = AssignmentsCache.getInstance().getAssignmentCommand(node.getAssignmentOperator());

		String id = context.popIdentifier();
		IntegerConnection in = context.popConnection();

		comm.connect(c, id, in, context);
	}
}
