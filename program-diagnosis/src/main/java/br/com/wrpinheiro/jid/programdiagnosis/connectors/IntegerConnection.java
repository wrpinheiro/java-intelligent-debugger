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
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.Conditional;

/**
 * A connection between ports.
 * 
 * @author wrp
 * 
 *         29/03/2008
 */
public class IntegerConnection {

  /**
   * A list of instantiated connections.
   */
  private static List<IntegerConnection> connections = new ArrayList<IntegerConnection>();

  /**
   * Add a new instance to list of instantiated connections.
   * 
   * @param c
   */
  private static void addInstance(IntegerConnection c) {
    connections.add(c);
  }

  /**
   * Clear all the connections values.
   */
  public static void clearAllConnectionsValues() {
    for (IntegerConnection conn : IntegerConnection.connections) {
      conn.clearValue();
      conn.valuesSet.clear();
    }
  }

  /**
   * Clear the connection list. This operation is required every time that the
   * program parser starts.
   */
  public static void clearConnectionList() {
    IntegerConnection.connections.clear();
  }

  private static final Logger LOG = Logger.getLogger(IntegerConnection.class);
  /**
   * A counter used to give an id for each connection.
   */
  private static int count = 0;

  /**
   * The connection ID.
   */
  private int id;

  /**
   * The set of connected ports by this connection.
   */
  private Set<IntegerPort> connectedPorts;

  /**
   * The value of this connection.
   */
  private Integer value;

  /**
   * A list of values that have been set in this connection since the last call
   * to clearAllConnectionsValues.
   */
  private Set<Integer> valuesSet = new LinkedHashSet<Integer>();

  /**
   * The name of this connection. The connection name must be unique in the
   * application.
   */
  private String name;

  /**
   * A simple name is used to "humanly" identify this connection.
   */
  private String simpleName;

  private boolean isBool;

  /**
   * Set of componentes that derived the value in this port.
   */
  private Set<IntegerComponent> dependencies = new HashSet<IntegerComponent>();

  /**
   * Informs that the value on this connection was observed. An observed value
   * cannot be changed. To change the value you must call
   * IntegerConnection#setObservableValue.
   */
  private boolean observable;

  /**
   * The observed value for connections with an observable value.
   */
  private Integer observableValue;
  
  /**
   * Inform if this variable can have an informed value.
   */
  private boolean assignable = true;

  /**
   * Creates a named connector.
   * 
   * @param name
   *          the name of this port.
   */
  public IntegerConnection(IntegerAbstractComponent system, String name,
      final String simpleName) {
    LOG.debug("creating connection " + name + " in " + system);

    IntegerConnection.addInstance(this);

    this.id = count++;
    this.simpleName = simpleName;
    this.name = name;
    this.connectedPorts = new LinkedHashSet<IntegerPort>();

    system.addConnection(this);
  }

  /**
   * Add a new port to this connection. All ports in a connection must have the
   * same value.
   * 
   * @param port
   *          the new port in this connection.
   * @return true if the port was added to the connection or false if the has
   *         already been in the connection.
   */
  public IntegerConnection addPort(IntegerPort port) {
    LOG.debug("connecting [" + port + "] to " + this.getName());

    if (port == null)
      throw new RuntimeException("Cannot connect to a null port.");

    if (this.connectedPorts.contains(port)) {
      LOG.debug("port [" + port + "] is already in connection "
          + this.getName());
      return this;
    }

    try {
      port.addConnection(this);
      this.connectedPorts.add(port);
    } catch (ConnectionException cex) {
      throw new RuntimeException(cex);
    }

    return this;
  }

  /**
   * Get the list of connected ports.
   * 
   * @return the list of connected ports.
   */
  public Set<IntegerPort> getConnectedPorts() {
    return Collections.unmodifiableSet(this.connectedPorts);
  }

  /**
   * Propagates the value from port to the other connected ports.
   * 
   * @param port
   *          the port whose value was given.
   */
  public void propagateFrom(IntegerPort port) throws ConflictException {
    // TODO se for gerada uma ConflictException na hora que o valor for
    // atribuído
    // à porta, como fazer para revogar as dependências da porta e da
    // conexão?

    this.addAllDependencies(port.getDependencies());
    this.value = port.getValue();
    this.valuesSet.add(value);

    Integer value = port.getValue();

    for (IntegerPort intP : this.connectedPorts) {
      if (!intP.equals(port)) {
        intP.addAssumption(port.getDependencies());
        intP.setValue(value, true);
      }
    }
  }

  /**
   * Set the dependencies of this connection as being the same of the port that
   * is delivering the value to this connection.
   * 
   * @param dependencies
   *          a set of components.
   */
  private void addAllDependencies(Set<IntegerComponent> dependencies) {
    this.dependencies.addAll(dependencies);
  }

  /**
   * Set a value for this connection.
   * 
   * @param value
   *          the new value for the connection.
   * @throws ConflictException
   *           this exception should never happen.
   */
  public void setValue(Integer value) throws ConflictException {
    this.value = value;
    this.valuesSet.add(value);

    for (IntegerPort intP : this.connectedPorts) {
      intP.setValue(value, true);
    }
  }

  /**
   * Get the name of the connection.
   * 
   * @return the name of the connection.
   */
  public String getName() {
    return name;
  }

  /**
   * Return the simple name for this connection.
   * 
   * @return the simple name.
   */
  public String getSimpleName() {
    return this.simpleName;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  /**
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
    final IntegerConnection other = (IntegerConnection) obj;
    if (id != other.id)
      return false;
    return true;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.getName();
  }

  /**
   * Clear the value of this connection.
   */
  public void clearValue() {
    if (this.value != null) {
      this.value = null;
    }
    this.dependencies.clear();
  }

  /**
   * Get the value of this connection.
   * 
   * @return the value of the connection.
   */
  public Integer getValue() {
    return this.value;
  }

  /**
   * Get the list of dependencies (components) of this connection.
   * 
   * @return
   */
  public Set<IntegerComponent> getDependencies() {
    return Collections.unmodifiableSet(this.dependencies);
  }

  /**
   * Remove a port from this connection.
   * 
   * @param port
   *          the port that will be disconnected.
   */
  public void removePort(IntegerPort port) {
    this.connectedPorts.remove(port);
  }

  /**
   * Set the observable flag of this connection.
   * 
   * @param observable
   *          TRUE indicate that the value was observed and FALSE otherwise.
   * @throws ConflictException
   */
  public void setObservable(final boolean observable) throws ConflictException {
    this.observable = observable;

    this.observableValue = this.observable ? this.value : null;
  }

  /**
   * Check if this connection has an observable value.
   * 
   * @return TRUE if the connection has an observable value and FALSE ,
   *         otherwise.
   */
  public boolean isObservable() {
    return this.observable;
  }

  /**
   * Restore the observed value to the connection.
   * 
   * @throws ConflictException
   *           exception thrown when a conflict between values is detected.
   */
  public void restoreObservableValue() throws ConflictException {
    if (this.observable)
      this.setValue(this.observableValue);
  }

  /**
   * Get the list of values set in this connection since the last call to
   * clearAllConnectionsValues.
   * 
   * @return
   */
  public Set<Integer> getValuesSet() {
    return this.valuesSet;
  }

  public boolean isBoolean() {
    return this.isBool;
  }

  public void setIsBoolean(boolean b) {
    this.isBool = b;
  }

  public boolean isConnectedToCondResult() {
    for (IntegerPort p : this.getConnectedPorts()) {
      if (p.getName().indexOf(Conditional.PORT_COND_RESULT) > -1) {
        return true;
      }
    }
    return false;
  }

  public void setAssignable(final boolean b) {
    this.assignable = b;
  }
  
  public boolean isAssignable() {
    return assignable;
  }
}
