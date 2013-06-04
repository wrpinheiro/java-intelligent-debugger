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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.wrpinheiro.jid.diagnostician.models.test.GeneserethSystem;
import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class PropagationTest {
	private IntegerSystem s;

	@Before
	public void setup() {
		s = new IntegerSystem("PropagationTest", null);
	}

	@Test
	public void testPropagation() throws ConflictException {
		IntegerPort p1 = new IntegerPort("p1", null);
		IntegerPort p2 = new IntegerPort("p2", null);

		new IntegerConnection(s, "c1", "").addPort(p1).addPort(p2);

		p1.setValue(10, false);

		Assert.assertEquals(new Integer(10), p1.getValue());
		Assert.assertEquals(new Integer(10), p2.getValue());
	}

	@Test
	public void testSimpleInputOutputPropagation() throws ConflictException {
		IntegerPort in = new IntegerPort("in", null);
		IntegerPort middle = new IntegerPort("middle", null);
		IntegerPort out = new IntegerPort("out", null);

		new IntegerConnection(s, "c1", "").addPort(in).addPort(middle);
		new IntegerConnection(s, "c2", "").addPort(middle).addPort(out);

		in.setValue(new Integer(10), false);

		Assert.assertEquals(new Integer(10), in.getValue());
		Assert.assertEquals(new Integer(10), middle.getValue());
		Assert.assertEquals(new Integer(10), out.getValue());
	}

	public void testSimplePropatationWithMultiplier() throws ConflictException {

		IntegerConnection inC1 = new IntegerConnection(s, "ic1", "");
		IntegerConnection inC2 = new IntegerConnection(s, "ic2", "");
		IntegerConnection outC3 = new IntegerConnection(s, "ic3", "");

		IntegerBinaryOperator m1 = new IntegerMultiplier(s, "M1", 0, null);

		inC1.addPort(m1.getIn1());
		inC2.addPort(m1.getIn2());
		outC3.addPort(m1.getResult());

		inC1.setValue(5);
		inC2.setValue(6);

		m1.propagate();

		Assert.assertEquals(30, outC3.getValue().intValue());
	}

	/*
	 * +------+ i1 -- | | | M1 |-------+ i2 -- | | | +------+ +------+ +---| | |
	 * M2 |--- out i3 ----------------------| | +------+
	 */
	@Test
	public void testPropatationWithMultiplier() throws ConflictException {
		IntegerConnection c1 = new IntegerConnection(s, "c1", "");
		IntegerConnection c2 = new IntegerConnection(s, "c2", "");
		IntegerConnection c3 = new IntegerConnection(s, "c3", "");
		IntegerConnection c4 = new IntegerConnection(s, "c4", "");
		IntegerConnection c5 = new IntegerConnection(s, "c5", "");

		IntegerBinaryOperator m1 = new IntegerMultiplier(s, "M1", 0, null);
		IntegerBinaryOperator m2 = new IntegerMultiplier(s, "M2", 0, null);

		c1.addPort(m1.getIn1());
		c2.addPort(m1.getIn2());
		c3.addPort(m1.getResult());
		c3.addPort(m2.getIn1());
		c4.addPort(m2.getIn2());
		c5.addPort(m2.getResult());

		c1.setValue(5);
		c2.setValue(6);
		c4.setValue(7);

		Assert.assertNull(c3.getValue());
		Assert.assertNull(c5.getValue());

		m1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertNull(c5.getValue());

		m2.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertEquals(new Integer(210), c5.getValue());
	}

	/*
	 * +------+ i1 -- | | | M1 |-------+ i2 -- | | | +------+ +------+ +---| | |
	 * A1 |--- out i3 ----------------------| | +------+
	 */
	@Test
	public void testMultiplierAndAdder() throws ConflictException {
		IntegerConnection c1 = new IntegerConnection(s, "c1", "");
		IntegerConnection c2 = new IntegerConnection(s, "c2", "");
		IntegerConnection c3 = new IntegerConnection(s, "c3", "");
		IntegerConnection c4 = new IntegerConnection(s, "c4", "");
		IntegerConnection c5 = new IntegerConnection(s, "c5", "");

		IntegerBinaryOperator m1 = new IntegerMultiplier(s, "M1", 0, null);
		IntegerBinaryOperator a1 = new IntegerAdder(s, "M2", 0, null);

		c1.addPort(m1.getIn1());
		c2.addPort(m1.getIn2());
		c3.addPort(m1.getResult());
		c3.addPort(a1.getIn1());
		c4.addPort(a1.getIn2());
		c5.addPort(a1.getResult());

		c1.setValue(5);
		c2.setValue(6);
		c4.setValue(7);

		Assert.assertNull(c3.getValue());
		Assert.assertNull(c5.getValue());

		m1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertNull(c5.getValue());

		a1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertEquals(new Integer(37), c5.getValue());
	}

	/*
	 * +------+ i1 -- | | | M1 |-------+ i2 -- | | | +------+ +------+ +---| | |
	 * A1 |--- out i3 ----------------------| | +------+
	 */
	@Test
	public void testMultiplierAndAdderWithNoI1() throws ConflictException {
		IntegerConnection c1 = new IntegerConnection(s, "c1", "");
		IntegerConnection c2 = new IntegerConnection(s, "c2", "");
		IntegerConnection c3 = new IntegerConnection(s, "c3", "");
		IntegerConnection c4 = new IntegerConnection(s, "c4", "");
		IntegerConnection c5 = new IntegerConnection(s, "c5", "");

		IntegerBinaryOperator m1 = new IntegerMultiplier(s, "M1", 0, null);
		IntegerBinaryOperator a1 = new IntegerAdder(s, "M2", 0, null);

		c1.addPort(m1.getIn1());
		c2.addPort(m1.getIn2());
		c3.addPort(m1.getResult());
		c3.addPort(a1.getIn1());
		c4.addPort(a1.getIn2());
		c5.addPort(a1.getResult());

		c1.setValue(5);
		c2.setValue(6);
		c5.setValue(37);

		Assert.assertNull(c3.getValue());
		Assert.assertNull(c4.getValue());

		m1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertNull(c4.getValue());

		a1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertEquals(new Integer(7), c4.getValue());
	}

	/*
	 * +------+ i1 -- | | | M1 |-------+ i2 -- | | | +------+ +------+ +---| | |
	 * A1 |--- out i3 ----------------------| | +------+
	 */
	@Test
	public void testMultiplierAndAdderWithNoI2() throws ConflictException {
		IntegerConnection c1 = new IntegerConnection(s, "c1", "");
		IntegerConnection c2 = new IntegerConnection(s, "c2", "");
		IntegerConnection c3 = new IntegerConnection(s, "c3", "");
		IntegerConnection c4 = new IntegerConnection(s, "c4", "");
		IntegerConnection c5 = new IntegerConnection(s, "c5", "");

		IntegerBinaryOperator m1 = new IntegerMultiplier(s, "M1", 0, null);
		IntegerBinaryOperator a1 = new IntegerAdder(s, "M2", 0, null);

		c1.addPort(m1.getIn1());
		c2.addPort(m1.getIn2());
		c3.addPort(m1.getResult());
		c3.addPort(a1.getIn1());
		c4.addPort(a1.getIn2());
		c5.addPort(a1.getResult());

		c1.setValue(5);
		c4.setValue(7);
		c5.setValue(37);

		Assert.assertNull(c2.getValue());
		Assert.assertNull(c3.getValue());

		m1.propagate();

		Assert.assertNull(c2.getValue());
		Assert.assertNull(c3.getValue());

		a1.propagate();

		Assert.assertNull(c2.getValue());
		Assert.assertEquals(new Integer(30), c3.getValue());

		m1.propagate();

		Assert.assertEquals(new Integer(6), c2.getValue());
		Assert.assertEquals(new Integer(30), c3.getValue());
	}

	/*
	 * +------+ i1 -- | | | M1 |-------+ i2 -- | | | +------+ +------+ +---| | |
	 * A1 |--- out i3 ----------------------| | +------+
	 */
	@Test
	public void testMultiplierAndAdderWithNoI3() throws ConflictException {
		IntegerConnection c1 = new IntegerConnection(s, "c1", "");
		IntegerConnection c2 = new IntegerConnection(s, "c2", "");
		IntegerConnection c3 = new IntegerConnection(s, "c3", "");
		IntegerConnection c4 = new IntegerConnection(s, "c4", "");
		IntegerConnection c5 = new IntegerConnection(s, "c5", "");

		IntegerBinaryOperator m1 = new IntegerMultiplier(s, "M1", 0, null);
		IntegerBinaryOperator a1 = new IntegerAdder(s, "M2", 0, null);

		c1.addPort(m1.getIn1());
		c2.addPort(m1.getIn2());
		c3.addPort(m1.getResult());
		c3.addPort(a1.getIn1());
		c4.addPort(a1.getIn2());
		c5.addPort(a1.getResult());

		c1.setValue(5);
		c2.setValue(6);
		c5.setValue(37);

		Assert.assertNull(c3.getValue());
		Assert.assertNull(c4.getValue());

		m1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertNull(c4.getValue());

		a1.propagate();

		Assert.assertEquals(new Integer(30), c3.getValue());
		Assert.assertEquals(new Integer(7), c4.getValue());
	}

	@Test
	public void testGeneserethExamplePropagatingForward()
			throws ConflictException {
		GeneserethSystem s = new GeneserethSystem();

		s.getA().setValue(3);
		s.getB().setValue(2);
		s.getC().setValue(2);
		s.getD().setValue(3);
		s.getE().setValue(3);
		
		s.getM1().propagate();
		s.getM2().propagate();
		s.getM3().propagate();
		s.getA1().propagate();
		s.getA2().propagate();

		Assert.assertEquals(new Integer(12), s.getF().getValue());
		Assert.assertEquals(new Integer(12), s.getG().getValue());
	}

	@Test
	public void testGeneserethExamplePropagatingBackingAndForward()
			throws ConflictException {
		GeneserethSystem s = new GeneserethSystem();

		s.getA().setValue(3);
		s.getC().setValue(2);
		s.getE().setValue(3);
		s.getF().setValue(12);
		
		s.getM1().propagate();
		s.getM2().propagate();
		s.getM3().propagate();
		s.getA1().propagate();
		s.getA2().propagate();

		Assert.assertEquals(new Integer(6), s.getX().getValue());
		Assert.assertEquals(new Integer(6), s.getY().getValue());
		Assert.assertEquals(new Integer(6), s.getZ().getValue());
		Assert.assertEquals(new Integer(12), s.getG().getValue());
	}
}
