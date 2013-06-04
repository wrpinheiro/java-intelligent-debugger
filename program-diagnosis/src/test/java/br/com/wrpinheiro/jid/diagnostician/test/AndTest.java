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
import br.com.wrpinheiro.jid.programdiagnosis.components.And;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class AndTest extends BooleanOperatorTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("AndTest", null);

		IntegerBinaryOperator andOp = new And(system, "A1", 0, null);

		assertNotNull(andOp);
		assertNotNull(andOp.getIn1());
		assertNotNull(andOp.getIn2());
		assertNotNull(andOp.getResult());

		assertEquals(andOp, andOp.getIn1().getOwner());
		assertEquals(andOp, andOp.getIn2().getOwner());
		assertEquals(andOp, andOp.getResult().getOwner());

		assertNull(andOp.getIn1().getValue());
		assertNull(andOp.getIn2().getValue());
		assertNull(andOp.getResult().getValue());
	}

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("AndTest", null);

		And andOp = new And(system, "A1", 0, null);

		// in1 = ? ^ in2 = ? -> out = ?
		testOperation(andOp, null, null, null, null, null, null);

		// in1 = 0 -> out = 0
		testOperation(andOp, ZERO, null, null, ZERO, null, ZERO);

		// in2 = 0 -> out = 0
		testOperation(andOp, null, ZERO, null, null, ZERO, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(andOp, ZERO, ZERO, null, ZERO, ZERO, ZERO);

		// in1 = 1 -> T
		testOperation(andOp, ONE, null, null, ONE, null, null);

		// in2 = 1 -> T
		testOperation(andOp, null, ONE, null, null, ONE, null);

		// in1 = in2 = 1 -> out = 1
		testOperation(andOp, ONE, ONE, null, ONE, ONE, ONE);

		// out = 0 ^ in1 = ? -> in2 = ?
		testOperation(andOp, null, null, ZERO, null, null, ZERO);

		// out = 0 ^ in1 = 1-> in2 = 0
		testOperation(andOp, ONE, null, ZERO, ONE, ZERO, ZERO);

		// out = 0 ^ in2 = 1-> in1 = 0
		testOperation(andOp, null, ONE, ZERO, ZERO, ONE, ZERO);

		// out = 1 ^ in1 = 1 -> in2 = 1
		testOperation(andOp, ONE, null, ONE, ONE, ONE, ONE);

		// out = 1 ^ in2 = 1 -> in1 = 1
		testOperation(andOp, null, ONE, ONE, ONE, ONE, ONE);

		// out = 1 -> in1 = 1 ^ in2 = 1
		testOperation(andOp, null, null, ONE, ONE, ONE, ONE);
		
		// in1 = 1 ^ in2 = 1 ^ out = 1 -> T
		testOperation(andOp, ONE, ONE, ONE, ONE, ONE, ONE);
		
		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(andOp, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);
	}
}
