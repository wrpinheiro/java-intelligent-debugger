package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.List;

import br.com.wrpinheiro.jid.javacompiler.node.ABlock;
import br.com.wrpinheiro.jid.javacompiler.node.ABlockStatementWithoutTrailingSubstatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIifThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIfStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIifStatement;
import br.com.wrpinheiro.jid.javacompiler.node.ANoTrailStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PExpression;
import br.com.wrpinheiro.jid.javacompiler.node.PStatement;
import br.com.wrpinheiro.jid.javacompiler.node.TIif;
import br.com.wrpinheiro.jid.javacompiler.node.TLBrc;
import br.com.wrpinheiro.jid.javacompiler.node.TLPar;
import br.com.wrpinheiro.jid.javacompiler.node.TRBrc;
import br.com.wrpinheiro.jid.javacompiler.node.TRPar;
import br.com.wrpinheiro.jid.javacompiler.node.Token;

public abstract class AbstractLoopHelper {

	/**
	 * Return the block of the statement.
	 * 
	 * @param statement
	 *            the statement
	 * @return a block.
	 */
	private ABlock extractBlock(final PStatement statement) {
	
		if (statement instanceof ANoTrailStatement) {
			ANoTrailStatement noTrailSt = (ANoTrailStatement) statement;
			if (noTrailSt.getStatementWithoutTrailingSubstatement() instanceof ABlockStatementWithoutTrailingSubstatement) {
				ABlockStatementWithoutTrailingSubstatement stWithoutTrailSubSt = (ABlockStatementWithoutTrailingSubstatement) noTrailSt
						.getStatementWithoutTrailingSubstatement();
	
				if (stWithoutTrailSubSt.getBlock() instanceof ABlock) {
					ABlock block = (ABlock) stWithoutTrailSubSt.getBlock();
					return block;
				}
			}
		} else if (statement instanceof AIifStatement) {
			AIifStatement ifSt = (AIifStatement) statement;
			if (ifSt.getIifThenStatement() instanceof AExpressionIifThenStatement) {
				AExpressionIifThenStatement exprIfThenSt = (AExpressionIifThenStatement) ifSt
						.getIifThenStatement();
				return extractBlock(exprIfThenSt.getStatement());
			}
		}
	
		throw new RuntimeException("Could not find a block for the statement: "
				+ statement);
	}

	/**
	 * Creates an if statement.
	 * 
	 * @param pEx
	 *            the condition
	 * @param pSt
	 *            the statements.
	 * @return an IfStatement.
	 */
	protected AIifStatement createIifStatement(final PExpression pEx, final PStatement pSt, final Token rootToken) {
		AExpressionIifThenStatement exprIfSt = new AExpressionIifThenStatement();
		exprIfSt.setLPar(new TLPar());
		exprIfSt.setRPar(new TRPar());
		exprIfSt.setIif(new TIif(rootToken.getLine(), rootToken.getPos()));
		exprIfSt.setExpression(pEx);
		exprIfSt.setStatement(pSt);
	
		AIifStatement ifSt = new AIifStatement();
		ifSt.setIifThenStatement(exprIfSt);
	
		return ifSt;
	}

	/**
	 * Add the statements to the statement. If the statements doesn't have a block, a new one is created 
	 * and set in the statement.
	 * @param statement the statement that will have new statements.
	 * @param statements the statements that will be added to the statement.
	 */
	protected PStatement addStatementStatements(final PStatement statement, final List<PStatement> statements) {
		ABlock statementsBlock = null;
		PStatement result = statement;

		if (statement instanceof ANoTrailStatement) {
			ANoTrailStatement noTrailSt = (ANoTrailStatement) statement;
			if (noTrailSt.getStatementWithoutTrailingSubstatement() instanceof ABlockStatementWithoutTrailingSubstatement) {
				ABlockStatementWithoutTrailingSubstatement blockSWithoutTrailSubSt = (ABlockStatementWithoutTrailingSubstatement) noTrailSt.getStatementWithoutTrailingSubstatement();
				// this cast is ok 'cause to the fact that ABlock is the only class that extends 
				statementsBlock = (ABlock) blockSWithoutTrailSubSt.getBlock();
			}
		}

		// in this case, the statements must be put inside of a block.
		if (statementsBlock == null) {
			// create block
			statementsBlock = new ABlock();
			statementsBlock.setLBrc(new TLBrc());
			statementsBlock.setRBrc(new TRBrc());

			// add pSt in the block.
			AStatementBlockStatement blockSt = new AStatementBlockStatement(statement); 
			statementsBlock.getBlockStatements().add(blockSt);

			result = new ANoTrailStatement(new ABlockStatementWithoutTrailingSubstatement(statementsBlock));
		}
		
		// now, let's do the easier job!
		for (PStatement st : statements)
			statementsBlock.getBlockStatements().add(new AStatementBlockStatement(st));
		
		return result;
	}

	/**
	 * Create numberOfNestedIfs nested ifStatement using modelIfStatement as
	 * model. This method should only be called by createNestedIfStatements.
	 * 
	 * @param modelIfStatement
	 *            the model.
	 * @param ifStatement
	 *            the if statement that will contain the nested ifs.
	 * @param numberOfNestedIfs
	 *            determines the number of nested ifs.
	 * @return an AIfStatement.
	 */
	private AIifStatement internalCreateNestedStatements(final AIifStatement modelIfStatement, final AIifStatement ifStatement,
			final int numberOfNestedIfs) {
				if (numberOfNestedIfs < 1)
					return ifStatement;
			
				AIifStatement outerIfStatement = (AIifStatement) modelIfStatement.clone();
				ABlock block = this.extractBlock(outerIfStatement);
			
				AStatementBlockStatement statementBlockStatement = new AStatementBlockStatement();
				statementBlockStatement.setStatement(ifStatement);
				block.getBlockStatements().addLast(statementBlockStatement);
			
				return internalCreateNestedStatements(modelIfStatement,
						outerIfStatement, numberOfNestedIfs - 1);
			}

	/**
	 * Create numberOfNestedIfs nested ifStatement using modelIfStatement as
	 * model. This is a wrapper method for internalCreateNestedStatements.
	 * 
	 * @param modelIfStatement
	 *            the model.
	 * @param ifStatement
	 *            the if statement that will contain the nested ifs.
	 * @param numberOfNestedIfs
	 *            determines the number of nested ifs.
	 * @return an AIfStatement.
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.
	 *     
	 *     
	 *      ExpressionWhileStatementCommand#internalCreateNestedStatements(AIfStatement,
	 *      AIfStatement, int)
	 */
	protected AIifStatement createNestedIifStatements(final AIifStatement modelIfStatement, final int numberOfNestedIfs) {
		return this.internalCreateNestedStatements(modelIfStatement,
				(AIifStatement) modelIfStatement.clone(), numberOfNestedIfs);
	}
}