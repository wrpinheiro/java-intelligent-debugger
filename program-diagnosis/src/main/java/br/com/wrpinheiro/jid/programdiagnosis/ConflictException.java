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

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

/**
 * Exception thrown when the propagation detects a conflict. This instance
 * maintains the port involved in the conflict.
 * 
 * @author wrp
 * 
 *         29/04/2008
 */
public class ConflictException extends Exception {
	/**
	 * serial version.
	 */
	private static final long serialVersionUID = 437818603039734897L;

	/**
	 * The offending value.
	 */
	private Integer offendingValue;

	private Set<IntegerComponent> conflictSet;

	/**
	 * Creates an exception and set the conflicted port.
	 * 
	 * @param message
	 *            error message
	 * @param port
	 *            the offended port.
	 * @param value
	 *            the offending value.
	 * @param conflictSet
	 */
	public ConflictException(String message, Integer value,
			Set<IntegerComponent> conflictSet) {
		super(message);
		this.offendingValue = value;
		this.conflictSet = conflictSet;
	}

	/**
	 * Get the offending value.
	 * 
	 * @return offending value.
	 */
	public Integer getAffectingValue() {
		return this.offendingValue;
	}

	/**
	 * Get the conflict set.
	 * 
	 * @return the conflict set.
	 */
	public Set<IntegerComponent> getConflictSet() {
		return this.conflictSet;
	}
}
