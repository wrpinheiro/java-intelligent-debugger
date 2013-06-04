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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jgraphlib.familyset.ArrayFamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

/**
 * @author wrp
 * 
 *         06/01/2009
 */
public class IntegerProgramDiagnosisProblem {

	/**
	 * The inner diagnosis problem used to solve this debugging problem.
	 */
	private IntegerDiagnosisProblem problem;

	/**
	 * Log instance.
	 */
	private static final Logger LOG = Logger
			.getLogger(IntegerProgramDiagnosisProblem.class);

	/**
	 * Construct a program diagnosis problem and set its name.
	 * 
	 * @param name
	 *            identification for the PDP.
	 * @param system
	 *            model for the system.
	 */
	public IntegerProgramDiagnosisProblem(String name, IntegerSystem system) {
		system.restart();

		problem = new IntegerDiagnosisProblem(name, system);
	}

	/**
	 * Create a mapping from lines to the components of hyphotesis.  
	 * @param hyphotesis the hyphotesis.
	 * @return
	 */
	private SetMaintainer<HypothesisValue> hyphotesisToLines(
			final SetMaintainer<IntegerComponent> hyphotesis) {

		Map<Set<Integer>, Set<IntegerComponent>> mapLinesToComps = new LinkedHashMap<Set<Integer>, Set<IntegerComponent>>();

		for (IntegerComponent comp : hyphotesis.toArray(new IntegerComponent[0])) {
			Set<IntegerComponent> comps = mapLinesToComps.get(comp.getProgramLines());
			if (comps == null) {
				comps = new LinkedHashSet<IntegerComponent>();
				mapLinesToComps.put(comp.getProgramLines(), comps);
			}
			comps.add(comp);
		}

		SetMaintainer<HypothesisValue> hv = new SetMaintainer<HypothesisValue>();
		for (Set<IntegerComponent> comps : mapLinesToComps.values()) {
			HypothesisValue h = new HypothesisValue(comps);	
			hv.add(h);
		}

		return hv;
	}

	// TODO fazer tratamento para disponibilizar nas hipóteses todos os
	// componentes com as mesmas linhas mas em hipóteses diferentes.
	public FamilySet<HypothesisValue> findHyphotesisSet() {
		FamilySet<IntegerComponent> hypothesisSet = problem.diagnose();
		SetMaintainer<IntegerComponent>[] hyps = hypothesisSet.getFamilySet();

		FamilySet<HypothesisValue> faultyLinesSet = new ArrayFamilySet<HypothesisValue>();

		for (SetMaintainer<IntegerComponent> hyphotesis : hyps) {
			SetMaintainer<HypothesisValue> newHyp = this
					.hyphotesisToLines(hyphotesis);

			SetMaintainer<HypothesisValue> firstHyp = faultyLinesSet.getEqual(newHyp);
			if (firstHyp != null) {
				mergeHyphotesis(firstHyp, newHyp);
			} else {
				faultyLinesSet.add(newHyp);
			}
		}

		LOG.info("Faulty lines: " + faultyLinesSet);
		return faultyLinesSet;
	}

	private void mergeHyphotesis(final SetMaintainer<HypothesisValue> firstHyp,
			final SetMaintainer<HypothesisValue> newHyp) {

		Map<Set<Integer>, HypothesisValue> mapLinesToHyphotesisValue = new LinkedHashMap<Set<Integer>, HypothesisValue>();

		for (HypothesisValue hv : firstHyp) {
			mapLinesToHyphotesisValue.put(hv.getProgramLines(), hv);
		}

		for (HypothesisValue newHv : newHyp) {
			HypothesisValue hv = mapLinesToHyphotesisValue.get(newHv.getProgramLines());
			hv.addComponents(newHv.getComponents());
		}
	}

	public void loadObservations(String testCase) {
		this.problem.loadObservations(testCase);
	}

	public void loadObservations(Map<String, String> inputsAndOutputs) {
		this.problem.loadObservations(inputsAndOutputs);
	}
}
