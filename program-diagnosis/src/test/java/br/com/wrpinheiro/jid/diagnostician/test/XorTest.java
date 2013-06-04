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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.Xor;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class XorTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("XorTest", null);

		IntegerBinaryOperator xorOp = new Xor(system, "X1", 0, null);

		assertNotNull(xorOp);
		assertNotNull(xorOp.getIn1());
		assertNotNull(xorOp.getIn2());
		assertNotNull(xorOp.getResult());

		assertEquals(xorOp, xorOp.getIn1().getOwner());
		assertEquals(xorOp, xorOp.getIn2().getOwner());
		assertEquals(xorOp, xorOp.getResult().getOwner());

		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
	}

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("XorTest", null);

		IntegerBinaryOperator xorOp = new Xor(system, "A1", 0, null);

		xorOp.propagate();

		// in1 = 0 -> T
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn1().setValue(ZERO, true);
		//assertNull(xorOp.propagate());
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		
		// in2 = 0 -> T
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn2().setValue(ZERO, true);
		//assertNull(xorOp.propagate());
		xorOp.propagate();
		assertNull(xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		
		// in1 = 1 -> T
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn1().setValue(ONE, true);
		//assertNull(xorOp.propagate());
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		
		// in2 = 1 -> T
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn2().setValue(ONE, true);
		//assertNull(xorOp.propagate());
		xorOp.propagate();
		assertNull(xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());

		// in1 = in2 = 0 -> out = 0
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn1().setValue(ZERO, true);
		xorOp.getIn2().setValue(ZERO, true);
		//assertEquals(xorOp.getResult(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertEquals(ZERO, xorOp.getResult().getValue());
		
		// in1 = in2 = 1 -> out = 0
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn1().setValue(ONE, true);
		xorOp.getIn2().setValue(ONE, true);
		//assertEquals(xorOp.getResult(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertEquals(ZERO, xorOp.getResult().getValue());

		// in1 = 0 ^ in2 = 1 -> out = 1
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn1().setValue(ZERO, true);
		xorOp.getIn2().setValue(ONE, true);
		//assertEquals(xorOp.getResult(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertEquals(ONE, xorOp.getResult().getValue());
		
		// in1 = 1 ^ in2 = 0 -> out = 1
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getIn1().setValue(ONE, true);
		xorOp.getIn2().setValue(ZERO, true);
		//assertEquals(xorOp.getResult(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertEquals(ONE, xorOp.getResult().getValue());

		// out = 0 ^ in1 = 0 -> in2 = 0
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ZERO, true);
		xorOp.getIn1().setValue(ZERO, true);
		//assertEquals(xorOp.getIn2(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertEquals(ZERO, xorOp.getResult().getValue());
		
		// out = 0 ^ in1 = 1 -> in2 = 1
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ZERO, true);
		xorOp.getIn1().setValue(ONE, true);
		//assertEquals(xorOp.getIn2(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertEquals(ZERO, xorOp.getResult().getValue());
		
		// out = 1 ^ in1 = 0 -> in2 = 1
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ONE, true);
		xorOp.getIn1().setValue(ZERO, true);
		//assertEquals(xorOp.getIn2(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertEquals(ONE, xorOp.getResult().getValue());

		// out = 1 ^ in1 = 1 -> in2 = 0
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ONE, true);
		xorOp.getIn1().setValue(ONE, true);
		//assertEquals(xorOp.getIn2(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertEquals(ONE, xorOp.getResult().getValue());
		
		// out = 0 ^ in2 = 0 -> in1 = 0
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ZERO, true);
		xorOp.getIn2().setValue(ZERO, true);
		//assertEquals(xorOp.getIn1(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertEquals(ZERO, xorOp.getResult().getValue());
		
		// out = 0 ^ in2 = 1 -> in1 = 1
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ZERO, true);
		xorOp.getIn2().setValue(ONE, true);
		//assertEquals(xorOp.getIn1(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertEquals(ZERO, xorOp.getResult().getValue());
		
		// out = 1 ^ in2 = 0 -> in1 = 1
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ONE, true);
		xorOp.getIn2().setValue(ZERO, true);
		//assertEquals(xorOp.getIn1(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ONE, xorOp.getIn1().getValue());
		assertEquals(ZERO, xorOp.getIn2().getValue());
		assertEquals(ONE, xorOp.getResult().getValue());

		// out = 1 ^ in2 = 1 -> in1 = 0
		xorOp.clearValues();
		assertNull(xorOp.getIn1().getValue());
		assertNull(xorOp.getIn2().getValue());
		assertNull(xorOp.getResult().getValue());
		xorOp.getResult().setValue(ONE, true);
		xorOp.getIn2().setValue(ONE, true);
		//assertEquals(xorOp.getIn1(), xorOp.propagate()[0]);
		xorOp.propagate();
		assertEquals(ZERO, xorOp.getIn1().getValue());
		assertEquals(ONE, xorOp.getIn2().getValue());
		assertEquals(ONE, xorOp.getResult().getValue());
	}
}
