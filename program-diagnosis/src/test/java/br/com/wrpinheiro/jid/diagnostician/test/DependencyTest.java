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

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.com.wrpinheiro.jid.diagnostician.models.test.GeneserethSystem;
import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 * 03/05/2008
 */
public class DependencyTest {

	@Test
	public void testInferenceWithMultAndIO() throws ConflictException {
		IntegerSystem system = new IntegerSystem("DependencyTest", null);

		IntegerBinaryOperator m = new IntegerMultiplier(system, "M1", 0, null);
		IntegerConnection in1Conn = new IntegerConnection(system, "in1conn", "in");
		IntegerConnection in2Conn = new IntegerConnection(system, "in2conn", "in");
		IntegerConnection outConn = new IntegerConnection(system, "outConn", "out");

		in1Conn.addPort(m.getIn1());
		in2Conn.addPort(m.getIn2());
		outConn.addPort(m.getResult());

		in1Conn.setValue(3);
		in2Conn.setValue(4);

		m.propagate();

		Set<IntegerComponent> in1Deps = new HashSet<IntegerComponent>();
		Set<IntegerComponent> in2Deps = new HashSet<IntegerComponent>();
		Set<IntegerComponent> outDeps = new HashSet<IntegerComponent>();
		outDeps.add(m);

		assertEquals(in1Deps, m.getIn1().getDependencies());
		assertEquals(in2Deps, m.getIn2().getDependencies());
		assertEquals(outDeps, m.getResult().getDependencies());

		assertEquals(in1Deps, in1Conn.getDependencies());
		assertEquals(in2Deps, in2Conn.getDependencies());
		assertEquals(outDeps, outConn.getDependencies());
	}

	@Test
	public void testGeneserethSimpleExamplePropagatingForward()
			throws ConflictException {
		IntegerSystem system = new IntegerSystem("DependencyTest", null);

		IntegerConnection A = new IntegerConnection(system, "A", "A");
		IntegerConnection B = new IntegerConnection(system, "B", "B");
		IntegerConnection C = new IntegerConnection(system, "C", "C");
		IntegerConnection D = new IntegerConnection(system, "D", "D");

		IntegerConnection F = new IntegerConnection(system, "F", "F");

		IntegerBinaryOperator M1 = new IntegerMultiplier(system, "M1", 0, null);
		IntegerBinaryOperator M2 = new IntegerMultiplier(system, "M2", 0, null);
		IntegerBinaryOperator A1 = new IntegerAdder(system, "A1", 0, null);

		A.addPort(M1.getIn1());
		C.addPort(M1.getIn2());
		B.addPort(M2.getIn1());
		D.addPort(M2.getIn2());
		F.addPort(A1.getResult());
		//F.addPort(M1.getResult());
		
		IntegerConnection X = new IntegerConnection(system, "X", "X");
		X.addPort(M1.getResult());
		X.addPort(A1.getIn1());
		
		IntegerConnection Y = new IntegerConnection(system, "Y", "Y");
		Y.addPort(M2.getResult());
		Y.addPort(A1.getIn2());

		A.setValue(3);
		B.setValue(2);
		C.setValue(2);
		D.setValue(3);

		M1.propagate();
		M2.propagate();
		A1.propagate();

		Set<IntegerComponent> deps = new HashSet<IntegerComponent>();

		assertEquals(deps, A.getDependencies());
		assertEquals(deps, B.getDependencies());
		assertEquals(deps, C.getDependencies());
		assertEquals(deps, D.getDependencies());

		assertEquals(deps, M1.getIn1().getDependencies());
		assertEquals(deps, M1.getIn2().getDependencies());
		assertEquals(deps, M2.getIn1().getDependencies());
		assertEquals(deps, M2.getIn2().getDependencies());

		// X
		deps.add(M1);
		assertEquals(deps, M1.getResult().getDependencies());
		assertEquals(deps, system.getConnection("X").getDependencies());
		assertEquals(deps, A1.getIn1().getDependencies());

		// Y
		deps.clear();
		deps.add(M2);
		assertEquals(deps, M2.getResult().getDependencies());
		assertEquals(deps, system.getConnection("Y").getDependencies());
		assertEquals(deps, A1.getIn2().getDependencies());

		// F
		deps.clear();
		deps.add(M1);
		deps.add(M2);
		deps.add(A1);

		assertEquals(deps, A1.getResult().getDependencies());
		assertEquals(deps, F.getDependencies());
	}

	@Test
	public void testGeneserethExamplePropagatingForward()
			throws ConflictException {
		GeneserethSystem s = new GeneserethSystem();

		s.getA().setValue(3);
		s.getB().setValue(2);
		s.getC().setValue(2);
		s.getD().setValue(3);
		s.getE().setValue(3);

		s.getM1().propagate();
		s.getM2().propagate();
		s.getM3().propagate();
		s.getA1().propagate();
		s.getA2().propagate();

		Set<IntegerComponent> deps = new HashSet<IntegerComponent>();

		assertEquals(deps, s.getA().getDependencies());
		assertEquals(deps, s.getB().getDependencies());
		assertEquals(deps, s.getC().getDependencies());
		assertEquals(deps, s.getD().getDependencies());
		assertEquals(deps, s.getE().getDependencies());

		assertEquals(deps, s.getM1().getIn1().getDependencies());
		assertEquals(deps, s.getM1().getIn2().getDependencies());
		assertEquals(deps, s.getM2().getIn1().getDependencies());
		assertEquals(deps, s.getM2().getIn2().getDependencies());
		assertEquals(deps, s.getM3().getIn1().getDependencies());
		assertEquals(deps, s.getM3().getIn2().getDependencies());

		// X
		deps.add(s.getM1());
		assertEquals(deps, s.getM1().getResult().getDependencies());
		assertEquals(deps, s.getX().getDependencies());
		assertEquals(deps, s.getA1().getIn1().getDependencies());

		// Y
		deps.clear();
		deps.add(s.getM2());
		assertEquals(deps, s.getM2().getResult().getDependencies());
		assertEquals(deps, s.getY().getDependencies());
		assertEquals(deps, s.getA1().getIn2().getDependencies());
		assertEquals(deps, s.getA2().getIn1().getDependencies());

		// Z
		deps.clear();
		deps.add(s.getM3());
		assertEquals(deps, s.getM3().getResult().getDependencies());
		assertEquals(deps, s.getZ().getDependencies());
		assertEquals(deps, s.getA2().getIn2().getDependencies());

		// F
		deps.clear();
		deps.add(s.getM1());
		deps.add(s.getM2());
		deps.add(s.getA1());
		assertEquals(deps, s.getA1().getResult().getDependencies());
		assertEquals(deps, s.getF().getDependencies());

		// G
		deps.clear();
		deps.add(s.getM2());
		deps.add(s.getM3());
		deps.add(s.getA2());
		assertEquals(deps, s.getA2().getResult().getDependencies());
		assertEquals(deps, s.getG().getDependencies());
	}

	@Test
	public void testGeneserethExampleWith_M2_Disabled() throws ConflictException {
		GeneserethSystem s = new GeneserethSystem();

		// the component M2 should not propagate values.
		s.getM2().disable();

		s.getA().setValue(3);
		s.getB().setValue(2);
		s.getC().setValue(2);
		s.getD().setValue(3);
		s.getE().setValue(3);
		s.getF().setValue(12);

		s.getM1().propagate();
		s.getM2().propagate();
		s.getM3().propagate();
		s.getA1().propagate();
		s.getA2().propagate();

		Set<IntegerComponent> deps = new HashSet<IntegerComponent>();

		// G
		deps.add(s.getM1());
		deps.add(s.getM3());
		deps.add(s.getA1());
		deps.add(s.getA2());
		assertEquals(deps, s.getA2().getResult().getDependencies());
		assertEquals(deps, s.getG().getDependencies());
	}

	@Test
	public void testGeneserethExamplePropagatingForwardAndBackward()
			throws ConflictException {
		GeneserethSystem s = new GeneserethSystem();

		s.getM2().disable();

		Set<IntegerComponent> deps = new HashSet<IntegerComponent>();
		deps.add(s.getM1());
		deps.add(s.getM3());
		deps.add(s.getA1());
		deps.add(s.getA2());

		try {
			s.getA().setValue(3);
			s.getB().setValue(2);
			s.getC().setValue(2);
			s.getD().setValue(3);
			s.getE().setValue(3);
			s.getF().setValue(10);
			s.getG().setValue(12);

			s.getM1().propagate();
			//s.getM2().propagate();
			s.getM3().propagate();
			s.getA1().propagate();
			s.getA2().propagate();

			Assert.fail();

		} catch (ConflictException cex) {
			assertEquals(deps, cex.getConflictSet());
		}
	}
}
