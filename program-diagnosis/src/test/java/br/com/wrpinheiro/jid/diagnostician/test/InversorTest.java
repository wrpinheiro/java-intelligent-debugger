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

import static br.com.wrpinheiro.jid.programdiagnosis.components.BooleanUtils.ONE;
import static br.com.wrpinheiro.jid.programdiagnosis.components.BooleanUtils.ZERO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.Inversor;

/**
 * @author wrp
 * 
 *         Sep 2, 2008
 */
public class InversorTest {
	
	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("InversorTest", null);

		Inversor inv = new Inversor(system, "I1", 0, null);

		assertNotNull(inv);
		assertNotNull(inv.getIn());
		assertNotNull(inv.getOut());

		assertEquals(inv, inv.getIn().getOwner());
		assertEquals(inv, inv.getOut().getOwner());

		assertNull(inv.getIn().getValue());
		assertNull(inv.getOut().getValue());
	}

	private void testOperation(Inversor inv, Integer in1,
			Integer in2, Integer out1, Integer out2)
			throws ConflictException {

		inv.clearValues();
		assertNull(inv.getIn().getValue());
		assertNull(inv.getOut().getValue());

		inv.getIn().setValue(in1, true);
		inv.getOut().setValue(in2, true);

		//IntegerPort[] propagatedPorts = 
		
		inv.propagate();

//		int count = 0;
//		int nPropagated = in1 != out1 ? 1 : 0;
//		nPropagated = in2 != out2 ? nPropagated + 1 : nPropagated;

//		if (nPropagated > 0)
//			assertEquals(nPropagated, propagatedPorts.length);
//
//		if (in1 != out1) {
//			assertEquals(inv.getIn(), propagatedPorts[count++]);
//		}
//		if (in2 != out2) {
//			assertEquals(inv.getOut(), propagatedPorts[count++]);
//		}

		if (out1 == null)
			assertNull(inv.getIn().getValue());
		else
			assertEquals(out1, inv.getIn().getValue());

		if (out2 == null)
			assertNull(inv.getOut().getValue());
		else
			assertEquals(out2, inv.getOut().getValue());
	}
	
	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("InversorTest", null);

		Inversor inv = new Inversor(system, "I1", 0, null);

		// in1 = ? ^ out = ? -> in1 = ? ^out = ?
		testOperation(inv, null, null, null, null);

		// in1 = 1 -> out = 0
		testOperation(inv, ONE, null, ONE, ZERO);
		
		// in1 = 0 -> out = 1
		testOperation(inv, ZERO, null, ZERO, ONE);

		// in1 = 0 ^ out = 0 -> in1 = 0 ^ out = 0
		testOperation(inv, ZERO, ONE, ZERO, ONE);
		
		// in1 = 1 ^ out = 0 -> in1 = 1 ^ out = 0
		testOperation(inv, ONE, ZERO, ONE, ZERO);
		
		// out = 1 -> in = 0
		testOperation(inv, null, ONE, ZERO, ONE);
		
		// out = 0 -> in = 1
		testOperation(inv, null, ZERO, ONE, ZERO);
		
		inv.disable();
		
		// in1 = ? ^ out = ? -> in1 = ? ^out = ?
		testOperation(inv, null, null, null, null);

		// in1 = 1 -> out = 0
		testOperation(inv, ONE, null, ONE, null);
		
		// in1 = 0 -> out = 1
		testOperation(inv, ZERO, null, ZERO, null);

		// in1 = 0 ^ out = 0 -> in1 = 0 ^ out = 0
		testOperation(inv, ZERO, ONE, ZERO, ONE);
		
		// in1 = 1 ^ out = 0 -> in1 = 1 ^ out = 0
		testOperation(inv, ONE, ZERO, ONE, ZERO);
		
		// out = 1 -> in = 0
		testOperation(inv, null, ONE, null, ONE);
		
		// out = 0 -> in = 1
		testOperation(inv, null, ZERO, null, ZERO);
		
	}	
}
