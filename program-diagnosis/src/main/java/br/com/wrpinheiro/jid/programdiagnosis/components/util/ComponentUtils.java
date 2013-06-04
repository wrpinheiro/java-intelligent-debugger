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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jid.programdiagnosis.HypothesisValue;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConstantConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * A utility class to work with components and hypothesis.
 * @author wrp
 */
public class ComponentUtils {
  
  private static final int INPUT = 0;
  private static final int OUTPUT = 1;

	/**
	 * Get all the connections based on a set of components.
	 * @param _hyphValues the set of hypothesis.
	 * @param connsIn the input connections.
	 * @param connsOut the output connections.
	 */
	public static void getConnectionFromHypotheses(
			final SetMaintainer<HypothesisValue> _hyphValues,
			final Set<InfoConnection> connsIn,
			final Set<InfoConnection> connsOut) {

		Set<IntegerComponent> comps = new LinkedHashSet<IntegerComponent>();

		for (HypothesisValue hv : _hyphValues) {
			comps.addAll(hv.getComponents());
		}

		getConnectionsOfTheComponents(comps, connsIn, connsOut);
	}

	/**
	 * Filters the kind of connections that must be added.
	 * 
	 * @param conn
	 *            the connecgtion.
	 * @return TRUE if the connection must be added or FALSE if it must be
	 *         discarded.
	 */
	private static boolean mustAdd(IntegerConnection conn) {
		return (!(conn instanceof IntegerConstantConnection) && !conn
				.getSimpleName().equals("aux") && conn.isAssignable());
	}

	/**
	 * Add the connections form the ports of a components.
	 * @param comp the component.
	 * @param ports the ports.
	 * @param infoConns the connections set.
	 */
	private static void addConnections(final IntegerComponent comp,
			final Collection<IntegerPort> ports,
			final Set<InfoConnection> infoConns, int ioValue) {
		if (ports != null && ports.size() > 0) {
			for (IntegerPort p : ports)
				for (IntegerConnection conn : p.getConnections()) {
					if (mustAdd(conn) || isSpecialConnection(comp, p, ioValue)) {
						List<Integer> lines = new ArrayList<Integer>(comp.getProgramLines());
						int min = Integer.MAX_VALUE;
						int max = Integer.MIN_VALUE;
						for (Integer l : lines) {
							if (l > max) max = l;
							if (l < min) min = l;
						}
						infoConns.add(new InfoConnection(min, max, conn, comp));
					}
				}
		}
	}

	private static boolean isSpecialConnection(IntegerComponent comp,
      IntegerPort p, int ioValue) {
	  return comp.isBoolean() && comp.isConditionalExpression() && ioValue == OUTPUT;
  }

  /**
	 * Return a set of input and output connections of a component.
	 * 
	 * @param components
	 *            the components.
	 * @param connsIn
	 *            the set of inputs.
	 * @param connsOut
	 *            the set of outputs.
	 */
	public static void getConnectionsOfTheComponents(
			final Set<IntegerComponent> components,
			final Set<InfoConnection> connsIn,
			final Set<InfoConnection> connsOut) {

		for (IntegerComponent comp : components) {
			Map<String, IntegerPort> inputPorts = comp.getInputPorts();
			Map<String, IntegerPort> outputPorts = comp.getOutputPorts();

			addConnections(comp, inputPorts.values(), connsIn, INPUT);
			addConnections(comp, outputPorts.values(), connsOut, OUTPUT);
		}
	}
}
