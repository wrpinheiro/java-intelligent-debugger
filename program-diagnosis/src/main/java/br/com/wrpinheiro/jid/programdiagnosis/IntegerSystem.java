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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponentAdapter;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.AbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * A representation for a system.
 * 
 * @author wrp
 * 
 *         30/06/2008
 */
public class IntegerSystem extends AbstractComponent {
	/**
	 * A mapping from lines of the program to components.
	 */
	private Map<Integer, List<IntegerComponent>> linesToComps = new LinkedHashMap<Integer, List<IntegerComponent>>();

	public IntegerSystem(final String name,
			final IntegerAbstractComponent parent) {
		super(parent, name, DefaultSets.EMPTY, ComplementaryInfo.EMPTY_INFO);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.AbstractComponent#isRefined()
	 */
	@Override
	public boolean isRefined() {
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.AbstractComponent#isAbstract()
	 */
	@Override
	public boolean isAbstract() {
		return false;
	}

	/**
	 * Add a mapping from a line to a component.
	 * 
	 * @param line
	 *            the line
	 * @param comp
	 *            the component.
	 */
	public void mapLineToComponents(final Set<Integer> lines,
			final IntegerComponent comp) {
		for (Integer line : lines) {
			List<IntegerComponent> comps = this.linesToComps.get(line);
			if (comps == null) {
				comps = new ArrayList<IntegerComponent>();

				this.linesToComps.put(line, comps);
			}
			comps.add(comp);
		}

	}

	/**
	 * Clear the values and enable all components of the system. The values of the
	 * connections are also cleared.
	 */
	public void restart() {
		IntegerConnection.clearAllConnectionsValues();
		IntegerPort.clearAllPortsValues();
		IntegerComponentAdapter.enableAllComponents();

		AbstractComponent.clearAllComponentsPropagationList();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#clearValues()
	 */
	public void clearValues() {
		IntegerConnection.clearAllConnectionsValues();
		IntegerPort.clearAllPortsValues();
		AbstractComponent.clearAllComponentsPropagationList();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getInputPorts()
	 */
	@Override
	public Map<String, IntegerPort> getInputPorts() {
		return new HashMap<String, IntegerPort>();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getOutputPorts()
	 */
	@Override
	public Map<String, IntegerPort> getOutputPorts() {
		return new HashMap<String, IntegerPort>();
	}

	/**
	 * Obtain the components declared in some specific line of the program. 
	 * 
	 * @param line
	 * @return
	 */
	public List<IntegerComponent> getComponentsInLine(final Integer line) {
	  return linesToComps.get(line);
	}
	
	/**
	 * Get a list of component lines.
	 * @return
	 */
	public List<Integer> getLines() {
	  return new ArrayList<Integer>(linesToComps.keySet());
	}

  @Override
  public String getComponentName() {
    return "Sistema " + formatInfo();
  }
}
