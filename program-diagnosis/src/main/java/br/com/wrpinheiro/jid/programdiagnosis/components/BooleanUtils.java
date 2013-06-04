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
package br.com.wrpinheiro.jid.programdiagnosis.components;

import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * A class for utility methods for boolean operations.
 * 
 * @author wrp
 */
public final class BooleanUtils {
	/**
	 * Constant definition for input port 1.
	 */
	public static final int IN1 = 0;

	/**
	 * Constant definition for input port 2.
	 */
	public static final int IN2 = 1;

	/**
	 * Constant definition for both input ports.
	 */
	public static final int IN_1_2 = 2;

	/**
	 * Constant for a value 0.
	 */
	public static final Integer ZERO = new Integer(0);

	/**
	 * Constant for a value 1.
	 */
	public static final Integer ONE = new Integer(1);

	/**
	 * Integer value for a falsum.
	 */
	public static final Integer FALSE = new Integer(0);

	/**
	 * Integer value for a true.
	 */
	public static final Integer TRUE = new Integer(1);

	/**
	 * Private constructor preventing this class to be instantiated.
	 */
	private BooleanUtils() {
		// does not need implementation.
	}

	/**
	 * Check if intP's value is value.
	 * 
	 * @param intP
	 *            the referring port.
	 * @param value
	 *            the value.
	 * @return True if intP's value is value or false, otherwise.
	 */
	public static boolean checkPortValue(IntegerPort intP, Integer value) {
		return intP.hasValue() && intP.getValue().equals(value);
	}

	/**
	 * Return a integer representation for a boolean value.
	 * @param b the boolean value.
	 * @return an integer value.
	 */
	public static Integer boolToInt(boolean b) {
		return b ? TRUE : FALSE;
	}
}
