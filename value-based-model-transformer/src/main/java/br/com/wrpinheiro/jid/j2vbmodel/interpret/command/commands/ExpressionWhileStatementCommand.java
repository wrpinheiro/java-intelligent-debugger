package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.util.Constants;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionWhileStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIifStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PStatement;

/**
 * Parser for: while (<expr>) <block>
 * 
 * @author wrp
 */
public class ExpressionWhileStatementCommand extends AbstractLoopHelper implements
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

		AExpressionWhileStatement node = (AExpressionWhileStatement) aNode;
		PStatement pSt = node.getStatement();

		AIifStatement ifStatement = this.createIifStatement(node.getExpression(),
				pSt, node.getWhile());
		AIifStatement ifSt = this.createNestedIifStatements(ifStatement,
				Constants.MAX_ITERATIONS);

		node.parent().replaceBy(ifSt);

		ifSt.parent().apply(analysisAdapter);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void outNode(InterpretationContext context, Node node,
			BaseInterpreter analysisAdapter) {
		// does not need implementation.
	}
}
