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

import org.junit.Assert;
import org.junit.Test;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerDivisor;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class IntegerDivisorTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("IntegerDivisorTest", null);

		IntegerBinaryOperator d = new IntegerDivisor(system, "D1", 0, null);

		assertNotNull(d);
		assertNotNull(d.getIn1());
		assertNotNull(d.getIn2());
		assertNotNull(d.getResult());
		
		assertEquals(d, d.getIn1().getOwner());
		assertEquals(d, d.getIn2().getOwner());
		assertEquals(d, d.getResult().getOwner());
	}

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("IntegerDivisorTest", null);

		IntegerBinaryOperator D1 = new IntegerDivisor(system, "D1", 0, null);

		D1.propagate();

		Assert.assertNull(D1.getIn1().getValue());
		Assert.assertNull(D1.getIn2().getValue());
		Assert.assertNull(D1.getResult().getValue());
		D1.getIn1().setValue(new Integer(49), true);
		D1.propagate();
		Assert.assertEquals(new Integer(49), D1.getIn1().getValue());
		Assert.assertNull(D1.getIn2().getValue());
		Assert.assertNull(D1.getResult().getValue());

		D1.clearValues();
		D1.getIn1().setValue(new Integer(42), true);
		D1.getIn2().setValue(new Integer(7), true);
		Assert.assertEquals(new Integer(42), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(7), D1.getIn2().getValue());
		Assert.assertNull(D1.getResult().getValue());
		D1.propagate();
		Assert.assertEquals(new Integer(42), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(7), D1.getIn2().getValue());
		Assert.assertEquals(new Integer(6), D1.getResult().getValue());

		D1.clearValues();
		D1.getIn1().setValue(new Integer(45), true);
		D1.getIn2().setValue(new Integer(7), true);
		Assert.assertEquals(new Integer(45), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(7), D1.getIn2().getValue());
		Assert.assertNull(D1.getResult().getValue());
		D1.propagate();
		Assert.assertEquals(new Integer(45), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(7), D1.getIn2().getValue());
		Assert.assertEquals(new Integer(6), D1.getResult().getValue());
		
		D1.clearValues();
		D1.getIn1().setValue(new Integer(48), true);
		D1.getIn2().setValue(new Integer(7), true);
		Assert.assertEquals(new Integer(48), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(7), D1.getIn2().getValue());
		Assert.assertNull(D1.getResult().getValue());
		D1.propagate();
		Assert.assertEquals(new Integer(48), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(7), D1.getIn2().getValue());
		Assert.assertEquals(new Integer(6), D1.getResult().getValue());		
		
		D1.clearValues();
		D1.getIn1().setValue(new Integer(-18), true);
		D1.getResult().setValue(new Integer(9), true);
		Assert.assertEquals(new Integer(-18), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(9), D1.getResult().getValue());
		Assert.assertNull(D1.getIn2().getValue());
		D1.propagate();
		Assert.assertEquals(new Integer(-18), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(-2), D1.getIn2().getValue());
		Assert.assertEquals(new Integer(9), D1.getResult().getValue());

		D1.clearValues();
		D1.getIn2().setValue(new Integer(11), true);
		D1.getResult().setValue(new Integer(-2), true);
		Assert.assertNull(D1.getIn1().getValue());
		Assert.assertEquals(new Integer(11), D1.getIn2().getValue());
		Assert.assertEquals(new Integer(-2), D1.getResult().getValue());
		D1.propagate();
		Assert.assertEquals(new Integer(-22), D1.getIn1().getValue());
		Assert.assertEquals(new Integer(11), D1.getIn2().getValue());
		Assert.assertEquals(new Integer(-2), D1.getResult().getValue());
	}
}
