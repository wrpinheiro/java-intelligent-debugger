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
package br.com.wrpinheiro.jid.j2vbmodel.interpret;

import java.util.LinkedList;
import java.util.List;


import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;
import br.com.wrpinheiro.jid.javacompiler.node.TLBrc;
import br.com.wrpinheiro.jid.javacompiler.node.TRBrc;

public class BraceMatcher extends DepthFirstAdapter {
  private List<Integer> lBraces = new LinkedList<Integer>();
  private List<Integer> rBraces = new LinkedList<Integer>();

  @Override
  public void caseTLBrc(TLBrc node) {
    lBraces.add(node.getLine());
  }

  @Override
  public void caseTRBrc(TRBrc node) {
    rBraces.add(node.getLine());
  }
  
  public List<Integer> getLBraces() {
    return this.lBraces;
  }
  
  public List<Integer> getRBraces() {
    return this.rBraces;
  }
}
