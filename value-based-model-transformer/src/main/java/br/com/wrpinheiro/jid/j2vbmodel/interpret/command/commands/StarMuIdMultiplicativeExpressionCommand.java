package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AStarMuIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 *
 * Apr 2, 2009
 */
public class StarMuIdMultiplicativeExpressionCommand implements
		ProductionInterpreterCommand {

	/**
	 * (non-Javadoc)
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext, br.com.wrpinheiro.jid.javacompiler.node.Node, org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
	 */
	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		AStarMuIdMultiplicativeExpression node = (AStarMuIdMultiplicativeExpression) aNode;

		String name = context.getNextComponentId();

		IntegerMultiplier c = new IntegerMultiplier(context.getSystem(), name, node
				.getIdentifier().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	/* (non-Javadoc)
	 * @see org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand#outNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext, org.sablecc.grammars.java_1_5.node.Node, org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
	 */
	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		AStarMuIdMultiplicativeExpression node = (AStarMuIdMultiplicativeExpression) aNode;

		IntegerMultiplier c = (IntegerMultiplier) context.popComponent();

		IntegerConnection conn1 = context.getCurrentConnectionFor(node
				.getIdentifier().getText());
		IntegerConnection conn2 = context.popConnection();

		conn1.addPort(c.getIn1());
		conn2.addPort(c.getIn2());

		// String id = node.getIdentifier().getText();
		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}
}
