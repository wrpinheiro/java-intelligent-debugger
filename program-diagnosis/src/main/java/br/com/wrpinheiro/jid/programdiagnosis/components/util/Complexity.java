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

/**
 * Possible complexities related to the inference process of components. Low
 * complexity components must be propagated first than the high complexity ones.
 * 
 * @author wrp
 */
public enum Complexity {
	EASIEST(1), EASY(2), MEDIUM(3), HIGH(4), HIGHEST(5);

	/**
	 * The complexity.
	 */
	private int complexity;

	/**
	 * Return the complexity.
	 * 
	 * @return the complexity.
	 */
	public int getComplexity() {
		return this.complexity;
	}

	/**
	 * Create the enum with an specific complexity.
	 * 
	 * @param complexity
	 */
	private Complexity(final int complexity) {
		this.complexity = complexity;
	}
}
