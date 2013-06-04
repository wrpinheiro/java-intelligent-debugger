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
import br.com.wrpinheiro.jid.programdiagnosis.components.Nand;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class NandTest extends BooleanOperatorTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("NandTest", null);

		IntegerBinaryOperator nandOp = new Nand(system, "Na1", 0, null);

		assertNotNull(nandOp);
		assertNotNull(nandOp.getIn1());
		assertNotNull(nandOp.getIn2());
		assertNotNull(nandOp.getResult());

		assertEquals(nandOp, nandOp.getIn1().getOwner());
		assertEquals(nandOp, nandOp.getIn2().getOwner());
		assertEquals(nandOp, nandOp.getResult().getOwner());

		assertNull(nandOp.getIn1().getValue());
		assertNull(nandOp.getIn2().getValue());
		assertNull(nandOp.getResult().getValue());
	}

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("NandTest", null);

		Nand nandOp = new Nand(system, "Na1", 0, null);

		// in1 = ? ^ in2 = ? -> out = ?
		testOperation(nandOp, null, null, null, null, null, null);

		// in1 = 0 -> out = 0
		testOperation(nandOp, ZERO, null, null, ZERO, null, ONE);

		// in2 = 0 -> out = 0
		testOperation(nandOp, null, ZERO, null, null, ZERO, ONE);

		// in1 = in2 = 0 -> out = 0
		testOperation(nandOp, ZERO, ZERO, null, ZERO, ZERO, ONE);

		// in1 = 1 -> T
		testOperation(nandOp, ONE, null, null, ONE, null, null);

		// in2 = 1 -> T
		testOperation(nandOp, null, ONE, null, null, ONE, null);

		// in1 = in2 = 1 -> out = 1
		testOperation(nandOp, ONE, ONE, null, ONE, ONE, ZERO);

		// out = 0 ^ in1 = ? -> in2 = ?
		testOperation(nandOp, null, null, ZERO, ONE, ONE, ZERO);

		// out = 0 ^ in1 = 1-> in2 = 0
		testOperation(nandOp, ONE, null, ZERO, ONE, ONE, ZERO);

		// out = 0 ^ in2 = 1-> in1 = 0
		testOperation(nandOp, null, ONE, ZERO, ONE, ONE, ZERO);

		// out = 1 ^ in1 = 1 -> in2 = 1
		testOperation(nandOp, ONE, null, ONE, ONE, ZERO, ONE);

		// out = 1 ^ in2 = 1 -> in1 = 1
		testOperation(nandOp, null, ONE, ONE, ZERO, ONE, ONE);

		// out = 1 -> in1 = 1 ^ in2 = 1
		//testOperation(nandOp, null, null, ZERO, ONE, ONE, ZERO);
		
		// in1 = 1 ^ in2 = 1 ^ out = 1 -> T
		testOperation(nandOp, ONE, ONE, ZERO, ONE, ONE, ZERO);
		
		// in1 = 0 ^ in2 = 0 ^ out = 0 -> T
		testOperation(nandOp, ZERO, ZERO, ONE, ZERO, ZERO, ONE);
	}
}
