package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AEqEqReEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for <equality_expression> == <relational_expression>
 * 
 * @author wrp
 *
 */
public class EqEqReEqualityExpressionCommand implements
		ProductionInterpreterCommand {

	/* (non-Javadoc)
	 * @see org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand#inNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext, org.sablecc.grammars.java_1_5.node.Node, org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
	 */
	@Override
	public void inNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {
		
		AEqEqReEqualityExpression node = (AEqEqReEqualityExpression)aNode;
		String name = context.getNextComponentId();
		IntegerEqual c = new IntegerEqual(context.getSystem(), name, node.getEq().getLine(), ParserUtils.getInfo(aNode));

		context.pushComponent(c);
	}

	/* (non-Javadoc)
	 * @see org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand#outNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext, org.sablecc.grammars.java_1_5.node.Node, org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
	 */
	@Override
	public void outNode(InterpretationContext context, Node aNode,
			BaseInterpreter analysisAdapter) {

		IntegerEqual c = (IntegerEqual) context.popComponent();

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
