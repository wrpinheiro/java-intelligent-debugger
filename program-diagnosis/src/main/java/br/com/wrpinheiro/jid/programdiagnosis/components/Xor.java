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
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * A XOR component.
 * 
 * This component executes a boolean XOR of two operands.
 * 
 * @author wrp
 * 
 *         02/09/2008
 */
public class Xor extends IntegerBinaryOperator {
  /**
   * Create the instance, setting the system and its name.
   * 
   * @param system
   *          the system.
   * @param name
   *          the name of the adder.
   */
  public Xor(IntegerSystem system, String name, Set<Integer> lines,
      ComplementaryInfo info) {
    super(system, name, lines, info);
    
    super.getResult().setIsBoolean(true);
  }

  public Xor(IntegerSystem system, String name, int line, ComplementaryInfo info) {
    this(system, name, DefaultSets.toSet(line), info);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#internalPropagate()
   */
  @Override
  public void internalPropagate() throws ConflictException {
    IntegerPort in1, in2, out;

    in1 = this.getIn1();
    in2 = this.getIn2();
    out = this.getResult();

    if (in1.hasValue() && in2.hasValue()) {
      this.propagateForward(in1, in2, out);
    } else if (out.hasValue() && in1.hasValue()) {
      this.propagateBackward(in1, in2, out, BooleanUtils.IN1);
    } else if (out.hasValue() && in2.hasValue()) {
      this.propagateBackward(in1, in2, out, BooleanUtils.IN2);
    } // else does nothing! The component has no sufficient values to
    // propagate.
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

    if (in1.hasValue() && in2.hasValue()) {
      Integer value;

      value = in1.getValue().equals(in2.getValue()) ? ZERO : ONE;

      out.addAssumption(this);
      out.addAssumption(in1.getDependencies());
      out.addAssumption(in2.getDependencies());
      out.setValue(value, false);
    }
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
      IntegerPort out, int port)
      throws ConflictException {

    IntegerPort inPort1, inPort2;

    inPort1 = port == BooleanUtils.IN1 ? in1 : in2;
    inPort2 = port == BooleanUtils.IN1 ? in2 : in1;

    Integer value;

    if (out.getValue().equals(ZERO)) {
      value = inPort1.getValue();
    } else {
      if (inPort1.getValue().equals(ZERO))
        value = ONE;
      else
        value = ZERO;
    }

    inPort2.addAssumption(this);
    inPort2.addAssumption(inPort1.getDependencies());
    inPort2.addAssumption(out.getDependencies());
    inPort2.setValue(value, false);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComponentName()
   */
  @Override
  public String getComponentName() {
    return "Xor booleano " + formatInfo();
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