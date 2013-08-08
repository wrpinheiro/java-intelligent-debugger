package br.com.wrpinheiro.jid.j2vbmodel.interpret.context;
/**
 * A class to manipulate sequences of integers.
 *
 * @author wrp
 */
public class Sequence {
	/**
	 * control variable of the sequence.
	 */
	private int seq = 0;

	/**
	 * Return the current value in this sequence.
	 * @return an integer.
	 */
	public int getCurrentValue() {
		return seq;
	}

	/**
	 * Return the next value of this sequence.
	 * @return an integer.
	 */
	public int getNextValue() {
		return seq++;
	}
}
