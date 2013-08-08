package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.ArrayList;
import java.util.List;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.adapters.BlockStatementAdapter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.util.Constants;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionBasicForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionStatementStatementWithoutTrailingSubstatement;
import br.com.wrpinheiro.jid.javacompiler.node.AForUpdate;
import br.com.wrpinheiro.jid.javacompiler.node.AIifStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AManyStatementExpressionList;
import br.com.wrpinheiro.jid.javacompiler.node.ANoTrailStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AOneStatementExpressionList;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementForInit;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.PStatementExpressionList;
import br.com.wrpinheiro.jid.javacompiler.node.TSemi;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComponentManager;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

/**
 * @author wrp
 * 
 *         May 13, 2009
 */
public class ExpressionBasicForStatementCommand extends AbstractLoopHelper
		implements ProductionInterpreterCommand {

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

		AExpressionBasicForStatement node = (AExpressionBasicForStatement) aNode;
		List<IntegerComponent> initComponents = new ArrayList<IntegerComponent>();

		if (!ParserUtils.isInsideABlock(node.parent()))
			throw new RuntimeException("For sentence is not inside a block.");

		Node aux = node.parent();
		while (!(aux instanceof AStatementBlockStatement))
			aux = aux.parent();

		BlockStatementAdapter adapter = new BlockStatementAdapter(aux.parent());

		List<PStatement> forInitStatements = new ArrayList<PStatement>(5);
		if (node.getForInit() instanceof AStatementForInit)
			this.extractForUpdateStatements(((AStatementForInit) node
					.getForInit()).getStatementExpressionList(),
					forInitStatements);
		else
			throw new RuntimeException(
					"Implementation for variable declation in the for loop is not implemented.");

		List<PBlockStatement> blockStatements = adapter.getBlockStatements();
		int positionOfTheForStructure = blockStatements.indexOf(aux);
		for (PStatement stmt : forInitStatements) {
			blockStatements.add(positionOfTheForStructure,
					new AStatementBlockStatement(stmt));
			positionOfTheForStructure++;

			ComponentManager.getInstance().mark();
			stmt.apply(analysisAdapter);
			initComponents.addAll(ComponentManager.getInstance().release());
		}

		/*
		if (node.getForInit() instanceof AVariableDeclarationForInit) {
			AVariableDeclarationForInit varDeclForInit = (AVariableDeclarationForInit) node
					.getForInit();
			if (varDeclForInit.getLocalVariableDeclaration() instanceof APrimitiveLocalVariableDeclaration) {
				// APrimitiveLocalVariableDeclaration primVarDecl =
				// (APrimitiveLocalVariableDeclaration)
				// varDeclForInit.getLocalVariableDeclaration();
				// ....
			} else
				throw new RuntimeException(
						"Implementation for reference objects is not implemented.");
		}
		*/

		ComponentManager.getInstance().mark();
		node.getForInit().apply(analysisAdapter);
		initComponents.addAll(ComponentManager.getInstance().release());

		List<PStatement> forUpdateStatements = new ArrayList<PStatement>(5);
		this.extractForUpdateStatements(((AForUpdate) node.getForUpdate())
				.getStatementExpressionList(), forUpdateStatements);

		PStatement forStatements = addStatementStatements(node.getStatement(),
				forUpdateStatements);
		node.setStatement(forStatements);

		AIifStatement ifStatement = super.createIifStatement(
				node.getExpression(), node.getStatement(), node.getFor());
		AIifStatement ifSt = this.createNestedIifStatements(ifStatement,
				Constants.MAX_ITERATIONS);

		node.parent().parent().replaceBy(ifSt);
		ComponentManager.getInstance().mark();
		ifSt.parent().apply(analysisAdapter);
		List<IntegerComponent> loopIteration = ComponentManager.getInstance().release();
		IntegerAbstractComponent parent = (IntegerAbstractComponent)loopIteration.get(0);
		for (IntegerComponent comp : initComponents) {
		  comp.replaceParent(parent);
		}
		//System.out.println(newComps);
	}

	private PStatement wrapStatementExpression(final PStatementExpression expr) {
		return new ANoTrailStatement(
				new AExpressionStatementStatementWithoutTrailingSubstatement(
						new AExpressionStatement(expr, new TSemi())));
	}

	/**
	 * Create a statement for each statement expression used in the update of
	 * the for structure.
	 * 
	 * @param expressionList
	 *            the list of statement expressions.
	 * @param statements
	 *            the created statements.
	 */
	private void extractForUpdateStatements(
			final PStatementExpressionList expressionList, final List<PStatement> statements) {
		if (expressionList instanceof AOneStatementExpressionList) {
			PStatementExpression expr = ((AOneStatementExpressionList) expressionList)
					.getStatementExpression();
			statements.add(0, wrapStatementExpression(expr));
		} else {
			AManyStatementExpressionList exprList = (AManyStatementExpressionList) expressionList;
			statements.add(0, wrapStatementExpression(exprList
					.getStatementExpression()));
			extractForUpdateStatements(exprList.getStatementExpressionList(),
					statements);
		}
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
		// does not need implementation.
	}
}
