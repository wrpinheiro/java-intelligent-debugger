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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class IntegerAdderTest {

	@Test
	public void testCreate() {
		IntegerSystem system = new IntegerSystem("IntegerAdderTest", null);

		IntegerBinaryOperator add = new IntegerAdder(system, "A1", 0, null);

		assertNotNull(add);
		assertNotNull(add.getIn1());
		assertNotNull(add.getIn2());
		assertNotNull(add.getResult());
		
		assertEquals(add, add.getIn1().getOwner());
		assertEquals(add, add.getIn2().getOwner());
		assertEquals(add, add.getResult().getOwner());

	}

	// private Integer propagate(BinaryOperator<Integer> m, int i1, int i2)
	// throws ConflictException {
	// m.getInputPort1().setValue(new Integer(i1));
	// m.getInputPort2().setValue(new Integer(i2));
	// return m.getOutputPort().getValue();
	// }

	@Test
	public void testPropagate() throws ConflictException {
		IntegerSystem system = new IntegerSystem("IntegerAdderTest", null);

		IntegerBinaryOperator add = new IntegerAdder(system, "A1", 0, null);

		add.propagate();
		
		assertEquals(add, add.getIn1().getOwner());
		assertEquals(add, add.getIn2().getOwner());
		assertEquals(add, add.getResult().getOwner());

		Assert.assertNull(add.getIn1().getValue());
		Assert.assertNull(add.getIn2().getValue());
		Assert.assertNull(add.getResult().getValue());
		add.getIn1().setValue(new Integer(5), true);
		add.propagate();
		Assert.assertEquals(new Integer(5), add.getIn1().getValue());
		Assert.assertNull(add.getIn2().getValue());
		Assert.assertNull(add.getResult().getValue());

		add.clearValues();
		add.getIn1().setValue(new Integer(6), true);
		add.getIn2().setValue(new Integer(3), true);
		Assert.assertEquals(new Integer(6), add.getIn1().getValue());
		Assert.assertEquals(new Integer(3), add.getIn2().getValue());
		Assert.assertNull(add.getResult().getValue());
		add.propagate();
		Assert.assertEquals(new Integer(6), add.getIn1().getValue());
		Assert.assertEquals(new Integer(3), add.getIn2().getValue());
		Assert.assertEquals(new Integer(9), add.getResult().getValue());

		add.clearValues();
		Assert.assertNull(add.getIn1().getValue());
		Assert.assertNull(add.getIn2().getValue());
		Assert.assertNull(add.getResult().getValue());

		add.clearValues();
		add.getIn1().setValue(new Integer(-2), true);
		add.getResult().setValue(new Integer(8), true);
		Assert.assertEquals(new Integer(-2), add.getIn1().getValue());
		Assert.assertEquals(new Integer(8), add.getResult().getValue());
		Assert.assertNull(add.getIn2().getValue());
		add.propagate();
		Assert.assertEquals(new Integer(-2), add.getIn1().getValue());
		Assert.assertEquals(new Integer(10), add.getIn2().getValue());
		Assert.assertEquals(new Integer(8), add.getResult().getValue());

		add.clearValues();
		add.getIn2().setValue(new Integer(-5), true);
		add.getResult().setValue(new Integer(-30), true);
		Assert.assertNull(add.getIn1().getValue());
		Assert.assertEquals(new Integer(-5), add.getIn2().getValue());
		Assert.assertEquals(new Integer(-30), add.getResult().getValue());
		add.propagate();
		Assert.assertEquals(new Integer(-25), add.getIn1().getValue());
		Assert.assertEquals(new Integer(-5), add.getIn2().getValue());
		Assert.assertEquals(new Integer(-30), add.getResult().getValue());
	}
}
