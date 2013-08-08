package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponentAdapter;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

public class NullComponent extends IntegerComponentAdapter {
  public NullComponent(IntegerAbstractComponent parent, String name,
      Set<Integer> line, ComplementaryInfo info) {
    super(parent, name, line, info);
  }

  @Override
  protected void internalPropagate() throws ConflictException {
  }

  @Override
  public void clearValues() {
  }

  @Override
  public Complexity getComplexity() {
    return Complexity.EASIEST;
  }

  @Override
  public String getComponentName() {
    return "nullComponent" + System.currentTimeMillis();
  }

  @Override
  public Map<String, IntegerPort> getInputPorts() {
    return new HashMap<String, IntegerPort>();
  }

  @Override
  public Map<String, IntegerPort> getOutputPorts() {
    return new HashMap<String, IntegerPort>();
  }

  @Override
  public boolean isAbstract() {
    return false;
  }
}
