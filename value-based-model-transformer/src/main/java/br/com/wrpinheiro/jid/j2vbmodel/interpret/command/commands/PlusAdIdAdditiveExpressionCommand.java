package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.APlusAdIdAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class PlusAdIdAdditiveExpressionCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		APlusAdIdAdditiveExpression node = (APlusAdIdAdditiveExpression) aNode;
		String name = context.getNextComponentId();
		IntegerAdder c = new IntegerAdder(context.getSystem(), name, node
				.getIdentifier().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	@Override
	public void outNode(final InterpretationContext context, final Node aNode, final BaseInterpreter analysisAdapter) {
		APlusAdIdAdditiveExpression node = (APlusAdIdAdditiveExpression) aNode;
		
		IntegerAdder adderComponent = (IntegerAdder) context.popComponent();

		IntegerConnection conn1 = context.popConnection();
		IntegerConnection conn2 = context.getCurrentConnectionFor(node
				.getIdentifier().getText());

		conn1.addPort(adderComponent.getIn1());
		conn2.addPort(adderComponent.getIn2());

		String index = adderComponent.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);

		outConn.addPort(adderComponent.getResult());
		context.pushConnection(outConn);
	}
}
