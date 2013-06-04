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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IOIntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponentAdapter;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * A component representing a method invocation.
 * 
 * @author wrp
 */
public class MethodInvocation extends IntegerComponentAdapter implements
    IOIntegerComponent {

  /**
   * Input ports (order is important).
   */
  private Map<String, IntegerPort> inputPorts = new LinkedHashMap<String, IntegerPort>();

  /**
   * Output ports (order is important).
   */
  private Map<String, IntegerPort> outputPorts = new LinkedHashMap<String, IntegerPort>();

  /**
   * Name of the method.
   */
  private String methodName;

  /**
   * Reference to the top most system.
   */
  private IntegerAbstractComponent topMostSystem;

  /**
   * The system that represents the methodName.
   */
  private MethodSystem methodSystem;

  /**
   * Creates component to represent the invocation of method, named by name and
   * parent equals to system. Line is the line of the program where the
   * invocation happens.
   * 
   * @param system
   *          the system of this component.
   * @param name
   *          the name of this component.
   * @param methodName
   *          the name of the method.
   * @param line
   *          the program line of this component.
   */
  public MethodInvocation(final IntegerAbstractComponent system,
      final String name, final String methodName, final Set<Integer> lines,
      ComplementaryInfo info) {
    super(system, name, lines, info);

    this.methodName = methodName;
    this.topMostSystem = system.getTopMostSystem();
  }

  public MethodInvocation(IntegerAbstractComponent system, String name,
      String methodName, int line, ComplementaryInfo info) {
    this(system, name, methodName, DefaultSets.toSet(line), info);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponentAdapter#internalPropagate()
   */
  @Override
  protected void internalPropagate() throws ConflictException {
    if (this.methodSystem == null)
      this.methodSystem = (MethodSystem) this.topMostSystem
          .getProperty(this.methodName);

    this.methodSystem.clearValues();
    this.methodSystem.reassignObservedValue();

    this.copyValues(this.getInputPorts(), this.methodSystem.getInputPorts());
    this.copyValues(this.getOutputPorts(), this.methodSystem.getOutputPorts());

    this.methodSystem.propagate();

    this.copyValues(this.methodSystem.getInputPorts(), this.getInputPorts());
    this.copyValues(this.methodSystem.getOutputPorts(), this.getOutputPorts());
  }

  /**
   * Copy the values in sourcePorts to targetPorts. The equivalence between
   * source and target ports is positional, i.e., the value in the 1st. source
   * port is copied to the 1st. target port, the 2nd. source port is copied to
   * the 2nd. target port and so on.
   * 
   * @param sourcePorts
   *          the source ports.
   * @param targetPorts
   *          the target ports.
   * @throws ConflictException
   *           Exception thrown if a conflict in values is detected.
   */
  private void copyValues(final Map<String, IntegerPort> sourcePorts,
      final Map<String, IntegerPort> targetPorts) throws ConflictException {
    IntegerPort srcPort;
    IntegerPort targetPort = null;
    String tempKey;

    Iterator<String> srcKeys = sourcePorts.keySet().iterator();
    Iterator<String> targetKeys = targetPorts.keySet().iterator();

    while (srcKeys.hasNext()) {
      srcPort = sourcePorts.get(srcKeys.next());
      tempKey = targetKeys.next();
      if (srcPort.hasValue()) {
        targetPort = targetPorts.get(tempKey);

        targetPort.addAssumption(srcPort.getDependencies());
        targetPort.addAssumption(this);
        targetPort.setValue(srcPort.getValue(), true);
      }
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#addInputPort(java.lang.String)
   */
  @Override
  public IntegerPort addInputPort(final String portName) {
    if (this.getInputPort(portName) != null) {
      throw new RuntimeException("A named port " + portName
          + " already exists.");
    }

    String name = portName;
    IntegerPort port = new IntegerPort(name, this);
    this.inputPorts.put(portName, port);

    return port;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#addOutputPort(java.lang.String)
   */
  @Override
  public IntegerPort addOutputPort(final String portName) {
    if (this.getOutputPort(portName) != null) {
      throw new RuntimeException("A named port " + portName
          + " already exists.");
    }

    String name = portName;
    IntegerPort port = new IntegerPort(name, this);
    this.outputPorts.put(portName, port);

    return port;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getInputPort(java.lang.String)
   */
  @Override
  public IntegerPort getInputPort(final String name) {
    return this.inputPorts.get(name);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getInputPorts()
   */
  @Override
  public Map<String, IntegerPort> getInputPorts() {
    return this.inputPorts;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getOutputPort(java.lang.String)
   */
  @Override
  public IntegerPort getOutputPort(final String name) {
    return this.outputPorts.get(name);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getOutputPorts()
   */
  @Override
  public Map<String, IntegerPort> getOutputPorts() {
    return this.outputPorts;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#clearValues(boolean)
   */
  @Override
  public void clearValues() {
    for (String key : this.getInputPorts().keySet()) {
      this.getInputPorts().get(key).clearValue();
    }

    for (String key : this.getOutputPorts().keySet()) {
      this.getOutputPorts().get(key).clearValue();
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComplexity()
   */
  @Override
  public Complexity getComplexity() {
    return Complexity.HIGH;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isAbstract()
   */
  @Override
  public boolean isAbstract() {
    return true;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getComponentName()
   */
  @Override
  public String getComponentName() {
    return "Invocação de método " + formatInfo();
  }
}
