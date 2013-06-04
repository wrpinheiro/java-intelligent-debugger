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
 * @author wrp
 * 
 *         09/03/2009
 */
public class IntegerEqual extends IntegerBinaryOperator {

  /**
   * Creates an instance of the integer equal operator.
   * 
   * @param system
   *          the system.
   * @param name
   *          the name.
   * @param line
   */
  public IntegerEqual(IntegerAbstractComponent system, String name,
      Set<Integer> lines, ComplementaryInfo info) {
    super(system, name, lines, info);
    
    super.getResult().setIsBoolean(true);
  }

  public IntegerEqual(IntegerAbstractComponent system, String name, int line,
      ComplementaryInfo info) {
    this(system, name, DefaultSets.toSet(line), info);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#propagateForward(br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort)
   */
  @Override
  public void propagateForward(IntegerPort in1, IntegerPort in2,
      IntegerPort result) throws ConflictException {

    result.addAssumption(this);
    result.addAssumption(in1.getDependencies());
    result.addAssumption(in2.getDependencies());

    if (in1.getValue().equals(in2.getValue()))
      result.setValue(BooleanUtils.TRUE, false);
    else
      result.setValue(BooleanUtils.FALSE, false);
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
      IntegerPort result, int port) throws ConflictException {

    if (result.getValue().equals(BooleanUtils.TRUE)) {
      if (port == BooleanUtils.IN1) {
        in2.setValue(in1.getValue(), false);
      } else {
        in1.setValue(in2.getValue(), false);
      }
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComponentName()
   */
  @Override
  public String getComponentName() {
    return "Op. Igualdade " + formatInfo();
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
