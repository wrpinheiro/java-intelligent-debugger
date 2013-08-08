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
