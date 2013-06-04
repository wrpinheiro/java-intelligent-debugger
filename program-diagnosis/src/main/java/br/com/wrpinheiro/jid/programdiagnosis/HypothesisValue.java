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

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

public class HypothesisValue {
	/**
	 * A set of components at the same program lines.
	 */
	private Set<IntegerComponent> components = new LinkedHashSet<IntegerComponent>();

	/**
	 * Creates as instance with an underlying component.
	 * 
	 * @param component
	 */
	public HypothesisValue(final IntegerComponent component) {
		this.components.add(component);
	}

	/**
	 * Create a instance of the hiphotesis value and all comps to it.
	 * @param comps the componentes.
	 */
	public HypothesisValue(final Set<IntegerComponent> comps) {
		this.components.addAll(comps);
	}

	/**
	 * Add a list of components to this hyphotesis.
	 * @param comps
	 */
	public void addComponents(final Set<IntegerComponent> comps) {
		this.components.addAll(comps);
	}

	/**
	 * Program lines related to the components of this hyphotesis.
	 * 
	 * @return the lines of the program.
	 */
	public Set<Integer> getProgramLines() {
		Set<Integer> lines = new LinkedHashSet<Integer>();

		for (IntegerComponent comp: this.components)
			lines.addAll(comp.getProgramLines());
		
		return lines;
	}

	/**
	 * Return the components maintained by this hyphotesis value.
	 * 
	 * @return the component.
	 */
	public Set<IntegerComponent> getComponents() {
		return this.components;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Set<String> valuesC = new LinkedHashSet<String>();
		Set<String> valuesL = new LinkedHashSet<String>();
		
		StringBuilder sb = new StringBuilder();
		for (IntegerComponent comp : this.components) {
			if (comp instanceof IntegerAbstractComponent) {
				valuesC.add(comp.getName());
				//sb.append(comp.getName()).append(";");
			} else {
				//sb.append("L:[");
				Set<Integer> lines = this.getProgramLines();
				for (Integer line : lines) {
					valuesL.add(line.toString());
					//sb.append(line).append(",");
				}
			}
		}

		for (String s : valuesL) {
			sb.append(s).append("; ");
		}

		for (String s : valuesC) {
			sb.append(s).append("; ");
		}

		sb.delete(sb.length()-2, sb.length());
		return sb.toString(); //components.toString(); // + " L:" + this.getProgramLines();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getProgramLines().hashCode();
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (!(obj instanceof HypothesisValue))
			return false;

		HypothesisValue other = (HypothesisValue) obj;
		return this.getProgramLines().equals(other.getProgramLines());
	}
}
