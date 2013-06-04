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
package br.com.wrpinheiro.jid.programdiagnosis.components.abstractions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponentAdapter;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConstantConnection;
import br.com.wrpinheiro.jid.programdiagnosis.statistics.DiagnosisStatistics;

public abstract class AbstractComponent extends IntegerComponentAdapter implements IntegerAbstractComponent {

	/**
	 * A list of instantiated abstract components.
	 */
	private static List<AbstractComponent> abstractComponents = new ArrayList<AbstractComponent>();

	/**
	 * Add a new instance to list of instantiated abstract components.
	 * @param c
	 */
	private static void addInstance(AbstractComponent c) {
		abstractComponents.add(c);
	}

	/**
	 * Clear the propagation list of every abstract component.
	 */
	public static void clearAllComponentsPropagationList() {
		for (AbstractComponent abs : AbstractComponent.abstractComponents) {
			abs.propagationList.clear();
		}
	}

	/**
	 * Log instance.
	 */
	private static final Logger LOG = Logger.getLogger(AbstractComponent.class);

	/**
	 * Flag indicating that this component is performing a cleaning. This flag
	 * is used to avoid recurrent calls.
	 */
	private boolean cleaning;

	/**
	 * Map of components in the system.
	 */
	private Map<String, IntegerComponent> components = new HashMap<String, IntegerComponent>();
	
	/**
	 * Map of integer connections in the system.
	 */
	private Map<String, IntegerConnection> connections = new HashMap<String, IntegerConnection>();

	/**
	 * Map of constant connections in the system.
	 */
	private Set<IntegerConstantConnection> constantConnections = new HashSet<IntegerConstantConnection>();

	/**
	 * Queue of components used during the search for the conflict set.
	 */
	private TreeSet<IntegerComponent> propagationList = new TreeSet<IntegerComponent>(
			new Comparator<IntegerComponent>() {

				@Override
				public int compare(IntegerComponent c0, IntegerComponent c1) {
					if (c0.equals(c1))
						return 0;

					int c0Compl = c0.getComplexity().getComplexity();
					int c1Compl = c1.getComplexity().getComplexity();

					if (c0Compl == c1Compl)
						return 1;

					return c0Compl - c1Compl;
				}
			});

	/**
	 * Properties of this system.
	 */
	private Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * If a component is refined, its internal components can be checked for
	 * faults. All abstract components must start as ... abstract! That is, its
	 * default state is not refined.
	 */
	private boolean refined = false;
	
	/**
	 * The statistics maintainer.
	 */
	private DiagnosisStatistics statistics = DiagnosisStatistics.getInstance();

	public AbstractComponent(final IntegerAbstractComponent parent, final String name,
			final Set<Integer> lines, ComplementaryInfo info) {
		super(parent, name, lines, info);
		
		AbstractComponent.addInstance(this);
	}
	
	/**
	 * Set the constant values in the connections and points the affected
	 * components that must propagate values.
	 * 
	 * @throws ConflictException
	 */
	public void constantPropagation() throws ConflictException {
		if (getParent() == null) {
			for (IntegerConstantConnection conn : this.constantConnections) {
				conn.propagateConstantValue();
			}
		} else getParent().constantPropagation();
	}

	
	/**
	 * Search for a conflict set in this system.
	 * 
	 * @param comps
	 *            a starting list of components.
	 * @throws ConflictException
	 */
	@Override
	protected void internalPropagate() throws ConflictException {

		statistics.addCspCalls();

		IntegerComponent currentComp;

		this.constantPropagation();

		while (!propagationList.isEmpty()) {
			// currentComp = propagationList.remove(0);
			currentComp = propagationList.pollFirst();

			while (propagationList.contains(currentComp)) {
				// LOG.debug("Removing spurious component in the propagation: "
				// + currentComp);
				propagationList.remove(currentComp);
			}

			statistics.addInferences();

			LOG.debug("propagating through: " + currentComp);
			currentComp.propagate();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isAbstract()
	 */
	@Override
	public boolean isAbstract() {
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#isRefined()
	 */
	@Override
	public boolean isRefined() {
		return this.refined;
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#refine()
	 */
	@Override
	public void refine() {
		this.refined = true;
	}

	/**
	 * Clear the values of all components and connections of the system,
	 * optionally enabling the components.
	 * 
	 * @param forneEnable
	 *            if true all the components of the system are enabled. If
	 *            false, the state of the components are not changed.
	 */
	
	public void clearValues() {
		if (this.isCleaning())
			return;
		startCleaning();

		this.propagationList.clear();

		for (IntegerComponent c : this.components.values()) {
			c.clearValues();
		}

		for (IntegerConnection c : this.connections.values())
			c.clearValue();

		finishCleaning();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComplexity()
	 */
	@Override
	public Complexity getComplexity() {
		return Complexity.HIGHEST;
	}
	
	/**
	 * Signals received when the cleaning process has finished.
	 */
	public void finishCleaning() {
		this.cleaning = false;
	}

	/**
	 * Check if cleaning is ocurring. This method must be checked before the
	 * cleaning process starts.
	 * 
	 * @return
	 */
	public boolean isCleaning() {
		return this.cleaning;
	}

	/**
	 * Signals received when the cleaning process starts.
	 */
	public void startCleaning() {
		this.cleaning = true;
	}


	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#addComponent(br.com.jdebugger.programdiagnosis.components.IntegerComponent)
	 */
	@Override
	public void addComponent(final IntegerComponent c) {
		// TODO verificar situacoes em que esse tipo de problema ocorre.
		// if (c.equals(this))
		// throw new RuntimeException("A component cannot be itself owner.");
		this.components.put(c.getName(), c);
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#removeComponent(br.com.jdebugger.programdiagnosis.components.IntegerComponent)
	 */
	@Override
	public void removeComponent(IntegerComponent component) {
		this.components.remove(component.getName());		
	}


	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#addConnection(br.com.jdebugger.programdiagnosis.connectors.IntegerConnection)
	 */
	@Override
	public void addConnection(final IntegerConnection c) {
		// TODO verificar se eh realmente necessario adicionar uma conexao
		// constante em this.connections. Acho que nao!!!
		this.connections.put(c.getName(), c);

		if (c instanceof IntegerConstantConnection) {
			this.constantConnections.add((IntegerConstantConnection) c);
		}

		if (getParent() != null) {
			getParent().addConnection(c);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getTopMostSystem()
	 */
	@Override
	public IntegerSystem getTopMostSystem() {
		if (getParent() == null)
			return (IntegerSystem) this;

		return getParent().getTopMostSystem();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getProperty(java.lang.String)
	 */
	@Override
	public Object getProperty(String propName) {
		return this.properties.get(propName);
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#addProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addProperty(String key, Object value) {
		this.properties.put(key, value);
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getNumberOfComponents()
	 */
	@Override
	public int getNumberOfComponents() {
		return this.components.size();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getNumberOfConnections()
	 */
	@Override
	public int getNumberOfConnections() {
		return this.connections.size();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#addComponentToPropagationList(br.com.jdebugger.programdiagnosis.components.IntegerComponent)
	 */
	@Override
	public void addComponentToPropagationList(final IntegerComponent component) {
		this.propagationList.add(component);		
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getComponent(java.lang.String)
	 */
	@Override
	public IntegerComponent getComponent(final String c) {
		return this.components.get(c);
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getConnection(java.lang.String)
	 */
	@Override
	public IntegerConnection getConnection(String c) {
		return this.connections.get(c);
	}


	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#getConstantConnections()
	 */
	@Override
	public Set<IntegerConstantConnection> getConstantConnections() {
		return Collections.unmodifiableSet(this.constantConnections);
	}


	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.abstractions.IntegerAbstractComponent#reassignObservedValue()
	 */
	@Override
	public void reassignObservedValue() throws ConflictException {
		for (IntegerConnection conn : this.connections.values()) {
			if (conn.isObservable())
				conn.restoreObservableValue();
		}
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponentAdapter#getProgramLines()
	 */
	@Override
	public Set<Integer> getProgramLines() {
		Set<Integer> lines = new LinkedHashSet<Integer>();
		
		if (this.components != null && this.components.size() > 0) {
			Collection<IntegerComponent> comps = components.values();

			for (IntegerComponent comp : comps)
				lines.addAll(comp.getProgramLines());
		}

		return lines;
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponentAdapter#setProgramLines(java.util.Set)
	 */
	@Override
	public void setProgramLines(final Set<Integer> line) {
		// abstract components can't have their lines set!
	}
}
