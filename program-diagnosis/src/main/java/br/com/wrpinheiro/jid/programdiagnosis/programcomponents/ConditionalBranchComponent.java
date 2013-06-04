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
package br.com.wrpinheiro.jid.programdiagnosis.programcomponents;

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

/**
 * A component that represents a conditional branch.
 * 
 * @author wrp
 */
public class ConditionalBranchComponent extends BlockComponent {

  public ConditionalBranchComponent(IntegerAbstractComponent parent,
      String name, final Set<Integer> lines, final ComplementaryInfo info) {
    super(parent, name, lines, info);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.abstractions.AbstractComponent#isRefined()
   */
  @Override
  public boolean isRefined() {
    return true;
  }

  @Override
  public String getComponentName() {
    return "Bloco " + formatInfo();
  }
}
