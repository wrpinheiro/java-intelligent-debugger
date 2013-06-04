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
 * A modulo component.
 * 
 * This component executes the modulo of two values. Every operand has as input
 * port associated and the result is stored in an output port.
 * 
 * Consider the equivalence between the names percent and modulo.
 * 
 * @author wrp
 * 
 *         07/04/2009
 */
public class IntegerPercent extends IntegerBinaryOperator {

  /**
   * 
   * @param system
   * @param name
   * @param line
   */
  public IntegerPercent(IntegerAbstractComponent system, String name,
      Set<Integer> lines, ComplementaryInfo info) {
    super(system, name, lines, info);
  }

  public IntegerPercent(IntegerAbstractComponent system, String name, int line,
      ComplementaryInfo info) {
    this(system, name, DefaultSets.toSet(line), info);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.ime.usp.br.diagnostician.components.IntegerBinaryOperator#propagateForward
   *      (br.ime.usp.br.diagnostician.connectors.IntegerPort,
   *      br.ime.usp.br.diagnostician.connectors.IntegerPort,
   *      br.ime.usp.br.diagnostician.connectors.IntegerPort)
   */
  @Override
  public void propagateForward(IntegerPort in1, IntegerPort in2, IntegerPort out)
      throws ConflictException {

    if (in2.getValue().intValue() != 0) {

      Integer value = new Integer(in1.getValue() % in2.getValue());

      out.addAssumption(this);
      out.addAssumption(in1.getDependencies());
      out.addAssumption(in2.getDependencies());
      out.setValue(value, false);
    }
  }

  /**
   * It is not possible to perform a backward propagation with the modulo
   * operation.
   * 
   * @see br.ime.usp.br.diagnostician.components.IntegerBinaryOperator#
   *      propagateBackward(br.ime.usp.br.diagnostician.connectors.IntegerPort,
   *      br.ime.usp.br.diagnostician.connectors.IntegerPort,
   *      br.ime.usp.br.diagnostician.connectors.IntegerPort, int)
   */
  @Override
  public void propagateBackward(final IntegerPort in1, final IntegerPort in2,
      final IntegerPort out, final int port) throws ConflictException {
    // does not need implementation.
  }

  @Override
  public String getComponentName() {
    return "Módulo " + formatInfo();
  }
}
