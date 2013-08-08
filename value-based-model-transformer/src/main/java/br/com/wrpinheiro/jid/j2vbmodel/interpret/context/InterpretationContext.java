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
package br.com.wrpinheiro.jid.j2vbmodel.interpret.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponentAdapter;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConstantConnection;

/**
 * The program context is a class responsible to maintain information about
 * systems, components and connections. This information can be used to create
 * new elements in the system.
 * 
 * @author wrp
 */
public class InterpretationContext {
	/**
	 * The system used by this context.
	 */
	private IntegerAbstractComponent system;

	/**
	 * Sequencial id generator for input connections.
	 */
	private Sequence inputConnectionId = new Sequence();
	
	/**
	 * Sequencial id generator for output connections.
	 */
	private Sequence outputConnectionId = new Sequence();
	
	/**
	 * Sequencial id generator for components.
	 */
	private Sequence componentId = new Sequence();

	private Map<String, List<IntegerConnection>> conns = new HashMap<String, List<IntegerConnection>>();
	private Map<String, IntegerConnection> constants = new HashMap<String, IntegerConnection>();

	/**
	 * For left side identifiers and the argument in the writeInt method.
	 */
	private Stack<String> identifiers = new Stack<String>();
	private Stack<IntegerComponent> components = new Stack<IntegerComponent>();
	private Stack<IntegerConnection> connections = new Stack<IntegerConnection>();

	/**
	 * Creates a context for a system.
	 * 
	 * @param system
	 *            the system.
	 */
	public InterpretationContext(final IntegerAbstractComponent system) {
		this.system = system;
	}

	/**
	 * Copy the values from ctx to this current context.
	 * @param ctx the source context.
	 */
	public void restartContextWith(final InterpretationContext ctx) {
		this.componentId = ctx.componentId;
		this.inputConnectionId = ctx.inputConnectionId;
		this.outputConnectionId = ctx.outputConnectionId;
	}

	/**
	 * Create a named connection.
	 * 
	 * @param name
	 *            the connection name.
	 * @return a named connection.
	 */
	private IntegerConnection createNamedConnection(final String name, final String simpleName) {
		return new IntegerConnection(getSystem(), name, simpleName);
	}

	public IntegerAbstractComponent getSystem() {
		return this.system;
	}

	public String getNextComponentId() {
	  String name = "C" + IntegerComponentAdapter.getComponents().size();
		//String name = "C" + componentId.getNextValue();

		return name;
	}

	public IntegerComponent pushComponent(final IntegerComponent c) {
		return this.components.push(c);
	}

	public IntegerComponent popComponent() {
		return this.components.pop();
	}

	public IntegerConnection pushConnection(final IntegerConnection c) {
		return this.connections.push(c);
	}

	public IntegerConnection popConnection() {
		return this.connections.pop();
	}

	public String popIdentifier() {
		return this.identifiers.pop();
	}

	public void pushIdentifier(final String identifier) {
		this.identifiers.push(identifier);
	}

	/**
	 * Create a connection identified by the concatenation of id and index. The
	 * index can be a component number identification or any arbitrary string.
	 * As a connection represents a value for a variable in a certain time t_i,
	 * there will be as many connections as the number of times that a variable
	 * has a new value assigned. The current value for a variable can be
	 * obtained by acessing the last stored connection in the list of the
	 * variable
	 *
	 * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext#getCurrentConnectionFor(String)
	 *
	 * @param id the id of the connection.
	 * @param index the reference index of the connection.
	 * @return the new connection.
	 */
	public IntegerConnection createConnection(final String id,
			final String index) {
		String fullId = id + index;

		List<IntegerConnection> listConn = this.conns.get(id);

		if (listConn == null) {
			listConn = new ArrayList<IntegerConnection>();
			this.conns.put(id, listConn);
		}

		IntegerConnection conn = new IntegerConnection(getSystem(), fullId, id);
		listConn.add(conn);

		return conn;
	}

	/**
	 * Check if a variable has a previously defined value. 
	 * @param varName the variable.
	 * @return TRUE if varName has a value or FALSE otherwise.
	 */
	public boolean hasCurrentConnection(final String varName) {
		List<IntegerConnection> listConn = this.conns.get(varName);
		return listConn != null;
	}

	/**
	 * Get the current connection for a variable. The current connection is related to the last value
	 * assigned to a variable until now.
	 * @param varName the variable.
	 * @return the connection representing the last value assigned to a variable.
	 */
	public IntegerConnection getCurrentConnectionFor(final String varName) {
		List<IntegerConnection> listConn = this.conns.get(varName);

		if (listConn == null) {
			throw new RuntimeException("Variable " + varName
					+ " does not have a current connection.");
		}
		return listConn.get(listConn.size() - 1);
	}

	public IntegerConnection getConstantConnectionFor(final String literal) {
		IntegerConnection conn = this.constants.get(literal);
		if (conn == null) {
			Integer value = Integer.valueOf(literal);
			conn = new IntegerConstantConnection(system, literal.toString(),
					value);
			this.constants.put(literal, conn);
		}

		return conn;
	}

	/**
	 * Create a new input connection. Input connections do not need to be stored
	 * in a local map.
	 * 
	 * @return a new input connection.
	 */
	public IntegerConnection createInputConnection() {
		String connName = "input" + inputConnectionId.getNextValue();
		return this.createNamedConnection(connName, "input");
	}

	/**
	 * Create a new input connection. Input connections do not need to be stored
	 * in a local map.
	 * 
	 * @return a new input connection.
	 */
	public IntegerConnection createOutputConnection() {
		String connName = "output" + outputConnectionId.getNextValue();
		return this.createNamedConnection(connName, "output");
	}

	/**
	 * Create an auxiliary connection.
	 * 
	 * @return a new auxiliary connection.
	 */
	public IntegerConnection createAuxiliaryConnection(final String index) {
		String connName = "aux" + index;
		return this.createNamedConnection(connName, "aux");
	}
}
