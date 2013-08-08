package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AEqIdIdEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class EqIdIdEqualityExpressionCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		AEqIdIdEqualityExpression node = (AEqIdIdEqualityExpression) aNode;
		String name = context.getNextComponentId();
		IntegerEqual c = new IntegerEqual(context.getSystem(), name, node.getEq().getLine(), ParserUtils.getInfo(aNode));
		context.pushComponent(c);
	}

	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		AEqIdIdEqualityExpression node = (AEqIdIdEqualityExpression) aNode;

		IntegerEqual c = (IntegerEqual) context.popComponent();

		context.getCurrentConnectionFor(node
				.getIdentifier1().getText()).addPort(c.getIn1());
		
		context.getCurrentConnectionFor(node
				.getIdentifier2().getText()).addPort(c.getIn2());

		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.setIsBoolean(true);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}
}
