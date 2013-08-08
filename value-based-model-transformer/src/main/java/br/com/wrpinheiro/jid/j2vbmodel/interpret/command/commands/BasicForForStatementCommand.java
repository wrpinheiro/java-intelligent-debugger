package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.LinkedList;
import java.util.List;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.adapters.StatementNodeAdapter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.ABasicForForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.ABlock;
import br.com.wrpinheiro.jid.javacompiler.node.ABlockStatementWithoutTrailingSubstatement;
import br.com.wrpinheiro.jid.javacompiler.node.ANoTrailStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PStatement;
import br.com.wrpinheiro.jid.javacompiler.node.TLBrc;
import br.com.wrpinheiro.jid.javacompiler.node.TRBrc;

/**
 * Parser for 'for' structures. We use this command to garantee that the 'for'
 * structure is inside a block.
 * 
 * @author wrp
 */
public class BasicForForStatementCommand implements
		ProductionInterpreterCommand {
	
	/**
	 * Insert the forStmt in a block.
	 * 
	 * @param forStmt
	 *            the for statement.
	 */
	private void insertNodeInABlock(ABasicForForStatement forStmt) {
		Node aux = forStmt;
		while (!ParserUtils.canAddBlock(aux))
			aux = aux.parent();

		StatementNodeAdapter adapter = new StatementNodeAdapter(aux);

		PStatement stmt = adapter.getStatement();

		List<PBlockStatement> blockStatements = new LinkedList<PBlockStatement>();
		blockStatements.add(new AStatementBlockStatement(stmt));

		PStatement newStmt = new ANoTrailStatement(
				new ABlockStatementWithoutTrailingSubstatement(new ABlock(
						new TLBrc(), blockStatements, new TRBrc())));

		adapter.setStatement(newStmt);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void inNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {

		ABasicForForStatement node = (ABasicForForStatement) aNode;

		if (!ParserUtils.isInsideABlock(node))
			insertNodeInABlock(node);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
	 *      br.com.wrpinheiro.jid.javacompiler.node.Node,
	 *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
	 */
	@Override
	public void outNode(final InterpretationContext context, final Node aNode,
			final BaseInterpreter analysisAdapter) {
		// does not need implementation
	}
}
