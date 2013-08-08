package br.com.wrpinheiro.jid.j2vbmodel.interpret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.wrpinheiro.jid.javacompiler.node.AAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AAssignmentStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ABlock;
import br.com.wrpinheiro.jid.javacompiler.node.ABlockMethodBody;
import br.com.wrpinheiro.jid.javacompiler.node.ABlockStatementWithoutTrailingSubstatement;
import br.com.wrpinheiro.jid.javacompiler.node.ABooleanLiteral;
import br.com.wrpinheiro.jid.javacompiler.node.ABooleanPrimitiveType;
import br.com.wrpinheiro.jid.javacompiler.node.AClassBody;
import br.com.wrpinheiro.jid.javacompiler.node.AClassTypeDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.ACompilationUnit;
import br.com.wrpinheiro.jid.javacompiler.node.AConditionalAssignmentExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ADim;
import br.com.wrpinheiro.jid.javacompiler.node.AExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionPrimaryNoNewArray;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionStatementStatementWithoutTrailingSubstatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionVariableInitializer;
import br.com.wrpinheiro.jid.javacompiler.node.AFinalModifier;
import br.com.wrpinheiro.jid.javacompiler.node.AForLoopStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIfStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIifStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AInstancePrimaryNoNewArray;
import br.com.wrpinheiro.jid.javacompiler.node.AIntIntegralType;
import br.com.wrpinheiro.jid.javacompiler.node.AIntegralNumericType;
import br.com.wrpinheiro.jid.javacompiler.node.ALiteralPrimaryNoNewArray;
import br.com.wrpinheiro.jid.javacompiler.node.ALocalVariableDeclarationStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AManyExArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.AManyFormalParameterList;
import br.com.wrpinheiro.jid.javacompiler.node.AManyFormalParameters;
import br.com.wrpinheiro.jid.javacompiler.node.AManyVariableDeclarators;
import br.com.wrpinheiro.jid.javacompiler.node.AMemberClassBodyDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodClassMemberDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodInvocationStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodPrimaryNoNewArray;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.ANoArrayPrimary;
import br.com.wrpinheiro.jid.javacompiler.node.ANoTrailStatement;
import br.com.wrpinheiro.jid.javacompiler.node.ANormalClassClassDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.ANumericPrimitiveType;
import br.com.wrpinheiro.jid.javacompiler.node.AOneExArgumentList;
import br.com.wrpinheiro.jid.javacompiler.node.AOneFormalParameterList;
import br.com.wrpinheiro.jid.javacompiler.node.AOneFormalParameters;
import br.com.wrpinheiro.jid.javacompiler.node.AOneStatementExpressionList;
import br.com.wrpinheiro.jid.javacompiler.node.AOneVariableDeclarators;
import br.com.wrpinheiro.jid.javacompiler.node.APercentAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.APlusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.APostDecrementPostfixExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APostDecrementStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APostIncrementPostfixExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APostIncrementStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APostfixUnaryExpressionNotPlusMinus;
import br.com.wrpinheiro.jid.javacompiler.node.APreDecrementStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APreDecrementUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APreIncrementStatementExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APreIncrementUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APrimaryPostfixExpression;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveLocalVariableDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveMethodHeader;
import br.com.wrpinheiro.jid.javacompiler.node.APublicModifier;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceFormalParameter;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceLocalVariableDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.AReturnStatementStatementWithoutTrailingSubstatement;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleClassInstanceCreationExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleConditionalAndExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleConditionalOrExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleEqualityExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleMultiplicativeExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleRelationalExpression;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleVariableDeclarator;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AStarAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementForInit;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementWithoutTrailingSubstatementStatementNoShortIf;
import br.com.wrpinheiro.jid.javacompiler.node.AStaticModifier;
import br.com.wrpinheiro.jid.javacompiler.node.AStringLiteral;
import br.com.wrpinheiro.jid.javacompiler.node.ASuper;
import br.com.wrpinheiro.jid.javacompiler.node.AUnaryUnaryExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AVariableDeclarationBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AVoidMethodHeader;
import br.com.wrpinheiro.jid.javacompiler.node.AWhileLoopStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.Start;

/**
 * Supplies a list of classes representing language grammar elements that must
 * be ignored during the analysis.
 * 
 * @author wrp
 */
@SuppressWarnings("all")
public class ExclusionClasses {
	/**
	 * Exception list used to avoid processing of some nodes. 
	 */
	private static Set<Class<? extends Node>> exclusionList = new HashSet<Class<? extends Node>>();
	
	/**
	 * Add a new class to the exception list.
	 * @param clazz the exception class.
	 */
	private static void addToExceptionList(final Class<? extends Node> clazz) {
		ExclusionClasses.exclusionList.add(clazz);
	}
	
	/**
	 * Add a set of classes to the exception list. 
	 * @param classes the set of exception class.
	 */
	private static void addToExclusionList(final Class<? extends Node> ... classes) {
		for (Class<? extends Node> clazz : classes) {
			ExclusionClasses.addToExceptionList(clazz);
		}
	}

	/**
	 * Check if the exclusion list contains clazz.
	 * @param clazz the clazz.
	 * @return TRUE if clazz is in the exclusion list.
	 */
	public static boolean contains(final Class<? extends Node> clazz) {
		return ExclusionClasses.exclusionList.contains(clazz);
	}

	/**
	 * Fill the exclusion list.
	 */
	static {
		ExclusionClasses.addToExclusionList(
				AAssignAssignmentOperator.class,
				AAssignmentStatementExpression.class,
				ABlock.class,
				ABlockMethodBody.class,
				ABlockStatementWithoutTrailingSubstatement.class,
				ABooleanLiteral.class,
				ABooleanPrimitiveType.class,
				AClassBody.class,
				AClassTypeDeclaration.class,
				ACompilationUnit.class,
				AConditionalAssignmentExpression.class,
				ADim.class,
				AExpression.class,
				AExpressionPrimaryNoNewArray.class,
				AExpressionStatement.class,
				AExpressionStatementStatementWithoutTrailingSubstatement.class,
				AExpressionVariableInitializer.class,
				AFinalModifier.class,
				AForLoopStatement.class,
				AIfStatement.class,
				AIifStatement.class,
				AInstancePrimaryNoNewArray.class,
				AIntIntegralType.class,
				AIntegralNumericType.class,
				ALiteralPrimaryNoNewArray.class,
				ALocalVariableDeclarationStatement.class,
				AManyExArgumentList.class,
				AManyFormalParameters.class,
				AManyFormalParameterList.class,
				AManyVariableDeclarators.class,
				AMemberClassBodyDeclaration.class,
				AMethodClassMemberDeclaration.class,
				AMethodInvocationStatementExpression.class,
				AMethodPrimaryNoNewArray.class,
				AMinusAssignAssignmentOperator.class,
				ANoArrayPrimary.class,
				ANoTrailStatement.class,
				ANormalClassClassDeclaration.class,
				ANumericPrimitiveType.class,
				AOneExArgumentList.class,
				AOneFormalParameterList.class,
				AOneFormalParameters.class,
				AOneStatementExpressionList.class,
				AOneVariableDeclarators.class,
				APercentAssignAssignmentOperator.class,
				APlusAssignAssignmentOperator.class,
				APostDecrementPostfixExpression.class,
				APostDecrementStatementExpression.class,
				APostfixUnaryExpressionNotPlusMinus.class,
				APostIncrementPostfixExpression.class,
				APostIncrementStatementExpression.class,
				APreDecrementStatementExpression.class,
				APreDecrementUnaryExpression.class,
				APreIncrementStatementExpression.class,
				APreIncrementUnaryExpression.class,
				APrimaryPostfixExpression.class,
				APrimitiveLocalVariableDeclaration.class,
				APrimitiveMethodHeader.class,
				APublicModifier.class,
				AReferenceFormalParameter.class,
				AReferenceLocalVariableDeclaration.class,
				AReturnStatementStatementWithoutTrailingSubstatement.class,
				ASimpleAdditiveExpression.class,
				ASimpleClassInstanceCreationExpression.class,				
				ASimpleConditionalAndExpression.class,
				ASimpleConditionalOrExpression.class,
				ASimpleEqualityExpression.class,
				ASimpleMultiplicativeExpression.class,
				ASimpleRelationalExpression.class,
				ASimpleVariableDeclarator.class,
				ASlashAssignAssignmentOperator.class,
				AStarAssignAssignmentOperator.class,
				AStatementBlockStatement.class,
				AStatementForInit.class,
				AStatementWithoutTrailingSubstatementStatementNoShortIf.class,
				AStaticModifier.class,
				AStringLiteral.class,
				ASuper.class,
				AUnaryUnaryExpression.class,
				AVariableDeclarationBlockStatement.class,
				AVoidMethodHeader.class,
				AWhileLoopStatement.class,
				Start.class
				);
	}

	public static void main(String[] args) {
		List l = new ArrayList();
		for (Class c : exclusionList) {
			l.add(c.getSimpleName()+".class,");
		}

		Collections.sort(l);

		for (Object o : l) 
			System.out.println(o);
	}
}
