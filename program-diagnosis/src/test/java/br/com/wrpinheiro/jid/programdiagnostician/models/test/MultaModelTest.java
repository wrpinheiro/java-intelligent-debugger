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
package br.com.wrpinheiro.jid.programdiagnostician.models.test;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;

import br.com.wrpinheiro.jgraphlib.familyset.ArrayFamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.HypothesisValue;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerProgramDiagnosisProblem;
import br.com.wrpinheiro.jid.programdiagnosis.LineHyphotesisValue;

public class MultaModelTest {
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	/*
	 * Teste do MultaModel 1.
	 */
	
	@Test
	public void testMultaDiagnosis() throws ConflictException {
		MultaModel s = new MultaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Multa model", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "1000");
		obs.put("input1", "0");
		obs.put("input2", "0");
		obs.put("output0", "970");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<HypothesisValue> afs = new ArrayFamilySet<HypothesisValue>();
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(28)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(14)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(16)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(24)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(15)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(18)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(20)));
		afs.add(new SetMaintainer<HypothesisValue>(new HypothesisValue(s.getComponent("C11"))));
		afs.add(new SetMaintainer<HypothesisValue>(new HypothesisValue(s.getComponent("C5"))));

		Assert.assertEquals(afs, fs);
	}
}
