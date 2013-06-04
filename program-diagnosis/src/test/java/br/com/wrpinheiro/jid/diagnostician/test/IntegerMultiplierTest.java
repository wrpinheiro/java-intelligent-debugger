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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class IntegerMultiplierTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("IntegerMultiplierTest", null);

		IntegerBinaryOperator m = new IntegerMultiplier(system, "M1", 0, null);

		assertNotNull(m);
		assertNotNull(m.getIn1());
		assertNotNull(m.getIn2());
		assertNotNull(m.getResult());
		
		assertEquals(m, m.getIn1().getOwner());
		assertEquals(m, m.getIn2().getOwner());
		assertEquals(m, m.getResult().getOwner());
	}

	// private Integer propagate(BinaryOperator<Integer> m, int i1, int i2)
	// throws ConflictException {
	// m.getInputPort1().setValue(new Integer(i1));
	// m.getInputPort2().setValue(new Integer(i2));
	// return m.getOutputPort().getValue();
	// }

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("IntegerMultiplierTest", null);

		IntegerBinaryOperator m = new IntegerMultiplier(system, "M1", 0, null);

		m.propagate();

		Assert.assertNull(m.getIn1().getValue());
		Assert.assertNull(m.getIn2().getValue());
		Assert.assertNull(m.getResult().getValue());
		m.getIn1().setValue(new Integer(5), true);
		m.propagate();
		Assert.assertEquals(new Integer(5), m.getIn1().getValue());
		Assert.assertNull(m.getIn2().getValue());
		Assert.assertNull(m.getResult().getValue());

		m.clearValues();
		m.getIn1().setValue(new Integer(6), true);
		m.getIn2().setValue(new Integer(3), true);
		Assert.assertEquals(new Integer(6), m.getIn1().getValue());
		Assert.assertEquals(new Integer(3), m.getIn2().getValue());
		Assert.assertNull(m.getResult().getValue());
		m.propagate();
		Assert.assertEquals(new Integer(6), m.getIn1().getValue());
		Assert.assertEquals(new Integer(3), m.getIn2().getValue());
		Assert.assertEquals(new Integer(18), m.getResult().getValue());

		m.clearValues();
		Assert.assertNull(m.getIn1().getValue());
		Assert.assertNull(m.getIn2().getValue());
		Assert.assertNull(m.getResult().getValue());

		m.clearValues();
		m.getIn1().setValue(new Integer(-2), true);
		m.getResult().setValue(new Integer(8), true);
		Assert.assertEquals(new Integer(-2), m.getIn1().getValue());
		Assert.assertEquals(new Integer(8), m.getResult().getValue());
		Assert.assertNull(m.getIn2().getValue());
		m.propagate();
		Assert.assertEquals(new Integer(-2), m.getIn1().getValue());
		Assert.assertEquals(new Integer(-4), m.getIn2().getValue());
		Assert.assertEquals(new Integer(8), m.getResult().getValue());

		m.clearValues();
		m.getIn2().setValue(new Integer(-5), true);
		m.getResult().setValue(new Integer(-30), true);
		Assert.assertNull(m.getIn1().getValue());
		Assert.assertEquals(new Integer(-5), m.getIn2().getValue());
		Assert.assertEquals(new Integer(-30), m.getResult().getValue());
		m.propagate();
		Assert.assertEquals(new Integer(6), m.getIn1().getValue());
		Assert.assertEquals(new Integer(-5), m.getIn2().getValue());
		Assert.assertEquals(new Integer(-30), m.getResult().getValue());
	}
}
