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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerLess;

public class IntegerLessTest extends BooleanOperatorTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("IntegerLessTest", null);

		IntegerBinaryOperator lessOp = new IntegerLess(system, "LESS1", 0, null);

		assertNotNull(lessOp);
		assertNotNull(lessOp.getIn1());
		assertNotNull(lessOp.getIn2());
		assertNotNull(lessOp.getResult());

		assertEquals(lessOp, lessOp.getIn1().getOwner());
		assertEquals(lessOp, lessOp.getIn2().getOwner());
		assertEquals(lessOp, lessOp.getResult().getOwner());

		assertNull(lessOp.getIn1().getValue());
		assertNull(lessOp.getIn2().getValue());
		assertNull(lessOp.getResult().getValue());
	}

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("IntegerLessTest", null);

		IntegerBinaryOperator lessOp = new IntegerLess(system, "LESS1", 0, null);

		// in1 = ? ^ in2 = ? -> out = ?
		testOperation(lessOp, null, null, null, null, null, null);

		// in1 = 0 -> out = 0
		testOperation(lessOp, ZERO, null, null, ZERO, null, null);

		// in2 = 0 -> out = 0
		testOperation(lessOp, null, ZERO, null, null, ZERO, null);

		testOperation(lessOp, null, null, ZERO, null, null, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, ZERO, ZERO, null, ZERO, ZERO, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, ZERO, ONE, null, ZERO, ONE, ONE);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, ONE, ZERO, null, ONE, ZERO, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, ZERO, null, ZERO, ZERO, null, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, ONE, null, ZERO, ONE, null, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, ZERO, null, ONE, ZERO, null, ONE);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, null, ZERO, ZERO, null, ZERO, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, null, ONE, ZERO, null, ONE, ZERO);

		// in1 = in2 = 0 -> out = 0
		testOperation(lessOp, null, ZERO, ONE, null, ZERO, ONE);

		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(lessOp, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);

		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(lessOp, ONE, ONE, ZERO, ONE, ONE, ZERO);

		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(lessOp, ZERO, ONE, ONE, ZERO, ONE, ONE);

		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(lessOp, ONE, ZERO, ZERO, ONE, ZERO, ZERO);
	}
}
