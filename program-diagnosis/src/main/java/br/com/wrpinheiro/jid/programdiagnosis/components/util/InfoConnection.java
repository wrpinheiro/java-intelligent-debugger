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
package br.com.wrpinheiro.jid.programdiagnosis.components.util;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * A class to relate lines of the program and connections.
 * 
 * @author wrp
 */
public class InfoConnection {
	/**
	 * The first line of the component.
	 */
	private Integer firstLine;
	
	/**
	 * The last line of the component.
	 */
	private Integer lastLine;

	/**
	 * The connection related to the line.
	 */
	private IntegerConnection connection;
	
	/**
	 * The component of this connection.
	 */
	private IntegerComponent comp;

	public InfoConnection(final Integer firstLine, final Integer lastLine, final IntegerConnection connection, final IntegerComponent comp) {
		this.firstLine = firstLine;
		this.lastLine = lastLine;
		this.connection = connection;
		this.comp = comp;
	}
	
	/**
	 * Get the line of the program that is related to the connection.
	 * 
	 * @return the line of the program.
	 */
	public Integer getFirstLine() {
		return firstLine;
	}

	/**
	 * Get the last line of the program that is related to component.
	 * 
	 * @return the line of the program.
	 */
	public Integer getLastLine() {
		return lastLine;
	}
	
	/**
	 * The connection of.
	 * 
	 * @return
	 */
	public IntegerConnection getConnection() {
		return connection;
	}

  public IntegerComponent getComponent() {
    return comp;
  }

}
