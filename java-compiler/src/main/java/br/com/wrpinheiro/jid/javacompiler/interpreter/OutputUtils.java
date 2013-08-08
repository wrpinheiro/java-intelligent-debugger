package br.com.wrpinheiro.jid.javacompiler.interpreter;

import org.apache.log4j.Logger;

public class OutputUtils {
	/**
	 * OutputUtils logger.
	 */
	private static final Logger LOG = Logger.getLogger(OutputUtils.class);
	
	private boolean firstOut = true;
	private StringBuilder sb = new StringBuilder("");

	private void positiveIndentation() {
		sb.append("  ");
	}

	private void negativeIndentation() {
		if (!firstOut)
		  sb.delete(7, 9);
		else firstOut = false;
	}

	public void print(String str) {
		if (str.substring(0, 2).equals("in")) {
			positiveIndentation();
			LOG.debug(sb.toString() + str);
		} else {
			LOG.debug(sb.toString() + str);
			negativeIndentation();
		}
	}
}
