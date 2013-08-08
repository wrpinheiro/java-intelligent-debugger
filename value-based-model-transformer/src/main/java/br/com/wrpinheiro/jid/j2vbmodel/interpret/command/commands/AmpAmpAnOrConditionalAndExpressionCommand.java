package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpAnOrConditionalAndExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.And;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser of <conditional_and_expression> && <equality_expression>
 * @author wrp
 */
public class AmpAmpAnOrConditionalAndExpressionCommand implements 
		ProductionInterpreterCommand {

	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		AAmpAmpAnOrConditionalAndExpression node = (AAmpAmpAnOrConditionalAndExpression) aNode;
		String name = context.getNextComponentId();
		And c = new And(context.getSystem(), name, node.getAmpAmp().getLine(), ParserUtils.getInfo(aNode));
		context.pushComponent(c);
	}

	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		And c = (And) context.popComponent();

		context.popConnection().addPort(c.getIn2());
		context.popConnection().addPort(c.getIn1());

		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.setIsBoolean(true);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}
}
