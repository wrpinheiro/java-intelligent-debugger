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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.BooleanUtils;
import br.com.wrpinheiro.jid.programdiagnosis.components.ComplementaryInfo;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.util.Complexity;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * @author wrp
 * 
 *         Sep 4, 2008
 */
public class Conditional extends BlockComponent {

  /**
   * Log instance.
   */
  private static final Logger LOG = Logger.getLogger(Conditional.class);
  
  public static final String PORT_COND_RESULT = "-COND";

  /**
   * A system representing the then branch of the conditional.
   */
  private BlockComponent thenSystem;

  /**
   * A system representing the else branch of the conditional.
   */
  private BlockComponent elseSystem;

  /**
   * A set of components in the conditional expression.
   */
  private List<IntegerComponent> expressionComponents;

  /**
   * The port for the condition of this component.
   */
  private IntegerPort condResult = new IntegerPort(this.getName() + PORT_COND_RESULT,
      this);

  /**
   * Creates an instance of the Conditional.
   * 
   * @param name
   *          the name of the component.
   * @param parent
   *          the parent system.
   * @param line
   *          the program line where this component starts.
   */
  public Conditional(final IntegerAbstractComponent parent, final String name,
      final Set<Integer> lines, final ComplementaryInfo info) {
    super(parent, name, lines, info);
    
    condResult.setIsBoolean(true);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.usp.ime.diagnostician.components.IntegerAbstractComponent#
   *      internalPropagate ()
   */
  @Override
  protected void internalPropagate() throws ConflictException {

    LOG.debug("===== (st) CONDITIONAL: " + this.getName());

    boolean thenBranchOldState = this.thenSystem.isEnabled();
    boolean elseBranchOldState = this.elseSystem.isEnabled();

    thenSystem.disable();
    elseSystem.disable();

    super.internalPropagate();

    if (thenBranchOldState)
      thenSystem.enable();
    if (elseBranchOldState)
      elseSystem.enable();
    // //aqui

    LOG.debug("condResult.hasValue() = " + condResult.hasValue());

    if (condResult.hasValue()) {

      LOG.debug("condResult.getValue() = " + condResult.getValue());

      // nothing must be done if the condition is false and there is no
      // else branch.
      if (condResult.getValue().equals(BooleanUtils.FALSE)
          && elseSystem == null) {
        LOG.debug("Conditional " + this.getName() + " has no else branch.");
        return;
      }

      BlockComponent branchS = condResult.getValue().equals(BooleanUtils.TRUE) ? thenSystem
          : elseSystem;
      propagateToBranch(branchS);

      // propagate the values to outside.
      propagateFromOnlyOneBranch();
    }
    /*
     * else { boolean thenConflict = false; boolean elseConflict = false;
     * Set<IntegerComponent> thenConflictSet = new HashSet<IntegerComponent>();
     * // Set<IntegerComponent> elseConflictSet = new //
     * HashSet<IntegerComponent>();
     * 
     * // check if the then branch derives a conflict. try {
     * propagateToBranch(thenSystem); } catch (ConflictException ex) {
     * thenConflict = true; thenConflictSet = ex.getConflictSet();
     * thenSystem.clearValues(); }
     * 
     * if (elseSystem != null) { // check if the else branch derives a conflict.
     * try { propagateToBranch(elseSystem); } catch (ConflictException ex) {
     * elseConflict = true; // elseConflictSet = ex.getConflictSet();
     * elseSystem.clearValues(); } }
     * 
     * if (elseSystem == null) { LOG.debug("==> [" + this.getName() + "]" +
     * " Setting condResult = " + !thenConflict); if (thenConflict) {
     * this.condResult.addAssumption(thenConflictSet); }
     * this.condResult.setValue(BooleanUtils.boolToInt(!thenConflict), true); if
     * (!thenConflict) this.propagate(); else thenSystem.clearValues(); } else {
     * if (!thenConflict && !elseConflict) { // none of the branches derivated a
     * conflict. // check the variables with same value in both branches // and
     * propagate them outside the branches. propagateFromBothBranches(); } else
     * if (!thenConflict) { LOG.debug("==> [" + this.getName() + "]" +
     * " Setting condResult = " + BooleanUtils.TRUE);
     * this.condResult.addAssumption(thenConflictSet); // TODO Verificar se esta
     * certo usar os conflitos to else // nesse caso. //
     * this.condResult.addAssumption(elseConflictSet);
     * this.condResult.setValue(BooleanUtils.TRUE, true); this.propagate(); }
     * else if (!elseConflict) { LOG.debug("==> [" + this.getName() + "]" +
     * " Setting condResult = " + BooleanUtils.FALSE);
     * this.condResult.addAssumption(thenConflictSet);
     * this.condResult.setValue(BooleanUtils.FALSE, true); this.propagate(); }
     * else { // TODO Talvez, nesse caso, seja interessante verificar se //
     * existe uma var. // com valor inferido diferente nas entradas e sai­das do
     * // ramos then/else. // Caso isso ocorra, gerar uma excecao de conflito
     * unindo os // dois conjuntos // de contribuintes. LOG
     * .debug(this.getName() +
     * ": conditional generated a conflict in both branches.");
     * LOG.debug(this.getName() + ": does not propagate outside.");
     * this.thenSystem.clearValues(); if (this.elseSystem != null)
     * this.elseSystem.clearValues(); } } }
     */

    LOG.debug("===== (end) CONDITIONAL: " + this.getName());
  }

  /**
   * Check the input/output variables from both branches and propagate to the
   * outside the values of the variables that are equal in both branches.
   * 
   * @throws ConflictException
   */
  /*
   * private void propagateFromBothBranches() throws ConflictException {
   * this.checkPortValuesAndPropagateFromBranches( thenSystem.getInputPorts(),
   * elseSystem.getInputPorts(), this .getInputPorts());
   * this.checkPortValuesAndPropagateFromBranches(thenSystem .getOutputPorts(),
   * elseSystem.getOutputPorts(), this .getOutputPorts()); }
   * 
   * private void checkPortValuesAndPropagateFromBranches( Map<String,
   * IntegerPort> src1, Map<String, IntegerPort> src2, Map<String, IntegerPort>
   * targetPorts) throws ConflictException {
   * 
   * IntegerPort sourceT, sourceE, target;
   * 
   * for (String key : src1.keySet()) { sourceT = src1.get(key); sourceE =
   * src2.get(key); target = targetPorts.get(key);
   * 
   * if (sourceT.hasValue() && sourceE.hasValue() &&
   * sourceT.getValue().equals(sourceE.getValue())) {
   * 
   * // TODO a dependencia esta sendo feita com referencia // nos valores da
   * propagacao pelo bloco then, mas isso talvez // nao seja correto!! Verificar
   * o que fazer depois !
   * 
   * target.addAssumption(sourceT.getDependencies()); //
   * target.addAssumption(condResult.getDependencies());
   * target.setValue(sourceT.getValue(), true); } } }
   */

  /**
   * Propagate the value outside of a branch. The branch is chosen by the value
   * set at the condResult port.
   */
  private void propagateFromOnlyOneBranch() throws ConflictException {
    BlockComponent branchS = this.condResult.getValue().equals(
        BooleanUtils.TRUE) ? thenSystem : elseSystem;

    this.copyValuesFromBranch(this.getInputPorts(), branchS.getInputPorts());
    if (this.condResult.hasValue())
      this.copyValuesFromBranch(this.getOutputPorts(), branchS.getOutputPorts());
  }

  /**
   * Propagate the values from the branch to the conditional.
   * 
   * @param sourcePorts
   *          the ports from where the values will be copied.
   * @param branchSystem
   *          the branch of the conditional to copy the values.
   * @throws ConflictException
   */
  private void copyValuesFromBranch(final Map<String, IntegerPort> sourcePorts,
      final Map<String, IntegerPort> branchPorts) throws ConflictException {
    IntegerPort source, target;

    for (String key : sourcePorts.keySet()) {
      source = branchPorts.get(key);
      target = sourcePorts.get(key);

      if (source.hasValue()) {
        target.addAssumption(source.getDependencies());
        // target.addAssumption(condResult.getDependencies());
        target.setValue(source.getValue(), true);
      }
    }
  }

  /**
   * Propagate the values to a branch.
   * 
   * @param branchSystem
   *          the branch.
   * @throws ConflictException
   */
  private void propagateToBranch(final BlockComponent branchSystem)
      throws ConflictException {
    copyValues(this.getInputPorts(), branchSystem.getInputPorts(), branchSystem);
    copyValues(this.getOutputPorts(), branchSystem.getOutputPorts(),
       branchSystem);

    branchSystem.propagate();
  }

  /**
   * @param sourcePorts
   * @param branchSystem
   * @param block
   *          the block of the ports.
   * @throws ConflictException
   */
  private void copyValues(final Map<String, IntegerPort> sourcePorts,
      final Map<String, IntegerPort> branchPorts, final BlockComponent block)
      throws ConflictException {

    IntegerPort source, target;

    for (String key : sourcePorts.keySet()) {
      source = sourcePorts.get(key);
      target = branchPorts.get(key);

      if (target == null) {
        throw new RuntimeException("Could not find port: " + key
            + " in branch " + block.getName() + ";\nInputport = "
            + block.getInputPorts() + ";\nOutputport = "
            + block.getOutputPorts());
      }

      if (source.hasValue()) {
        target.addAssumption(source.getDependencies());
        target.addAssumption(condResult.getDependencies());
        target.setValue(source.getValue(), true);
      }
    }
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.components.IntegerComponent#clearValues()
   */
  @Override
  public void clearValues() {
    condResult.clearValue();

    for (String key : this.getInputPorts().keySet()) {
      this.getInputPorts().get(key).clearValue();
    }

    for (String key : this.getOutputPorts().keySet()) {
      this.getOutputPorts().get(key).clearValue();
    }

    thenSystem.clearValues();

    if (elseSystem != null) {
      elseSystem.clearValues();
    }
  }

  /**
   * Return the conditional result.
   * 
   * @return the conditional result.
   */
  public IntegerPort getCondResult() {
    return this.condResult;
  }

  /**
   * Sets the conditional expression evaluation
   * 
   * @param condResult
   */
  public void setCondResult(final IntegerPort condResult) {
    this.condResult = condResult;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.jdebugger.programdiagnosis.programcomponents.BlockComponent#addInputPort(java.lang.String)
   */
  @Override
  public IntegerPort addInputPort(final String portName) {
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
  public IntegerPort addOutputPort(final String portName) {
    String name = this.getName() + "-" + portName + "(o)";

    IntegerPort port = new IntegerPort(name, this);
    super.addOutputPort(portName, port);

    return port;
  }

  /**
   * Set the then branch.
   * 
   * @param then
   *          the then branch.
   */
  public void setThenSystem(final BlockComponent thenSystem) {
    this.thenSystem = thenSystem;
  }

  /**
   * Set the else branch.
   * 
   * @param else the else branch.
   */
  public void setElseSystem(final BlockComponent elseSystem) {
    this.elseSystem = elseSystem;
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
   * Get the list of expression components.
   * 
   * @return
   */
  public List<IntegerComponent> getExpressionComponents() {
    return expressionComponents;
  }

  /**
   * Set a list of expression components.
   * 
   * @param expressionComponents
   *          the list of expression components.
   */
  public void setExpressionComponents(
      final List<IntegerComponent> expressionComponents) {
    this.expressionComponents = expressionComponents;
  }

  @Override
  public String getComponentName() {
    return "Condicional " + formatInfo();
  }
}
