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
package br.com.wrpinheiro.jid.programdiagnosis;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jgraphlib.familyset.ArrayFamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jgraphlib.hittingset.MinimalHittingSet;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * A diagnosis problem maintains the system description and the observations.
 * 
 * @author wrp
 * 
 *         30/06/2008
 */
public class IntegerDiagnosisProblem extends AbstractDiagnosisProblem { // DP =
	// diagnosis
	// problem.
	private static Logger LOG = Logger.getLogger(IntegerDiagnosisProblem.class);

	/**
	 * Model for the system being diagnosed.
	 */
	private IntegerSystem system;

	/**
	 * Construct a diagnosis problem and set its name.
	 * 
	 * @param name
	 *            identification for the DP.
	 * @param system
	 *            model for the system.
	 */
	public IntegerDiagnosisProblem(final String name, final IntegerSystem system) {
		super(name);
		this.system = system;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.AbstractDiagnosisProblem#applyObservations()
	 */
	@Override
	public void applyObservations() throws ConflictException {
		IntegerConnection intConn;
		Integer v;

		system.clearValues();

		for (String s : super.getObservations().keySet()) {
			intConn = system.getConnection(s);

			if (intConn == null) {
				throw new RuntimeException("Connection not found: " + s);
			}

			v = super.getObservations().get(s);
			intConn.setValue(v);
			intConn.setObservable(true);

			addToThePropagationList(intConn);
		}

		system.propagate();
	}

	private void addToThePropagationList(IntegerConnection intConn) {
		Set<IntegerComponent> propagationSet = new HashSet<IntegerComponent>();

		for (IntegerPort iPort : intConn.getConnectedPorts()) {
			propagationSet.addAll(iPort.getConnectedComponents());
		}

		// TODO VERIFICAR ESSE CAST ABAIXO!!! ISSO DEVE SEMPRE FUNCIONAR
		// POIS UM COMPONENTE DEVE SEMPRE TER UM SISTEMA COMO PAI, MAS
		// SE ESSA REGRA FALHAR, DEVE SER VERIFICADO A CONSTRUCAO DO
		// MODELO!
		for (IntegerComponent comp : propagationSet) {
			if (comp.getParent() instanceof IntegerAbstractComponent) {
				IntegerAbstractComponent pSystem = (IntegerAbstractComponent) comp.getParent();
				pSystem.addComponentToPropagationList(comp);
				addParentsToPropagationList(pSystem);
			} else
				throw new RuntimeException(
						"A component must have a system as its parent!");
			// comp.getParent().addComponentToPropagationList(comp);
			// addParentsToPropagationList(comp.getParent());
		}
	}

	private void addParentsToPropagationList(IntegerAbstractComponent integerAbstractComponent) {
		// TODO MODIFICADO O TRATAMENTO DEVIDO À MUDANÇA NA HERANÇA DA
		// IntegerSystem
		// QUE PASSA TAMBÉM A SER UM IntegerComponent.
		/*
		 * if (system.getParent() != null) { if (system instanceof
		 * IntegerComponent)
		 * system.getParent().addComponentToPropagationList((IntegerComponent
		 * )system); addParentsToPropagationList(system.getParent()); }
		 */

		if (integerAbstractComponent.getParent() != null) {
			integerAbstractComponent.getParent().addComponentToPropagationList(integerAbstractComponent);
			addParentsToPropagationList(integerAbstractComponent.getParent());
		}
	}

	/**
	 * Find a diagnose for the system.
	 * 
	 * @return the hyphotesis set.
	 */
	public FamilySet<IntegerComponent> diagnose() {
		super.getStatistics().clearVariables();

		MinimalHittingSet<IntegerComponent> mhs = new MinimalHittingSet<IntegerComponent>();
		ContributorsSetConstructor csc = new ContributorsSetConstructor();

		FamilySet<IntegerComponent> result = mhs.solve(csc);

		LOG.info(super.getStatistics().printStatistics());

		LOG.info("Result: " + result);
		return result;
	}

	/**
	 * Get the system model.
	 * 
	 * @return the system
	 */
	public IntegerAbstractComponent getSystem() {
		return system;
	}

	/**
	 * This class is just a wrapper to construct the conflict sets. Reiter's
	 * algorithm will use this class to callback when it needs a new conflict
	 * set. This class' resposibility also includes enable and disable the
	 * components during propagation.
	 * 
	 * @author wrp
	 * 
	 *         07/07/2008
	 */
	private class ContributorsSetConstructor extends
			ArrayFamilySet<IntegerComponent> {
		/**
		 * The current set of suspended components.
		 */
		private SetMaintainer<IntegerComponent> suspendedComponents = new SetMaintainer<IntegerComponent>();

		/**
		 * (non-Javadoc).
		 * 
		 * @see br.com.wrpinheiro.jid.jgraphlib.hittingset.AbstractFamilySet#findEmptyIntersection(br.com.wrpinheiro.jid.jgraphlib.set.SetMaintainer)
		 */
		@Override
		public SetMaintainer<IntegerComponent> findEmptyIntersection(
				SetMaintainer<IntegerComponent> componentsToBeSuspended) {

			LOG.debug("suspended: " + componentsToBeSuspended);
			SetMaintainer<IntegerComponent> result = new SetMaintainer<IntegerComponent>();

			// re-enable the suspended components.
			for (IntegerComponent c : this.suspendedComponents) {
				c.enable();
			}
			this.suspendedComponents.clear();

			// suspend the new components.
			for (IntegerComponent c : componentsToBeSuspended) {
				c.disable();
				this.suspendedComponents.add(c);
			}

			try {
				IntegerDiagnosisProblem.this.applyObservations();
			} catch (ConflictException ex) {
				IntegerDiagnosisProblem.super.getStatistics().addConflictSets();
				LOG.debug("CS:"
						+ IntegerDiagnosisProblem.super.getStatistics()
								.getConflictSets() + " " + ex.getConflictSet());
				result.addAll(ex.getConflictSet());
			}

			return result;
		}
	}
}
