package br.com.wrpinheiro.jid.j2vbmodel.interpret.adapters;

import br.com.wrpinheiro.jid.javacompiler.node.AEmptyBasicForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionBasicForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionDoStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIfThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionWhileStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierBasicForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierDoStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierWhileStatement;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveExpressionEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveIdentifierEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceExpressionEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceIdentifierEnhancedForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PStatement;

/**
 * An adapter to provide an easier access to the sentences of some nodes.
 * 
 * @author wrp
 */
public class StatementNodeAdapter {
	/**
	 * The source node.
	 */
	private Node sourceNode;

	/**
	 * Create an adapter for node.
	 * @param node the node.
	 */
	public StatementNodeAdapter(final Node node) {
		this.sourceNode = node;
	}

	/**
	 * Get the source node.
	 * 
	 * @return the source node.
	 */
	public Node getSourceNode() {
		return sourceNode;
	}

	/**
	 * Return the statement of the sourceNode.
	 * 
	 * @return the statement.
	 */
	public PStatement getStatement() {
		if (sourceNode instanceof AExpressionIfThenStatement) {
			return ((AExpressionIfThenStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AIdentifierIfThenStatement) {
			return ((AIdentifierIfThenStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AExpressionIfThenElseStatement) {
			return ((AExpressionIfThenElseStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AIdentifierIfThenElseStatement) {
			return ((AIdentifierIfThenElseStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AExpressionWhileStatement) {
			return ((AExpressionWhileStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AIdentifierWhileStatement) {
			return ((AIdentifierWhileStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AExpressionDoStatement) {
			return ((AExpressionDoStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AIdentifierDoStatement) {
			return ((AIdentifierDoStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AEmptyBasicForStatement) {
			return ((AEmptyBasicForStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AExpressionBasicForStatement) {
			return ((AExpressionBasicForStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof AIdentifierBasicForStatement) {
			return ((AIdentifierBasicForStatement) sourceNode).getStatement();
		}
		if (sourceNode instanceof APrimitiveExpressionEnhancedForStatement) {
			return ((APrimitiveExpressionEnhancedForStatement) sourceNode)
					.getStatement();
		}
		if (sourceNode instanceof APrimitiveIdentifierEnhancedForStatement) {
			return ((APrimitiveIdentifierEnhancedForStatement) sourceNode)
					.getStatement();
		}
		if (sourceNode instanceof AReferenceExpressionEnhancedForStatement) {
			return ((AReferenceExpressionEnhancedForStatement) sourceNode)
					.getStatement();
		}
		if (sourceNode instanceof AReferenceIdentifierEnhancedForStatement) {
			return ((AReferenceIdentifierEnhancedForStatement) sourceNode)
					.getStatement();
		}

		throw new RuntimeException("Cannot adapt class type: "
				+ this.sourceNode);
	}

	/**
	 * Set the statement in the source node.
	 * 
	 * @param stmt
	 *            the statement.
	 */
	public void setStatement(final PStatement stmt) {
		if (sourceNode instanceof AExpressionIfThenStatement) {
			((AExpressionIfThenStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AIdentifierIfThenStatement) {
			((AIdentifierIfThenStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AExpressionIfThenElseStatement) {
			((AExpressionIfThenElseStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AIdentifierIfThenElseStatement) {
			((AIdentifierIfThenElseStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AExpressionWhileStatement) {
			((AExpressionWhileStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AIdentifierWhileStatement) {
			((AIdentifierWhileStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AExpressionDoStatement) {
			((AExpressionDoStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AIdentifierDoStatement) {
			((AIdentifierDoStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AEmptyBasicForStatement) {
			((AEmptyBasicForStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AExpressionBasicForStatement) {
			((AExpressionBasicForStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof AIdentifierBasicForStatement) {
			((AIdentifierBasicForStatement) sourceNode).setStatement(stmt);
		} else if (sourceNode instanceof APrimitiveExpressionEnhancedForStatement) {
			((APrimitiveExpressionEnhancedForStatement) sourceNode)
					.setStatement(stmt);
		} else if (sourceNode instanceof APrimitiveIdentifierEnhancedForStatement) {
			((APrimitiveIdentifierEnhancedForStatement) sourceNode)
					.setStatement(stmt);
		} else if (sourceNode instanceof AReferenceExpressionEnhancedForStatement) {
			((AReferenceExpressionEnhancedForStatement) sourceNode)
					.setStatement(stmt);
		} else if (sourceNode instanceof AReferenceIdentifierEnhancedForStatement) {
			((AReferenceIdentifierEnhancedForStatement) sourceNode)
					.setStatement(stmt);
		} else throw new RuntimeException("Cannot adapt class type: "
					+ this.sourceNode);
	}
}
