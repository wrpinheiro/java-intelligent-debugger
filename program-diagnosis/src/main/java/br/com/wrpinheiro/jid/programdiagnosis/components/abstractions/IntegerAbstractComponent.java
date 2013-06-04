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

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConstantConnection;

/**
 * Operation definition for abstract components.
 * 
 * @author wrp
 * 
 *         Oct 2, 2008
 */
public interface IntegerAbstractComponent extends IntegerComponent {
	/**
	 * A refined component can have its internal components checked for a faults.
	 */
	public void refine();

	/**
	 * Check if this abstract component was refined.
	 * 
	 * @return
	 */
	public boolean isRefined();

	/**
	 * Check if this component is an abstract component.
	 * 
	 * @return
	 */
	public boolean isAbstract();

	/**
	 * Add a new component in the system.
	 * 
	 * @param c
	 *          the new component.
	 */
	public void addComponent(final IntegerComponent c);

	/**
	 * Remove a component from the system.
	 * 
	 * @param component
	 *          the component to be removed.
	 */
	public void removeComponent(final IntegerComponent component);

	/**
	 * Set the constant values in the connections and points the affected
	 * components that must propagate values.
	 * 
	 * @throws ConflictException
	 */
	public void constantPropagation() throws ConflictException;

	/**
	 * Add a new integer connection in the system.
	 * 
	 * @param c
	 *          the new connection.
	 */
	public void addConnection(final IntegerConnection c);

	/**
	 * Get the system enclosing all other systems.
	 * 
	 * @return the topmost system.
	 */
	public IntegerSystem getTopMostSystem();

	/**
	 * Return a property of this system by the property name.
	 * 
	 * @param propName
	 *          the name of property
	 * @return a property.
	 */
	public Object getProperty(final String propName);

	/**
	 * Add a property in this system.
	 * 
	 * @param key
	 *          the name of the property.
	 * @param value
	 *          the value for the property.
	 */
	public void addProperty(final String key, final Object value);

	/**
	 * The number of components in this system.
	 * 
	 * @return the number of components.
	 */
	public int getNumberOfComponents();

	/**
	 * The number of connections in this system.
	 * 
	 * @return the number of connections.
	 */
	public int getNumberOfConnections();

	/**
	 * Get the integer connection named by c.
	 * 
	 * @param c
	 *          the name of the connection.
	 * @return the connection c.
	 */
	public IntegerConnection getConnection(final String c);

	/**
	 * Get the component named by c.
	 * 
	 * @param c
	 *          the name of the component.
	 * @return the component c.
	 */
	public IntegerComponent getComponent(final String c);

	/**
	 * Clear the values and enable all components of the system. The values of the
	 * connections are also cleared.
	 */
	//public void restart();

	/**
	 * Add a new component to the list of the components that must be used in
	 * propagation.
	 * 
	 * @param component
	 *          the component.
	 */
	public void addComponentToPropagationList(final IntegerComponent component);

	/**
	 * Get the connections with constant values.
	 * 
	 * @return the connection with constant value.
	 */
	public Set<IntegerConstantConnection> getConstantConnections();

	/**
	 * Restore the observed values in the connections.
	 * 
	 * @throws ConflictException
	 */
	public void reassignObservedValue() throws ConflictException;
}
