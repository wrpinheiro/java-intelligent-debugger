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
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * Block representing a while structure and its conditional component.
 * @author wrp
 *
 * Feb 4, 2009
 */
public class While extends BlockComponent {
	/**
	 * Creates an instance of the While.
	 * 
	 * @param name
	 *            the name of the component.
	 * @param parent the parent system.
	 * @param line the program line where this component starts.
	 */
	public While(IntegerAbstractComponent parent, final String name, final Set<Integer> lines, final ComplementaryInfo info) {
		super(parent, name, lines, info);
	}

	/**
	 * @param string
	 */
	@Override
	public IntegerPort addInputPort(String portName) {
		String name = this.getName() + "-" + portName + "(i)";

		IntegerPort port = new IntegerPort(name, this);
		super.addInputPort(portName, port);

		return port;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.IOIntegerComponent#addOutputPort(java.lang.String)
	 */
	@Override
	public IntegerPort addOutputPort(String portName) {
		String name = this.getName() + "-" + portName + "(o)";

		IntegerPort port = new IntegerPort(name, this);
		super.addOutputPort(portName, port);

		return port;
	}

  @Override
  public String getComponentName() {
    return "La�o " + formatInfo();
  }
}
