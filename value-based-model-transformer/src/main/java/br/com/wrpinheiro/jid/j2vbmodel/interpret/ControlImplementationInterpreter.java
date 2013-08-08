/*
 * Copyright 2006-2013 Wellington Ricardo Pinheiro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wrpinheiro.jid.j2vbmodel.interpret;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;
import br.com.wrpinheiro.jid.javacompiler.node.*;

public class ControlImplementationInterpreter extends DepthFirstAdapter {
	private Logger LOG = Logger.getLogger(ControlImplementationInterpreter.class);

	@Override
	public void inAAdditionalIdentifier(AAdditionalIdentifier node) {
		LOG.debug("inAAdditionalIdentifier: provided default implementation");
	}

	@Override
	public void inAAmpAmpAnIdConditionalAndExpression(
			AAmpAmpAnIdConditionalAndExpression node) {
		LOG.debug("inAAmpAmpAnIdConditionalAndExpression: provided default implementation");	
	}

	@Override
	public void inAAmpAmpIdIdConditionalAndExpression(
			AAmpAmpIdIdConditionalAndExpression node) {
		LOG.debug("inAAmpAmpIdIdConditionalAndExpression: provided default implementation");	
	}

	@Override
	public void inAArguments(AArguments node) {
		LOG.debug("inAArguments: provided default implementation");	
	}

	@Override
	public void inABarBarIdIdConditionalOrExpression(
			ABarBarIdIdConditionalOrExpression node) {
		LOG.debug("inABarBarIdIdConditionalOrExpression: provided default implementation");	
	}

	@Override
	public void inABarBarIdOrConditionalOrExpression(
			ABarBarIdOrConditionalOrExpression node) {
		LOG.debug("inABarBarIdOrConditionalOrExpression: provided default implementation");	
	}

	@Override
	public void inABarBarOrIdConditionalOrExpression(
			ABarBarOrIdConditionalOrExpression node) {
		LOG.debug("inABarBarOrIdConditionalOrExpression: provided default implementation");	
	}

	@Override
	public void inABarBarOrOrConditionalOrExpression(
			ABarBarOrOrConditionalOrExpression node) {
		LOG.debug("inABarBarOrOrConditionalOrExpression: provided default implementation");	
	}

	@Override
	public void inABooleanPrimitiveType(ABooleanPrimitiveType node) {
		LOG.debug("inABooleanPrimitiveType: provided default implementation");	
	}

	@Override
	public void inAByteIntegralType(AByteIntegralType node) {
		LOG.debug("inAByteIntegralType: provided default implementation");	
	}

	@Override
	public void inACastUnaryExpressionNotPlusMinus(
			ACastUnaryExpressionNotPlusMinus node) {
		LOG.debug("inACastUnaryExpressionNotPlusMinus: provided default implementation");	
	}

	@Override
	public void inACharacterLiteral(ACharacterLiteral node) {
		LOG.debug("inACharacterLiteral: provided default implementation");	
	}

	@Override
	public void inACharIntegralType(ACharIntegralType node) {
		LOG.debug("inACharIntegralType: provided default implementation");	
	}

	@Override
	public void inAClassClassMemberDeclaration(AClassClassMemberDeclaration node) {
		LOG.debug("inAClassClassMemberDeclaration: provided default implementation");	
	}

	@Override
	public void inAClassDeclarationBlockStatement(
			AClassDeclarationBlockStatement node) {
		LOG.debug("inAClassDeclarationBlockStatement: provided default implementation");	
	}

	@Override
	public void inAClassNameMethodInvocation(AClassNameMethodInvocation node) {
		LOG.debug("inAClassNameMethodInvocation: provided default implementation");	
	}

	@Override
	public void inAClassPrimaryNoNewArray(AClassPrimaryNoNewArray node) {
		LOG.debug("inAClassPrimaryNoNewArray: provided default implementation");	
	}

	@Override
	public void inAConditionalElementValue(AConditionalElementValue node) {
		LOG.debug("inAConditionalElementValue: provided default implementation");
	}

	@Override
	public void inAConstantExpression(AConstantExpression node) {
		LOG.debug("inAConstantExpression: provided default implementation");
	}

	@Override
	public void inAConstructorBody(AConstructorBody node) {
		LOG.debug("inAConstructorBody: provided default implementation");
	}

	@Override
	public void inAConstructorClassBodyDeclaration(
			AConstructorClassBodyDeclaration node) {
		LOG.debug("inAConstructorClassBodyDeclaration: provided default implementation");
	}

	@Override
	public void inAConstructorDeclaration(AConstructorDeclaration node) {
		LOG.debug("inAConstructorDeclaration: provided default implementation");
	}

	@Override
	public void inAConstructorDeclarator(AConstructorDeclarator node) {
		LOG.debug("inAConstructorDeclarator: provided default implementation");
	}

	@Override
	public void inAContinueStatement(AContinueStatement node) {
		LOG.debug("inAContinueStatement: provided default implementation");
	}

	@Override
	public void inAContinueStatementStatementWithoutTrailingSubstatement(
			AContinueStatementStatementWithoutTrailingSubstatement node) {
		LOG.debug("inAContinueStatementStatementWithoutTrailingSubstatement: provided default implementation");
	}

	@Override
	public void inADefaultSwitchLabel(ADefaultSwitchLabel node) {
		LOG.debug("inADefaultSwitchLabel: provided default implementation");
	}

	@Override
	public void inADoStatementStatementWithoutTrailingSubstatement(
			ADoStatementStatementWithoutTrailingSubstatement node) {
		LOG.debug("inADoStatementStatementWithoutTrailingSubstatement: provided default implementation");
	}

	@Override
	public void inADoubleFloatingPointType(ADoubleFloatingPointType node) {
		LOG.debug("inADoubleFloatingPointType: provided default implementation");
	}

	@Override
	public void inAElementValueArrayInitializer(
			AElementValueArrayInitializer node) {
		LOG.debug("inAElementValueArrayInitializer: provided default implementation");
	}

	@Override
	public void inAEmarkExpressionUnaryExpressionNotPlusMinus(
			AEmarkExpressionUnaryExpressionNotPlusMinus node) {
		LOG.debug("inAEmarkExpressionUnaryExpressionNotPlusMinus: provided default implementation");
	}

	@Override
	public void inAEmarkIdentifierUnaryExpressionNotPlusMinus(
			AEmarkIdentifierUnaryExpressionNotPlusMinus node) {
		LOG.debug("inAEmarkIdentifierUnaryExpressionNotPlusMinus: provided default implementation");
	}

	@Override
	public void inAEmptyBasicForStatement(AEmptyBasicForStatement node) {
		LOG.debug("inAEmptyBasicForStatement: provided default implementation");
	}

	@Override
	public void inAEmptyClassMemberDeclaration(AEmptyClassMemberDeclaration node) {
		LOG.debug("inAEmptyClassMemberDeclaration: provided default implementation");
	}

	@Override
	public void inAEmptyForStatementNoShortIf(AEmptyForStatementNoShortIf node) {
		LOG.debug("inAEmptyForStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAEmptyMethodBody(AEmptyMethodBody node) {
		LOG.debug("inAEmptyMethodBody: provided default implementation");
	}

	@Override
	public void inAEmptyReturnStatement(AEmptyReturnStatement node) {
		LOG.debug("inAEmptyReturnStatement: provided default implementation");
	}

	@Override
	public void inAEmptyStatement(AEmptyStatement node) {
		LOG.debug("inAEmptyStatement: provided default implementation");
	}

	@Override
	public void inAEmptyStatementStatementWithoutTrailingSubstatement(
			AEmptyStatementStatementWithoutTrailingSubstatement node) {
		LOG.debug("inAEmptyStatementStatementWithoutTrailingSubstatement: provided default implementation");
	}

	@Override
	public void inAEmptyTypeDeclaration(AEmptyTypeDeclaration node) {
		LOG.debug("inAEmptyTypeDeclaration: provided default implementation");
	}

	@Override
	public void inAEnhancedForForStatement(AEnhancedForForStatement node) {
		LOG.debug("inAEnhancedForForStatement: provided default implementation");
	}

	@Override
	public void inAEqEqIdEqualityExpression(AEqEqIdEqualityExpression node) {
		LOG.debug("inAEqEqIdEqualityExpression: provided default implementation");
	}

	@Override
	public void inAExceptionType(AExceptionType node) {
		LOG.debug("inAExceptionType: provided default implementation");
	}

	@Override
	public void inAExpressionDimExpr(AExpressionDimExpr node) {
		LOG.debug("inAExpressionDimExpr: provided default implementation");
	}

	@Override
	public void inAExpressionDoStatement(AExpressionDoStatement node) {
		LOG.debug("inAExpressionDoStatement: provided default implementation");
	}

	@Override
	public void inAExpressionForStatementNoShortIf(
			AExpressionForStatementNoShortIf node) {
		LOG.debug("inAExpressionForStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAExpressionIfThenElseStatementNoShortIf(
			AExpressionIfThenElseStatementNoShortIf node) {
		LOG.debug("inAExpressionIfThenElseStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAExpressionSwitchLabel(AExpressionSwitchLabel node) {
		LOG.debug("inAExpressionSwitchLabel: provided default implementation");
	}

	@Override
	public void inAExpressionSwitchStatement(AExpressionSwitchStatement node) {
		LOG.debug("inAExpressionSwitchStatement: provided default implementation");
	}

	@Override
	public void inAExpressionThrowStatement(AExpressionThrowStatement node) {
		LOG.debug("inAExpressionThrowStatement: provided default implementation");
	}

	@Override
	public void inAExpressionWhileStatementNoShortIf(
			AExpressionWhileStatementNoShortIf node) {
		LOG.debug("inAExpressionWhileStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAFieldClassMemberDeclaration(AFieldClassMemberDeclaration node) {
		LOG.debug("inAFieldClassMemberDeclaration: provided default implementation");
	}

	@Override
	public void inAFieldLeftHandSide(AFieldLeftHandSide node) {
		LOG.debug("inAFieldLeftHandSide: provided default implementation");
	}

	@Override
	public void inAFieldPrimaryNoNewArray(AFieldPrimaryNoNewArray node) {
		LOG.debug("inAFieldPrimaryNoNewArray: provided default implementation");
	}

	@Override
	public void inAFinally(AFinally node) {
		LOG.debug("inAFinally: provided default implementation");
	}

	@Override
	public void inAFinallyTryStatement(AFinallyTryStatement node) {
		LOG.debug("inAFinallyTryStatement: provided default implementation");
	}

	@Override
	public void inAFloatFloatingPointType(AFloatFloatingPointType node) {
		LOG.debug("inAFloatFloatingPointType: provided default implementation");
	}

	@Override
	public void inAFloatingNumericType(AFloatingNumericType node) {
		LOG.debug("inAFloatingNumericType: provided default implementation");
	}

	@Override
	public void inAFloatingPointLiteral(AFloatingPointLiteral node) {
		LOG.debug("inAFloatingPointLiteral: provided default implementation");
	}

	@Override
	public void inAForStatementNoShortIfStatementNoShortIf(
			AForStatementNoShortIfStatementNoShortIf node) {
		LOG.debug("inAForStatementNoShortIfStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAForUpdate(AForUpdate node) {
		LOG.debug("inAForUpdate: provided default implementation");
	}

	@Override
	public void inAIdentifierBasicForStatement(AIdentifierBasicForStatement node) {
		LOG.debug("inAIdentifierBasicForStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierClassInstanceCreationExpression(
			AIdentifierClassInstanceCreationExpression node) {
		LOG.debug("inAIdentifierClassInstanceCreationExpression: provided default implementation");
	}

	@Override
	public void inAIdentifierDimExpr(AIdentifierDimExpr node) {
		LOG.debug("inAIdentifierDimExpr: provided default implementation");
	}

	@Override
	public void inAIdentifierDoStatement(AIdentifierDoStatement node) {
		LOG.debug("inAIdentifierDoStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierElementValue(AIdentifierElementValue node) {
		LOG.debug("inAIdentifierElementValue: provided default implementation");
	}

	@Override
	public void inAIdentifierExArrayAccess(AIdentifierExArrayAccess node) {
		LOG.debug("inAIdentifierExArrayAccess: provided default implementation");
	}

	@Override
	public void inAIdentifierExplicitConstructorInvocation(
			AIdentifierExplicitConstructorInvocation node) {
		LOG.debug("inAIdentifierExplicitConstructorInvocation: provided default implementation");
	}

	@Override
	public void inAIdentifierForStatementNoShortIf(
			AIdentifierForStatementNoShortIf node) {
		LOG.debug("inAIdentifierForStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAIdentifierIdArrayAccess(AIdentifierIdArrayAccess node) {
		LOG.debug("inAIdentifierIdArrayAccess: provided default implementation");
	}

	@Override
	public void inAIdentifierIfThenElseStatement(
			AIdentifierIfThenElseStatement node) {
		LOG.debug("inAIdentifierIfThenElseStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierIfThenElseStatementNoShortIf(
			AIdentifierIfThenElseStatementNoShortIf node) {
		LOG.debug("inAIdentifierIfThenElseStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAIdentifierIfThenStatement(AIdentifierIfThenStatement node) {
		LOG.debug("inAIdentifierIfThenStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierPrimaryNoNewArray(AIdentifierPrimaryNoNewArray node) {
		LOG.debug("inAIdentifierPrimaryNoNewArray: provided default implementation");
	}

	@Override
	public void inAIdentifierSwitchLabel(AIdentifierSwitchLabel node) {
		LOG.debug("inAIdentifierSwitchLabel: provided default implementation");
	}

	@Override
	public void inAIdentifierSwitchStatement(AIdentifierSwitchStatement node) {
		LOG.debug("inAIdentifierSwitchStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierThrowStatement(AIdentifierThrowStatement node) {
		LOG.debug("inAIdentifierThrowStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierWhileStatement(AIdentifierWhileStatement node) {
		LOG.debug("inAIdentifierWhileStatement: provided default implementation");
	}

	@Override
	public void inAIdentifierWhileStatementNoShortIf(
			AIdentifierWhileStatementNoShortIf node) {
		LOG.debug("inAIdentifierWhileStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAIfThenElseStatementNoShortIfStatementNoShortIf(
			AIfThenElseStatementNoShortIfStatementNoShortIf node) {
		LOG.debug("inAIfThenElseStatementNoShortIfStatementNoShortIf: provided default implementation");
	}

	@Override
	public void inAInstanceCreationStatementExpression(
			AInstanceCreationStatementExpression node) {
		LOG.debug("inAInstanceCreationStatementExpression: provided default implementation");
	}

	@Override
	public void inALongIntegralType(ALongIntegralType node) {
		LOG.debug("inALongIntegralType: provided default implementation");
	}

	@Override
	public void inAManyElementValues(AManyElementValues node) {
		LOG.debug("inAManyElementValues: provided default implementation");
	}

	@Override
	public void inAManyExceptionTypeList(AManyExceptionTypeList node) {
		LOG.debug("inAManyExceptionTypeList: provided default implementation");
	}

	@Override
	public void inAManyFormalParameters(AManyFormalParameters node) {
		LOG.debug("inAManyFormalParameters: provided default implementation");
	}

	@Override
	public void inAManyStatementExpressionList(AManyStatementExpressionList node) {
		LOG.debug("inAManyStatementExpressionList: provided default implementation");
	}

	@Override
	public void inAManyVariableInitializers(AManyVariableInitializers node) {
		LOG.debug("inAManyVariableInitializers: provided default implementation");
	}

	@Override
	public void inAMethodDeclarator(AMethodDeclarator node) {
		LOG.debug("inAMethodDeclarator: provided default implementation");
	}

	@Override
	public void inAMinusAdIdAdditiveExpression(AMinusAdIdAdditiveExpression node) {
		LOG.debug("inAMinusAdIdAdditiveExpression: provided default implementation");
	}

	@Override
	public void inAMinusAdMuAdditiveExpression(AMinusAdMuAdditiveExpression node) {
		LOG.debug("inAMinusAdMuAdditiveExpression: provided default implementation");
	}

	@Override
	public void inAMinusExpressionUnaryExpression(
			AMinusExpressionUnaryExpression node) {
		LOG.debug("inAMinusExpressionUnaryExpression: provided default implementation");
	}

	@Override
	public void inANeqEqIdEqualityExpression(ANeqEqIdEqualityExpression node) {
		LOG.debug("inANeqEqIdEqualityExpression: provided default implementation");
	}

	@Override
	public void inANullLiteral(ANullLiteral node) {
		LOG.debug("inANullLiteral: provided default implementation");
	}

	@Override
	public void inAOneElementValues(AOneElementValues node) {
		LOG.debug("inAOneElementValues: provided default implementation");
	}

	@Override
	public void inAOneExceptionTypeList(AOneExceptionTypeList node) {
		LOG.debug("inAOneExceptionTypeList: provided default implementation");
	}

	@Override
	public void inAOneVariableInitializers(AOneVariableInitializers node) {
		LOG.debug("inAOneVariableInitializers: provided default implementation");
	}

	@Override
	public void inAPercentIdIdMultiplicativeExpression(
			APercentIdIdMultiplicativeExpression node) {
		LOG.debug("inAPercentIdIdMultiplicativeExpression: provided default implementation");
	}

	@Override
	public void inAPercentMuIdMultiplicativeExpression(
			APercentMuIdMultiplicativeExpression node) {
		LOG.debug("inAPercentMuIdMultiplicativeExpression: provided default implementation");
	}

	@Override
	public void inAPercentMuUnMultiplicativeExpression(
			APercentMuUnMultiplicativeExpression node) {
		LOG.debug("inAPercentMuUnMultiplicativeExpression: provided default implementation");
	}

	@Override
	public void inAPlusExpressionUnaryExpression(
			APlusExpressionUnaryExpression node) {
		LOG.debug("inAPlusExpressionUnaryExpression: provided default implementation");
	}

	@Override
	public void inAPlusIdentifierUnaryExpression(
			APlusIdentifierUnaryExpression node) {
		LOG.debug("inAPlusIdentifierUnaryExpression: provided default implementation");
	}

	@Override
	public void inAPrimaryClassInstanceCreationExpression(
			APrimaryClassInstanceCreationExpression node) {
		LOG.debug("inAPrimaryClassInstanceCreationExpression: provided default implementation");
	}

	@Override
	public void inAPrimaryExArrayAccess(APrimaryExArrayAccess node) {
		LOG.debug("inAPrimaryExArrayAccess: provided default implementation");
	}

	@Override
	public void inAPrimaryExplicitConstructorInvocation(
			APrimaryExplicitConstructorInvocation node) {
		LOG.debug("inAPrimaryExplicitConstructorInvocation: provided default implementation");
	}

	@Override
	public void inAPrimaryFieldAccess(APrimaryFieldAccess node) {
		LOG.debug("inAPrimaryFieldAccess: provided default implementation");
	}

	@Override
	public void inAPrimaryIdArrayAccess(APrimaryIdArrayAccess node) {
		LOG.debug("inAPrimaryIdArrayAccess: provided default implementation");
	}

	@Override
	public void inAPrimaryMethodInvocation(APrimaryMethodInvocation node) {
		LOG.debug("inAPrimaryMethodInvocation: provided default implementation");
	}

	@Override
	public void inAPrimitiveArrayCreationExpression(
			APrimitiveArrayCreationExpression node) {
		LOG.debug("inAPrimitiveArrayCreationExpression: provided default implementation");
	}

	@Override
	public void inAPrimitiveConstantDeclaration(
			APrimitiveConstantDeclaration node) {
		LOG.debug("inAPrimitiveConstantDeclaration: provided default implementation");
	}

	@Override
	public void inAPrimitiveExpressionCastExpression(
			APrimitiveExpressionCastExpression node) {
		LOG.debug("inAPrimitiveExpressionCastExpression: provided default implementation");
	}

	@Override
	public void inAPrimitiveExpressionEnhancedForStatement(
			APrimitiveExpressionEnhancedForStatement node) {
		LOG.debug("inAPrimitiveExpressionEnhancedForStatement: provided default implementation");
	}

	@Override
	public void inAPrimitiveFieldDeclaration(APrimitiveFieldDeclaration node) {
		LOG.debug("inAPrimitiveFieldDeclaration: provided default implementation");
	}

	@Override
	public void inAPrimitiveFormalParameter(APrimitiveFormalParameter node) {
		LOG.debug("inAPrimitiveFormalParameter: provided default implementation");
	}

	@Override
	public void inAPrimitiveIdentifierCastExpression(
			APrimitiveIdentifierCastExpression node) {
		LOG.debug("inAPrimitiveIdentifierCastExpression: provided default implementation");
	}

	@Override
	public void inAPrimitiveIdentifierEnhancedForStatement(
			APrimitiveIdentifierEnhancedForStatement node) {
		LOG.debug("inAPrimitiveIdentifierEnhancedForStatement: provided default implementation");
	}

	@Override
	public void inAPrimitiveInitializerArrayCreationExpression(
			APrimitiveInitializerArrayCreationExpression node) {
		LOG.debug("inAPrimitiveInitializerArrayCreationExpression: provided default implementation");
	}

	@Override
	public void inAPrimitivePrimaryNoNewArray(APrimitivePrimaryNoNewArray node) {
		LOG.debug("inAPrimitivePrimaryNoNewArray: provided default implementation");
	}

	@Override
	public void inAPrivateModifier(APrivateModifier node) {
		LOG.debug("inAPrivateModifier: provided default implementation");
	}

	@Override
	public void inAProtectedModifier(AProtectedModifier node) {
		LOG.debug("inAProtectedModifier: provided default implementation");
	}

	@Override
	public void inAReferenceArrayCreationExpression(
			AReferenceArrayCreationExpression node) {
		LOG.debug("inAReferenceArrayCreationExpression: provided default implementation");
	}

	@Override
	public void inAReferenceConstantDeclaration(
			AReferenceConstantDeclaration node) {
		LOG.debug("inAReferenceConstantDeclaration: provided default implementation");
	}

	@Override
	public void inAReferenceExpressionCastExpression(
			AReferenceExpressionCastExpression node) {
		LOG.debug("inAReferenceExpressionCastExpression: provided default implementation");
	}

	@Override
	public void inAReferenceExpressionEnhancedForStatement(
			AReferenceExpressionEnhancedForStatement node) {
		LOG.debug("inAReferenceExpressionEnhancedForStatement: provided default implementation");
	}

	@Override
	public void inAReferenceFieldDeclaration(AReferenceFieldDeclaration node) {
		LOG.debug("inAReferenceFieldDeclaration: provided default implementation");
	}

	@Override
	public void inAReferenceIdentifierCastExpression(
			AReferenceIdentifierCastExpression node) {
		LOG.debug("inAReferenceIdentifierCastExpression: provided default implementation");
	}

	@Override
	public void inAReferenceIdentifierEnhancedForStatement(
			AReferenceIdentifierEnhancedForStatement node) {
		LOG.debug("inAReferenceIdentifierEnhancedForStatement: provided default implementation");
	}

	@Override
	public void inAReferenceInitializerArrayCreationExpression(
			AReferenceInitializerArrayCreationExpression node) {
		LOG.debug("inAReferenceInitializerArrayCreationExpression: provided default implementation");
	}

	@Override
	public void inAReferenceMethodHeader(AReferenceMethodHeader node) {
		LOG.debug("inAReferenceMethodHeader: provided default implementation");
	}

	@Override
	public void inAReferencePrimaryNoNewArray(AReferencePrimaryNoNewArray node) {
		LOG.debug("inAReferencePrimaryNoNewArray: provided default implementation");
	}

	@Override
	public void inAReferenceSuperFieldAccess(AReferenceSuperFieldAccess node) {
		LOG.debug("inAReferenceSuperFieldAccess: provided default implementation");
	}

	@Override
	public void inAShortIntegralType(AShortIntegralType node) {
		LOG.debug("inAShortIntegralType: provided default implementation");
	}

	@Override
	public void inASlashMuIdMultiplicativeExpression(
			ASlashMuIdMultiplicativeExpression node) {
		LOG.debug("inASlashMuIdMultiplicativeExpression: provided default implementation");
	}

	@Override
	public void inASuperExplicitConstructorInvocation(
			ASuperExplicitConstructorInvocation node) {
		LOG.debug("inASuperExplicitConstructorInvocation: provided default implementation");
	}

	@Override
	public void inASuperFieldAccess(ASuperFieldAccess node) {
		LOG.debug("inASuperFieldAccess: provided default implementation");
	}

	@Override
	public void inASuperMethodInvocation(ASuperMethodInvocation node) {
		LOG.debug("inASuperMethodInvocation: provided default implementation");
	}

	@Override
	public void inASwitchBlock(ASwitchBlock node) {
		LOG.debug("inASwitchBlock: provided default implementation");
	}

	@Override
	public void inASwitchBlockStatementGroup(ASwitchBlockStatementGroup node) {
		LOG.debug("inASwitchBlockStatementGroup: provided default implementation");
	}

	@Override
	public void inASwitchStatementStatementWithoutTrailingSubstatement(
			ASwitchStatementStatementWithoutTrailingSubstatement node) {
		LOG.debug("inASwitchStatementStatementWithoutTrailingSubstatement: provided default implementation");
	}

	@Override
	public void inAThisExplicitConstructorInvocation(
			AThisExplicitConstructorInvocation node) {
		LOG.debug("inAThisExplicitConstructorInvocation: provided default implementation");
	}

	@Override
	public void inAThisPrimaryNoNewArray(AThisPrimaryNoNewArray node) {
		LOG.debug("inAThisPrimaryNoNewArray: provided default implementation");
	}

	@Override
	public void inAThrows(AThrows node) {
		LOG.debug("inAThrows: provided default implementation");
	}

	@Override
	public void inAThrowStatementStatementWithoutTrailingSubstatement(
			AThrowStatementStatementWithoutTrailingSubstatement node) {
		LOG.debug("inAThrowStatementStatementWithoutTrailingSubstatement: provided default implementation");
	}

	@Override
	public void inATryStatementStatementWithoutTrailingSubstatement(
			ATryStatementStatementWithoutTrailingSubstatement node) {
		LOG.debug("inATryStatementStatementWithoutTrailingSubstatement: provided default implementation");
	}

	@Override
	public void inAVariableDeclarationForInit(AVariableDeclarationForInit node) {
		LOG.debug("inAVariableDeclarationForInit: provided default implementation");
	}

	@Override
	public void inAVoidPrimaryNoNewArray(AVoidPrimaryNoNewArray node) {
		LOG.debug("inAVoidPrimaryNoNewArray: provided default implementation");
	}

	@Override
	public void inAWhileStatementNoShortIfStatementNoShortIf(
			AWhileStatementNoShortIfStatementNoShortIf node) {
		LOG.debug("inAWhileStatementNoShortIfStatementNoShortIf: provided default implementation");
	}
}
