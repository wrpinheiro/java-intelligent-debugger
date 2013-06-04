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
 * A multiplier component.
 * 
 * This component executes a multiplication of two values. Every operand has as
 * input port associated and the result is stored in an output port.
 * 
 * @author wrp
 * 
 *         29/03/2008
 */
public class IntegerMultiplier extends IntegerBinaryOperator {

  /**
   * @param maratonaModel
   * @param string
   * @param i
   */
  public IntegerMultiplier(IntegerAbstractComponent system, String name,
      Set<Integer> lines, ComplementaryInfo info) {
    super(system, name, lines, info);
  }

  public IntegerMultiplier(IntegerAbstractComponent system, String name,
      int line, ComplementaryInfo info) {
    this(system, name, DefaultSets.toSet(line), info);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#propagateBackward(br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort, int)
   */
  @Override
  public void propagateBackward(IntegerPort in1, IntegerPort in2,
      IntegerPort out, int port) throws ConflictException {

    IntegerPort inPort1, inPort2;

    inPort1 = port == BooleanUtils.IN1 ? in1 : in2;
    inPort2 = port == BooleanUtils.IN1 ? in2 : in1;

    Integer value = out.getValue() / inPort1.getValue();

    if ((value * inPort1.getValue().intValue()) == out.getValue().intValue()) {
      inPort2.addAssumption(this);
      inPort2.addAssumption(inPort1.getDependencies());
      inPort2.addAssumption(out.getDependencies());
      inPort2.setValue(value, false);
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#propagateForward(br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort)
   */
  @Override
  public void propagateForward(IntegerPort in1, IntegerPort in2, IntegerPort out)
      throws ConflictException {

    Integer n1 = in1.getValue();
    Integer n2 = in2.getValue();
    Integer value = new Integer(n1 * n2);

    out.addAssumption(this);
    out.addAssumption(in1.getDependencies());
    out.addAssumption(in2.getDependencies());
    out.setValue(value, false);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponentAdapter#toString()
   */
  @Override
  public String toString() {
    return this.getName() + ":L" + this.getProgramLines();
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComponentName()
   */
  @Override
  public String getComponentName() {
    return "Multiplicação " + formatInfo();
  }
}