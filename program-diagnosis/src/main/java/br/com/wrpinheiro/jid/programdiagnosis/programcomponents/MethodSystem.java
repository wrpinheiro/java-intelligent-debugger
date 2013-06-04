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
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * A component that represents a method.
 *
 * @author wrp
 */
public class MethodSystem extends BlockComponent {
	/**
	 * Value type for method that returns void.
	 */
	public static final int TYPE_VOID = 0;

	/**
	 * Value type for methods that return anything but void. 
	 */
	public static final int TYPE_OTHER = 1;

	/**
	 * The method represented by this system.
	 */
	private String methodName;
	
	/**
	 * The returning type of this method.
	 */
	private int methodType;

	/**
	 * Creates an instance of the Method.
	 *
	 * @param componentName
	 *            the name of the component.
	 * @param parent
	 *            the parent system.
	 * @param line
	 *            the program line where this component starts.
	 */
	public MethodSystem(final IntegerAbstractComponent parent, final String name, final Set<Integer> lines,
			final String methodName, final int methodType, final ComplementaryInfo info) {
		super(parent, name, lines, info);
		
		super.setProgramLines(lines);

		this.setMethodName(methodName);
		this.setMethodType(methodType);

		IntegerAbstractComponent topMostSystem = this.getTopMostSystem();
		topMostSystem.addProperty(methodName, this);
	}

	public MethodSystem(String name, IntegerAbstractComponent system, int line,
			String methodName, Integer methodType, ComplementaryInfo info) {
		this(system, name, DefaultSets.toSet(line), methodName, methodType, info);
	}

	/**
	 * Set the method name.
	 * @param methodName the methodName to set
	 */
	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Return the method name.
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Get the returning type of this method.
	 * @return the methodType
	 */
	public int getMethodType() {
		return methodType;
	}

	/**
	 * Set the returning type of this method.
	 * @param methodType the methodType to set
	 */
	public void setMethodType(final int methodType) {
		this.methodType = methodType;
	}

  @Override
  public String getComponentName() {
    return "Método " + formatInfo();
  }
}
