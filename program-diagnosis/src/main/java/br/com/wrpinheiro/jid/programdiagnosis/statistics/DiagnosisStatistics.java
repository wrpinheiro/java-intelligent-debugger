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
package br.com.wrpinheiro.jid.programdiagnosis.statistics;

/**
 * Class responsible to maintain information about the diagnosis process.
 * 
 * @author wrp
 * 
 *         Sep 4, 2008
 */
public final class DiagnosisStatistics {
	/**
	 * Private instance of the singleton.
	 */
	private static DiagnosisStatistics instance;

	/**
	 * The number of inferences.
	 */
	private int inferences = 0;

	/**
	 * The number of call to the constraint propagator.
	 */
	private int cspCalls = 0;

	/**
	 * The number of conflict set found during the diagnosis session.
	 */
	private int conflictSets = 0;

	/**
	 * Private constructor to prevent this class to be instantiated.
	 */
	private DiagnosisStatistics() {
		// does not need implementation.
	}

	/**
	 * Get the instance of the singleton.
	 * 
	 * @return the instance of the singleton.
	 */
	public static DiagnosisStatistics getInstance() {
		if (instance == null) {
			instance = new DiagnosisStatistics();
		}
		return instance;
	}

	/**
	 * Increment the counter of inferences.
	 */
	public void addInferences() {
		this.inferences++;
	}

	/**
	 * Increment the counter of csp calls.
	 */
	public void addCspCalls() {
		this.cspCalls++;
	}

	/**
	 * Increment the counter of conflict sets found.
	 */
	public void addConflictSets() {
		this.conflictSets++;
	}

	/**
	 * Get the number of inferences.
	 * 
	 * @return the number of inferences.
	 */
	public int getInferences() {
		return inferences;
	}

	/**
	 * Get the number of calls to the CSP.
	 * 
	 * @return the number of calls to the CSP.
	 */
	public int getCspCalls() {
		return this.cspCalls;
	}

	/**
	 * Get the number of conflict sets.
	 * 
	 * @return the number of conflict sets.
	 */
	public int getConflictSets() {
		return this.conflictSets;
	}

	/**
	 * Clear the value of the variables.
	 */
	public void clearVariables() {
		this.inferences = 0;
		this.cspCalls = 0;
		this.conflictSets = 0;
	}

	/**
	 * Get a formatted report containing all the statistics.
	 * 
	 * @return A formatted string.
	 */
	public String printStatistics() {
		StringBuilder sb = new StringBuilder("\n\n");

		sb.append("# Inferences\t\t:").append(this.inferences).append("\n");
		sb.append("# Calls to the CSP\t:").append(this.cspCalls).append("\n");
		sb.append("# Conflict Sets\t\t:").append(this.conflictSets).append("\n");

		sb.append("\n\n");
		
		return sb.toString();
	}
}
