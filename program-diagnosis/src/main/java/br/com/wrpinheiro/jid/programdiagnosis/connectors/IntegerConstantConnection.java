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
package br.com.wrpinheiro.jid.programdiagnosis.connectors;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

/**
 * A class for constant connections.
 * 
 * @author wrp
 * 
 *         Mar 4, 2009
 */
public class IntegerConstantConnection extends IntegerConnection {
	/**
	 * This constant value.
	 */
	private Integer constantValue;

	/**
	 * Creates an integer constant connection.
	 * 
	 * @param name
	 *            the name of this port.
	 */
	public IntegerConstantConnection(IntegerAbstractComponent system, String name,
			Integer constantValue) {
		super(system, name, name);

		this.constantValue = constantValue;
	}

	/**
	 * Propagate the constant value of this connection.
	 * 
	 * @throws ConflictException
	 */
	public void propagateConstantValue() throws ConflictException {
		this.setValue(this.constantValue);
	}

	/**
	 * The constant value of this connection.
	 * 
	 * @return the constant value.
	 */
	public Integer getConstantValue() {
		return this.constantValue;
	}

	/**
	 * Constant values cannot be cleaned.
	 */
	@Override
	public void clearValue() {
		// does not need implementation.
	}
}
