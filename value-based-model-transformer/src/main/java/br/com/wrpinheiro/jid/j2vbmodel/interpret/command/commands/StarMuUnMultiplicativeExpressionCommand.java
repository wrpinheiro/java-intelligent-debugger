package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AStarMuUnMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for sentences like: <id> * <id>.
 * 
 * @author wrp
 *
 */
public class StarMuUnMultiplicativeExpressionCommand implements
		ProductionInterpreterCommand {

	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext, br.com.wrpinheiro.jid.javacompiler.node.Node, br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void inNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {
		
		AStarMuUnMultiplicativeExpression node = (AStarMuUnMultiplicativeExpression) aNode;

		String name = context.getNextComponentId();
		IntegerMultiplier c = new IntegerMultiplier(context.getSystem(), name, node
				.getStar().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext, br.com.wrpinheiro.jid.javacompiler.node.Node, br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void outNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {

		IntegerMultiplier c = (IntegerMultiplier) context.popComponent();

		IntegerConnection conn2 = context.popConnection();
		IntegerConnection conn1 = context.popConnection();

		conn1.addPort(c.getIn1());
		conn2.addPort(c.getIn2());

		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}
}
