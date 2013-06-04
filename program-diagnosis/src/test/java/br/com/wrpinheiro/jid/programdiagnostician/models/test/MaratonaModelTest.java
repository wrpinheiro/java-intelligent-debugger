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

public class MaratonaModelTest {
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	/*
	 * Teste do MaratonaModel 1.
	 */
	
	@Test
	@SuppressWarnings("unchecked")
	public void testMaratonaDiagnosis() throws ConflictException {
		MaratonaModel s = new MaratonaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Maratona System", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "1");
		obs.put("input1", "5");
		obs.put("input2", "100");
		obs.put("input3", "8000");
		obs.put("input4", "55");

		obs.put("output0", "2");
		obs.put("output1", "55000");

		obs.put("0", "3600");
		obs.put("600", "600");
		obs.put("1000", "1000");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<HypothesisValue> afs = new ArrayFamilySet<HypothesisValue>(
				new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(11)),
				new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(14)),
				new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(15)),
				new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(16)),
				new SetMaintainer<HypothesisValue>(new HypothesisValue(s.getComponent("C16"))));

		Assert.assertEquals(afs, fs);
	}
	
	@Test
	public void testMaratonaDiagnosis2() throws ConflictException {
		MaratonaModel s = new MaratonaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Maratona System", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "1");
		obs.put("input1", "0");
		obs.put("input2", "100");
		obs.put("input3", "3700");
		obs.put("input4", "60");

		obs.put("output0", "1");
		obs.put("output1", "60000");

		obs.put("0", "3600");
		obs.put("600", "600");
		obs.put("1000", "1000");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<Integer> afs = new ArrayFamilySet<Integer>();

		Assert.assertEquals(afs, fs);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testMaratonaDiagnosis3() throws ConflictException {
		MaratonaModel s = new MaratonaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Maratona System", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "2");
		obs.put("input1", "10");
		obs.put("input2", "0");
		obs.put("input3", "23400");
		obs.put("input4", "70");

		obs.put("output0", "3");
		obs.put("output1", "70000");

		obs.put("0", "3600");
		obs.put("600", "600");
		obs.put("1000", "1000");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

    ArrayFamilySet<HypothesisValue> afs = new ArrayFamilySet<HypothesisValue>(
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(11)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(14)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(15)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(16)),
        new SetMaintainer<HypothesisValue>(new HypothesisValue(s.getComponent("C16"))));

		Assert.assertEquals(afs, fs);
	}
	
	/*
	 * Teste do MaratonaModel 2.
	 */
	
	@Test
	@SuppressWarnings("unchecked")
	public void testMaratonaDiagnosis4() throws ConflictException {
		MaratonaModel s = new MaratonaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Maratona System", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "1");
		obs.put("input1", "5");
		obs.put("input2", "100");
		obs.put("input3", "8000");
		obs.put("input4", "55");

		obs.put("output0", "2");
		obs.put("output1", "55000");

		obs.put("0", "3600");
		obs.put("600", "600");
		obs.put("1000", "100");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<HypothesisValue> afs = new ArrayFamilySet<HypothesisValue>(
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            14), new LineHyphotesisValue(17)),		    
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            15), new LineHyphotesisValue(17)), 
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            11), new LineHyphotesisValue(17)),  
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            16), new LineHyphotesisValue(17)),
        new SetMaintainer<HypothesisValue>(new HypothesisValue(
            s.getComponent("C16")), new LineHyphotesisValue(17)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            14), new LineHyphotesisValue(18)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            15), new LineHyphotesisValue(18)),  
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            16), new LineHyphotesisValue(18)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            11), new LineHyphotesisValue(18)),
        new SetMaintainer<HypothesisValue>(new HypothesisValue(
            s.getComponent("C16")), new LineHyphotesisValue(18)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            13), new LineHyphotesisValue(14)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            13), new LineHyphotesisValue(15)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            13), new LineHyphotesisValue(16)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            11), new LineHyphotesisValue(13)),
        new SetMaintainer<HypothesisValue>(new LineHyphotesisValue(
            13), new HypothesisValue(s.getComponent("C16"))));

		Assert.assertEquals(afs, fs);
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testMaratonaDiagnosis5() throws ConflictException {
		MaratonaModel s = new MaratonaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Maratona System", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "1");
		obs.put("input1", "0");
		obs.put("input2", "100");
		obs.put("input3", "3700");
		obs.put("input4", "60");

		obs.put("output0", "1");
		obs.put("output1", "60000");

		obs.put("0", "3600");
		obs.put("600", "600");
		obs.put("1000", "100");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<LineHyphotesisValue> afs = new ArrayFamilySet<LineHyphotesisValue>(				
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(13)),
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(17)),
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(18)));

		Assert.assertEquals(afs, fs);
	}
	
}
