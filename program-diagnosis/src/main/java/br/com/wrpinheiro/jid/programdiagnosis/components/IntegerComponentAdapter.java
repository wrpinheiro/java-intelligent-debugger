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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

/**
 * An abstract is used as base class for all components in the model.
 * 
 * @author wrp
 * 
 *         05/04/2008
 */
public abstract class IntegerComponentAdapter implements IntegerComponent {

  /**
   * A list of instantiated components.
   */
  private static List<IntegerComponent> components = new ArrayList<IntegerComponent>();

  /**
   * Add a new instance to list of instantiated components.
   * 
   * @param c
   */
  private static void addInstance(IntegerComponent c) {
    components.add(c);
  }

  /**
   * Enable all components in the system.
   */
  public static void enableAllComponents() {
    for (IntegerComponent c : IntegerComponentAdapter.components) {
      c.enable();
    }
  }

  /**
   * Clear the component list. This operation is required every time that the
   * program parser starts.
   */
  public static void clearComponentList() {
    IntegerComponentAdapter.components.clear();
  }

  /**
   * Return the list of components instantiated.
   * 
   * @return a list of components.
   */
  public static List<IntegerComponent> getComponents() {
    return Collections.unmodifiableList(IntegerComponentAdapter.components);
  }

  /**
   * Return the Id for the next component to be created.
   * 
   * @return
   */
  public static String getNextComponentId() {
    return "C" + IntegerComponentAdapter.getComponents().size();
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#addComponentToPropagationList()
   */
  @Override
  public final void addComponentToPropagationList() {
    this.getParent().addComponentToPropagationList(this);
  }

  /**
   * Log instance.
   */
  private static final Logger LOG = Logger
      .getLogger(IntegerComponentAdapter.class);

  /**
   * The parent of this component.
   */
  private IntegerAbstractComponent parent;

  /**
   * Component name.
   */
  private String name;

  /**
   * The propagation status of the component.
   */
  private boolean enabled = true;

  /**
   * The program lines related to this component.
   */
  private Set<Integer> programLines;

  /**
   * Some complementary info for this component.
   */
  private ComplementaryInfo complementaryInfo;

  private boolean conditionalCondition = false;

  /**
   * Get the system where this component belong.
   * 
   * @return the system.
   */
  public IntegerAbstractComponent getParent() {
    return this.parent;
  }

  /**
   * Creates a named instance of an AbstractComponent in a specific system, in a
   * given line.
   * 
   * @param parent
   *          the system of this component.
   * @param name
   *          the name of this component.
   * @param line
   *          the program line of this component.
   */
  public IntegerComponentAdapter(IntegerAbstractComponent parent, String name,
      Set<Integer> line, ComplementaryInfo info) {

    Logger.getLogger(getClass()).debug(
        "creating " + getClass().getSimpleName() + ": " + name);

    IntegerComponentAdapter.addInstance(this);

    this.complementaryInfo = info;
    this.name = name;
    this.parent = parent;
    this.programLines = line;

    if (parent != null) {
      parent.addComponent(this);

      parent.getTopMostSystem().mapLineToComponents(line, this);

      // this.abstractParents.addAll(parent.getAbstractParents());

      // this case does not hold when the parent is an IntegerSystem.
      // if (parent instanceof IntegerAbstractComponent) {
      // this.abstractParents.add((IntegerAbstractComponent) parent);
      // }
    }

    ComponentManager.getInstance().register(this);

    LOG.debug("abstract parent's list: " + this.getAbstractParents());
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getName()
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.getName() + ":" + this.getClass().getSimpleName() + ":L"
        + this.getProgramLines();
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#disable()
   */
  @Override
  public void disable() {
    this.enabled = false;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#enable()
   */
  @Override
  public void enable() {
    this.enabled = true;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  /**
   * This implementation guarantees that the propagation will not occur when
   * this component is not enabled. Classes inheriting from this one must
   * implement internalPropagate to do the propagation.
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#propagate()
   */
  @Override
  public final void propagate() throws ConflictException {
    if (this.isEnabled())
      this.internalPropagate();
  }

  /**
   * This method must be implemented by children classes to do the propagation.
   * 
   * THIS METHOD IS ONLY EXECUTED WHEN THIS COMPONENT IS ENABLED! So, you don't
   * need to check its state before do the job!
   * 
   * @return the same of the method propagate.
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#propagate()
   */
  protected abstract void internalPropagate() throws ConflictException;

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getProgramLines()
   */
  @Override
  public Set<Integer> getProgramLines() {
    return this.programLines;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#setProgramLines(Set)
   */
  @Override
  public void setProgramLines(Set<Integer> line) {
    this.programLines = line;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#replaceParent(br.com.jdebugger.programdiagnosis.IntegerSystem)
   */
  @Override
  public void replaceParent(IntegerAbstractComponent target) {
    LOG.debug("replacing " + this.getName() + "'s parent from "
        + this.getParent() + " to " + target);
    this.getParent().removeComponent(this);
    this.parent = target;
    target.addComponent(this);

    LOG.debug("replacing ports parents");
  }

  /**
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((parent == null) ? 0 : parent.hashCode());
    return result;
  }

  /**
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    IntegerComponentAdapter other = (IntegerComponentAdapter) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (parent == null) {
      if (other.parent != null)
        return false;
    } else if (!parent.equals(other.parent))
      return false;
    return true;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getAbstractParents()
   */
  public List<IntegerAbstractComponent> getAbstractParents() {
    List<IntegerAbstractComponent> abstractParents = new LinkedList<IntegerAbstractComponent>();
    IntegerAbstractComponent tmp = parent;

    while (tmp != null) {
      abstractParents.add(0, tmp);
      // //aqui!
      tmp = tmp.getParent();
    }

    return abstractParents;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#getTheMostAbstractParentNotRefined()
   */
  public IntegerAbstractComponent getTheMostAbstractParentNotRefined() {
    for (IntegerAbstractComponent abs : this.getAbstractParents()) {
      if (!abs.isRefined())
        return abs;
    }
    return null;
  }

  public ComplementaryInfo getComplementaryInfo() {
    return this.complementaryInfo;
  }

  public String formatInfo() {
    return "[" + this.getComplementaryInfo().getNodeStr() + "]";
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isBoolean()
   */
  @Override
  public boolean isBoolean() {
    return false;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#isConditionalExpression()
   */
  @Override
  public boolean isConditionalExpression() {
    return conditionalCondition;
  }
  
  public void setConditionalExpression(boolean value) {
    this.conditionalCondition = value;
  }
}
