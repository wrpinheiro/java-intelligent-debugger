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
package br.com.wrpinheiro.jid.diagnostician.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.com.wrpinheiro.jgraphlib.familyset.ArrayFamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jid.diagnostician.models.test.FullAdderSystem;
import br.com.wrpinheiro.jid.diagnostician.models.test.GeneserethSystem;
import br.com.wrpinheiro.jid.diagnostician.models.test.InversorSystem;
import br.com.wrpinheiro.jid.diagnostician.models.test.Iscas85c17System;
import br.com.wrpinheiro.jid.diagnostician.models.test.TwoNandsDetailedSystem;
import br.com.wrpinheiro.jid.programdiagnosis.AbstractDiagnosisProblem;
import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerDiagnosisProblem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;

/**
 * @author wrp
 * 
 * 03/05/2008
 */
public class DiagnosisTest {
	@Test
	public void testDiagnose1() throws ConflictException {
		GeneserethSystem s = new GeneserethSystem();

		AbstractDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"Genesereth Circuit's example", s);
		dp.addObservation("A", 3);
		dp.addObservation("B", 2);
		dp.addObservation("C", 2);
		dp.addObservation("D", 3);
		dp.addObservation("E", 3);
		dp.addObservation("F", 10);
		dp.addObservation("G", 12);

		try {
			dp.applyObservations();
			Assert.fail("Should throw ConflictException!");
		} catch (ConflictException cex) {

			Set<IntegerComponent> deps = new HashSet<IntegerComponent>();
			deps.add(s.getM1());
			deps.add(s.getM2());
			deps.add(s.getA1());

			Assert.assertEquals(deps, cex.getConflictSet());
		}

		try {
			s.clearValues();
			s.getM1().disable();

			dp.applyObservations();

		} catch (ConflictException ex) {
			Assert.fail("Conflict detected where there in no conflict!!!");
		}

		try {
			s.clearValues();
			s.getM1().enable();
			s.getM2().disable();

			dp.applyObservations();

			Assert.fail("Should throw ConflictException!");
		} catch (ConflictException cex) {
			Set<IntegerComponent> deps = new HashSet<IntegerComponent>();
			deps.add(s.getM1());
			deps.add(s.getM3());
			deps.add(s.getA1());
			deps.add(s.getA2());

			Assert.assertEquals(deps, cex.getConflictSet());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDiagnosisProblem1() {
		GeneserethSystem s = new GeneserethSystem();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"Genesereth Circuit's example", s);
		dp.addObservation("A", 3);
		dp.addObservation("B", 2);
		dp.addObservation("C", 2);
		dp.addObservation("D", 3);
		dp.addObservation("E", 3);
		dp.addObservation("F", 10);
		dp.addObservation("G", 12);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getM1()),
				new SetMaintainer<IntegerComponent>(s.getA1()),
				new SetMaintainer<IntegerComponent>(s.getA2(), s.getM2()),
				new SetMaintainer<IntegerComponent>(s.getM2(), s.getM3()));

		Assert.assertEquals(afs, fs);

		/* Hypothesis discrimination. */

		/* X = 6 */
		dp.addObservation("X", 6);

		s.restart();

		fs = dp.diagnose();

		afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getA1()),
				new SetMaintainer<IntegerComponent>(s.getA2(), s.getM2()),
				new SetMaintainer<IntegerComponent>(s.getM2(), s.getM3()));

		Assert.assertEquals(afs, fs);

		/* Y = 4 */
		dp.addObservation("Y", 4);

		s.restart();

		fs = dp.diagnose();

		afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getA2(), s.getM2()),
				new SetMaintainer<IntegerComponent>(s.getM2(), s.getM3()));

		Assert.assertEquals(afs, fs);

		/* Z = 6 */
		dp.addObservation("Z", 6);

		s.restart();

		fs = dp.diagnose();

		afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getA2(), s.getM2()));

		Assert.assertEquals(afs, fs);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFullAdderDiagnosis() {
		FullAdderSystem s = new FullAdderSystem();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"Full Adder example", s);

		dp.addObservation("I1", 1);
		dp.addObservation("I2", 0);
		dp.addObservation("I3", 1);
		dp.addObservation("O1", 1);
		dp.addObservation("O2", 0);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getXor1()),
				new SetMaintainer<IntegerComponent>(s.getXor2(), s.getOr1()),
				new SetMaintainer<IntegerComponent>(s.getXor2(), s.getAnd2()));

		Assert.assertEquals(afs, fs);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInversorDiagnosis() {
		InversorSystem s = new InversorSystem();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"Inversor System", s);

		dp.addObservation("A", 1);
		dp.addObservation("C", 0);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getI1()),
				new SetMaintainer<IntegerComponent>(s.getI2()));

		Assert.assertEquals(afs, fs);

		/* B = 1 */
		s.restart();

		dp.addObservation("B", 1);

		fs = dp.diagnose();

		afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getI1()));

		Assert.assertEquals(afs, fs);

		/* B = 0 */
		s.restart();

		dp.addObservation("B", 0);

		fs = dp.diagnose();

		afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getI2()));

		Assert.assertEquals(afs, fs);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testTwoNandsDetailedDiagnosis() {
		TwoNandsDetailedSystem s = new TwoNandsDetailedSystem();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"Two Nand Detailed System", s);

		dp.addObservation("A", 0);
		dp.addObservation("B", 1);
		dp.addObservation("C", 0);
		dp.addObservation("D", 0);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getAnd2()),
				new SetMaintainer<IntegerComponent>(s.getNot2()));

		Assert.assertEquals(afs, fs);
	}
	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testTwoNandsDiagnosis() {
//		TwoNandsDetailedSystem ds = new TwoNandsDetailedSystem();
//		TwoNandsSystem s = new TwoNandsSystem(ds);
//
//		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
//				"Two Nands System", s);
//
//		dp.addObservation("A", 0);
//		dp.addObservation("B", 1);
//		dp.addObservation("C", 0);
//		dp.addObservation("D", 0);
//
//		FamilySet<IntegerComponent> fs = dp.diagnose();
//
//		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
//				new SetMaintainer<IntegerComponent>(s.getN2()));
//
//		Assert.assertEquals(afs, fs);
//	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testIscas85c17Diagnosis() {
		Iscas85c17System s = new Iscas85c17System();

		IntegerDiagnosisProblem dp = new IntegerDiagnosisProblem(
				"Iscas85c17System", s);

		dp.addObservation("I1", 0);
		dp.addObservation("I2", 0);
		dp.addObservation("I3", 1);
		dp.addObservation("I6", 1);
		dp.addObservation("I7", 0);
		dp.addObservation("I22", 1);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getN10()),
				new SetMaintainer<IntegerComponent>(s.getN16()),
				new SetMaintainer<IntegerComponent>(s.getN22()));

		Assert.assertEquals(afs, fs);
	}
// TODO Rever esses testes.	
/*
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
		dp.addObservation("Y", 0);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getAnd1()));

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
		dp.addObservation("Y", 0);

		FamilySet<IntegerComponent> fs = dp.diagnose();

		ArrayFamilySet<IntegerComponent> afs = new ArrayFamilySet<IntegerComponent>(
				new SetMaintainer<IntegerComponent>(s.getNand1()));

		Assert.assertEquals(afs, fs);
	}
*/
}
