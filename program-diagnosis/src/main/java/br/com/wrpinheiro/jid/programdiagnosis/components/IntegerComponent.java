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

import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * A component definition.
 * 
 * @author wrp
 * 
 *         29/03/2008
 */
public interface IntegerComponent {

  /**
   * Propagates the values through the component.
   * 
   * @throws ConflictException
   *           exception thrown when a conflict is found during the propagation.
   */
  void propagate() throws ConflictException;

  /**
   * Propagates the values from the input ports to the output port through the
   * operation.
   * 
   * @throws ConflictException
   *           exception thrown when a conflict is found during the propagation.
   */
  // void propagateForward() throws ConflictException;
  /**
   * Get the name of this component.
   * 
   * @return name of the component.
   */
  public String getName();

  public String getComponentName();

  /**
   * Disable the propagation through the component.
   */
  public void disable();

  /**
   * Enable the propagation through the component.
   */
  public void enable();

  /**
   * Get the propagation status of this component.
   * 
   * @return True if propagation is enabled or False otherwise.
   */
  public boolean isEnabled();

  /**
   * Clear the component value and force all its connections to be cleaned.
   */
  public void clearValues();

  /**
   * Program lines related to this component.
   * 
   * @return the lines of the program.
   */
  public Set<Integer> getProgramLines();

  /**
   * Set the program line related to this component.
   * 
   * @param line
   *          the program line.
   */
  public void setProgramLines(Set<Integer> line);

  /**
   * Return the parent system.
   * 
   * @return the parent system.
   */
  public IntegerAbstractComponent getParent();

  /**
   * Return the complexity of this component related to inference process. The
   * complexity is used to sort the components, i.e., components with less
   * complexity used in the propagation before the complexes ones.
   * 
   * @return the complexity.
   */
  public Complexity getComplexity();

  /**
   * Check if the component is abstract.
   * 
   * @return true if the component is abstract or false otherwise.
   */
  public boolean isAbstract();

  /**
   * Return a list of the abstract parents of this component.
   * 
   * @return a list of IntegerAbstractComponent.
   */
  public List<IntegerAbstractComponent> getAbstractParents();

  /**
   * Get the most abstract parent of this component that is not refined.
   * 
   * @return the top most abstract parent that is not refined. If no such
   *         component can be found, a null must be returned.
   */
  public IntegerAbstractComponent getTheMostAbstractParentNotRefined();

  /**
   * Change the component's parent. The implementation must remove the compenent
   * from the parent's component list and add it to the new parent's component
   * list.
   * 
   * @param target
   *          the new component's parent.
   */
  public void replaceParent(IntegerAbstractComponent target);

  /**
   * Add this component to its owner propagation list.
   */
  public void addComponentToPropagationList();

  /**
   * Return a map of named input ports.
   * 
   * @return a map of ports.
   */
  public Map<String, IntegerPort> getInputPorts();

  /**
   * Return a map of named output ports.
   * 
   * @return a map of ports.
   */
  public Map<String, IntegerPort> getOutputPorts();

  /**
   * Return the complementary info for the component.
   * 
   * @return
   */
  public ComplementaryInfo getComplementaryInfo();

  /**
   * Check if this component is boolean (and, or, less, greater, etc.).
   * 
   * @return
   */
  public boolean isBoolean();

  /**
   * Check if this component belongs to a conditional expression.
   * 
   * @return
   */
  public boolean isConditionalExpression();

  /**
   * Inform if this component belongs to a conditional expression (b == true) or
   * not (b == false).
   * 
   * @param b
   */
  void setConditionalExpression(boolean b);
}