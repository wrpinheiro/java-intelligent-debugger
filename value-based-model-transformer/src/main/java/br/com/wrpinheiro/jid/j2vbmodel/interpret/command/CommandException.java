package br.com.wrpinheiro.jid.j2vbmodel.interpret.command;

/**
 * Exception for commands.
 * 
 * @author wrp
 */
public class CommandException extends RuntimeException {
	/**
	 * serial version.
	 */
	private static final long serialVersionUID = -9162885557278307387L;

	/**
	 * Creates a command exception with the message msg.
	 * @param msg the message.
	 */
	public CommandException(final String msg) {
		super(msg);
	}
}
