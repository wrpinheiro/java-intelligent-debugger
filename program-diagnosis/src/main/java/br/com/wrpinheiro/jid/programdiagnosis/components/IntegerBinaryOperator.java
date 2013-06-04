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

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * An abstract component definition for binary operations.
 * 
 * @author wrp
 * 
 *         30/03/2008
 */
public abstract class IntegerBinaryOperator extends IntegerComponentAdapter {
	/**
	 * Constant definition for output port.
	 */
	public static final int RESULT = 3;

	/**
	 * The first operand.
	 */
	private IntegerPort in1;

	/**
	 * The second operand.
	 */
	private IntegerPort in2;

	/**
	 * The result.
	 */
	private IntegerPort result;

	/**
	 * Creates an instance of BinaryOperator setting its name and a type class.
	 * 
	 * @param name
	 *            the name of the BinaryOperator.
	 */
	public IntegerBinaryOperator(IntegerAbstractComponent system, String name,
			Set<Integer> lines, ComplementaryInfo info) {
		super(system, name, lines, info);
		in1 = new IntegerPort(name + "-IN1", this);
		in2 = new IntegerPort(name + "-IN2", this);
		result = new IntegerPort(name + "-RESULT", this);
	}

	/**
	 * Get the 1st input port.
	 * 
	 * @return the inputPort1
	 */
	public IntegerPort getIn1() {
		return in1;
	}

	/**
	 * Get the 2nd input port.
	 * 
	 * @return the inputPort2
	 */
	public IntegerPort getIn2() {
		return in2;
	}

	/**
	 * Get the result port.
	 * 
	 * @return the result port
	 */
	public IntegerPort getResult() {
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponentAdapter#internalPropagate()
	 */
	@Override
	public void internalPropagate() throws ConflictException {
		IntegerPort in1, in2, result;

		if (!this.isEnabled())
			return;

		in1 = this.getIn1();
		in2 = this.getIn2();
		result = this.getResult();

		if (in1.hasValue() && in2.hasValue()) {
			this.propagateForward(in1, in2, result);
		} else if (result.hasValue() && (in1.hasValue() || in2.hasValue())) {
			if (in1.hasValue()) {
				this.propagateBackward(in1, in2, result, BooleanUtils.IN1);
			} else if (in2.hasValue()) {
				this.propagateBackward(in1, in2, result, BooleanUtils.IN2);
			}
		}
		// else does nothing! The component has no sufficient values to
		// propagate.
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.usp.ime.programdiagnostician.model.components.Component#clearValues()
	 */
	@Override
	public void clearValues() {
		this.in1.clearValue();
		this.in2.clearValue();
		this.result.clearValue();
	}

	/**
	 * Propagates to output (result) the values in input 1 and 2.
	 * 
	 * @param in1
	 *            the first input port.
	 * @param in2
	 *            the second input port.
	 * @param result
	 *            the output port
	 */
	public abstract void propagateForward(IntegerPort in1, IntegerPort in2,
			IntegerPort result) throws ConflictException;

	/**
	 * Propagates backward input (it might be input one or two) the values in
	 * the result port and port one or two.
	 * 
	 * @param in1
	 *            the first input port.
	 * @param in2
	 *            the second input port.
	 * 
	 * @param result
	 *            the output port
	 * 
	 * @param port
	 *            used to identify the port in. The constant IN1 must be used
	 *            when in = inputPort1 and the constant IN2 must be used when in
	 *            = inputPort2.
	 */
	public abstract void propagateBackward(IntegerPort in1, IntegerPort in2,
			IntegerPort result, int port) throws ConflictException;

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComplexity()
	 */
	@Override
	public Complexity getComplexity() {
		return Complexity.EASY;
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
		
		ports.put(in1.getName(), in1);
		ports.put(in2.getName(), in2);
		
		return ports;
	}

	@Override
	public Map<String, IntegerPort> getOutputPorts() {
		Map<String, IntegerPort> ports = new LinkedHashMap<String, IntegerPort>();
		
		ports.put(result.getName(), result);
		
		return ports;
	}
	
	
}
