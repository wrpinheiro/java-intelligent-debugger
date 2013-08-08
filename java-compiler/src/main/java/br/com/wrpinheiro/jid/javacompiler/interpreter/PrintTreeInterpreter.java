package br.com.wrpinheiro.jid.javacompiler.interpreter;



import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;
import br.com.wrpinheiro.jid.javacompiler.node.*;

public class PrintTreeInterpreter extends DepthFirstAdapter {
	private OutputUtils out = new OutputUtils();

	@Override
	public void inAAdditionalIdentifier(AAdditionalIdentifier node) {
		out.print("inAAdditionalIdentifier: " + node.toString());
	}

	@Override
	public void inAAmpAmpAnIdConditionalAndExpression(
			AAmpAmpAnIdConditionalAndExpression node) {
		out.print("inAAmpAmpAnIdConditionalAndExpression: " + node.toString());	
	}

	@Override
	public void inAAmpAmpAnOrConditionalAndExpression(
			AAmpAmpAnOrConditionalAndExpression node) {
		out.print("inAAmpAmpAnOrConditionalAndExpression: " + node.toString());	
	}

	@Override
	public void inAAmpAmpIdIdConditionalAndExpression(
			AAmpAmpIdIdConditionalAndExpression node) {
		out.print("inAAmpAmpIdIdConditionalAndExpression: " + node.toString());	
	}

	@Override
	public void inAAmpAmpIdOrConditionalAndExpression(
			AAmpAmpIdOrConditionalAndExpression node) {
		out.print("inAAmpAmpIdOrConditionalAndExpression: " + node.toString());	
	}

	@Override
	public void inAArguments(AArguments node) {
		out.print("inAArguments: " + node.toString());	
	}

	@Override
	public void inAArrayInitializer(AArrayInitializer node) {
		out.print("inAArrayInitializer: " + node.toString());	
	}

	@Override
	public void inAArrayInitializerElementValue(
			AArrayInitializerElementValue node) {
		out.print("inAArrayInitializerElementValue: " + node.toString());	
	}

	@Override
	public void inAArrayLeftHandSide(AArrayLeftHandSide node) {
		out.print("inAArrayLeftHandSide: " + node.toString());	
	}

	@Override
	public void inAArrayPrimary(AArrayPrimary node) {
		out.print("inAArrayPrimary: " + node.toString());	
	}

	@Override
	public void inAArrayPrimaryNoNewArray(AArrayPrimaryNoNewArray node) {
		out.print("inAArrayPrimaryNoNewArray: " + node.toString());	
	}

	@Override
	public void inAArrayVariableInitializer(AArrayVariableInitializer node) {
		out.print("inAArrayVariableInitializer: " + node.toString());	
	}

	@Override
	public void inAAssignAssignmentOperator(AAssignAssignmentOperator node) {
		out.print("inAAssignAssignmentOperator: " + node.toString());	
	}

	@Override
	public void inAAssignmentAssignmentExpression(
			AAssignmentAssignmentExpression node) {
		out.print("inAAssignmentAssignmentExpression: " + node.toString());	
	}

	@Override
	public void inAAssignmentStatementExpression(
			AAssignmentStatementExpression node) {
		out.print("inAAssignmentStatementExpression: " + node.toString());	
	}

	@Override
	public void inABarBarIdIdConditionalOrExpression(
			ABarBarIdIdConditionalOrExpression node) {
		out.print("inABarBarIdIdConditionalOrExpression: " + node.toString());	
	}

	@Override
	public void inABarBarIdOrConditionalOrExpression(
			ABarBarIdOrConditionalOrExpression node) {
		out.print("inABarBarIdOrConditionalOrExpression: " + node.toString());	
	}

	@Override
	public void inABarBarOrIdConditionalOrExpression(
			ABarBarOrIdConditionalOrExpression node) {
		out.print("inABarBarOrIdConditionalOrExpression: " + node.toString());	
	}

	@Override
	public void inABarBarOrOrConditionalOrExpression(
			ABarBarOrOrConditionalOrExpression node) {
		out.print("inABarBarOrOrConditionalOrExpression: " + node.toString());	
	}

	@Override
	public void inABasicForForStatement(ABasicForForStatement node) {
		out.print("inABasicForForStatement: " + node.toString());	
	}

	@Override
	public void inABlock(ABlock node) {
		out.print("inABlock: " + node.toString());	
	}

	@Override
	public void inABlockMethodBody(ABlockMethodBody node) {
		out.print("inABlockMethodBody: " + node.toString());	
	}

	@Override
	public void inABlockStatementWithoutTrailingSubstatement(
			ABlockStatementWithoutTrailingSubstatement node) {
		out.print("inABlockStatementWithoutTrailingSubstatement: " + node.toString());	
	}

	@Override
	public void inABooleanLiteral(ABooleanLiteral node) {
		out.print("inABooleanLiteral: " + node.toString());	
	}

	@Override
	public void inABooleanPrimitiveType(ABooleanPrimitiveType node) {
		out.print("inABooleanPrimitiveType: " + node.toString());	
	}

	@Override
	public void inABreakStatement(ABreakStatement node) {
		out.print("inABreakStatement: " + node.toString());	
	}

	@Override
	public void inABreakStatementStatementWithoutTrailingSubstatement(
			ABreakStatementStatementWithoutTrailingSubstatement node) {
		out.print("inABreakStatementStatementWithoutTrailingSubstatement: " + node.toString());	
	}

	@Override
	public void inAByteIntegralType(AByteIntegralType node) {
		out.print("inAByteIntegralType: " + node.toString());	
	}

	@Override
	public void inACastUnaryExpressionNotPlusMinus(
			ACastUnaryExpressionNotPlusMinus node) {
		out.print("inACastUnaryExpressionNotPlusMinus: " + node.toString());	
	}

	@Override
	public void inACatchClause(ACatchClause node) {
		out.print("inACatchClause: " + node.toString());	
	}

	@Override
	public void inACatchTryStatement(ACatchTryStatement node) {
		out.print("inACatchTryStatement: " + node.toString());	
	}

	@Override
	public void inACharacterLiteral(ACharacterLiteral node) {
		out.print("inACharacterLiteral: " + node.toString());	
	}

	@Override
	public void inACharIntegralType(ACharIntegralType node) {
		out.print("inACharIntegralType: " + node.toString());	
	}

	@Override
	public void inAClassBody(AClassBody node) {
		out.print("inAClassBody: " + node.toString());	
	}

	@Override
	public void inAClassClassMemberDeclaration(AClassClassMemberDeclaration node) {
		out.print("inAClassClassMemberDeclaration: " + node.toString());	
	}

	@Override
	public void inAClassDeclarationBlockStatement(
			AClassDeclarationBlockStatement node) {
		out.print("inAClassDeclarationBlockStatement: " + node.toString());	
	}

	@Override
	public void inAClassNameMethodInvocation(AClassNameMethodInvocation node) {
		out.print("inAClassNameMethodInvocation: " + node.toString());	
	}

	@Override
	public void inAClassPrimaryNoNewArray(AClassPrimaryNoNewArray node) {
		out.print("inAClassPrimaryNoNewArray: " + node.toString());	
	}

	@Override
	public void inAClassTypeDeclaration(AClassTypeDeclaration node) {
		out.print("inAClassTypeDeclaration: " + node.toString());
	}

	@Override
	public void inAConditionalAssignmentExpression(
			AConditionalAssignmentExpression node) {
		out.print("inAConditionalAssignmentExpression: " + node.toString());
	}

	@Override
	public void inAConditionalElementValue(AConditionalElementValue node) {
		out.print("inAConditionalElementValue: " + node.toString());
	}

	@Override
	public void inAConstantExpression(AConstantExpression node) {
		out.print("inAConstantExpression: " + node.toString());
	}

	@Override
	public void inAConstructorBody(AConstructorBody node) {
		out.print("inAConstructorBody: " + node.toString());
	}

	@Override
	public void inAConstructorClassBodyDeclaration(
			AConstructorClassBodyDeclaration node) {
		out.print("inAConstructorClassBodyDeclaration: " + node.toString());
	}

	@Override
	public void inAConstructorDeclaration(AConstructorDeclaration node) {
		out.print("inAConstructorDeclaration: " + node.toString());
	}

	@Override
	public void inAConstructorDeclarator(AConstructorDeclarator node) {
		out.print("inAConstructorDeclarator: " + node.toString());
	}

	@Override
	public void inAContinueStatement(AContinueStatement node) {
		out.print("inAContinueStatement: " + node.toString());
	}

	@Override
	public void inAContinueStatementStatementWithoutTrailingSubstatement(
			AContinueStatementStatementWithoutTrailingSubstatement node) {
		out.print("inAContinueStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inADefaultSwitchLabel(ADefaultSwitchLabel node) {
		out.print("inADefaultSwitchLabel: " + node.toString());
	}

	@Override
	public void inADim(ADim node) {
		out.print("inADim: " + node.toString());
	}

	@Override
	public void inADoStatementStatementWithoutTrailingSubstatement(
			ADoStatementStatementWithoutTrailingSubstatement node) {
		out.print("inADoStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inADoubleFloatingPointType(ADoubleFloatingPointType node) {
		out.print("inADoubleFloatingPointType: " + node.toString());
	}

	@Override
	public void inAElementValueArrayInitializer(
			AElementValueArrayInitializer node) {
		out.print("inAElementValueArrayInitializer: " + node.toString());
	}

	@Override
	public void inAEmarkExpressionUnaryExpressionNotPlusMinus(
			AEmarkExpressionUnaryExpressionNotPlusMinus node) {
		out.print("inAEmarkExpressionUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void inAEmarkIdentifierUnaryExpressionNotPlusMinus(
			AEmarkIdentifierUnaryExpressionNotPlusMinus node) {
		out.print("inAEmarkIdentifierUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void inAEmptyBasicForStatement(AEmptyBasicForStatement node) {
		out.print("inAEmptyBasicForStatement: " + node.toString());
	}

	@Override
	public void inAEmptyClassMemberDeclaration(AEmptyClassMemberDeclaration node) {
		out.print("inAEmptyClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void inAEmptyForStatementNoShortIf(AEmptyForStatementNoShortIf node) {
		out.print("inAEmptyForStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAEmptyMethodBody(AEmptyMethodBody node) {
		out.print("inAEmptyMethodBody: " + node.toString());
	}

	@Override
	public void inAEmptyReturnStatement(AEmptyReturnStatement node) {
		out.print("inAEmptyReturnStatement: " + node.toString());
	}

	@Override
	public void inAEmptyStatement(AEmptyStatement node) {
		out.print("inAEmptyStatement: " + node.toString());
	}

	@Override
	public void inAEmptyStatementStatementWithoutTrailingSubstatement(
			AEmptyStatementStatementWithoutTrailingSubstatement node) {
		out.print("inAEmptyStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inAEmptyTypeDeclaration(AEmptyTypeDeclaration node) {
		out.print("inAEmptyTypeDeclaration: " + node.toString());
	}

	@Override
	public void inAEnhancedForForStatement(AEnhancedForForStatement node) {
		out.print("inAEnhancedForForStatement: " + node.toString());
	}

	@Override
	public void inAEqEqIdEqualityExpression(AEqEqIdEqualityExpression node) {
		out.print("inAEqEqIdEqualityExpression: " + node.toString());
	}

	@Override
	public void inAEqEqReEqualityExpression(AEqEqReEqualityExpression node) {
		out.print("inAEqEqReEqualityExpression: " + node.toString());
	}

	@Override
	public void inAEqIdIdEqualityExpression(AEqIdIdEqualityExpression node) {
		out.print("inAEqIdIdEqualityExpression: " + node.toString());
	}

	@Override
	public void inAEqIdReEqualityExpression(AEqIdReEqualityExpression node) {
		out.print("inAEqIdReEqualityExpression: " + node.toString());
	}

	@Override
	public void inAExceptionType(AExceptionType node) {
		out.print("inAExceptionType: " + node.toString());
	}

	@Override
	public void inAExpression(AExpression node) {
		out.print("inAExpression: " + node.toString());
	}

	@Override
	public void inAExpressionAssignment(AExpressionAssignment node) {
		out.print("inAExpressionAssignment: " + node.toString());
	}

	@Override
	public void inAExpressionBasicForStatement(AExpressionBasicForStatement node) {
		out.print("inAExpressionBasicForStatement: " + node.toString());
	}

	@Override
	public void inAExpressionDimExpr(AExpressionDimExpr node) {
		out.print("inAExpressionDimExpr: " + node.toString());
	}

	@Override
	public void inAExpressionDoStatement(AExpressionDoStatement node) {
		out.print("inAExpressionDoStatement: " + node.toString());
	}

	@Override
	public void inAExpressionForStatementNoShortIf(
			AExpressionForStatementNoShortIf node) {
		out.print("inAExpressionForStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAExpressionIfThenElseStatement(
			AExpressionIfThenElseStatement node) {
		out.print("inAExpressionIfThenElseStatement: " + node.toString());
	}

	@Override
	public void inAExpressionIfThenElseStatementNoShortIf(
			AExpressionIfThenElseStatementNoShortIf node) {
		out.print("inAExpressionIfThenElseStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAExpressionIfThenStatement(AExpressionIfThenStatement node) {
		out.print("inAExpressionIfThenStatement: " + node.toString());
	}

	@Override
	public void inAExpressionPostDecrementExpression(
			AExpressionPostDecrementExpression node) {
		out.print("inAExpressionPostDecrementExpression: " + node.toString());
	}

	@Override
	public void inAExpressionPostIncrementExpression(
			AExpressionPostIncrementExpression node) {
		out.print("inAExpressionPostIncrementExpression: " + node.toString());
	}

	@Override
	public void inAExpressionPreDecrementExpression(
			AExpressionPreDecrementExpression node) {
		out.print("inAExpressionPreDecrementExpression: " + node.toString());
	}

	@Override
	public void inAExpressionPreIncrementExpression(
			AExpressionPreIncrementExpression node) {
		out.print("inAExpressionPreIncrementExpression: " + node.toString());
	}

	@Override
	public void inAExpressionPrimaryNoNewArray(AExpressionPrimaryNoNewArray node) {
		out.print("inAExpressionPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAExpressionReturnStatement(AExpressionReturnStatement node) {
		out.print("inAExpressionReturnStatement: " + node.toString());
	}

	@Override
	public void inAExpressionStatement(AExpressionStatement node) {
		out.print("inAExpressionStatement: " + node.toString());
	}

	@Override
	public void inAExpressionStatementStatementWithoutTrailingSubstatement(
			AExpressionStatementStatementWithoutTrailingSubstatement node) {
		out.print("inAExpressionStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inAExpressionSwitchLabel(AExpressionSwitchLabel node) {
		out.print("inAExpressionSwitchLabel: " + node.toString());
	}

	@Override
	public void inAExpressionSwitchStatement(AExpressionSwitchStatement node) {
		out.print("inAExpressionSwitchStatement: " + node.toString());
	}

	@Override
	public void inAExpressionThrowStatement(AExpressionThrowStatement node) {
		out.print("inAExpressionThrowStatement: " + node.toString());
	}

	@Override
	public void inAExpressionVariableInitializer(
			AExpressionVariableInitializer node) {
		out.print("inAExpressionVariableInitializer: " + node.toString());
	}

	@Override
	public void inAExpressionWhileStatement(AExpressionWhileStatement node) {
		out.print("inAExpressionWhileStatement: " + node.toString());
	}

	@Override
	public void inAExpressionWhileStatementNoShortIf(
			AExpressionWhileStatementNoShortIf node) {
		out.print("inAExpressionWhileStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAFalseBooleanLiteral(AFalseBooleanLiteral node) {
		out.print("inAFalseBooleanLiteral: " + node.toString());
	}

	@Override
	public void inAFieldClassMemberDeclaration(AFieldClassMemberDeclaration node) {
		out.print("inAFieldClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void inAFieldLeftHandSide(AFieldLeftHandSide node) {
		out.print("inAFieldLeftHandSide: " + node.toString());
	}

	@Override
	public void inAFieldPrimaryNoNewArray(AFieldPrimaryNoNewArray node) {
		out.print("inAFieldPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAFinally(AFinally node) {
		out.print("inAFinally: " + node.toString());
	}

	@Override
	public void inAFinallyTryStatement(AFinallyTryStatement node) {
		out.print("inAFinallyTryStatement: " + node.toString());
	}

	@Override
	public void inAFinalModifier(AFinalModifier node) {
		out.print("inAFinalModifier: " + node.toString());
	}

	@Override
	public void inAFloatFloatingPointType(AFloatFloatingPointType node) {
		out.print("inAFloatFloatingPointType: " + node.toString());
	}

	@Override
	public void inAFloatingNumericType(AFloatingNumericType node) {
		out.print("inAFloatingNumericType: " + node.toString());
	}

	@Override
	public void inAFloatingPointLiteral(AFloatingPointLiteral node) {
		out.print("inAFloatingPointLiteral: " + node.toString());
	}

	@Override
	public void inAForLoopStatement(AForLoopStatement node) {
		out.print("inAForLoopStatement: " + node.toString());
	}

	@Override
	public void inAForStatementNoShortIfStatementNoShortIf(
			AForStatementNoShortIfStatementNoShortIf node) {
		out.print("inAForStatementNoShortIfStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAForUpdate(AForUpdate node) {
		out.print("inAForUpdate: " + node.toString());
	}

	@Override
	public void inAGteqIdIdRelationalExpression(
			AGteqIdIdRelationalExpression node) {
		out.print("inAGteqIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGteqIdShRelationalExpression(
			AGteqIdShRelationalExpression node) {
		out.print("inAGteqIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGteqShIdRelationalExpression(
			AGteqShIdRelationalExpression node) {
		out.print("inAGteqShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGteqShShRelationalExpression(
			AGteqShShRelationalExpression node) {
		out.print("inAGteqShShRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGtIdIdRelationalExpression(AGtIdIdRelationalExpression node) {
		out.print("inAGtIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGtIdShRelationalExpression(AGtIdShRelationalExpression node) {
		out.print("inAGtIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGtShIdRelationalExpression(AGtShIdRelationalExpression node) {
		out.print("inAGtShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inAGtShShRelationalExpression(AGtShShRelationalExpression node) {
		out.print("inAGtShShRelationalExpression: " + node.toString());
	}

	@Override
	public void inAIdentifierAssignment(AIdentifierAssignment node) {
		out.print("inAIdentifierAssignment: " + node.toString());
	}

	@Override
	public void inAIdentifierBasicForStatement(AIdentifierBasicForStatement node) {
		out.print("inAIdentifierBasicForStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierClassInstanceCreationExpression(
			AIdentifierClassInstanceCreationExpression node) {
		out.print("inAIdentifierClassInstanceCreationExpression: " + node.toString());
	}

	@Override
	public void inAIdentifierDimExpr(AIdentifierDimExpr node) {
		out.print("inAIdentifierDimExpr: " + node.toString());
	}

	@Override
	public void inAIdentifierDoStatement(AIdentifierDoStatement node) {
		out.print("inAIdentifierDoStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierElementValue(AIdentifierElementValue node) {
		out.print("inAIdentifierElementValue: " + node.toString());
	}

	@Override
	public void inAIdentifierExArrayAccess(AIdentifierExArrayAccess node) {
		out.print("inAIdentifierExArrayAccess: " + node.toString());
	}

	@Override
	public void inAIdentifierExplicitConstructorInvocation(
			AIdentifierExplicitConstructorInvocation node) {
		out.print("inAIdentifierExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void inAIdentifierForStatementNoShortIf(
			AIdentifierForStatementNoShortIf node) {
		out.print("inAIdentifierForStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAIdentifierIdArrayAccess(AIdentifierIdArrayAccess node) {
		out.print("inAIdentifierIdArrayAccess: " + node.toString());
	}

	@Override
	public void inAIdentifierIfThenElseStatement(
			AIdentifierIfThenElseStatement node) {
		out.print("inAIdentifierIfThenElseStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierIfThenElseStatementNoShortIf(
			AIdentifierIfThenElseStatementNoShortIf node) {
		out.print("inAIdentifierIfThenElseStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAIdentifierIfThenStatement(AIdentifierIfThenStatement node) {
		out.print("inAIdentifierIfThenStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierLeftHandSide(AIdentifierLeftHandSide node) {
		out.print("inAIdentifierLeftHandSide: " + node.toString());
	}

	@Override
	public void inAIdentifierPostDecrementExpression(
			AIdentifierPostDecrementExpression node) {
		out.print("inAIdentifierPostDecrementExpression: " + node.toString());
	}

	@Override
	public void inAIdentifierPostIncrementExpression(
			AIdentifierPostIncrementExpression node) {
		out.print("inAIdentifierPostIncrementExpression: " + node.toString());
	}

	@Override
	public void inAIdentifierPreDecrementExpression(
			AIdentifierPreDecrementExpression node) {
		out.print("inAIdentifierPreDecrementExpression: " + node.toString());
	}

	@Override
	public void inAIdentifierPreIncrementExpression(
			AIdentifierPreIncrementExpression node) {
		out.print("inAIdentifierPreIncrementExpression: " + node.toString());
	}

	@Override
	public void inAIdentifierPrimaryNoNewArray(AIdentifierPrimaryNoNewArray node) {
		out.print("inAIdentifierPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAIdentifierReturnStatement(AIdentifierReturnStatement node) {
		out.print("inAIdentifierReturnStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierSwitchLabel(AIdentifierSwitchLabel node) {
		out.print("inAIdentifierSwitchLabel: " + node.toString());
	}

	@Override
	public void inAIdentifierSwitchStatement(AIdentifierSwitchStatement node) {
		out.print("inAIdentifierSwitchStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierThrowStatement(AIdentifierThrowStatement node) {
		out.print("inAIdentifierThrowStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierVariableInitializer(
			AIdentifierVariableInitializer node) {
		out.print("inAIdentifierVariableInitializer: " + node.toString());
	}

	@Override
	public void inAIdentifierWhileStatement(AIdentifierWhileStatement node) {
		out.print("inAIdentifierWhileStatement: " + node.toString());
	}

	@Override
	public void inAIdentifierWhileStatementNoShortIf(
			AIdentifierWhileStatementNoShortIf node) {
		out.print("inAIdentifierWhileStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAIfElseStatement(AIfElseStatement node) {
		out.print("inAIfElseStatement: " + node.toString());
	}

	@Override
	public void inAIfStatement(AIfStatement node) {
		out.print("inAIfStatement: " + node.toString());
	}

	@Override
	public void inAIfThenElseStatementNoShortIfStatementNoShortIf(
			AIfThenElseStatementNoShortIfStatementNoShortIf node) {
		out.print("inAIfThenElseStatementNoShortIfStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAInitializerVariableDeclarator(
			AInitializerVariableDeclarator node) {
		out.print("inAInitializerVariableDeclarator: " + node.toString());
	}

	@Override
	public void inAInstanceCreationStatementExpression(
			AInstanceCreationStatementExpression node) {
		out.print("inAInstanceCreationStatementExpression: " + node.toString());
	}

	@Override
	public void inAInstancePrimaryNoNewArray(AInstancePrimaryNoNewArray node) {
		out.print("inAInstancePrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAIntegerLiteral(AIntegerLiteral node) {
		out.print("inAIntegerLiteral: " + node.toString());
	}

	@Override
	public void inAIntegralNumericType(AIntegralNumericType node) {
		out.print("inAIntegralNumericType: " + node.toString());
	}

	@Override
	public void inAIntIntegralType(AIntIntegralType node) {
		out.print("inAIntIntegralType: " + node.toString());
	}

	@Override
	public void inALiteralPrimaryNoNewArray(ALiteralPrimaryNoNewArray node) {
		out.print("inALiteralPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inALocalVariableDeclarationStatement(
			ALocalVariableDeclarationStatement node) {
		out.print("inALocalVariableDeclarationStatement: " + node.toString());
	}

	@Override
	public void inALongIntegralType(ALongIntegralType node) {
		out.print("inALongIntegralType: " + node.toString());
	}

	@Override
	public void inALteqIdIdRelationalExpression(
			ALteqIdIdRelationalExpression node) {
		out.print("inALteqIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inALteqIdShRelationalExpression(
			ALteqIdShRelationalExpression node) {
		out.print("inALteqIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void inALteqShIdRelationalExpression(
			ALteqShIdRelationalExpression node) {
		out.print("inALteqShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inALteqShShRelationalExpression(
			ALteqShShRelationalExpression node) {
		out.print("inALteqShShRelationalExpression: " + node.toString());
	}

	@Override
	public void inALtIdIdRelationalExpression(ALtIdIdRelationalExpression node) {
		out.print("inALtIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inALtIdShRelationalExpression(ALtIdShRelationalExpression node) {
		out.print("inALtIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void inALtShIdRelationalExpression(ALtShIdRelationalExpression node) {
		out.print("inALtShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void inALtShShRelationalExpression(ALtShShRelationalExpression node) {
		out.print("inALtShShRelationalExpression: " + node.toString());
	}

	@Override
	public void inAManyElementValues(AManyElementValues node) {
		out.print("inAManyElementValues: " + node.toString());
	}

	@Override
	public void inAManyExArgumentList(AManyExArgumentList node) {
		out.print("inAManyExArgumentList: " + node.toString());
	}

	@Override
	public void inAManyExceptionTypeList(AManyExceptionTypeList node) {
		out.print("inAManyExceptionTypeList: " + node.toString());
	}

	@Override
	public void inAManyFormalParameterList(AManyFormalParameterList node) {
		out.print("inAManyFormalParameterList: " + node.toString());
	}

	@Override
	public void inAManyFormalParameters(AManyFormalParameters node) {
		out.print("inAManyFormalParameters: " + node.toString());
	}

	@Override
	public void inAManyIdArgumentList(AManyIdArgumentList node) {
		out.print("inAManyIdArgumentList: " + node.toString());
	}

	@Override
	public void inAManyStatementExpressionList(AManyStatementExpressionList node) {
		out.print("inAManyStatementExpressionList: " + node.toString());
	}

	@Override
	public void inAManyVariableDeclarators(AManyVariableDeclarators node) {
		out.print("inAManyVariableDeclarators: " + node.toString());
	}

	@Override
	public void inAManyVariableInitializers(AManyVariableInitializers node) {
		out.print("inAManyVariableInitializers: " + node.toString());
	}

	@Override
	public void inAMemberClassBodyDeclaration(AMemberClassBodyDeclaration node) {
		out.print("inAMemberClassBodyDeclaration: " + node.toString());
	}

	@Override
	public void inAMethodClassMemberDeclaration(
			AMethodClassMemberDeclaration node) {
		out.print("inAMethodClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void inAMethodDeclaration(AMethodDeclaration node) {
		out.print("inAMethodDeclaration: " + node.toString());
	}

	@Override
	public void inAMethodDeclarator(AMethodDeclarator node) {
		out.print("inAMethodDeclarator: " + node.toString());
	}

	@Override
	public void inAMethodInvocationStatementExpression(
			AMethodInvocationStatementExpression node) {
		out.print("inAMethodInvocationStatementExpression: " + node.toString());
	}

	@Override
	public void inAMethodPrimaryNoNewArray(AMethodPrimaryNoNewArray node) {
		out.print("inAMethodPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAMinusAdIdAdditiveExpression(AMinusAdIdAdditiveExpression node) {
		out.print("inAMinusAdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAMinusAdMuAdditiveExpression(AMinusAdMuAdditiveExpression node) {
		out.print("inAMinusAdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAMinusAssignAssignmentOperator(
			AMinusAssignAssignmentOperator node) {
		out.print("inAMinusAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void inAMinusExpressionUnaryExpression(
			AMinusExpressionUnaryExpression node) {
		out.print("inAMinusExpressionUnaryExpression: " + node.toString());
	}

	@Override
	public void inAMinusIdentifierUnaryExpression(
			AMinusIdentifierUnaryExpression node) {
		out.print("inAMinusIdentifierUnaryExpression: " + node.toString());
	}

	@Override
	public void inAMinusIdIdAdditiveExpression(AMinusIdIdAdditiveExpression node) {
		out.print("inAMinusIdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAMinusIdMuAdditiveExpression(AMinusIdMuAdditiveExpression node) {
		out.print("inAMinusIdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void inANeqEqIdEqualityExpression(ANeqEqIdEqualityExpression node) {
		out.print("inANeqEqIdEqualityExpression: " + node.toString());
	}

	@Override
	public void inANeqEqReEqualityExpression(ANeqEqReEqualityExpression node) {
		out.print("inANeqEqReEqualityExpression: " + node.toString());
	}

	@Override
	public void inANeqIdIdEqualityExpression(ANeqIdIdEqualityExpression node) {
		out.print("inANeqIdIdEqualityExpression: " + node.toString());
	}

	@Override
	public void inANeqIdReEqualityExpression(ANeqIdReEqualityExpression node) {
		out.print("inANeqIdReEqualityExpression: " + node.toString());
	}

	@Override
	public void inANoArrayPrimary(ANoArrayPrimary node) {
		out.print("inANoArrayPrimary: " + node.toString());
	}

	@Override
	public void inANormalClassClassDeclaration(ANormalClassClassDeclaration node) {
		out.print("inANormalClassClassDeclaration: " + node.toString());
	}

	@Override
	public void inANormalClassDeclaration(ANormalClassDeclaration node) {
		out.print("inANormalClassDeclaration: " + node.toString());
	}

	@Override
	public void inANoTrailStatement(ANoTrailStatement node) {
		out.print("inANoTrailStatement: " + node.toString());
	}

	@Override
	public void inANullLiteral(ANullLiteral node) {
		out.print("inANullLiteral: " + node.toString());
	}

	@Override
	public void inANumericPrimitiveType(ANumericPrimitiveType node) {
		out.print("inANumericPrimitiveType: " + node.toString());
	}

	@Override
	public void inAOneElementValues(AOneElementValues node) {
		out.print("inAOneElementValues: " + node.toString());
	}

	@Override
	public void inAOneExArgumentList(AOneExArgumentList node) {
		out.print("inAOneExArgumentList: " + node.toString());
	}

	@Override
	public void inAOneExceptionTypeList(AOneExceptionTypeList node) {
		out.print("inAOneExceptionTypeList: " + node.toString());
	}

	@Override
	public void inAOneFormalParameterList(AOneFormalParameterList node) {
		out.print("inAOneFormalParameterList: " + node.toString());
	}

	@Override
	public void inAOneFormalParameters(AOneFormalParameters node) {
		out.print("inAOneFormalParameters: " + node.toString());
	}

	@Override
	public void inAOneIdArgumentList(AOneIdArgumentList node) {
		out.print("inAOneIdArgumentList: " + node.toString());
	}

	@Override
	public void inAOneStatementExpressionList(AOneStatementExpressionList node) {
		out.print("inAOneStatementExpressionList: " + node.toString());
	}

	@Override
	public void inAOneVariableDeclarators(AOneVariableDeclarators node) {
		out.print("inAOneVariableDeclarators: " + node.toString());
	}

	@Override
	public void inAOneVariableInitializers(AOneVariableInitializers node) {
		out.print("inAOneVariableInitializers: " + node.toString());
	}

	@Override
	public void inAPercentAssignAssignmentOperator(
			APercentAssignAssignmentOperator node) {
		out.print("inAPercentAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void inAPercentIdIdMultiplicativeExpression(
			APercentIdIdMultiplicativeExpression node) {
		out.print("inAPercentIdIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAPercentIdUnMultiplicativeExpression(
			APercentIdUnMultiplicativeExpression node) {
		out.print("inAPercentIdUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAPercentMuIdMultiplicativeExpression(
			APercentMuIdMultiplicativeExpression node) {
		out.print("inAPercentMuIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAPercentMuUnMultiplicativeExpression(
			APercentMuUnMultiplicativeExpression node) {
		out.print("inAPercentMuUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAPlusAdIdAdditiveExpression(APlusAdIdAdditiveExpression node) {
		out.print("inAPlusAdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAPlusAdMuAdditiveExpression(APlusAdMuAdditiveExpression node) {
		out.print("inAPlusAdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAPlusAssignAssignmentOperator(
			APlusAssignAssignmentOperator node) {
		out.print("inAPlusAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void inAPlusExpressionUnaryExpression(
			APlusExpressionUnaryExpression node) {
		out.print("inAPlusExpressionUnaryExpression: " + node.toString());
	}

	@Override
	public void inAPlusIdentifierUnaryExpression(
			APlusIdentifierUnaryExpression node) {
		out.print("inAPlusIdentifierUnaryExpression: " + node.toString());
	}

	@Override
	public void inAPlusIdIdAdditiveExpression(APlusIdIdAdditiveExpression node) {
		out.print("inAPlusIdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAPlusIdMuAdditiveExpression(APlusIdMuAdditiveExpression node) {
		out.print("inAPlusIdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void inAPostDecrementPostfixExpression(
			APostDecrementPostfixExpression node) {
		out.print("inAPostDecrementPostfixExpression: " + node.toString());
	}

	@Override
	public void inAPostDecrementStatementExpression(
			APostDecrementStatementExpression node) {
		out.print("inAPostDecrementStatementExpression: " + node.toString());
	}

	@Override
	public void inAPostfixUnaryExpressionNotPlusMinus(
			APostfixUnaryExpressionNotPlusMinus node) {
		out.print("inAPostfixUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void inAPostIncrementPostfixExpression(
			APostIncrementPostfixExpression node) {
		out.print("inAPostIncrementPostfixExpression: " + node.toString());
	}

	@Override
	public void inAPostIncrementStatementExpression(
			APostIncrementStatementExpression node) {
		out.print("inAPostIncrementStatementExpression: " + node.toString());
	}

	@Override
	public void inAPreDecrementStatementExpression(
			APreDecrementStatementExpression node) {
		out.print("inAPreDecrementStatementExpression: " + node.toString());
	}

	@Override
	public void inAPreDecrementUnaryExpression(APreDecrementUnaryExpression node) {
		out.print("inAPreDecrementUnaryExpression: " + node.toString());
	}

	@Override
	public void inAPreIncrementStatementExpression(
			APreIncrementStatementExpression node) {
		out.print("inAPreIncrementStatementExpression: " + node.toString());
	}

	@Override
	public void inAPreIncrementUnaryExpression(APreIncrementUnaryExpression node) {
		out.print("inAPreIncrementUnaryExpression: " + node.toString());
	}

	@Override
	public void inAPrimaryClassInstanceCreationExpression(
			APrimaryClassInstanceCreationExpression node) {
		out.print("inAPrimaryClassInstanceCreationExpression: " + node.toString());
	}

	@Override
	public void inAPrimaryExArrayAccess(APrimaryExArrayAccess node) {
		out.print("inAPrimaryExArrayAccess: " + node.toString());
	}

	@Override
	public void inAPrimaryExplicitConstructorInvocation(
			APrimaryExplicitConstructorInvocation node) {
		out.print("inAPrimaryExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void inAPrimaryFieldAccess(APrimaryFieldAccess node) {
		out.print("inAPrimaryFieldAccess: " + node.toString());
	}

	@Override
	public void inAPrimaryIdArrayAccess(APrimaryIdArrayAccess node) {
		out.print("inAPrimaryIdArrayAccess: " + node.toString());
	}

	@Override
	public void inAPrimaryMethodInvocation(APrimaryMethodInvocation node) {
		out.print("inAPrimaryMethodInvocation: " + node.toString());
	}

	@Override
	public void inAPrimaryPostfixExpression(APrimaryPostfixExpression node) {
		out.print("inAPrimaryPostfixExpression: " + node.toString());
	}

	@Override
	public void inAPrimitiveArrayCreationExpression(
			APrimitiveArrayCreationExpression node) {
		out.print("inAPrimitiveArrayCreationExpression: " + node.toString());
	}

	@Override
	public void inAPrimitiveConstantDeclaration(
			APrimitiveConstantDeclaration node) {
		out.print("inAPrimitiveConstantDeclaration: " + node.toString());
	}

	@Override
	public void inAPrimitiveExpressionCastExpression(
			APrimitiveExpressionCastExpression node) {
		out.print("inAPrimitiveExpressionCastExpression: " + node.toString());
	}

	@Override
	public void inAPrimitiveExpressionEnhancedForStatement(
			APrimitiveExpressionEnhancedForStatement node) {
		out.print("inAPrimitiveExpressionEnhancedForStatement: " + node.toString());
	}

	@Override
	public void inAPrimitiveFieldDeclaration(APrimitiveFieldDeclaration node) {
		out.print("inAPrimitiveFieldDeclaration: " + node.toString());
	}

	@Override
	public void inAPrimitiveFormalParameter(APrimitiveFormalParameter node) {
		out.print("inAPrimitiveFormalParameter: " + node.toString());
	}

	@Override
	public void inAPrimitiveIdentifierCastExpression(
			APrimitiveIdentifierCastExpression node) {
		out.print("inAPrimitiveIdentifierCastExpression: " + node.toString());
	}

	@Override
	public void inAPrimitiveIdentifierEnhancedForStatement(
			APrimitiveIdentifierEnhancedForStatement node) {
		out.print("inAPrimitiveIdentifierEnhancedForStatement: " + node.toString());
	}

	@Override
	public void inAPrimitiveInitializerArrayCreationExpression(
			APrimitiveInitializerArrayCreationExpression node) {
		out.print("inAPrimitiveInitializerArrayCreationExpression: " + node.toString());
	}

	@Override
	public void inAPrimitiveLocalVariableDeclaration(
			APrimitiveLocalVariableDeclaration node) {
		out.print("inAPrimitiveLocalVariableDeclaration: " + node.toString());
	}

	@Override
	public void inAPrimitiveMethodHeader(APrimitiveMethodHeader node) {
		out.print("inAPrimitiveMethodHeader: " + node.toString());
	}

	@Override
	public void inAPrimitivePrimaryNoNewArray(APrimitivePrimaryNoNewArray node) {
		out.print("inAPrimitivePrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAPrivateModifier(APrivateModifier node) {
		out.print("inAPrivateModifier: " + node.toString());
	}

	@Override
	public void inAProtectedModifier(AProtectedModifier node) {
		out.print("inAProtectedModifier: " + node.toString());
	}

	@Override
	public void inAPublicModifier(APublicModifier node) {
		out.print("inAPublicModifier: " + node.toString());
	}

	@Override
	public void inAReferenceArrayCreationExpression(
			AReferenceArrayCreationExpression node) {
		out.print("inAReferenceArrayCreationExpression: " + node.toString());
	}

	@Override
	public void inAReferenceConstantDeclaration(
			AReferenceConstantDeclaration node) {
		out.print("inAReferenceConstantDeclaration: " + node.toString());
	}

	@Override
	public void inAReferenceExpressionCastExpression(
			AReferenceExpressionCastExpression node) {
		out.print("inAReferenceExpressionCastExpression: " + node.toString());
	}

	@Override
	public void inAReferenceExpressionEnhancedForStatement(
			AReferenceExpressionEnhancedForStatement node) {
		out.print("inAReferenceExpressionEnhancedForStatement: " + node.toString());
	}

	@Override
	public void inAReferenceFieldDeclaration(AReferenceFieldDeclaration node) {
		out.print("inAReferenceFieldDeclaration: " + node.toString());
	}

	@Override
	public void inAReferenceFormalParameter(AReferenceFormalParameter node) {
		out.print("inAReferenceFormalParameter: " + node.toString());
	}

	@Override
	public void inAReferenceIdentifierCastExpression(
			AReferenceIdentifierCastExpression node) {
		out.print("inAReferenceIdentifierCastExpression: " + node.toString());
	}

	@Override
	public void inAReferenceIdentifierEnhancedForStatement(
			AReferenceIdentifierEnhancedForStatement node) {
		out.print("inAReferenceIdentifierEnhancedForStatement: " + node.toString());
	}

	@Override
	public void inAReferenceInitializerArrayCreationExpression(
			AReferenceInitializerArrayCreationExpression node) {
		out.print("inAReferenceInitializerArrayCreationExpression: " + node.toString());
	}

	@Override
	public void inAReferenceLocalVariableDeclaration(
			AReferenceLocalVariableDeclaration node) {
		out.print("inAReferenceLocalVariableDeclaration: " + node.toString());
	}

	@Override
	public void inAReferenceMethodHeader(AReferenceMethodHeader node) {
		out.print("inAReferenceMethodHeader: " + node.toString());
	}

	@Override
	public void inAReferencePrimaryNoNewArray(AReferencePrimaryNoNewArray node) {
		out.print("inAReferencePrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAReferenceSuperFieldAccess(AReferenceSuperFieldAccess node) {
		out.print("inAReferenceSuperFieldAccess: " + node.toString());
	}

	@Override
	public void inAReturnStatementStatementWithoutTrailingSubstatement(
			AReturnStatementStatementWithoutTrailingSubstatement node) {
		out.print("inAReturnStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inAShortIntegralType(AShortIntegralType node) {
		out.print("inAShortIntegralType: " + node.toString());
	}

	@Override
	public void inASimpleAdditiveExpression(ASimpleAdditiveExpression node) {
		out.print("inASimpleAdditiveExpression: " + node.toString());
	}

	@Override
	public void inASimpleClassInstanceCreationExpression(
			ASimpleClassInstanceCreationExpression node) {
		out.print("inASimpleClassInstanceCreationExpression: " + node.toString());
	}

	@Override
	public void inASimpleConditionalAndExpression(
			ASimpleConditionalAndExpression node) {
		out.print("inASimpleConditionalAndExpression: " + node.toString());
	}

	@Override
	public void inASimpleConditionalOrExpression(
			ASimpleConditionalOrExpression node) {
		out.print("inASimpleConditionalOrExpression: " + node.toString());
	}

	@Override
	public void inASimpleEqualityExpression(ASimpleEqualityExpression node) {
		out.print("inASimpleEqualityExpression: " + node.toString());
	}

	@Override
	public void inASimpleMethodInvocation(ASimpleMethodInvocation node) {
		out.print("inASimpleMethodInvocation: " + node.toString());
	}

	@Override
	public void inASimpleMultiplicativeExpression(
			ASimpleMultiplicativeExpression node) {
		out.print("inASimpleMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inASimpleRelationalExpression(ASimpleRelationalExpression node) {
		out.print("inASimpleRelationalExpression: " + node.toString());
	}

	@Override
	public void inASimpleVariableDeclarator(ASimpleVariableDeclarator node) {
		out.print("inASimpleVariableDeclarator: " + node.toString());
	}

	@Override
	public void inASlashAssignAssignmentOperator(
			ASlashAssignAssignmentOperator node) {
		out.print("inASlashAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void inASlashIdIdMultiplicativeExpression(
			ASlashIdIdMultiplicativeExpression node) {
		out.print("inASlashIdIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inASlashIdUnMultiplicativeExpression(
			ASlashIdUnMultiplicativeExpression node) {
		out.print("inASlashIdUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inASlashMuIdMultiplicativeExpression(
			ASlashMuIdMultiplicativeExpression node) {
		out.print("inASlashMuIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inASlashMuUnMultiplicativeExpression(
			ASlashMuUnMultiplicativeExpression node) {
		out.print("inASlashMuUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAStarAssignAssignmentOperator(
			AStarAssignAssignmentOperator node) {
		out.print("inAStarAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void inAStarIdIdMultiplicativeExpression(
			AStarIdIdMultiplicativeExpression node) {
		out.print("inAStarIdIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAStarIdUnMultiplicativeExpression(
			AStarIdUnMultiplicativeExpression node) {
		out.print("inAStarIdUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAStarMuIdMultiplicativeExpression(
			AStarMuIdMultiplicativeExpression node) {
		out.print("inAStarMuIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAStarMuUnMultiplicativeExpression(
			AStarMuUnMultiplicativeExpression node) {
		out.print("inAStarMuUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void inAStatementBlockStatement(AStatementBlockStatement node) {
		out.print("inAStatementBlockStatement: " + node.toString());
	}

	@Override
	public void inAStatementForInit(AStatementForInit node) {
		out.print("inAStatementForInit: " + node.toString());
	}

	@Override
	public void inAStatementWithoutTrailingSubstatementStatementNoShortIf(
			AStatementWithoutTrailingSubstatementStatementNoShortIf node) {
		out.print("inAStatementWithoutTrailingSubstatementStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inAStaticModifier(AStaticModifier node) {
		out.print("inAStaticModifier: " + node.toString());
	}

	@Override
	public void inAStringLiteral(AStringLiteral node) {
		out.print("inAStringLiteral: " + node.toString());
	}

	@Override
	public void inASuperExplicitConstructorInvocation(
			ASuperExplicitConstructorInvocation node) {
		out.print("inASuperExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void inASuperFieldAccess(ASuperFieldAccess node) {
		out.print("inASuperFieldAccess: " + node.toString());
	}

	@Override
	public void inASuperMethodInvocation(ASuperMethodInvocation node) {
		out.print("inASuperMethodInvocation: " + node.toString());
	}

	@Override
	public void inASwitchBlock(ASwitchBlock node) {
		out.print("inASwitchBlock: " + node.toString());
	}

	@Override
	public void inASwitchBlockStatementGroup(ASwitchBlockStatementGroup node) {
		out.print("inASwitchBlockStatementGroup: " + node.toString());
	}

	@Override
	public void inASwitchStatementStatementWithoutTrailingSubstatement(
			ASwitchStatementStatementWithoutTrailingSubstatement node) {
		out.print("inASwitchStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inAThisExplicitConstructorInvocation(
			AThisExplicitConstructorInvocation node) {
		out.print("inAThisExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void inAThisPrimaryNoNewArray(AThisPrimaryNoNewArray node) {
		out.print("inAThisPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAThrows(AThrows node) {
		out.print("inAThrows: " + node.toString());
	}

	@Override
	public void inAThrowStatementStatementWithoutTrailingSubstatement(
			AThrowStatementStatementWithoutTrailingSubstatement node) {
		out.print("inAThrowStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inATrueBooleanLiteral(ATrueBooleanLiteral node) {
		out.print("inATrueBooleanLiteral: " + node.toString());
	}

	@Override
	public void inATryStatementStatementWithoutTrailingSubstatement(
			ATryStatementStatementWithoutTrailingSubstatement node) {
		out.print("inATryStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void inAUnaryUnaryExpression(AUnaryUnaryExpression node) {
		out.print("inAUnaryUnaryExpression: " + node.toString());
	}

	@Override
	public void inAVariableDeclarationBlockStatement(
			AVariableDeclarationBlockStatement node) {
		out.print("inAVariableDeclarationBlockStatement: " + node.toString());
	}

	@Override
	public void inAVariableDeclarationForInit(AVariableDeclarationForInit node) {
		out.print("inAVariableDeclarationForInit: " + node.toString());
	}

	@Override
	public void inAVoidMethodHeader(AVoidMethodHeader node) {
		out.print("inAVoidMethodHeader: " + node.toString());
	}

	@Override
	public void inAVoidPrimaryNoNewArray(AVoidPrimaryNoNewArray node) {
		out.print("inAVoidPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void inAWhileLoopStatement(AWhileLoopStatement node) {
		out.print("inAWhileLoopStatement: " + node.toString());
	}

	@Override
	public void inAWhileStatementNoShortIfStatementNoShortIf(
			AWhileStatementNoShortIfStatementNoShortIf node) {
		out.print("inAWhileStatementNoShortIfStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAAdditionalIdentifier(AAdditionalIdentifier node) {
		out.print("id: " + node.toString());
	}

	@Override
	public void outAAmpAmpAnIdConditionalAndExpression(
			AAmpAmpAnIdConditionalAndExpression node) {
		out.print("outAAmpAmpAnIdConditionalAndExpression: " + node.toString());
	}

	@Override
	public void outAAmpAmpAnOrConditionalAndExpression(
			AAmpAmpAnOrConditionalAndExpression node) {
		out.print("outAAmpAmpAnOrConditionalAndExpression: " + node.toString());
	}

	@Override
	public void outAAmpAmpIdIdConditionalAndExpression(
			AAmpAmpIdIdConditionalAndExpression node) {
		out.print("outAAmpAmpIdIdConditionalAndExpression: " + node.toString());
	}

	@Override
	public void outAAmpAmpIdOrConditionalAndExpression(
			AAmpAmpIdOrConditionalAndExpression node) {
		out.print("outAAmpAmpIdOrConditionalAndExpression: " + node.toString());
	}

	@Override
	public void outAArguments(AArguments node) {
		out.print("outAArguments: " + node.toString());
	}

	@Override
	public void outAArrayInitializer(AArrayInitializer node) {
		out.print("outAArrayInitializer: " + node.toString());
	}

	@Override
	public void outAArrayInitializerElementValue(
			AArrayInitializerElementValue node) {
		out.print("outAArrayInitializerElementValue: " + node.toString());
	}

	@Override
	public void outAArrayLeftHandSide(AArrayLeftHandSide node) {
		out.print("outAArrayLeftHandSide: " + node.toString());
	}

	@Override
	public void outAArrayPrimary(AArrayPrimary node) {
		out.print("outAArrayPrimary: " + node.toString());
	}

	@Override
	public void outAArrayPrimaryNoNewArray(AArrayPrimaryNoNewArray node) {
		out.print("outAArrayPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAArrayVariableInitializer(AArrayVariableInitializer node) {
		out.print("outAArrayVariableInitializer: " + node.toString());
	}

	@Override
	public void outAAssignAssignmentOperator(AAssignAssignmentOperator node) {
		out.print("outAAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void outAAssignmentAssignmentExpression(
			AAssignmentAssignmentExpression node) {
		out.print("outAAssignmentAssignmentExpression: " + node.toString());
	}

	@Override
	public void outAAssignmentStatementExpression(
			AAssignmentStatementExpression node) {
		out.print("outAAssignmentStatementExpression: " + node.toString());
	}

	@Override
	public void outABarBarIdIdConditionalOrExpression(
			ABarBarIdIdConditionalOrExpression node) {
		out.print("outABarBarIdIdConditionalOrExpression: " + node.toString());
	}

	@Override
	public void outABarBarIdOrConditionalOrExpression(
			ABarBarIdOrConditionalOrExpression node) {
		out.print("outABarBarIdOrConditionalOrExpression: " + node.toString());
	}

	@Override
	public void outABarBarOrIdConditionalOrExpression(
			ABarBarOrIdConditionalOrExpression node) {
		out.print("outABarBarOrIdConditionalOrExpression: " + node.toString());
	}

	@Override
	public void outABarBarOrOrConditionalOrExpression(
			ABarBarOrOrConditionalOrExpression node) {
		out.print("outABarBarOrOrConditionalOrExpression: " + node.toString());
	}

	@Override
	public void outABasicForForStatement(ABasicForForStatement node) {
		out.print("outABasicForForStatement: " + node.toString());
	}

	@Override
	public void outABlock(ABlock node) {
		out.print("outABlock: " + node.toString());
	}

	@Override
	public void outABlockMethodBody(ABlockMethodBody node) {
		out.print("outABlockMethodBody: " + node.toString());
	}

	@Override
	public void outABlockStatementWithoutTrailingSubstatement(
			ABlockStatementWithoutTrailingSubstatement node) {
		out.print("outABlockStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outABooleanLiteral(ABooleanLiteral node) {
		out.print("outABooleanLiteral: " + node.toString());
	}

	@Override
	public void outABooleanPrimitiveType(ABooleanPrimitiveType node) {
		out.print("outABooleanPrimitiveType: " + node.toString());
	}

	@Override
	public void outABreakStatement(ABreakStatement node) {
		out.print("outABreakStatement: " + node.toString());
	}

	@Override
	public void outABreakStatementStatementWithoutTrailingSubstatement(
			ABreakStatementStatementWithoutTrailingSubstatement node) {
		out.print("outABreakStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outAByteIntegralType(AByteIntegralType node) {
		out.print("outAByteIntegralType: " + node.toString());
	}

	@Override
	public void outACastUnaryExpressionNotPlusMinus(
			ACastUnaryExpressionNotPlusMinus node) {
		out.print("outACastUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void outACatchClause(ACatchClause node) {
		out.print("outACatchClause: " + node.toString());
	}

	@Override
	public void outACatchTryStatement(ACatchTryStatement node) {
		out.print("outACatchTryStatement: " + node.toString());
	}

	@Override
	public void outACharacterLiteral(ACharacterLiteral node) {
		out.print("outACharacterLiteral: " + node.toString());
	}

	@Override
	public void outACharIntegralType(ACharIntegralType node) {
		out.print("outACharIntegralType: " + node.toString());
	}

	@Override
	public void outAClassBody(AClassBody node) {
		out.print("outAClassBody: " + node.toString());
	}

	@Override
	public void outAClassClassMemberDeclaration(
			AClassClassMemberDeclaration node) {
		out.print("outAClassClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void outAClassDeclarationBlockStatement(
			AClassDeclarationBlockStatement node) {
		out.print("outAClassDeclarationBlockStatement: " + node.toString());
	}

	@Override
	public void outAClassNameMethodInvocation(AClassNameMethodInvocation node) {
		out.print("outAClassNameMethodInvocation: " + node.toString());
	}

	@Override
	public void outAClassPrimaryNoNewArray(AClassPrimaryNoNewArray node) {
		out.print("outAClassPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAClassTypeDeclaration(AClassTypeDeclaration node) {
		out.print("outAClassTypeDeclaration: " + node.toString());
	}

	@Override
	public void outAConditionalAssignmentExpression(
			AConditionalAssignmentExpression node) {
		out.print("outAConditionalAssignmentExpression: " + node.toString());
	}

	@Override
	public void outAConditionalElementValue(AConditionalElementValue node) {
		out.print("outAConditionalElementValue: " + node.toString());
	}

	@Override
	public void outAConstantExpression(AConstantExpression node) {
		out.print("outAConstantExpression: " + node.toString());
	}

	@Override
	public void outAConstructorBody(AConstructorBody node) {
		out.print("outAConstructorBody: " + node.toString());
	}

	@Override
	public void outAConstructorClassBodyDeclaration(
			AConstructorClassBodyDeclaration node) {
		out.print("outAConstructorClassBodyDeclaration: " + node.toString());
	}

	@Override
	public void outAConstructorDeclaration(AConstructorDeclaration node) {
		out.print("outAConstructorDeclaration: " + node.toString());
	}

	@Override
	public void outAConstructorDeclarator(AConstructorDeclarator node) {
		out.print("outAConstructorDeclarator: " + node.toString());
	}

	@Override
	public void outAContinueStatement(AContinueStatement node) {
		out.print("outAContinueStatement: " + node.toString());
	}

	@Override
	public void outAContinueStatementStatementWithoutTrailingSubstatement(
			AContinueStatementStatementWithoutTrailingSubstatement node) {
		out.print("outAContinueStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outADefaultSwitchLabel(ADefaultSwitchLabel node) {
		out.print("outADefaultSwitchLabel: " + node.toString());
	}

	@Override
	public void outADim(ADim node) {
		out.print("outADim: " + node.toString());
	}

	@Override
	public void outADoStatementStatementWithoutTrailingSubstatement(
			ADoStatementStatementWithoutTrailingSubstatement node) {
		out.print("outADoStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outADoubleFloatingPointType(ADoubleFloatingPointType node) {
		out.print("outADoubleFloatingPointType: " + node.toString());
	}

	@Override
	public void outAElementValueArrayInitializer(
			AElementValueArrayInitializer node) {
		out.print("outAElementValueArrayInitializer: " + node.toString());
	}

	@Override
	public void outAEmarkExpressionUnaryExpressionNotPlusMinus(
			AEmarkExpressionUnaryExpressionNotPlusMinus node) {
		out.print("outAEmarkExpressionUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void outAEmarkIdentifierUnaryExpressionNotPlusMinus(
			AEmarkIdentifierUnaryExpressionNotPlusMinus node) {
		out.print("outAEmarkIdentifierUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void outAEmptyBasicForStatement(AEmptyBasicForStatement node) {
		out.print("outAEmptyBasicForStatement: " + node.toString());
	}

	@Override
	public void outAEmptyClassMemberDeclaration(
			AEmptyClassMemberDeclaration node) {
		out.print("outAEmptyClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void outAEmptyForStatementNoShortIf(AEmptyForStatementNoShortIf node) {
		out.print("outAEmptyForStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAEmptyMethodBody(AEmptyMethodBody node) {
		out.print("outAEmptyMethodBody: " + node.toString());
	}

	@Override
	public void outAEmptyReturnStatement(AEmptyReturnStatement node) {
		out.print("outAEmptyReturnStatement: " + node.toString());
	}

	@Override
	public void outAEmptyStatement(AEmptyStatement node) {
		out.print("outAEmptyStatement: " + node.toString());
	}

	@Override
	public void outAEmptyStatementStatementWithoutTrailingSubstatement(
			AEmptyStatementStatementWithoutTrailingSubstatement node) {
		out.print("outAEmptyStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outAEmptyTypeDeclaration(AEmptyTypeDeclaration node) {
		out.print("outAEmptyTypeDeclaration: " + node.toString());
	}

	@Override
	public void outAEnhancedForForStatement(AEnhancedForForStatement node) {
		out.print("outAEnhancedForForStatement: " + node.toString());
	}

	@Override
	public void outAEqEqIdEqualityExpression(AEqEqIdEqualityExpression node) {
		out.print("outAEqEqIdEqualityExpression: " + node.toString());
	}

	@Override
	public void outAEqEqReEqualityExpression(AEqEqReEqualityExpression node) {
		out.print("outAEqEqReEqualityExpression: " + node.toString());
	}

	@Override
	public void outAEqIdIdEqualityExpression(AEqIdIdEqualityExpression node) {
		out.print("outAEqIdIdEqualityExpression: " + node.toString());
	}

	@Override
	public void outAEqIdReEqualityExpression(AEqIdReEqualityExpression node) {
		out.print("outAEqIdReEqualityExpression: " + node.toString());
	}

	@Override
	public void outAExceptionType(AExceptionType node) {
		out.print("outAExceptionType: " + node.toString());
	}

	@Override
	public void outAExpression(AExpression node) {
		out.print("outAExpression: " + node.toString());
	}

	@Override
	public void outAExpressionAssignment(AExpressionAssignment node) {
		out.print("outAExpressionAssignment: " + node.toString());
	}

	@Override
	public void outAExpressionBasicForStatement(
			AExpressionBasicForStatement node) {
		out.print("outAExpressionBasicForStatement: " + node.toString());
	}

	@Override
	public void outAExpressionDimExpr(AExpressionDimExpr node) {
		out.print("outAExpressionDimExpr: " + node.toString());
	}

	@Override
	public void outAExpressionDoStatement(AExpressionDoStatement node) {
		out.print("outAExpressionDoStatement: " + node.toString());
	}

	@Override
	public void outAExpressionForStatementNoShortIf(
			AExpressionForStatementNoShortIf node) {
		out.print("outAExpressionForStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAExpressionIfThenElseStatement(
			AExpressionIfThenElseStatement node) {
		out.print("outAExpressionIfThenElseStatement: " + node.toString());
	}

	@Override
	public void outAExpressionIfThenElseStatementNoShortIf(
			AExpressionIfThenElseStatementNoShortIf node) {
		out.print("outAExpressionIfThenElseStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAExpressionIfThenStatement(AExpressionIfThenStatement node) {
		out.print("outAExpressionIfThenStatement: " + node.toString());
	}

	@Override
	public void outAExpressionPostDecrementExpression(
			AExpressionPostDecrementExpression node) {
		out.print("outAExpressionPostDecrementExpression: " + node.toString());
	}

	@Override
	public void outAExpressionPostIncrementExpression(
			AExpressionPostIncrementExpression node) {
		out.print("outAExpressionPostIncrementExpression: " + node.toString());
	}

	@Override
	public void outAExpressionPreDecrementExpression(
			AExpressionPreDecrementExpression node) {
		out.print("outAExpressionPreDecrementExpression: " + node.toString());
	}

	@Override
	public void outAExpressionPreIncrementExpression(
			AExpressionPreIncrementExpression node) {
		out.print("outAExpressionPreIncrementExpression: " + node.toString());
	}

	@Override
	public void outAExpressionPrimaryNoNewArray(
			AExpressionPrimaryNoNewArray node) {
		out.print("outAExpressionPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAExpressionReturnStatement(AExpressionReturnStatement node) {
		out.print("outAExpressionReturnStatement: " + node.toString());
	}

	@Override
	public void outAExpressionStatement(AExpressionStatement node) {
		out.print("outAExpressionStatement: " + node.toString());
	}

	@Override
	public void outAExpressionStatementStatementWithoutTrailingSubstatement(
			AExpressionStatementStatementWithoutTrailingSubstatement node) {
		out.print("outAExpressionStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outAExpressionSwitchLabel(AExpressionSwitchLabel node) {
		out.print("outAExpressionSwitchLabel: " + node.toString());
	}

	@Override
	public void outAExpressionSwitchStatement(AExpressionSwitchStatement node) {
		out.print("outAExpressionSwitchStatement: " + node.toString());
	}

	@Override
	public void outAExpressionThrowStatement(AExpressionThrowStatement node) {
		out.print("outAExpressionThrowStatement: " + node.toString());
	}

	@Override
	public void outAExpressionVariableInitializer(
			AExpressionVariableInitializer node) {
		out.print("outAExpressionVariableInitializer: " + node.toString());
	}

	@Override
	public void outAExpressionWhileStatement(AExpressionWhileStatement node) {
		out.print("outAExpressionWhileStatement: " + node.toString());
	}

	@Override
	public void outAExpressionWhileStatementNoShortIf(
			AExpressionWhileStatementNoShortIf node) {
		out.print("outAExpressionWhileStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAFalseBooleanLiteral(AFalseBooleanLiteral node) {
		out.print("outAFalseBooleanLiteral: " + node.toString());
	}

	@Override
	public void outAFieldClassMemberDeclaration(
			AFieldClassMemberDeclaration node) {
		out.print("outAFieldClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void outAFieldLeftHandSide(AFieldLeftHandSide node) {
		out.print("outAFieldLeftHandSide: " + node.toString());
	}

	@Override
	public void outAFieldPrimaryNoNewArray(AFieldPrimaryNoNewArray node) {
		out.print("outAFieldPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAFinally(AFinally node) {
		out.print("outAFinally: " + node.toString());
	}

	@Override
	public void outAFinallyTryStatement(AFinallyTryStatement node) {
		out.print("outAFinallyTryStatement: " + node.toString());
	}

	@Override
	public void outAFinalModifier(AFinalModifier node) {
		out.print("outAFinalModifier: " + node.toString());
	}

	@Override
	public void outAFloatFloatingPointType(AFloatFloatingPointType node) {
		out.print("outAFloatFloatingPointType: " + node.toString());
	}

	@Override
	public void outAFloatingNumericType(AFloatingNumericType node) {
		out.print("outAFloatingNumericType: " + node.toString());
	}

	@Override
	public void outAFloatingPointLiteral(AFloatingPointLiteral node) {
		out.print("outAFloatingPointLiteral: " + node.toString());
	}

	@Override
	public void outAForLoopStatement(AForLoopStatement node) {
		out.print("outAForLoopStatement: " + node.toString());
	}

	@Override
	public void outAForStatementNoShortIfStatementNoShortIf(
			AForStatementNoShortIfStatementNoShortIf node) {
		out.print("outAForStatementNoShortIfStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAForUpdate(AForUpdate node) {
		out.print("outAForUpdate: " + node.toString());
	}

	@Override
	public void outAGteqIdIdRelationalExpression(
			AGteqIdIdRelationalExpression node) {
		out.print("outAGteqIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGteqIdShRelationalExpression(
			AGteqIdShRelationalExpression node) {
		out.print("outAGteqIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGteqShIdRelationalExpression(
			AGteqShIdRelationalExpression node) {
		out.print("outAGteqShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGteqShShRelationalExpression(
			AGteqShShRelationalExpression node) {
		out.print("outAGteqShShRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGtIdIdRelationalExpression(AGtIdIdRelationalExpression node) {
		out.print("outAGtIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGtIdShRelationalExpression(AGtIdShRelationalExpression node) {
		out.print("outAGtIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGtShIdRelationalExpression(AGtShIdRelationalExpression node) {
		out.print("outAGtShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outAGtShShRelationalExpression(AGtShShRelationalExpression node) {
		out.print("outAGtShShRelationalExpression: " + node.toString());
	}

	@Override
	public void outAIdentifierAssignment(AIdentifierAssignment node) {
		out.print("outAIdentifierAssignment: " + node.toString());
	}

	@Override
	public void outAIdentifierBasicForStatement(
			AIdentifierBasicForStatement node) {
		out.print("outAIdentifierBasicForStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierClassInstanceCreationExpression(
			AIdentifierClassInstanceCreationExpression node) {
		out.print("outAIdentifierClassInstanceCreationExpression: " + node.toString());
	}

	@Override
	public void outAIdentifierDimExpr(AIdentifierDimExpr node) {
		out.print("outAIdentifierDimExpr: " + node.toString());
	}

	@Override
	public void outAIdentifierDoStatement(AIdentifierDoStatement node) {
		out.print("outAIdentifierDoStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierElementValue(AIdentifierElementValue node) {
		out.print("outAIdentifierElementValue: " + node.toString());
	}

	@Override
	public void outAIdentifierExArrayAccess(AIdentifierExArrayAccess node) {
		out.print("outAIdentifierExArrayAccess: " + node.toString());
	}

	@Override
	public void outAIdentifierExplicitConstructorInvocation(
			AIdentifierExplicitConstructorInvocation node) {
		out.print("outAIdentifierExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void outAIdentifierForStatementNoShortIf(
			AIdentifierForStatementNoShortIf node) {
		out.print("outAIdentifierForStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAIdentifierIdArrayAccess(AIdentifierIdArrayAccess node) {
		out.print("outAIdentifierIdArrayAccess: " + node.toString());
	}

	@Override
	public void outAIdentifierIfThenElseStatement(
			AIdentifierIfThenElseStatement node) {
		out.print("outAIdentifierIfThenElseStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierIfThenElseStatementNoShortIf(
			AIdentifierIfThenElseStatementNoShortIf node) {
		out.print("outAIdentifierIfThenElseStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAIdentifierIfThenStatement(AIdentifierIfThenStatement node) {
		out.print("outAIdentifierIfThenStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierLeftHandSide(AIdentifierLeftHandSide node) {
		out.print("outAIdentifierLeftHandSide: " + node.toString());
	}

	@Override
	public void outAIdentifierPostDecrementExpression(
			AIdentifierPostDecrementExpression node) {
		out.print("outAIdentifierPostDecrementExpression: " + node.toString());
	}

	@Override
	public void outAIdentifierPostIncrementExpression(
			AIdentifierPostIncrementExpression node) {
		out.print("outAIdentifierPostIncrementExpression: " + node.toString());
	}

	@Override
	public void outAIdentifierPreDecrementExpression(
			AIdentifierPreDecrementExpression node) {
		out.print("outAIdentifierPreDecrementExpression: " + node.toString());
	}

	@Override
	public void outAIdentifierPreIncrementExpression(
			AIdentifierPreIncrementExpression node) {
		out.print("outAIdentifierPreIncrementExpression: " + node.toString());
	}

	@Override
	public void outAIdentifierPrimaryNoNewArray(
			AIdentifierPrimaryNoNewArray node) {
		out.print("outAIdentifierPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAIdentifierReturnStatement(AIdentifierReturnStatement node) {
		out.print("outAIdentifierReturnStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierSwitchLabel(AIdentifierSwitchLabel node) {
		out.print("outAIdentifierSwitchLabel: " + node.toString());
	}

	@Override
	public void outAIdentifierSwitchStatement(AIdentifierSwitchStatement node) {
		out.print("outAIdentifierSwitchStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierThrowStatement(AIdentifierThrowStatement node) {
		out.print("outAIdentifierThrowStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierVariableInitializer(
			AIdentifierVariableInitializer node) {
		out.print("outAIdentifierVariableInitializer: " + node.toString());
	}

	@Override
	public void outAIdentifierWhileStatement(AIdentifierWhileStatement node) {
		out.print("outAIdentifierWhileStatement: " + node.toString());
	}

	@Override
	public void outAIdentifierWhileStatementNoShortIf(
			AIdentifierWhileStatementNoShortIf node) {
		out.print("outAIdentifierWhileStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAIfElseStatement(AIfElseStatement node) {
		out.print("outAIfElseStatement: " + node.toString());
	}

	@Override
	public void outAIfStatement(AIfStatement node) {
		out.print("outAIfStatement: " + node.toString());
	}

	@Override
	public void outAIfThenElseStatementNoShortIfStatementNoShortIf(
			AIfThenElseStatementNoShortIfStatementNoShortIf node) {
		out.print("outAIfThenElseStatementNoShortIfStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAInitializerVariableDeclarator(
			AInitializerVariableDeclarator node) {
		out.print("outAInitializerVariableDeclarator: " + node.toString());
	}

	@Override
	public void outAInstanceCreationStatementExpression(
			AInstanceCreationStatementExpression node) {
		out.print("outAInstanceCreationStatementExpression: " + node.toString());
	}

	@Override
	public void outAInstancePrimaryNoNewArray(AInstancePrimaryNoNewArray node) {
		out.print("outAInstancePrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAIntegerLiteral(AIntegerLiteral node) {
		out.print("outAIntegerLiteral: " + node.toString());
	}

	@Override
	public void outAIntegralNumericType(AIntegralNumericType node) {
		out.print("outAIntegralNumericType: " + node.toString());
	}

	@Override
	public void outAIntIntegralType(AIntIntegralType node) {
		out.print("outAIntIntegralType: " + node.toString());
	}

	@Override
	public void outALiteralPrimaryNoNewArray(ALiteralPrimaryNoNewArray node) {
		out.print("outALiteralPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outALocalVariableDeclarationStatement(
			ALocalVariableDeclarationStatement node) {
		out.print("outALocalVariableDeclarationStatement: " + node.toString());
	}

	@Override
	public void outALongIntegralType(ALongIntegralType node) {
		out.print("outALongIntegralType: " + node.toString());
	}

	@Override
	public void outALteqIdIdRelationalExpression(
			ALteqIdIdRelationalExpression node) {
		out.print("outALteqIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outALteqIdShRelationalExpression(
			ALteqIdShRelationalExpression node) {
		out.print("outALteqIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void outALteqShIdRelationalExpression(
			ALteqShIdRelationalExpression node) {
		out.print("outALteqShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outALteqShShRelationalExpression(
			ALteqShShRelationalExpression node) {
		out.print("outALteqShShRelationalExpression: " + node.toString());
	}

	@Override
	public void outALtIdIdRelationalExpression(ALtIdIdRelationalExpression node) {
		out.print("outALtIdIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outALtIdShRelationalExpression(ALtIdShRelationalExpression node) {
		out.print("outALtIdShRelationalExpression: " + node.toString());
	}

	@Override
	public void outALtShIdRelationalExpression(ALtShIdRelationalExpression node) {
		out.print("outALtShIdRelationalExpression: " + node.toString());
	}

	@Override
	public void outALtShShRelationalExpression(ALtShShRelationalExpression node) {
		out.print("outALtShShRelationalExpression: " + node.toString());
	}

	@Override
	public void outAManyElementValues(AManyElementValues node) {
		out.print("outAManyElementValues: " + node.toString());
	}

	@Override
	public void outAManyExArgumentList(AManyExArgumentList node) {
		out.print("outAManyExArgumentList: " + node.toString());
	}

	@Override
	public void outAManyExceptionTypeList(AManyExceptionTypeList node) {
		out.print("outAManyExceptionTypeList: " + node.toString());
	}

	@Override
	public void outAManyFormalParameterList(AManyFormalParameterList node) {
		out.print("outAManyFormalParameterList: " + node.toString());
	}

	@Override
	public void outAManyFormalParameters(AManyFormalParameters node) {
		out.print("outAManyFormalParameters: " + node.toString());
	}

	@Override
	public void outAManyIdArgumentList(AManyIdArgumentList node) {
		out.print("outAManyIdArgumentList: " + node.toString());
	}

	@Override
	public void outAManyStatementExpressionList(
			AManyStatementExpressionList node) {
		out.print("outAManyStatementExpressionList: " + node.toString());
	}

	@Override
	public void outAManyVariableDeclarators(AManyVariableDeclarators node) {
		out.print("outAManyVariableDeclarators: " + node.toString());
	}

	@Override
	public void outAManyVariableInitializers(AManyVariableInitializers node) {
		out.print("outAManyVariableInitializers: " + node.toString());
	}

	@Override
	public void outAMemberClassBodyDeclaration(AMemberClassBodyDeclaration node) {
		out.print("outAMemberClassBodyDeclaration: " + node.toString());
	}

	@Override
	public void outAMethodClassMemberDeclaration(
			AMethodClassMemberDeclaration node) {
		out.print("outAMethodClassMemberDeclaration: " + node.toString());
	}

	@Override
	public void outAMethodDeclaration(AMethodDeclaration node) {
		out.print("outAMethodDeclaration: " + node.toString());
	}

	@Override
	public void outAMethodDeclarator(AMethodDeclarator node) {
		out.print("outAMethodDeclarator: " + node.toString());
	}

	@Override
	public void outAMethodInvocationStatementExpression(
			AMethodInvocationStatementExpression node) {
		out.print("outAMethodInvocationStatementExpression: " + node.toString());
	}

	@Override
	public void outAMethodPrimaryNoNewArray(AMethodPrimaryNoNewArray node) {
		out.print("outAMethodPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAMinusAdIdAdditiveExpression(
			AMinusAdIdAdditiveExpression node) {
		out.print("outAMinusAdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAMinusAdMuAdditiveExpression(
			AMinusAdMuAdditiveExpression node) {
		out.print("outAMinusAdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAMinusAssignAssignmentOperator(
			AMinusAssignAssignmentOperator node) {
		out.print("outAMinusAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void outAMinusExpressionUnaryExpression(
			AMinusExpressionUnaryExpression node) {
		out.print("outAMinusExpressionUnaryExpression: " + node.toString());
	}

	@Override
	public void outAMinusIdentifierUnaryExpression(
			AMinusIdentifierUnaryExpression node) {
		out.print("outAMinusIdentifierUnaryExpression: " + node.toString());
	}

	@Override
	public void outAMinusIdIdAdditiveExpression(
			AMinusIdIdAdditiveExpression node) {
		out.print("outAMinusIdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAMinusIdMuAdditiveExpression(
			AMinusIdMuAdditiveExpression node) {
		out.print("outAMinusIdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void outANeqEqIdEqualityExpression(ANeqEqIdEqualityExpression node) {
		out.print("outANeqEqIdEqualityExpression: " + node.toString());
	}

	@Override
	public void outANeqEqReEqualityExpression(ANeqEqReEqualityExpression node) {
		out.print("outANeqEqReEqualityExpression: " + node.toString());
	}

	@Override
	public void outANeqIdIdEqualityExpression(ANeqIdIdEqualityExpression node) {
		out.print("outANeqIdIdEqualityExpression: " + node.toString());
	}

	@Override
	public void outANeqIdReEqualityExpression(ANeqIdReEqualityExpression node) {
		out.print("outANeqIdReEqualityExpression: " + node.toString());
	}

	@Override
	public void outANoArrayPrimary(ANoArrayPrimary node) {
		out.print("outANoArrayPrimary: " + node.toString());
	}

	@Override
	public void outANormalClassClassDeclaration(
			ANormalClassClassDeclaration node) {
		out.print("outANormalClassClassDeclaration: " + node.toString());
	}

	@Override
	public void outANormalClassDeclaration(ANormalClassDeclaration node) {
		out.print("outANormalClassDeclaration: " + node.toString());
	}

	@Override
	public void outANoTrailStatement(ANoTrailStatement node) {
		out.print("outANoTrailStatement: " + node.toString());
	}

	@Override
	public void outANullLiteral(ANullLiteral node) {
		out.print("outANullLiteral: " + node.toString());
	}

	@Override
	public void outANumericPrimitiveType(ANumericPrimitiveType node) {
		out.print("outANumericPrimitiveType: " + node.toString());
	}

	@Override
	public void outAOneElementValues(AOneElementValues node) {
		out.print("outAOneElementValues: " + node.toString());
	}

	@Override
	public void outAOneExArgumentList(AOneExArgumentList node) {
		out.print("outAOneExArgumentList: " + node.toString());
	}

	@Override
	public void outAOneExceptionTypeList(AOneExceptionTypeList node) {
		out.print("outAOneExceptionTypeList: " + node.toString());
	}

	@Override
	public void outAOneFormalParameterList(AOneFormalParameterList node) {
		out.print("outAOneFormalParameterList: " + node.toString());
	}

	@Override
	public void outAOneFormalParameters(AOneFormalParameters node) {
		out.print("outAOneFormalParameters: " + node.toString());
	}

	@Override
	public void outAOneIdArgumentList(AOneIdArgumentList node) {
		out.print("outAOneIdArgumentList: " + node.toString());
	}

	@Override
	public void outAOneStatementExpressionList(AOneStatementExpressionList node) {
		out.print("outAOneStatementExpressionList: " + node.toString());
	}

	@Override
	public void outAOneVariableDeclarators(AOneVariableDeclarators node) {
		out.print("outAOneVariableDeclarators: " + node.toString());
	}

	@Override
	public void outAOneVariableInitializers(AOneVariableInitializers node) {
		out.print("outAOneVariableInitializers: " + node.toString());
	}

	@Override
	public void outAPercentAssignAssignmentOperator(
			APercentAssignAssignmentOperator node) {
		out.print("outAPercentAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void outAPercentIdIdMultiplicativeExpression(
			APercentIdIdMultiplicativeExpression node) {
		out.print("outAPercentIdIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAPercentIdUnMultiplicativeExpression(
			APercentIdUnMultiplicativeExpression node) {
		out.print("outAPercentIdUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAPercentMuIdMultiplicativeExpression(
			APercentMuIdMultiplicativeExpression node) {
		out.print("outAPercentMuIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAPercentMuUnMultiplicativeExpression(
			APercentMuUnMultiplicativeExpression node) {
		out.print("outAPercentMuUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAPlusAdIdAdditiveExpression(APlusAdIdAdditiveExpression node) {
		out.print("outAPlusAdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAPlusAdMuAdditiveExpression(APlusAdMuAdditiveExpression node) {
		out.print("outAPlusAdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAPlusAssignAssignmentOperator(
			APlusAssignAssignmentOperator node) {
		out.print("outAPlusAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void outAPlusExpressionUnaryExpression(
			APlusExpressionUnaryExpression node) {
		out.print("outAPlusExpressionUnaryExpression: " + node.toString());
	}

	@Override
	public void outAPlusIdentifierUnaryExpression(
			APlusIdentifierUnaryExpression node) {
		out.print("outAPlusIdentifierUnaryExpression: " + node.toString());
	}

	@Override
	public void outAPlusIdIdAdditiveExpression(APlusIdIdAdditiveExpression node) {
		out.print("outAPlusIdIdAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAPlusIdMuAdditiveExpression(APlusIdMuAdditiveExpression node) {
		out.print("outAPlusIdMuAdditiveExpression: " + node.toString());
	}

	@Override
	public void outAPostDecrementPostfixExpression(
			APostDecrementPostfixExpression node) {
		out.print("outAPostDecrementPostfixExpression: " + node.toString());
	}

	@Override
	public void outAPostDecrementStatementExpression(
			APostDecrementStatementExpression node) {
		out.print("outAPostDecrementStatementExpression: " + node.toString());
	}

	@Override
	public void outAPostfixUnaryExpressionNotPlusMinus(
			APostfixUnaryExpressionNotPlusMinus node) {
		out.print("outAPostfixUnaryExpressionNotPlusMinus: " + node.toString());
	}

	@Override
	public void outAPostIncrementPostfixExpression(
			APostIncrementPostfixExpression node) {
		out.print("outAPostIncrementPostfixExpression: " + node.toString());
	}

	@Override
	public void outAPostIncrementStatementExpression(
			APostIncrementStatementExpression node) {
		out.print("outAPostIncrementStatementExpression: " + node.toString());
	}

	@Override
	public void outAPreDecrementStatementExpression(
			APreDecrementStatementExpression node) {
		out.print("outAPreDecrementStatementExpression: " + node.toString());
	}

	@Override
	public void outAPreDecrementUnaryExpression(
			APreDecrementUnaryExpression node) {
		out.print("outAPreDecrementUnaryExpression: " + node.toString());
	}

	@Override
	public void outAPreIncrementStatementExpression(
			APreIncrementStatementExpression node) {
		out.print("outAPreIncrementStatementExpression: " + node.toString());
	}

	@Override
	public void outAPreIncrementUnaryExpression(
			APreIncrementUnaryExpression node) {
		out.print("outAPreIncrementUnaryExpression: " + node.toString());
	}

	@Override
	public void outAPrimaryClassInstanceCreationExpression(
			APrimaryClassInstanceCreationExpression node) {
		out.print("outAPrimaryClassInstanceCreationExpression: " + node.toString());
	}

	@Override
	public void outAPrimaryExArrayAccess(APrimaryExArrayAccess node) {
		out.print("outAPrimaryExArrayAccess: " + node.toString());
	}

	@Override
	public void outAPrimaryExplicitConstructorInvocation(
			APrimaryExplicitConstructorInvocation node) {
		out.print("outAPrimaryExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void outAPrimaryFieldAccess(APrimaryFieldAccess node) {
		out.print("outAPrimaryFieldAccess: " + node.toString());
	}

	@Override
	public void outAPrimaryIdArrayAccess(APrimaryIdArrayAccess node) {
		out.print("outAPrimaryIdArrayAccess: " + node.toString());
	}

	@Override
	public void outAPrimaryMethodInvocation(APrimaryMethodInvocation node) {
		out.print("outAPrimaryMethodInvocation: " + node.toString());
	}

	@Override
	public void outAPrimaryPostfixExpression(APrimaryPostfixExpression node) {
		out.print("outAPrimaryPostfixExpression: " + node.toString());
	}

	@Override
	public void outAPrimitiveArrayCreationExpression(
			APrimitiveArrayCreationExpression node) {
		out.print("outAPrimitiveArrayCreationExpression: " + node.toString());
	}

	@Override
	public void outAPrimitiveConstantDeclaration(
			APrimitiveConstantDeclaration node) {
		out.print("outAPrimitiveConstantDeclaration: " + node.toString());
	}

	@Override
	public void outAPrimitiveExpressionCastExpression(
			APrimitiveExpressionCastExpression node) {
		out.print("outAPrimitiveExpressionCastExpression: " + node.toString());
	}

	@Override
	public void outAPrimitiveExpressionEnhancedForStatement(
			APrimitiveExpressionEnhancedForStatement node) {
		out.print("outAPrimitiveExpressionEnhancedForStatement: " + node.toString());
	}

	@Override
	public void outAPrimitiveFieldDeclaration(APrimitiveFieldDeclaration node) {
		out.print("outAPrimitiveFieldDeclaration: " + node.toString());
	}

	@Override
	public void outAPrimitiveFormalParameter(APrimitiveFormalParameter node) {
		out.print("outAPrimitiveFormalParameter: " + node.toString());
	}

	@Override
	public void outAPrimitiveIdentifierCastExpression(
			APrimitiveIdentifierCastExpression node) {
		out.print("outAPrimitiveIdentifierCastExpression: " + node.toString());
	}

	@Override
	public void outAPrimitiveIdentifierEnhancedForStatement(
			APrimitiveIdentifierEnhancedForStatement node) {
		out.print("outAPrimitiveIdentifierEnhancedForStatement: " + node.toString());
	}

	@Override
	public void outAPrimitiveInitializerArrayCreationExpression(
			APrimitiveInitializerArrayCreationExpression node) {
		out.print("outAPrimitiveInitializerArrayCreationExpression: " + node.toString());
	}

	@Override
	public void outAPrimitiveLocalVariableDeclaration(
			APrimitiveLocalVariableDeclaration node) {
		out.print("outAPrimitiveLocalVariableDeclaration: " + node.toString());
	}

	@Override
	public void outAPrimitiveMethodHeader(APrimitiveMethodHeader node) {
		out.print("outAPrimitiveMethodHeader: " + node.toString());
	}

	@Override
	public void outAPrimitivePrimaryNoNewArray(APrimitivePrimaryNoNewArray node) {
		out.print("outAPrimitivePrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAPrivateModifier(APrivateModifier node) {
		out.print("outAPrivateModifier: " + node.toString());
	}

	@Override
	public void outAProtectedModifier(AProtectedModifier node) {
		out.print("outAProtectedModifier: " + node.toString());
	}

	@Override
	public void outAPublicModifier(APublicModifier node) {
		out.print("outAPublicModifier: " + node.toString());
	}

	@Override
	public void outAReferenceArrayCreationExpression(
			AReferenceArrayCreationExpression node) {
		out.print("outAReferenceArrayCreationExpression: " + node.toString());
	}

	@Override
	public void outAReferenceConstantDeclaration(
			AReferenceConstantDeclaration node) {
		out.print("outAReferenceConstantDeclaration: " + node.toString());
	}

	@Override
	public void outAReferenceExpressionCastExpression(
			AReferenceExpressionCastExpression node) {
		out.print("outAReferenceExpressionCastExpression: " + node.toString());
	}

	@Override
	public void outAReferenceExpressionEnhancedForStatement(
			AReferenceExpressionEnhancedForStatement node) {
		out.print("outAReferenceExpressionEnhancedForStatement: " + node.toString());
	}

	@Override
	public void outAReferenceFieldDeclaration(AReferenceFieldDeclaration node) {
		out.print("outAReferenceFieldDeclaration: " + node.toString());
	}

	@Override
	public void outAReferenceFormalParameter(AReferenceFormalParameter node) {
		out.print("outAReferenceFormalParameter: " + node.toString());
	}

	@Override
	public void outAReferenceIdentifierCastExpression(
			AReferenceIdentifierCastExpression node) {
		out.print("outAReferenceIdentifierCastExpression: " + node.toString());
	}

	@Override
	public void outAReferenceIdentifierEnhancedForStatement(
			AReferenceIdentifierEnhancedForStatement node) {
		out.print("outAReferenceIdentifierEnhancedForStatement: " + node.toString());
	}

	@Override
	public void outAReferenceInitializerArrayCreationExpression(
			AReferenceInitializerArrayCreationExpression node) {
		out.print("outAReferenceInitializerArrayCreationExpression: " + node.toString());
	}

	@Override
	public void outAReferenceLocalVariableDeclaration(
			AReferenceLocalVariableDeclaration node) {
		out.print("outAReferenceLocalVariableDeclaration: " + node.toString());
	}

	@Override
	public void outAReferenceMethodHeader(AReferenceMethodHeader node) {
		out.print("outAReferenceMethodHeader: " + node.toString());
	}

	@Override
	public void outAReferencePrimaryNoNewArray(AReferencePrimaryNoNewArray node) {
		out.print("outAReferencePrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAReferenceSuperFieldAccess(AReferenceSuperFieldAccess node) {
		out.print("outAReferenceSuperFieldAccess: " + node.toString());
	}

	@Override
	public void outAReturnStatementStatementWithoutTrailingSubstatement(
			AReturnStatementStatementWithoutTrailingSubstatement node) {
		out.print("outAReturnStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outAShortIntegralType(AShortIntegralType node) {
		out.print("outAShortIntegralType: " + node.toString());
	}

	@Override
	public void outASimpleAdditiveExpression(ASimpleAdditiveExpression node) {
		out.print("outASimpleAdditiveExpression: " + node.toString());
	}

	@Override
	public void outASimpleClassInstanceCreationExpression(
			ASimpleClassInstanceCreationExpression node) {
		out.print("outASimpleClassInstanceCreationExpression: " + node.toString());
	}

	@Override
	public void outASimpleConditionalAndExpression(
			ASimpleConditionalAndExpression node) {
		out.print("outASimpleConditionalAndExpression: " + node.toString());
	}

	@Override
	public void outASimpleConditionalOrExpression(
			ASimpleConditionalOrExpression node) {
		out.print("outASimpleConditionalOrExpression: " + node.toString());
	}

	@Override
	public void outASimpleEqualityExpression(ASimpleEqualityExpression node) {
		out.print("outASimpleEqualityExpression: " + node.toString());
	}

	@Override
	public void outASimpleMethodInvocation(ASimpleMethodInvocation node) {
		out.print("outASimpleMethodInvocation: " + node.toString());
	}

	@Override
	public void outASimpleMultiplicativeExpression(
			ASimpleMultiplicativeExpression node) {
		out.print("outASimpleMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outASimpleRelationalExpression(ASimpleRelationalExpression node) {
		out.print("outASimpleRelationalExpression: " + node.toString());
	}

	@Override
	public void outASimpleVariableDeclarator(ASimpleVariableDeclarator node) {
		out.print("outASimpleVariableDeclarator: " + node.toString());
	}

	@Override
	public void outASlashAssignAssignmentOperator(
			ASlashAssignAssignmentOperator node) {
		out.print("outASlashAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void outASlashIdIdMultiplicativeExpression(
			ASlashIdIdMultiplicativeExpression node) {
		out.print("outASlashIdIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outASlashIdUnMultiplicativeExpression(
			ASlashIdUnMultiplicativeExpression node) {
		out.print("outASlashIdUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outASlashMuIdMultiplicativeExpression(
			ASlashMuIdMultiplicativeExpression node) {
		out.print("outASlashMuIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outASlashMuUnMultiplicativeExpression(
			ASlashMuUnMultiplicativeExpression node) {
		out.print("outASlashMuUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAStarAssignAssignmentOperator(
			AStarAssignAssignmentOperator node) {
		out.print("outAStarAssignAssignmentOperator: " + node.toString());
	}

	@Override
	public void outAStarIdIdMultiplicativeExpression(
			AStarIdIdMultiplicativeExpression node) {
		out.print("outAStarIdIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAStarIdUnMultiplicativeExpression(
			AStarIdUnMultiplicativeExpression node) {
		out.print("outAStarIdUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAStarMuIdMultiplicativeExpression(
			AStarMuIdMultiplicativeExpression node) {
		out.print("outAStarMuIdMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAStarMuUnMultiplicativeExpression(
			AStarMuUnMultiplicativeExpression node) {
		out.print("outAStarMuUnMultiplicativeExpression: " + node.toString());
	}

	@Override
	public void outAStatementBlockStatement(AStatementBlockStatement node) {
		out.print("outAStatementBlockStatement: " + node.toString());
	}

	@Override
	public void outAStatementForInit(AStatementForInit node) {
		out.print("outAStatementForInit: " + node.toString());
	}

	@Override
	public void outAStatementWithoutTrailingSubstatementStatementNoShortIf(
			AStatementWithoutTrailingSubstatementStatementNoShortIf node) {
		out.print("outAStatementWithoutTrailingSubstatementStatementNoShortIf: " + node.toString());
	}

	@Override
	public void outAStaticModifier(AStaticModifier node) {
		out.print("outAStaticModifier: " + node.toString());
	}

	@Override
	public void outAStringLiteral(AStringLiteral node) {
		out.print("outAStringLiteral: " + node.toString());
	}

	@Override
	public void outASuperExplicitConstructorInvocation(
			ASuperExplicitConstructorInvocation node) {
		out.print("outASuperExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void outASuperFieldAccess(ASuperFieldAccess node) {
		out.print("outASuperFieldAccess: " + node.toString());
	}

	@Override
	public void outASuperMethodInvocation(ASuperMethodInvocation node) {
		out.print("outASuperMethodInvocation: " + node.toString());
	}

	@Override
	public void outASwitchBlock(ASwitchBlock node) {
		out.print("outASwitchBlock: " + node.toString());
	}

	@Override
	public void outASwitchBlockStatementGroup(ASwitchBlockStatementGroup node) {
		out.print("outASwitchBlockStatementGroup: " + node.toString());
	}

	@Override
	public void outASwitchStatementStatementWithoutTrailingSubstatement(
			ASwitchStatementStatementWithoutTrailingSubstatement node) {
		out.print("outASwitchStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outAThisExplicitConstructorInvocation(
			AThisExplicitConstructorInvocation node) {
		out.print("outAThisExplicitConstructorInvocation: " + node.toString());
	}

	@Override
	public void outAThisPrimaryNoNewArray(AThisPrimaryNoNewArray node) {
		out.print("outAThisPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAThrows(AThrows node) {
		out.print("outAThrows: " + node.toString());
	}

	@Override
	public void outAThrowStatementStatementWithoutTrailingSubstatement(
			AThrowStatementStatementWithoutTrailingSubstatement node) {
		out.print("outAThrowStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outATrueBooleanLiteral(ATrueBooleanLiteral node) {
		out.print("outATrueBooleanLiteral: " + node.toString());
	}

	@Override
	public void outATryStatementStatementWithoutTrailingSubstatement(
			ATryStatementStatementWithoutTrailingSubstatement node) {
		out.print("outATryStatementStatementWithoutTrailingSubstatement: " + node.toString());
	}

	@Override
	public void outAUnaryUnaryExpression(AUnaryUnaryExpression node) {
		out.print("outAUnaryUnaryExpression: " + node.toString());
	}

	@Override
	public void outAVariableDeclarationBlockStatement(
			AVariableDeclarationBlockStatement node) {
		out.print("outAVariableDeclarationBlockStatement: " + node.toString());
	}

	@Override
	public void outAVariableDeclarationForInit(AVariableDeclarationForInit node) {
		out.print("outAVariableDeclarationForInit: " + node.toString());
	}

	@Override
	public void outAVoidMethodHeader(AVoidMethodHeader node) {
		out.print("outAVoidMethodHeader: " + node.toString());
	}

	@Override
	public void outAVoidPrimaryNoNewArray(AVoidPrimaryNoNewArray node) {
		out.print("outAVoidPrimaryNoNewArray: " + node.toString());
	}

	@Override
	public void outAWhileLoopStatement(AWhileLoopStatement node) {
		out.print("outAWhileLoopStatement: " + node.toString());
	}

	@Override
	public void outAWhileStatementNoShortIfStatementNoShortIf(
			AWhileStatementNoShortIfStatementNoShortIf node) {
		out.print("outAWhileStatementNoShortIfStatementNoShortIf: " + node.toString());
	}

	@Override
	public void inACompilationUnit(ACompilationUnit node) {
		out.print("inACompilationUnit: " + node.toString());
	}

	@Override
	public void inAPackageDeclaration(APackageDeclaration node) {
		out.print("inAPackageDeclaration: " + node.toString());
	}

	@Override
	public void inASingleDemandImportDeclaration(
			ASingleDemandImportDeclaration node) {
		out.print("inASingleDemandImportDeclaration: " + node.toString());
	}

	@Override
	public void inASingleImportDeclaration(ASingleImportDeclaration node) {
		out.print("inASingleImportDeclaration: " + node.toString());
	}

	@Override
	public void inASingleTypeImportDeclaration(ASingleTypeImportDeclaration node) {
		out.print("inASingleTypeImportDeclaration: " + node.toString());
	}

	@Override
	public void inATypeImportOnDemandDeclaration(
			ATypeImportOnDemandDeclaration node) {
		out.print("inATypeImportOnDemandDeclaration: " + node.toString());
	}

	@Override
	public void inStart(Start node) {
		out.print("inStart: " + node.toString());
	}

	@Override
	public void outACompilationUnit(ACompilationUnit node) {
		out.print("outACompilationUnit: " + node.toString());
	}

	@Override
	public void outAPackageDeclaration(APackageDeclaration node) {
		out.print("outAPackageDeclaration: " + node.toString());
	}

	@Override
	public void outASingleDemandImportDeclaration(
			ASingleDemandImportDeclaration node) {
		out.print("outASingleDemandImportDeclaration: " + node.toString());
	}

	@Override
	public void outASingleImportDeclaration(ASingleImportDeclaration node) {
		out.print("outASingleImportDeclaration: " + node.toString());
	}

	@Override
	public void outASingleTypeImportDeclaration(
			ASingleTypeImportDeclaration node) {
		out.print("outASingleTypeImportDeclaration: " + node.toString());	
	}

	@Override
	public void outATypeImportOnDemandDeclaration(
			ATypeImportOnDemandDeclaration node) {
		out.print("outATypeImportOnDemandDeclaration: " + node.toString());
	}

	@Override
	public void outStart(Start node) {
		out.print("outStart: " + node.toString());	
	}
}
