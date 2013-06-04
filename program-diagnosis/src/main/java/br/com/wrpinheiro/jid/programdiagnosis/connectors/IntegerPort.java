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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.ConnectionException;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

/**
 * An abstract implementation for the Port interface.
 * 
 * @author wrp
 * 
 *         29/03/2008
 */
public class IntegerPort implements DependencyTrace {
	private static final Logger LOG = Logger.getLogger(IntegerPort.class);
	
	/**
	 * A list of instantiated port.
	 */
	private static List<IntegerPort> ports = new ArrayList<IntegerPort>();

	/**
	 * Add a new instance to list of instantiated ports.
	 * @param c
	 */
	private static void addInstance(IntegerPort c) {
		ports.add(c);
	}

	/**
	 * Clear all the ports values.
	 */
	public static void clearAllPortsValues() {
		for (IntegerPort port : IntegerPort.ports) {
			port.clearValue();
		}
	}
	
	/**
	 * Clear the port list. This operation is required every time that the
	 * program parser starts.
	 */
	public static void clearPortList() {
		IntegerPort.ports.clear();
	}
	
	/**
	 * A counter used to give an id for each port.
	 */
	private static int count = 0;

	/**
	 * The port ID.
	 */
	private int id;

	/**
	 * Name of this port.
	 */
	private String name;

	/**
	 * Owner component of this port.
	 */
	private IntegerComponent owner;

	/**
	 * The connection with this port. A connection can maintain many ports
	 * connected.
	 */
	private Set<IntegerConnection> connection = new LinkedHashSet<IntegerConnection>();

	/**
	 * The current value of this port.
	 */
	private Integer value;

	/**
	 * Set of components used to infer the value of this connection.
	 */
	private Set<IntegerComponent> dependencies = new HashSet<IntegerComponent>();

	/**
	 * Temporary set of components used to infer the value of this connection.
	 * Assumptions are discarded or used as dependencies.
	 */
	private Set<IntegerComponent> assumptions = new HashSet<IntegerComponent>();
	
	private boolean isBool;

	/**
	 * Creates a DefaultPort instance and set the owner component and a name.
	 * 
	 * @param system
	 *            the sytem of this port.
	 * 
	 * @param name
	 *            the name of the port.
	 * @param owner
	 *            the owner component of the port.
	 */
	public IntegerPort(String name, IntegerComponent owner) {
		LOG.debug("creating port " + name + " in " + owner);

		IntegerPort.addInstance(this);
		
		this.id = count++;

		this.name = name;
		this.owner = owner;
	}

	/**
	 * Get the current value of this port.
	 * 
	 * @return the current value of this port.
	 */
	public Integer getValue() {
		return this.value;
	}

	/**
	 * Set a value in this port.
	 * 
	 * @param value
	 *            the value for this port.
	 * @throws ConflictException
	 *             exception thrown to indicate that this port has a different
	 *             value from the argument value.
	 */
	public void setValue(Integer value, boolean addToPropagationList)
			throws ConflictException {

		if (this.getValue() == null) {
			if (this.getOwner() != null && this.getOwner().isEnabled()
					&& addToPropagationList)
				this.getOwner().addComponentToPropagationList(); 

			//this.system.addComponentToPropagationList(this.getOwner());

			this.acceptAssumptions();

			LOG.debug("[" + this + "] = " + value + "; Dep = "
					+ this.getDependencies());

			this.value = value;
			sendValueThroughConnection();
		} else {
			if (this.getValue().equals(value)) {
				this.rejectAssumptions();
			} else {
				StringBuilder sb = new StringBuilder(
						"The value expected at port: ");
				sb.append(this.getName());
				if (this.getOwner() != null)
					sb.append(" (component: " + this.getName() + ") ");
				sb.append(" is: ").append(value).append(
						", but its current value is: ");
				sb.append(this.getValue());

				LOG.debug(sb.toString());

				Set<IntegerComponent> conflictSet = new HashSet<IntegerComponent>();
				conflictSet.addAll(this.getAssumptions());
				conflictSet.addAll(this.getDependencies());

				throw new ConflictException(sb.toString(), value, conflictSet);
			}
		}
	}

	/**
	 * Send the current value of this port through the connection.
	 */
	public void sendValueThroughConnection() throws ConflictException {
		for (IntegerConnection conn : this.connection)
			conn.propagateFrom(this);
	}

	/**
	 * Add a new connection to this port.
	 * 
	 * @param conn
	 *            the new connection.
	 */
	public void addConnection(IntegerConnection conn)
			throws ConnectionException {
		if (this.connection.contains(conn))
			throw new ConnectionException("The port " + this.getName()
					+ " already contains the connection " + conn.getName());

		this.connection.add(conn);
	}

	/**
	 * Get the owner component of this port.
	 * 
	 * @return the owner component.
	 */
	public IntegerComponent getOwner() {
		return this.owner;
	}

	/**
	 * Get the name of this port.
	 * 
	 * @return the name of this port.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getName();
	}

	/**
	 * @see br.usp.ime.programdiagnostician.model.ports.Port#clearValue()
	 */
	public void clearValue() {
		if (this.value != null) {
			// LOG.debug("Cleaning port: " + this.getName());
			this.value = null;
		}
		clearDependencies();
	}

	/**
	 * Get a set of connected components related to this port.
	 * 
	 * @return a set of components.
	 */
	public Set<IntegerComponent> getConnectedComponents() {
		Set<IntegerComponent> connectedComps = new HashSet<IntegerComponent>();

		if (this.getOwner() != null)
			connectedComps.add(this.getOwner());

		for (IntegerConnection conn : this.connection) {
			for (IntegerPort intPort : conn.getConnectedPorts()) {
				if (!intPort.equals(this) && intPort.getOwner() != null) {
					connectedComps.add(intPort.getOwner());
				}
			}
		}

		return connectedComps;
	}

	/**
	 * Get the connection of this port.
	 * 
	 * @return the connection.
	 */
	public Set<IntegerConnection> getConnections() {
		return this.connection;
	}

	/**
	 * Check if this port has a value or not.
	 * 
	 * @return true if the value of the port is different of null or false,
	 *         otherwise.
	 */
	public boolean hasValue() {
		return this.value != null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#addAssumption(java.util.Set)
	 */
	@Override
	public void addAssumption(Set<IntegerComponent> assumptions) {
		this.assumptions.addAll(assumptions);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#addAssumption(br.com.jdebugger.programdiagnosis.components.IntegerComponent)
	 */
	@Override
	public void addAssumption(final IntegerComponent component) {
		IntegerComponent abs = component.getTheMostAbstractParentNotRefined();
		if (abs != null)
			this.assumptions.add(abs);
		else this.assumptions.add(component);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#rejectAssumptions()
	 */
	@Override
	public void rejectAssumptions() {
		this.assumptions.clear();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#acceptAssumptions()
	 */
	@Override
	public void acceptAssumptions() {
		this.dependencies.addAll(assumptions);
		this.assumptions.clear();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#getAssumptions()
	 */
	@Override
	public Set<IntegerComponent> getAssumptions() {
		return Collections.unmodifiableSet(this.assumptions);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#getDependencies()
	 */
	@Override
	public Set<IntegerComponent> getDependencies() {
		return Collections.unmodifiableSet(this.dependencies);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.connectors.DependencyTrace#clearDependencies()
	 */
	@Override
	public void clearDependencies() {
		this.dependencies.clear();
		this.assumptions.clear();
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
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntegerPort other = (IntegerPort) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Disconnect this port from the connections.
	 */
	public void disconnect() {
		for (IntegerConnection conn : this.connection)
			conn.removePort(this);
	}

  public void setIsBoolean(boolean isBool) {
    this.isBool = isBool;
  }

  public boolean isBoolean() {
    return isBool;
  }
}
