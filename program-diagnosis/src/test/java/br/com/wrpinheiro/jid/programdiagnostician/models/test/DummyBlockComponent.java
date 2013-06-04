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
package br.com.wrpinheiro.jid.programdiagnostician.models.test;

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.BlockComponent;

public class DummyBlockComponent extends BlockComponent {

  protected DummyBlockComponent(IntegerAbstractComponent parent, final String name,
      final Set<Integer> lines, final ComplementaryInfo info) {
    super(parent, name, lines, info);
  }

  @Override
  public String getComponentName() {
    return "Dummy Block " + formatInfo();
  }

}
