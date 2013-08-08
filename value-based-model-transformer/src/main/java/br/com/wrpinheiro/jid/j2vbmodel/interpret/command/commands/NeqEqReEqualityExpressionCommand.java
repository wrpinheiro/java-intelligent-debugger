package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ANeqEqReEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerNotEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for <id> != <expr>
 * 
 * @author wrp
 *
 * Apr 8, 2009
 */
public class NeqEqReEqualityExpressionCommand implements ProductionInterpreterCommand {

	/* (non-Javadoc)
	 * @see org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand#inNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext, org.sablecc.grammars.java_1_5.node.Node, org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
	 */
	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		ANeqEqReEqualityExpression node = (ANeqEqReEqualityExpression)aNode;
		String name = context.getNextComponentId();
		IntegerNotEqual c = new IntegerNotEqual(context.getSystem(), name, node.getNeq().getLine(), ParserUtils.getInfo(node));

		context.pushComponent(c);
	}

	/* (non-Javadoc)
	 * @see org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand#outNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext, org.sablecc.grammars.java_1_5.node.Node, org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
	 */
	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		IntegerNotEqual c = (IntegerNotEqual) context.popComponent();

		IntegerConnection conn2 = context.popConnection();
		IntegerConnection conn1 = context.popConnection();

		conn1.addPort(c.getIn1());
		conn2.addPort(c.getIn2());

		String index = c.getName().substring(1);

		IntegerConnection outConn = context.createAuxiliaryConnection(index);
		outConn.setIsBoolean(true);
		outConn.addPort(c.getResult());
		context.pushConnection(outConn);
	}

}
