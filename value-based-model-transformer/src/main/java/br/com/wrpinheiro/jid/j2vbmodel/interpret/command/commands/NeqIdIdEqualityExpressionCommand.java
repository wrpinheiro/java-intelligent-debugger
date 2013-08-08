package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ANeqIdIdEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerNotEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class NeqIdIdEqualityExpressionCommand implements
		ProductionInterpreterCommand {

	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		ANeqIdIdEqualityExpression node = (ANeqIdIdEqualityExpression) aNode;
		String name = context.getNextComponentId();
		IntegerNotEqual c = new IntegerNotEqual(context.getSystem(), name, node.getNeq().getLine(), ParserUtils.getInfo(node));
		context.pushComponent(c);
	}

	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		ANeqIdIdEqualityExpression node = (ANeqIdIdEqualityExpression) aNode;

		IntegerNotEqual c = (IntegerNotEqual) context.popComponent();

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
