package br.com.wrpinheiro.jid.j2vbmodel.interpret.blockvars;

import java.util.HashSet;
import java.util.Set;

import br.com.wrpinheiro.jid.javacompiler.node.AAdditionalIdentifier;
import br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpAnIdConditionalAndExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpIdIdConditionalAndExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpIdOrConditionalAndExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ABarBarIdIdConditionalOrExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ABarBarIdOrConditionalOrExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ABarBarOrIdConditionalOrExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AEmarkIdentifierUnaryExpressionNotPlusMinus;
import br.com.wrpinheiro.jid.javacompiler.node.AEqEqIdEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AEqIdIdEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AEqIdReEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionAssignment;
import br.com.wrpinheiro.jid.javacompiler.node.AGtIdIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AGtIdShRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AGtShIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AGteqIdIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AGteqIdShRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AGteqShIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierAssignment;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatementNoShortIf;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierLeftHandSide;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPostDecrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPostIncrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPreDecrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPreIncrementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPrimaryNoNewArray;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierReturnStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierSwitchLabel;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierSwitchStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierThrowStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierWhileStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierWhileStatementNoShortIf;
import br.com.wrpinheiro.jid.javacompiler.node.AInitializerVariableDeclarator;
import br.com.wrpinheiro.jid.javacompiler.node.AIntegerLiteral;
import br.com.wrpinheiro.jid.javacompiler.node.ALtIdIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ALtIdShRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ALtShIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ALteqIdIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ALteqIdShRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ALteqShIdRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AManyIdArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusAdIdAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusIdIdAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusIdMuAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusIdentifierUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ANeqEqIdEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ANeqIdIdEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ANeqIdReEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AOneIdArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.APercentIdIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APercentIdUnMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APercentMuIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APlusAdIdAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APlusIdIdAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APlusIdMuAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APlusIdentifierUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveExpressionEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveIdentifierEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceExpressionEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceIdentifierEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AReferencePrimaryNoNewArray;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashIdIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashIdUnMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashMuIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AStarIdIdMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AStarIdUnMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AStarMuIdMultiplicativeExpression;

/**
 * Check the input and output variables, and the literals of a program or a
 * block.
 * 
 * @author wrp
 */
public class VarsOfANodeInterpreter extends VarsInterpreterComponent {

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierLeftHandSide(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierLeftHandSide)
	 */
	@Override
	public void inAIdentifierLeftHandSide(AIdentifierLeftHandSide node) {
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAExpressionAssignment(br.com.wrpinheiro.jid.javacompiler.node.AExpressionAssignment)
	 */
	@Override
	public void caseAExpressionAssignment(final AExpressionAssignment node) {
		VarsInterpreterComponent interpreter = new VarsInterpreterComponent();
		IdentifierLeftHandSideDecorator leftHandDecorator = new IdentifierLeftHandSideDecorator(
				interpreter);
		node.getLeftHandSide().apply(leftHandDecorator);

		VarsOfANodeInterpreter exprInterpreter = new VarsOfANodeInterpreter();
		node.getAssignmentExpression().apply(exprInterpreter);

		Set<String> vars = new HashSet<String>(leftHandDecorator
				.getOutputVars());

		vars.retainAll(exprInterpreter.getInputVars());

		if (vars.size() > 0) {
			// vars.size MUST BE 1!
			String var = vars.iterator().next();
			addInputVar(var);
			addOutputVar(var);
		}

		for (String var : exprInterpreter.getInputVars())
			addInputVar(var);

		for (String var : leftHandDecorator.getOutputVars())
			addOutputVar(var);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inALteqIdIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.ALteqIdIdRelationalExpression)
	 */
	@Override
	public void inALteqIdIdRelationalExpression(
			ALteqIdIdRelationalExpression node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIntegerLiteral(br.com.wrpinheiro.jid.javacompiler.node.AIntegerLiteral)
	 */
	@Override
	public void inAIntegerLiteral(AIntegerLiteral node) {
		addLiteral(node.getIntegerLiteral().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAStarMuIdMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.AStarMuIdMultiplicativeExpression)
	 */
	@Override
	public void inAStarMuIdMultiplicativeExpression(
			AStarMuIdMultiplicativeExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAAdditionalIdentifier(br.com.wrpinheiro.jid.javacompiler.node.AAdditionalIdentifier)
	 */
	@Override
	public void inAAdditionalIdentifier(AAdditionalIdentifier node) {
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAAmpAmpAnIdConditionalAndExpression(br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpAnIdConditionalAndExpression)
	 */
	@Override
	public void inAAmpAmpAnIdConditionalAndExpression(
			AAmpAmpAnIdConditionalAndExpression node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAAmpAmpIdIdConditionalAndExpression(br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpIdIdConditionalAndExpression)
	 */
	@Override
	public void inAAmpAmpIdIdConditionalAndExpression(
			AAmpAmpIdIdConditionalAndExpression node) {

		addOutputVar(node.getIdentifier1().getText());
		addOutputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAAmpAmpIdOrConditionalAndExpression(br.com.wrpinheiro.jid.javacompiler.node.AAmpAmpIdOrConditionalAndExpression)
	 */
	@Override
	public void inAAmpAmpIdOrConditionalAndExpression(
			AAmpAmpIdOrConditionalAndExpression node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inABarBarIdIdConditionalOrExpression(br.com.wrpinheiro.jid.javacompiler.node.ABarBarIdIdConditionalOrExpression)
	 */
	@Override
	public void inABarBarIdIdConditionalOrExpression(
			ABarBarIdIdConditionalOrExpression node) {

		addOutputVar(node.getIdentifier1().getText());
		addOutputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inABarBarIdOrConditionalOrExpression(br.com.wrpinheiro.jid.javacompiler.node.ABarBarIdOrConditionalOrExpression)
	 */
	@Override
	public void inABarBarIdOrConditionalOrExpression(
			ABarBarIdOrConditionalOrExpression node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inABarBarOrIdConditionalOrExpression(br.com.wrpinheiro.jid.javacompiler.node.ABarBarOrIdConditionalOrExpression)
	 */
	@Override
	public void inABarBarOrIdConditionalOrExpression(
			ABarBarOrIdConditionalOrExpression node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAEmarkIdentifierUnaryExpressionNotPlusMinus(br.com.wrpinheiro.jid.javacompiler.node.AEmarkIdentifierUnaryExpressionNotPlusMinus)
	 */
	@Override
	public void inAEmarkIdentifierUnaryExpressionNotPlusMinus(
			AEmarkIdentifierUnaryExpressionNotPlusMinus node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAEqEqIdEqualityExpression(br.com.wrpinheiro.jid.javacompiler.node.AEqEqIdEqualityExpression)
	 */
	@Override
	public void inAEqEqIdEqualityExpression(AEqEqIdEqualityExpression node) {
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAEqIdIdEqualityExpression(br.com.wrpinheiro.jid.javacompiler.node.AEqIdIdEqualityExpression)
	 */
	@Override
	public void inAEqIdIdEqualityExpression(AEqIdIdEqualityExpression node) {
		addOutputVar(node.getIdentifier1().getText());
		addOutputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAEqIdReEqualityExpression(br.com.wrpinheiro.jid.javacompiler.node.AEqIdReEqualityExpression)
	 */
	@Override
	public void inAEqIdReEqualityExpression(AEqIdReEqualityExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAGteqIdIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.AGteqIdIdRelationalExpression)
	 */
	@Override
	public void inAGteqIdIdRelationalExpression(
			AGteqIdIdRelationalExpression node) {
		addOutputVar(node.getIdentifier1().getText());
		addOutputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAGteqIdShRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.AGteqIdShRelationalExpression)
	 */
	@Override
	public void inAGteqIdShRelationalExpression(
			AGteqIdShRelationalExpression node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAGteqShIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.AGteqShIdRelationalExpression)
	 */
	@Override
	public void inAGteqShIdRelationalExpression(
			AGteqShIdRelationalExpression node) {

		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAGtIdIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.AGtIdIdRelationalExpression)
	 */
	@Override
	public void inAGtIdIdRelationalExpression(AGtIdIdRelationalExpression node) {
		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAGtIdShRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.AGtIdShRelationalExpression)
	 */
	@Override
	public void inAGtIdShRelationalExpression(AGtIdShRelationalExpression node) {
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAGtShIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.AGtShIdRelationalExpression)
	 */
	@Override
	public void inAGtShIdRelationalExpression(AGtShIdRelationalExpression node) {
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierAssignment(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierAssignment)
	 */
	@Override
	public void inAIdentifierAssignment(AIdentifierAssignment node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierIfThenElseStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatement)
	 */
	@Override
	public void inAIdentifierIfThenElseStatement(
			AIdentifierIfThenElseStatement node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierIfThenElseStatementNoShortIf(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatementNoShortIf)
	 */
	@Override
	public void inAIdentifierIfThenElseStatementNoShortIf(
			AIdentifierIfThenElseStatementNoShortIf node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierIfThenStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenStatement)
	 */
	@Override
	public void inAIdentifierIfThenStatement(AIdentifierIfThenStatement node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierPostDecrementExpression(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPostDecrementExpression)
	 */
	@Override
	public void inAIdentifierPostDecrementExpression(
			AIdentifierPostDecrementExpression node) {
		addInputVar(node.getIdentifier().getText());
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierPostIncrementExpression(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPostIncrementExpression)
	 */
	@Override
	public void inAIdentifierPostIncrementExpression(
			AIdentifierPostIncrementExpression node) {
		addInputVar(node.getIdentifier().getText());
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierPreDecrementExpression(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPreDecrementExpression)
	 */
	@Override
	public void inAIdentifierPreDecrementExpression(
			AIdentifierPreDecrementExpression node) {
		addInputVar(node.getIdentifier().getText());
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierPreIncrementExpression(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPreIncrementExpression)
	 */
	@Override
	public void inAIdentifierPreIncrementExpression(
			AIdentifierPreIncrementExpression node) {
		addInputVar(node.getIdentifier().getText());
		addOutputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierPrimaryNoNewArray(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierPrimaryNoNewArray)
	 */
	@Override
	public void inAIdentifierPrimaryNoNewArray(AIdentifierPrimaryNoNewArray node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierReturnStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierReturnStatement)
	 */
	@Override
	public void inAIdentifierReturnStatement(AIdentifierReturnStatement node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierSwitchLabel(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierSwitchLabel)
	 */
	@Override
	public void inAIdentifierSwitchLabel(AIdentifierSwitchLabel node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierSwitchStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierSwitchStatement)
	 */
	@Override
	public void inAIdentifierSwitchStatement(AIdentifierSwitchStatement node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierThrowStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierThrowStatement)
	 */
	@Override
	public void inAIdentifierThrowStatement(AIdentifierThrowStatement node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierWhileStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierWhileStatement)
	 */
	@Override
	public void inAIdentifierWhileStatement(AIdentifierWhileStatement node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAIdentifierWhileStatementNoShortIf(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierWhileStatementNoShortIf)
	 */
	@Override
	public void inAIdentifierWhileStatementNoShortIf(
			AIdentifierWhileStatementNoShortIf node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAInitializerVariableDeclarator(br.com.wrpinheiro.jid.javacompiler.node.AInitializerVariableDeclarator)
	 */
	@Override
	public void inAInitializerVariableDeclarator(
			AInitializerVariableDeclarator node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inALteqIdShRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.ALteqIdShRelationalExpression)
	 */
	@Override
	public void inALteqIdShRelationalExpression(
			ALteqIdShRelationalExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inALteqShIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.ALteqShIdRelationalExpression)
	 */
	@Override
	public void inALteqShIdRelationalExpression(
			ALteqShIdRelationalExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inALtIdIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.ALtIdIdRelationalExpression)
	 */
	@Override
	public void inALtIdIdRelationalExpression(ALtIdIdRelationalExpression node) {
		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inALtIdShRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.ALtIdShRelationalExpression)
	 */
	@Override
	public void inALtIdShRelationalExpression(ALtIdShRelationalExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inALtShIdRelationalExpression(br.com.wrpinheiro.jid.javacompiler.node.ALtShIdRelationalExpression)
	 */
	@Override
	public void inALtShIdRelationalExpression(ALtShIdRelationalExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAManyIdArgumentList(br.com.wrpinheiro.jid.javacompiler.node.AManyIdArgumentList)
	 */
	@Override
	public void inAManyIdArgumentList(AManyIdArgumentList node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAMinusAdIdAdditiveExpression(br.com.wrpinheiro.jid.javacompiler.node.AMinusAdIdAdditiveExpression)
	 */
	@Override
	public void inAMinusAdIdAdditiveExpression(AMinusAdIdAdditiveExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAMinusIdentifierUnaryExpression(br.com.wrpinheiro.jid.javacompiler.node.AMinusIdentifierUnaryExpression)
	 */
	@Override
	public void inAMinusIdentifierUnaryExpression(
			AMinusIdentifierUnaryExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAMinusIdIdAdditiveExpression(br.com.wrpinheiro.jid.javacompiler.node.AMinusIdIdAdditiveExpression)
	 */
	@Override
	public void inAMinusIdIdAdditiveExpression(AMinusIdIdAdditiveExpression node) {
		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAMinusIdMuAdditiveExpression(br.com.wrpinheiro.jid.javacompiler.node.AMinusIdMuAdditiveExpression)
	 */
	@Override
	public void inAMinusIdMuAdditiveExpression(AMinusIdMuAdditiveExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inANeqEqIdEqualityExpression(br.com.wrpinheiro.jid.javacompiler.node.ANeqEqIdEqualityExpression)
	 */
	@Override
	public void inANeqEqIdEqualityExpression(ANeqEqIdEqualityExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inANeqIdIdEqualityExpression(br.com.wrpinheiro.jid.javacompiler.node.ANeqIdIdEqualityExpression)
	 */
	@Override
	public void inANeqIdIdEqualityExpression(ANeqIdIdEqualityExpression node) {
		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inANeqIdReEqualityExpression(br.com.wrpinheiro.jid.javacompiler.node.ANeqIdReEqualityExpression)
	 */
	@Override
	public void inANeqIdReEqualityExpression(ANeqIdReEqualityExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAOneIdArgumentList(br.com.wrpinheiro.jid.javacompiler.node.AOneIdArgumentList)
	 */
	@Override
	public void inAOneIdArgumentList(AOneIdArgumentList node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPercentIdIdMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.APercentIdIdMultiplicativeExpression)
	 */
	@Override
	public void inAPercentIdIdMultiplicativeExpression(
			APercentIdIdMultiplicativeExpression node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPercentIdUnMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.APercentIdUnMultiplicativeExpression)
	 */
	@Override
	public void inAPercentIdUnMultiplicativeExpression(
			APercentIdUnMultiplicativeExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPercentMuIdMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.APercentMuIdMultiplicativeExpression)
	 */
	@Override
	public void inAPercentMuIdMultiplicativeExpression(
			APercentMuIdMultiplicativeExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPlusAdIdAdditiveExpression(br.com.wrpinheiro.jid.javacompiler.node.APlusAdIdAdditiveExpression)
	 */
	@Override
	public void inAPlusAdIdAdditiveExpression(APlusAdIdAdditiveExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPlusIdentifierUnaryExpression(br.com.wrpinheiro.jid.javacompiler.node.APlusIdentifierUnaryExpression)
	 */
	@Override
	public void inAPlusIdentifierUnaryExpression(
			APlusIdentifierUnaryExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPlusIdIdAdditiveExpression(br.com.wrpinheiro.jid.javacompiler.node.APlusIdIdAdditiveExpression)
	 */
	@Override
	public void inAPlusIdIdAdditiveExpression(APlusIdIdAdditiveExpression node) {
		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPlusIdMuAdditiveExpression(br.com.wrpinheiro.jid.javacompiler.node.APlusIdMuAdditiveExpression)
	 */
	@Override
	public void inAPlusIdMuAdditiveExpression(APlusIdMuAdditiveExpression node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPrimitiveExpressionEnhancedForStatement(br.com.wrpinheiro.jid.javacompiler.node.APrimitiveExpressionEnhancedForStatement)
	 */
	@Override
	public void inAPrimitiveExpressionEnhancedForStatement(
			APrimitiveExpressionEnhancedForStatement node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAPrimitiveIdentifierEnhancedForStatement(br.com.wrpinheiro.jid.javacompiler.node.APrimitiveIdentifierEnhancedForStatement)
	 */
	@Override
	public void inAPrimitiveIdentifierEnhancedForStatement(
			APrimitiveIdentifierEnhancedForStatement node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAReferenceExpressionEnhancedForStatement(br.com.wrpinheiro.jid.javacompiler.node.AReferenceExpressionEnhancedForStatement)
	 */
	@Override
	public void inAReferenceExpressionEnhancedForStatement(
			AReferenceExpressionEnhancedForStatement node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAReferenceIdentifierEnhancedForStatement(br.com.wrpinheiro.jid.javacompiler.node.AReferenceIdentifierEnhancedForStatement)
	 */
	@Override
	public void inAReferenceIdentifierEnhancedForStatement(
			AReferenceIdentifierEnhancedForStatement node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAReferencePrimaryNoNewArray(br.com.wrpinheiro.jid.javacompiler.node.AReferencePrimaryNoNewArray)
	 */
	@Override
	public void inAReferencePrimaryNoNewArray(AReferencePrimaryNoNewArray node) {
		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inASlashIdIdMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.ASlashIdIdMultiplicativeExpression)
	 */
	@Override
	public void inASlashIdIdMultiplicativeExpression(
			ASlashIdIdMultiplicativeExpression node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inASlashIdUnMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.ASlashIdUnMultiplicativeExpression)
	 */
	@Override
	public void inASlashIdUnMultiplicativeExpression(
			ASlashIdUnMultiplicativeExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inASlashMuIdMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.ASlashMuIdMultiplicativeExpression)
	 */
	@Override
	public void inASlashMuIdMultiplicativeExpression(
			ASlashMuIdMultiplicativeExpression node) {

		addInputVar(node.getIdentifier().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAStarIdIdMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.AStarIdIdMultiplicativeExpression)
	 */
	@Override
	public void inAStarIdIdMultiplicativeExpression(
			AStarIdIdMultiplicativeExpression node) {

		addInputVar(node.getIdentifier1().getText());
		addInputVar(node.getIdentifier2().getText());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAStarIdUnMultiplicativeExpression(br.com.wrpinheiro.jid.javacompiler.node.AStarIdUnMultiplicativeExpression)
	 */
	@Override
	public void inAStarIdUnMultiplicativeExpression(
			AStarIdUnMultiplicativeExpression node) {

		addInputVar(node.getIdentifier().getText());
	}
}
