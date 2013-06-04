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

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * An integer divisor component.
 * 
 * This component executes an integer division of two values. Every operand has
 * as input port associated and the result is stored in an output port.
 * 
 * @author wrp
 * 
 *         Sep 3, 2008
 */
public class IntegerDivisor extends IntegerBinaryOperator {

	/**
	 * 
	 * @param system the system for this component.
	 * @param name the name of the component.
	 * @param line the line of the sentence used to create this component.
	 */
	public IntegerDivisor(IntegerAbstractComponent system, String name, Set<Integer> lines, ComplementaryInfo info) {
		super(system, name, lines, info);
	}

	public IntegerDivisor(IntegerAbstractComponent system, String name, int line, ComplementaryInfo info) {
		this(system, name, DefaultSets.toSet(line), info);
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#
	 * 		propagateBackward(br.com.jdebugger.programdiagnosis.connectors.IntegerPort, 
	 * 			br.com.jdebugger.programdiagnosis.connectors.IntegerPort, 
	 * 			br.com.jdebugger.programdiagnosis.connectors.IntegerPort, 
	 * 			int)
	 */
	public void propagateBackward(IntegerPort in1, IntegerPort in2,
			IntegerPort out, int port) throws ConflictException {

		Integer value;

		if (port == BooleanUtils.IN1) {
		  //if (!out.getValue().equals(0)) {
        value = in1.getValue() / out.getValue();

        in2.addAssumption(this);
        in2.addAssumption(in1.getDependencies());
        in2.addAssumption(out.getDependencies());
        in2.setValue(value, false);
		  //}
		} else if (port == BooleanUtils.IN2) {
			value = in2.getValue() * out.getValue();

			in1.addAssumption(this);
			in1.addAssumption(in1.getDependencies());
			in1.addAssumption(out.getDependencies());
			in1.setValue(value, false);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.IntegerAbstractBinaryOperator.usp.br.diagnostician.components.IntegerBinaryOperator#propagateForward
	 *      (br.ime.usp.br.diagnostician.connectors.IntegerPort,
	 *      br.ime.usp.br.diagnostician.connectors.IntegerPort,
	 *      br.ime.usp.br.diagnostician.connectors.IntegerPort)
	 */
	@Override
	public void propagateForward(IntegerPort in1, IntegerPort in2,
			IntegerPort out) throws ConflictException {

		Integer n1 = in1.getValue();
		Integer n2 = in2.getValue();
		Integer value = new Integer(n1 / n2);

		out.addAssumption(this);
		out.addAssumption(in1.getDependencies());
		out.addAssumption(in2.getDependencies());
		out.setValue(value, false);
	}

  @Override
  public String getComponentName() {
    return "Divisão " + formatInfo();
  }
}