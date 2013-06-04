/**
 * 
 */
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
package br.com.wrpinheiro.jid.diagnostician.models.test;

import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.And;
import br.com.wrpinheiro.jid.programdiagnosis.components.Or;
import br.com.wrpinheiro.jid.programdiagnosis.components.Xor;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * A model for the full adder circuit.
 * 
 * @author wrp
 * 
 *         Sep 2, 2008
 */
public class FullAdderSystem extends IntegerSystem {
	private final IntegerConnection i1 = new IntegerConnection(this, "I1", "I");
	private final IntegerConnection i2 = new IntegerConnection(this, "I2", "I");
	private final IntegerConnection i3 = new IntegerConnection(this, "I3", "I");
	private final IntegerConnection o1 = new IntegerConnection(this, "O1", "O");
	private final IntegerConnection o2 = new IntegerConnection(this, "O2", "O");

	private final IntegerConnection x = new IntegerConnection(this, "X", "X");
	private final IntegerConnection y = new IntegerConnection(this, "Y", "Y");
	private final IntegerConnection z = new IntegerConnection(this, "Z", "Z");

	private final Xor xor1 = new Xor(this, "XOR1", 0, null);
	private final Xor xor2 = new Xor(this, "XOR2", 0, null);
	private final And and1 = new And(this, "AND1", 0, null);
	private final And and2 = new And(this, "AND2", 0, null);
	private final Or or1 = new Or(this, "OR1", 0, null);

	public FullAdderSystem() {
		super("FullAdderSystem", null);

		i1.addPort(xor1.getIn1());
		i1.addPort(and1.getIn1());

		i2.addPort(xor1.getIn2());
		i2.addPort(and1.getIn2());

		i3.addPort(and2.getIn1());
		i3.addPort(xor2.getIn2());

		x.addPort(xor1.getResult());
		x.addPort(xor2.getIn1());
		x.addPort(and2.getIn2());

		y.addPort(and1.getResult());
		y.addPort(or1.getIn2());

		z.addPort(and2.getResult());
		z.addPort(or1.getIn1());

		o1.addPort(xor2.getResult());
		o2.addPort(or1.getResult());
	}

	/**
	 * @return the i1
	 */
	public IntegerConnection getI1() {
		return i1;
	}

	/**
	 * @return the i2
	 */
	public IntegerConnection getI2() {
		return i2;
	}

	/**
	 * @return the i3
	 */
	public IntegerConnection getI3() {
		return i3;
	}

	/**
	 * @return the o1
	 */
	public IntegerConnection getO1() {
		return o1;
	}

	/**
	 * @return the o2
	 */
	public IntegerConnection getO2() {
		return o2;
	}

	/**
	 * @return the x
	 */
	public IntegerConnection getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public IntegerConnection getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public IntegerConnection getZ() {
		return z;
	}

	/**
	 * @return the xor1
	 */
	public Xor getXor1() {
		return xor1;
	}

	/**
	 * @return the xor2
	 */
	public Xor getXor2() {
		return xor2;
	}

	/**
	 * @return the and1
	 */
	public And getAnd1() {
		return and1;
	}

	/**
	 * @return the and2
	 */
	public And getAnd2() {
		return and2;
	}

	/**
	 * @return the or1
	 */
	public Or getOr1() {
		return or1;
	}
}
