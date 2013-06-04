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
import br.com.wrpinheiro.jid.programdiagnosis.components.Or;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class OrTest extends BooleanOperatorTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("OrTest", null);

		IntegerBinaryOperator orOp = new Or(system, "O1", 0, null);

		assertNotNull(orOp);
		assertNotNull(orOp.getIn1());
		assertNotNull(orOp.getIn2());
		assertNotNull(orOp.getResult());

		assertEquals(orOp, orOp.getIn1().getOwner());
		assertEquals(orOp, orOp.getIn2().getOwner());
		assertEquals(orOp, orOp.getResult().getOwner());

		assertNull(orOp.getIn1().getValue());
		assertNull(orOp.getIn2().getValue());
		assertNull(orOp.getResult().getValue());
	}

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("OrTest", null);

		Or orOp = new Or(system, "O1", 0, null);

		// in1 = ? ^ in2 = ? -> out = ?
		testOperation(orOp, null, null, null, null, null, null);

		// in1 = 1 -> out = 1
		testOperation(orOp, ONE, null, null, ONE, null, ONE);
		
		// in2 = 1 -> out = 1
		testOperation(orOp, null, ONE, null, null, ONE, ONE);

		// in1 = in2 = 1 -> out = 1
		testOperation(orOp, ONE, ONE, null, ONE, ONE, ONE);

		// in1 = 0 -> T
		testOperation(orOp, ZERO, null, null, ZERO, null, null);

		// in2 = 0 -> T
		testOperation(orOp, null, ZERO, null, null, ZERO, null);

		// in1 = in2 = 0 -> out = 0
		testOperation(orOp, ZERO, ZERO, null, ZERO, ZERO, ZERO);
		
		// out = 1 ^ in1 = ? -> in2 = ?
		testOperation(orOp, null, null, ONE, null, null, ONE);
		
		// out = 1 ^ in1 = 0-> in2 = 1
		testOperation(orOp, ZERO, null, ONE, ZERO, ONE, ONE);

		// out = 1 ^ in2 = 0-> in1 = 1
		testOperation(orOp, null, ZERO, ONE, ONE, ZERO, ONE);

		// out = 0 ^ in1 = 0 -> in2 = 0
		testOperation(orOp, ZERO, null, ZERO, ZERO, ZERO, ZERO);

		// out = 0 ^ in2 = 0 -> in1 = 0
		testOperation(orOp, null, ZERO, ZERO, ZERO, ZERO, ZERO);

		// out = 0 -> in1 = 0 ^ in2 = 0
		testOperation(orOp, null, null, ZERO, ZERO, ZERO, ZERO);
		
		// in1 = 1 ^ in2 = 1 ^ out = 1 -> T
		testOperation(orOp, ONE, ONE, ONE, ONE, ONE, ONE);
		
		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(orOp, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);
	}
}
