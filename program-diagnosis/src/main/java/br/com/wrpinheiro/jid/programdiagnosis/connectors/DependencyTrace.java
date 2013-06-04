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

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

/**
 * Representation of a mechanism to treat dependencies between a value set in a
 * port (almost like a note in a TMS) and a set of components (the assumptions
 * used to infer the value set in the port).
 * 
 * @author wrp
 * 
 *         Sep 3, 2008
 */
public interface DependencyTrace {
	/**
	 * Add a set of assumptions (components) that were used to derive a value
	 * being set in the port.
	 * 
	 * @param assumptions
	 *            a set of components.
	 */
	public void addAssumption(Set<IntegerComponent> assumptions);

	/**
	 * Add an unique assumption (component) that was used to derive a value
	 * being set in the port.
	 * 
	 * @param assumptions
	 *            a set of components.
	 */
	public void addAssumption(IntegerComponent c);

	/**
	 * Rejects (discard) a set of assumptions.
	 * 
	 * If the value being set in the port could be derived by another set of
	 * assumptions , the current assumptions must be rejected.
	 */
	public void rejectAssumptions();

	/**
	 * Accepts the set of assumptions for the value set in the port.
	 * 
	 * A set of accepted assumptions is used as a dependency set of the current
	 * port.
	 */
	public void acceptAssumptions();

	/**
	 * Get the set of current pending assumptions used to derive the a value in
	 * this port.
	 * 
	 * @return a COPY of the pending assumptions (components) of this port.
	 */
	public Set<IntegerComponent> getAssumptions();

	/**
	 * Get the set of components used to derive the current value in this port.
	 * 
	 * @return a set o dependencies (components) of this port.
	 */
	public Set<IntegerComponent> getDependencies();

	/**
	 * Clear the dependency list of this port and the discard the pending
	 * assumptions set.
	 */
	public void clearDependencies();
}
