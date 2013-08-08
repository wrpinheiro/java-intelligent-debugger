package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ANeqIdReEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerNotEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for <id> != <literal>
 * 
 * @author wrp
 * 
 *         Jun 3, 2009
 */
public class NeqIdReEqualityExpressionCommand implements
		ProductionInterpreterCommand {

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		ANeqIdReEqualityExpression node = (ANeqIdReEqualityExpression) aNode;
		String name = context.getNextComponentId();
		IntegerNotEqual c = new IntegerNotEqual(context.getSystem(), name, node
				.getNeq().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		ANeqIdReEqualityExpression node = (ANeqIdReEqualityExpression) aNode;
		IntegerNotEqual c = (IntegerNotEqual) context.popComponent();

		IntegerConnection conn2 = context.popConnection();
		IntegerConnection conn1 = context.getCurrentConnectionFor(node
				.getIdentifier().getText());

		conn1.addPort(c.getIn1());
		conn2.addPort(c.getIn2());

		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.setIsBoolean(true);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}
}
