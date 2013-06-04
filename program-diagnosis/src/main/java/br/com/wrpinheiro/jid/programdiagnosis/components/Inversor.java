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

import static br.com.wrpinheiro.jid.programdiagnosis.components.BooleanUtils.ONE;
import static br.com.wrpinheiro.jid.programdiagnosis.components.BooleanUtils.ZERO;

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * An inversor component.
 * 
 * @author wrp
 * 
 *         Sep 3, 2008
 */
public class Inversor extends UnaryOperator {

	/**
	 * Creates an instance of the inversor with a given name in a given system.
	 * 
	 * @param system
	 *            the system
	 * @param name
	 *            the name
	 */
	public Inversor(final IntegerAbstractComponent system, final String name,
      final Set<Integer> lines, ComplementaryInfo info) {
	  super(system, name, lines, info);
	}

	public Inversor(IntegerAbstractComponent system, String name, int line,
      ComplementaryInfo info) {
		this(system, name, DefaultSets.toSet(line), info);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.components.IntegerComponentAdapter#internalPropagate()
	 */
	@Override
	public void internalPropagate() throws ConflictException {
		IntegerPort in = super.getIn();
		IntegerPort out = super.getOut();

		if (in.hasValue()) {
			if (in.getValue().equals(ONE)) {
				out.addAssumption(this);
				out.addAssumption(in.getDependencies());
				out.setValue(ZERO, false);
			} else if (in.getValue().equals(ZERO)) {
				out.addAssumption(this);
				out.addAssumption(in.getDependencies());
				out.setValue(ONE, false);
			}
		} else if (out.hasValue()) {
			if (out.getValue().equals(ONE)) {
				in.addAssumption(this);
				in.addAssumption(out.getDependencies());
				in.setValue(ZERO, false);
			} else if (out.getValue().equals(ZERO)) {
				in.addAssumption(this);
				in.addAssumption(out.getDependencies());
				in.setValue(ONE, false);
			}
		}
	}

  @Override
  public String getComponentName() {
    return "Inversor " + formatInfo();
  }
  
  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isBoolean()
   */
  @Override
  public boolean isBoolean() {
    return true;
  }
}
