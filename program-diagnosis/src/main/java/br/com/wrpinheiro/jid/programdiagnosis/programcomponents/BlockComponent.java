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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.IOIntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.AbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * An abstract representation of a block of code.
 * 
 * @author wrp
 * 
 *         14/01/2009
 */
public abstract class BlockComponent extends AbstractComponent implements
    IOIntegerComponent {

  /**
   * Input ports.
   */
  private Map<String, IntegerPort> inputPorts = new LinkedHashMap<String, IntegerPort>();

  /**
   * Output ports.
   */
  private Map<String, IntegerPort> outputPorts = new LinkedHashMap<String, IntegerPort>();

  /**
   * Creates a block as a child of a system.
   * 
   * @param system
   *          the system.
   */
  protected BlockComponent(IntegerAbstractComponent parent, String name, final Set<Integer> lines,
      ComplementaryInfo info) {
    super(parent, name, lines, info);

    parent.addComponent(this);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getInputPorts()
   */
  @Override
  public Map<String, IntegerPort> getInputPorts() {
    return Collections.unmodifiableMap(this.inputPorts);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getOutputPorts()
   */
  @Override
  public Map<String, IntegerPort> getOutputPorts() {
    return Collections.unmodifiableMap(this.outputPorts);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getInputPort(java.lang.String)
   */
  @Override
  public IntegerPort getInputPort(String name) {
    return this.inputPorts.get(name);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#getOutputPort(java.lang.String)
   */
  @Override
  public IntegerPort getOutputPort(String name) {
    return this.outputPorts.get(name);
  }

  /**
   * Clear the values of the input and output ports.
   * 
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IntegerSystem#clearValues()
   */
  @Override
  public void clearValues() {
    super.clearValues();

    Map<String, IntegerPort> ports;

    ports = this.getInputPorts();
    for (IntegerPort port : ports.values()) {
      port.clearValue();
    }

    ports = this.getOutputPorts();
    for (IntegerPort port : ports.values()) {
      port.clearValue();
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#addInputPort(java.lang.String)
   */
  @Override
  public IntegerPort addInputPort(String portName) {
    if (this.getInputPort(portName) != null) {
      throw new RuntimeException("A named port " + portName
          + " already exists.");
    }

    String name = this.getName() + "-" + portName + "(i)";

    IntegerPort port = new IntegerPort(name, this);
    this.inputPorts.put(portName, port);

    return port;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#addOutputPort(br.com.jdebugger.programdiagnosis.connectors.IntegerPort)
   */
  @Override
  public IntegerPort addOutputPort(String portName) {
    if (this.getOutputPort(portName) != null) {
      throw new RuntimeException("A named port " + portName
          + " already exists.");
    }

    String name = this.getName() + "-" + portName + "(o)";

    IntegerPort port = new IntegerPort(name, this);
    this.outputPorts.put(portName, port);

    return port;
  }

  /**
   * Add a port to the input port map.
   * 
   * @param portName
   *          the name that the port will be stored.
   * @param port
   *          the port to be added.
   */
  public void addInputPort(String portName, IntegerPort port) {
    this.inputPorts.put(portName, port);
  }

  /**
   * Add a port to the output port map.
   * 
   * @param portName
   *          the name that the port will be stored.
   * @param port
   *          the port to be added.
   */
  public void addOutputPort(String portName, IntegerPort port) {
    this.outputPorts.put(portName, port);
  }
  
  /*
  @Override
  public String getComponentName() {
    return "Bloco " + formatInfo();
  }
  */
}
