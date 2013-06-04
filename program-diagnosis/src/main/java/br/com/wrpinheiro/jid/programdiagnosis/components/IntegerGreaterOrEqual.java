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
 *         Apr 21, 2009
 */
public class IntegerGreaterOrEqual extends IntegerBinaryOperator {

  /**
   * Creates an instance of the greater of equal operator in a given system,
   * with a given name.
   * 
   * @param system
   *          the system.
   * @param name
   *          the name.
   * @param line
   *          the source code for the line.
   */
  public IntegerGreaterOrEqual(final IntegerAbstractComponent system,
      final String name, final Set<Integer> lines, ComplementaryInfo info) {
    super(system, name, lines, info);
    
    super.getResult().setIsBoolean(true);
  }

  public IntegerGreaterOrEqual(IntegerAbstractComponent system, String name,
      int line, ComplementaryInfo info) {
    this(system, name, DefaultSets.toSet(line), info);
  }

  /**
   * It's not possible to propagate backward with the component for '>='.
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerBinaryOperator#propagateBackward
   *      (br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerPort, int)
   */
  @Override
  public void propagateBackward(final IntegerPort in1, final IntegerPort in2,
      final IntegerPort result, final int port) throws ConflictException {
    // does not need implementation.
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * br.usp.ime.diagnostician.components.IntegerBinaryOperator#propagateForward
   * (br.usp.ime.diagnostician.connectors.IntegerPort,
   * br.usp.ime.diagnostician.connectors.IntegerPort,
   * br.usp.ime.diagnostician.connectors.IntegerPort)
   */
  @Override
  public void propagateForward(IntegerPort in1, IntegerPort in2,
      IntegerPort result) throws ConflictException {

    result.addAssumption(this);
    result.addAssumption(in1.getDependencies());
    result.addAssumption(in2.getDependencies());

    if (in1.getValue() >= in2.getValue())
      result.setValue(BooleanUtils.TRUE, false);
    else
      result.setValue(BooleanUtils.FALSE, false);
  }

  @Override
  public String getComponentName() {
    return "Maior ou Igual " + formatInfo();
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
