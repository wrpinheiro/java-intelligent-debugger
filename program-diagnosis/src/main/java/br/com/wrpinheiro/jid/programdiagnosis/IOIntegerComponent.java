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
package br.com.wrpinheiro.jid.programdiagnosis;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * Defines the behavior of system with inputs and outputs.
 * 
 * @author wrp
 * 
 * 14/01/2009
 */
public interface IOIntegerComponent extends IntegerComponent {
	/**
	 * Add a new input port.
	 * 
	 * @param portName
	 *            the name of the port to created.
	 * 
	 * @return the new created port.
	 */
	public IntegerPort addInputPort(String portName);

	/**
	 * Add a new output port.
	 * 
	 * @param portName
	 *            the name of the port to created.
	 * 
	 * @return the new created port.
	 */
	public IntegerPort addOutputPort(String portName);
	
	/**
	 * Return a named input port.
	 * 
	 * @param name
	 *            the port name.
	 * @return
	 */
	public IntegerPort getInputPort(String name);

	/**
	 * Return a named output port.
	 * 
	 * @param name
	 *            the port name.
	 * @return
	 */
	public IntegerPort getOutputPort(String name);
}
