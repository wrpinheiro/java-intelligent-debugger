package br.com.wrpinheiro.jid.j2vbmodel.interpret.command;


import br.com.wrpinheiro.jid.javacompiler.node.AAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.APercentAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.APlusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.ASlashAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AStarAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.PBasicForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PDoStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PIfThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.PWhileStatement;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;

/**
 * Utility class for model parsers.
 * 
 * @author wrp
 */
public final class ParserUtils {
	/**
	 * Check if the node is inside a block or direct inside a If, While, Do, or
	 * other For structure.
	 * 
	 * @param node the node.
	 * @return
	 */
	public static boolean isInsideABlock(final Node node) {
		if (node instanceof AStatementBlockStatement)
			return true;
		else if (node instanceof PIfThenStatement
				|| node instanceof PIfThenElseStatement
				|| node instanceof PWhileStatement
				|| node instanceof PDoStatement
				|| node instanceof PBasicForStatement
				|| node instanceof PEnhancedForStatement)
			return false;
		else
			return ParserUtils.isInsideABlock(node.parent());
	}

	/**
	 * Check if is possible to add a block in this node.
	 * 
	 * @param node
	 *            the node.
	 * @return true if a block can be added or false otherwise.
	 */
	public static boolean canAddBlock(final Node node) {
		return node instanceof PIfThenStatement
				|| node instanceof PIfThenElseStatement
				|| node instanceof PWhileStatement
				|| node instanceof PDoStatement
				|| node instanceof PBasicForStatement
				|| node instanceof PEnhancedForStatement;
	}

	/**
	 * Return the line of an assignment expression.
	 * @param assignmentOp the assignment operator.
	 * @return the line of the assignment.
	 */
	public static int getLineOfTheAssignment(final PAssignmentOperator assignmentOp) {
		if (assignmentOp instanceof AAssignAssignmentOperator) {
			AAssignAssignmentOperator op = (AAssignAssignmentOperator)assignmentOp;
			return op.getAssign().getLine();
		} else if (assignmentOp instanceof AMinusAssignAssignmentOperator) {
			AMinusAssignAssignmentOperator op = (AMinusAssignAssignmentOperator)assignmentOp;
			return op.getMinusAssign().getLine();
		} else if (assignmentOp instanceof APercentAssignAssignmentOperator) {
			APercentAssignAssignmentOperator op = (APercentAssignAssignmentOperator)assignmentOp;
			return op.getPercentAssign().getLine();
		} else if (assignmentOp instanceof APlusAssignAssignmentOperator) {
			APlusAssignAssignmentOperator op = (APlusAssignAssignmentOperator)assignmentOp;
			return op.getPlusAssign().getLine();
		} else if (assignmentOp instanceof ASlashAssignAssignmentOperator) {
			ASlashAssignAssignmentOperator op = (ASlashAssignAssignmentOperator)assignmentOp;
			return op.getSlashAssign().getLine();
		} else {
			AStarAssignAssignmentOperator op = (AStarAssignAssignmentOperator)assignmentOp;
			return op.getStarAssign().getLine();
		}
	}
	
	public static ComplementaryInfo getInfo(Node n) {
	  return new ComplementaryInfo(n.toString());
	}
}
