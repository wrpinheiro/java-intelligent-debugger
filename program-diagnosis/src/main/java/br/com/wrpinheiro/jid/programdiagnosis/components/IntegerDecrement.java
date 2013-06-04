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
 * A class representing an integer increment (sth. like i++ or ++i).
 * 
 * @author wrp
 * 
 *         Jul 11, 2009
 */
public class IntegerDecrement extends UnaryOperator {

	/**
	 * Create an instance of the postfix increment.
	 * 
	 * @param system
	 *            the system.
	 * @param name
	 *            the name of this component.
	 * @param line
	 *            the program line of this component.
	 */
	public IntegerDecrement(IntegerAbstractComponent system, String name, Set<Integer> lines, ComplementaryInfo info) {
		super(system, name, lines, info);
	}

	public IntegerDecrement(IntegerAbstractComponent system, String name,
			int line, ComplementaryInfo info) {
		this(system, name, DefaultSets.toSet(line), info);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#internalPropagate()
	 */
	@Override
	public void internalPropagate() throws ConflictException {
		IntegerPort in = super.getIn();
		IntegerPort out = super.getOut();

		if (in.getValue() != null) {
			out.addAssumption(this);
			out.addAssumption(in.getDependencies());
			out.setValue(in.getValue() - 1, false);
		} else if (out.getValue() != null) {
			in.addAssumption(this);
			in.addAssumption(out.getDependencies());
			in.setValue(out.getValue() + 1, false);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComponentName()
	 */
  @Override
  public String getComponentName() {
    return "Op. Decremento " + formatInfo();
  }
}