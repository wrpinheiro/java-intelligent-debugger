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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.wrpinheiro.jid.diagnostician.models.test.GeneserethSystem;
import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

public class IntegerPortTest {

	@Test
	public void testCreate() {
		new IntegerSystem("IntegerPortTest", null);

		IntegerPort p1 = new IntegerPort("P1", null);
		IntegerPort p2 = new IntegerPort("P2", null);

		assertNotNull(p1);
		assertNotNull(p2);

		assertNull(p1.getOwner());
		assertNull(p2.getOwner());

		assertNotNull(p1.getConnections());
		assertNotNull(p2.getConnections());
		
		assertEquals(0, p1.getConnections().size());
		assertEquals(0, p2.getConnections().size());
	}

	@Test
	public void testGetAndSetValue() throws ConflictException {
		new IntegerSystem("IntegerPortTest", null);

		IntegerPort p1 = new IntegerPort("P1", null);
		IntegerPort p2 = new IntegerPort("P2", null);

		Integer iv1 = new Integer(1);
		Integer iv2 = new Integer(2);

		p1.setValue(iv1, true);
		p2.setValue(iv2, true);

		assertEquals(iv1, p1.getValue());
		assertEquals(iv2, p2.getValue());
	}

	@Test
	public void testConnection() throws ConflictException {
		IntegerSystem system = new IntegerSystem("IntegerPortTest", null);

		IntegerPort p1 = new IntegerPort("P1", null);
		IntegerPort p2 = new IntegerPort("P2", null);

		IntegerConnection c = new IntegerConnection(system, "c", "C");

		c.addPort(p1);
		c.addPort(p2);

		p1.getConnections().iterator().next().addPort(p2);
		p1.setValue(new Integer(10), true);

		assertEquals(new Integer(10), p2.getValue());
	}

	private void assertAllPorts(IntegerPort[] ports, Integer expectedValue) {
		for (IntegerPort p : ports) {
			assertEquals(expectedValue, p.getValue());
		}

	}

	private void clearPortValues(IntegerPort p1, IntegerPort p2, IntegerPort p3, IntegerPort p4) {
		p1.clearValue();
		p2.clearValue();
		p3.clearValue();
		p4.clearValue();
	}
	
	@Test
	public void testManyConnectedPorts() throws ConflictException {
		IntegerSystem system = new IntegerSystem("IntegerPortTest", null);

		IntegerPort p1 = new IntegerPort("P1", null);
		IntegerPort p2 = new IntegerPort("P2", null);
		IntegerPort p3 = new IntegerPort("P3", null);
		IntegerPort p4 = new IntegerPort("P4", null);

		new IntegerConnection(system, "c1", "c").addPort(p1).addPort(p2);
		new IntegerConnection(system, "c2", "c").addPort(p1).addPort(p3);
		new IntegerConnection(system, "c3", "c").addPort(p3).addPort(p4);
		
		p3.setValue(new Integer(12), true);
		assertAllPorts(new IntegerPort[] { p1, p2, p3, p4 }, new Integer(12));

		clearPortValues(p1, p2, p3, p4);
		p1.setValue(new Integer(5), true);
		assertAllPorts(new IntegerPort[] { p1, p2, p3, p4 }, new Integer(5));

		clearPortValues(p1, p2, p3, p4);
		p2.setValue(new Integer(-3), true);
		assertAllPorts(new IntegerPort[] { p1, p2, p3, p4 }, new Integer(-3));

		clearPortValues(p1, p2, p3, p4);
		p4.setValue(new Integer(7), true);
		assertAllPorts(new IntegerPort[] { p1, p2, p3, p4 }, new Integer(7));
	}

	@Test
	public void testConnectedComponents() {
		GeneserethSystem s = new GeneserethSystem();

		Set<IntegerComponent> connectedComps = new HashSet<IntegerComponent>();

		// A and C
		connectedComps.add(s.getM1());
		assertEquals(connectedComps, s.getM1().getIn1()
				.getConnectedComponents());

		connectedComps.add(s.getM3());
		assertEquals(connectedComps, s.getM1().getIn2()
				.getConnectedComponents());
		assertEquals(connectedComps, s.getM3().getIn1()
				.getConnectedComponents());

		// B and D
		connectedComps.clear();
		connectedComps.add(s.getM2());
		assertEquals(connectedComps, s.getM2().getIn1()
				.getConnectedComponents());
		assertEquals(connectedComps, s.getM2().getIn2()
				.getConnectedComponents());

		// E (the C connection has already been verified).
		connectedComps.clear();
		connectedComps.add(s.getM3());
		assertEquals(connectedComps, s.getM3().getIn2()
				.getConnectedComponents());

		// F
		connectedComps.clear();
		connectedComps.add(s.getA1());
		assertEquals(connectedComps, s.getA1().getResult()
				.getConnectedComponents());

		// G
		connectedComps.clear();
		connectedComps.add(s.getA2());
		assertEquals(connectedComps, s.getA2().getResult()
				.getConnectedComponents());

		// X
		connectedComps.clear();
		connectedComps.add(s.getM1());
		connectedComps.add(s.getA1());
		assertEquals(connectedComps, s.getM1().getResult()
				.getConnectedComponents());
		assertEquals(connectedComps, s.getA1().getIn1()
				.getConnectedComponents());

		// Y
		connectedComps.clear();
		connectedComps.add(s.getM2());
		connectedComps.add(s.getA1());
		connectedComps.add(s.getA2());
		assertEquals(connectedComps, s.getM2().getResult()
				.getConnectedComponents());
		assertEquals(connectedComps, s.getA1().getIn2()
				.getConnectedComponents());
		assertEquals(connectedComps, s.getA2().getIn1()
				.getConnectedComponents());

		// Z
		connectedComps.clear();
		connectedComps.add(s.getM3());
		connectedComps.add(s.getA2());
		assertEquals(connectedComps, s.getM3().getResult()
				.getConnectedComponents());
		assertEquals(connectedComps, s.getA2().getIn2()
				.getConnectedComponents());
	}
}

