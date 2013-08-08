package br.com.wrpinheiro.jid.j2vbmodel.interpret.adapters;

import java.util.List;

import br.com.wrpinheiro.jid.javacompiler.node.ABlock;
import br.com.wrpinheiro.jid.javacompiler.node.AConstructorBody;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PBlockStatement;

/**
 * An adapter to provide an easier access to the block sentences of some nodes.
 * 
 * @author wrp
 */
public class BlockStatementAdapter {
	/**
	 * The source node.
	 */
	private Node sourceNode;

	/**
	 * Create an adapter for node.
	 * @param node the node.
	 */
	public BlockStatementAdapter(final Node node) {
		this.sourceNode = node;
	}

	/**
	 * Get the source node.
	 * 
	 * @return the source node.
	 */
	public Node getBlockSentenceAdapter() {
		return sourceNode;
	}

	/**
	 * Return the block statement of the sourceNode.
	 * 
	 * @return the block statements.
	 */
	public List<PBlockStatement> getBlockStatements() {
		if (sourceNode instanceof ABlock)
			return ((ABlock)sourceNode).getBlockStatements();

		if (sourceNode instanceof AConstructorBody) 
			return ((AConstructorBody)sourceNode).getBlockStatements();

		throw new RuntimeException("Cannot adapt class type: "
				+ this.sourceNode);
	}

	/**
	 * Set the block statements in the source node.
	 * 
	 * @param stmt
	 *            the block statements.
	 */
	public void setBlockStatement(final List<PBlockStatement> blockStatements) {
		if (sourceNode instanceof ABlock)
			((ABlock)sourceNode).setBlockStatements(blockStatements);
		else if (sourceNode instanceof AConstructorBody) 
			((AConstructorBody)sourceNode).setBlockStatements(blockStatements);
		else throw new RuntimeException("Cannot adapt class type: "
				+ this.sourceNode);
	}
}
