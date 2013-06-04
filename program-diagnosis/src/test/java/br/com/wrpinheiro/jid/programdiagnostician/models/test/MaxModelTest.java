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

public class MaxModelTest {
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	@Test
	public void testMaxDiagnosis1() throws ConflictException {
		MaxModel s = new MaxModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Max Program", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input_a", "4");
		obs.put("input_b", "5");
		obs.put("output_greater", "15");
		obs.put("const6", "3");
		obs.put("const9", "3");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<HypothesisValue> afs = new ArrayFamilySet<HypothesisValue>();
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(5)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(3)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(11)));
		afs.add(new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(4)));
		afs.add(new SetMaintainer<HypothesisValue>(new HypothesisValue(s.getComponent("c3"))));

		Assert.assertEquals(afs, fs);
	}
}
