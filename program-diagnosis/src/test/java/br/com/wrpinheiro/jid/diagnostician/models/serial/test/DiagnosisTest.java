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
package br.com.wrpinheiro.jid.diagnostician.models.serial.test;


import org.junit.Assert;
import org.junit.Test;

import br.com.wrpinheiro.jgraphlib.familyset.ArrayFamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerDiagnosisProblem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

/**
 * @author wrp
 * 
 * 03/05/2008
 */
public class DiagnosisTest {
	@SuppressWarnings("unchecked")
	@Test
	public void testSerialThreeAndNotDiagnosis() {
		FlatSerialThreeAndNotSystem s = new FlatSerialThreeAndNotSystem();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"SerialThreeAndNotSystem", s);

		dp.addObservation("I1", 1);
		dp.addObservation("I2", 1);
		dp.addObservation("I3", 0);
		dp.addObservation("O1", 0);
		dp.addObservation("W", 1);
		dp.addObservation("Y", 1);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getAnd1()),
				new SetMaintainer<IntegerComponent>(s.getNot1()));

		Assert.assertEquals(afs, fs);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSerialThreeNandDiagnosis() {
		SerialThreeNandSystem s = new SerialThreeNandSystem();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"SerialThreeNandSystem", s);

		dp.addObservation("I1", 1);
		dp.addObservation("I2", 1);
		dp.addObservation("I3", 0);
		dp.addObservation("O1", 0);
		dp.addObservation("W", 1);
		dp.addObservation("Y", 1);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getNand1()));

		Assert.assertEquals(afs, fs);
	}
}
