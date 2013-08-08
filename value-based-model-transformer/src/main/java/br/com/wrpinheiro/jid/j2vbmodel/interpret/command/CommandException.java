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
