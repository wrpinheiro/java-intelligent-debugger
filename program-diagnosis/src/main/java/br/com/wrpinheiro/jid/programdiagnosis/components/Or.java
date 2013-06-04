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
import static br.com.wrpinheiro.jid.programdiagnosis.components.BooleanUtils.checkPortValue;

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * An OR component.
 * 
 * This component executes a boolean OR of two operands.
 * 
 * @author wrp
 * 
 *         02/09/2008
 */
public class Or extends IntegerBinaryOperator {
  /**
   * Create the instance, setting the system and its name.
   * 
   * @param system
   *          the system.
   * @param name
   *          the name of the adder.
   */
  public Or(IntegerSystem system, String name, Set<Integer> lines,
      ComplementaryInfo info) {
    super(system, name, lines, info);
    
    super.getResult().setIsBoolean(true);
  }

  public Or(IntegerSystem system, String name, int line, ComplementaryInfo info) {
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

    if (checkPortValue(in1, ONE) || checkPortValue(in2, ONE)
        || (checkPortValue(in1, ZERO) && checkPortValue(in2, ZERO))) {
      this.propagateForward(in1, in2, out);
    } else if (checkPortValue(out, ZERO)) {
      if (!in1.hasValue() && !in2.hasValue()) {
        this.propagateBackward(in1, in2, out, BooleanUtils.IN_1_2);
      } else if (in1.hasValue()) {
        this.propagateBackward(in1, in2, out, BooleanUtils.IN1);
      } else if (in2.hasValue()) {
        this.propagateBackward(in1, in2, out, BooleanUtils.IN2);
      }
    } else if (checkPortValue(out, ONE)) {
      if (checkPortValue(in1, ZERO)) {
        this.propagateBackward(in1, in2, out, BooleanUtils.IN1);
      } else if (checkPortValue(in2, ZERO)) {
        this.propagateBackward(in1, in2, out, BooleanUtils.IN2);
      }
    }
    // else does nothing! The component has no sufficient values to
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

    if (checkPortValue(in1, ONE)) {
      this.propagateForwardFromOnlyOnePort(in1, out);
    } else if (checkPortValue(in2, ONE)) {
      this.propagateForwardFromOnlyOnePort(in2, out);
    } else if (in1.hasValue() && in2.hasValue()) {
      // in1 = in2 = 0
      out.addAssumption(this);
      out.addAssumption(in1.getDependencies());
      out.addAssumption(in2.getDependencies());
      out.setValue(ZERO, false);
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

    if (checkPortValue(out, ZERO)) {
      if (!in1.hasValue() && !in2.hasValue()) {
        in1.addAssumption(this);
        in1.addAssumption(out.getDependencies());
        in1.setValue(ZERO, false);

        in2.addAssumption(this);
        in2.addAssumption(out.getDependencies());
        in2.setValue(ZERO, false);
      }
      if (port == BooleanUtils.IN1) {
        in2.addAssumption(this);
        in2.addAssumption(out.getDependencies());
        in2.setValue(ZERO, false);
      } else if (port == BooleanUtils.IN2) {
        in1.addAssumption(this);
        in1.addAssumption(out.getDependencies());
        in1.setValue(ZERO, false);
      }
    } else if (checkPortValue(out, ONE)) {
      if (checkPortValue(in1, ZERO)) {
        in2.addAssumption(this);
        in2.addAssumption(out.getDependencies());
        in2.addAssumption(in1.getDependencies());
        in2.setValue(ONE, false);
      } else if (checkPortValue(in2, ZERO)) {
        in1.addAssumption(this);
        in1.addAssumption(out.getDependencies());
        in1.addAssumption(in2.getDependencies());
        in1.setValue(ONE, false);
      }
    }
  }

  /**
   * Propagate values from an input port.
   * 
   * @param inputPort
   *          the input port where the value is used.
   * @param out
   *          the output port whose value is going to be inferred.
   * @return true if a value could be inferred or false, otherwise.
   * @throws ConflictException
   */
  public void propagateForwardFromOnlyOnePort(IntegerPort inputPort,
      IntegerPort out) throws ConflictException {

    if (checkPortValue(inputPort, ONE)) {
      out.addAssumption(this);
      out.addAssumption(inputPort.getDependencies());
      out.setValue(ONE, false);
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComponentName()
   */
  @Override
  public String getComponentName() {
    return "Ou booleano " + formatInfo();
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