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
package br.com.wrpinheiro.jid.programdiagnosis.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * A class representing an integer unary operator.
 * 
 * @author wrp
 * 
 *         Sep 3, 2008
 */
public abstract class UnaryOperator extends IntegerComponentAdapter {

	/**
	 * The input port.
	 */
	private IntegerPort in;

	/**
	 * The output port.
	 */
	private IntegerPort out;

	/**
	 * Create an instance of the unary operator with a given name beloging to a
	 * given system.
	 * 
	 * @param system
	 *            the system
	 * @param name
	 *            the name
	 * @param line
	 *            the line
	 */
	public UnaryOperator(IntegerAbstractComponent system, String name, Set<Integer> lines, ComplementaryInfo info) {
		super(system, name, lines, info);

		in = new IntegerPort(name + "-IN", this);
		out = new IntegerPort(name + "-OUT", this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#clearValues(boolean)
	 */
	@Override
	public void clearValues() {
		this.in.clearValue();
		this.out.clearValue();
	}

	/**
	 * Get input port.
	 * 
	 * @return the input port.
	 */
	public IntegerPort getIn() {
		return in;
	}

	/**
	 * Get output port.
	 * 
	 * @return the output port.
	 */
	public IntegerPort getOut() {
		return out;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComplexity()
	 */
	@Override
	public Complexity getComplexity() {
		return Complexity.EASIEST;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isAbstract()
	 */
	@Override
	public boolean isAbstract() {
		return false;
	}
	
	@Override
	public Map<String, IntegerPort> getInputPorts() {
		Map<String, IntegerPort> ports = new LinkedHashMap<String, IntegerPort>();
		
		ports.put(in.getName(), in);
		
		return ports;
	}

	@Override
	public Map<String, IntegerPort> getOutputPorts() {
		Map<String, IntegerPort> ports = new LinkedHashMap<String, IntegerPort>();
		
		ports.put(out.getName(), out);
		
		return ports;
	}
}
