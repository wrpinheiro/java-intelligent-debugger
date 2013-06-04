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
package br.com.wrpinheiro.jid.diagnostician.models.serial.test;

import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.And;
import br.com.wrpinheiro.jid.programdiagnosis.components.Inversor;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * A model for the full adder circuit.
 * 
 * @author wrp
 * 
 *         Sep 2, 2008
 */
public class FlatSerialThreeAndNotSystem extends IntegerSystem {
	private final IntegerConnection i1 = new IntegerConnection(this, "I1", "I");
	private final IntegerConnection i2 = new IntegerConnection(this, "I2", "I");
	private final IntegerConnection i3 = new IntegerConnection(this, "I3", "I");
	private final IntegerConnection o1 = new IntegerConnection(this, "O1", "O");

	private final IntegerConnection v = new IntegerConnection(this, "V", "V");
	private final IntegerConnection w = new IntegerConnection(this, "W", "W");
	private final IntegerConnection x = new IntegerConnection(this, "X", "X");
	private final IntegerConnection y = new IntegerConnection(this, "Y", "Y");
	private final IntegerConnection z = new IntegerConnection(this, "Z", "Z");

	private final And and1 = new And(this, "AND1", 0, null);
	private final And and2 = new And(this, "AND2", 0, null);
	private final And and3 = new And(this, "AND3", 0, null);
	private final Inversor not1 = new Inversor(this, "NOT1", 0, null);
	private final Inversor not2 = new Inversor(this, "NOT2", 0, null);
	private final Inversor not3 = new Inversor(this, "NOT3", 0, null);

	public FlatSerialThreeAndNotSystem() {
		super("FlatSerialThreeAndNotSystem", null);

		i1.addPort(and1.getIn1());
		i1.addPort(and3.getIn2());
		i2.addPort(and1.getIn2());
		i3.addPort(and2.getIn2());

		o1.addPort(not3.getOut());

		z.addPort(and3.getResult());
		z.addPort(not3.getIn());

		y.addPort(and3.getIn1());
		y.addPort(not2.getOut());

		x.addPort(and2.getResult());
		x.addPort(not2.getIn());

		w.addPort(and2.getIn1());
		w.addPort(not1.getOut());

		v.addPort(and1.getResult());
		v.addPort(not1.getIn());
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
	 * @return the v
	 */
	public IntegerConnection getV() {
		return v;
	}

	/**
	 * @return the w
	 */
	public IntegerConnection getW() {
		return w;
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
	 * @return the and3
	 */
	public And getAnd3() {
		return and3;
	}

	/**
	 * @return the not1
	 */
	public Inversor getNot1() {
		return not1;
	}

	/**
	 * @return the not2
	 */
	public Inversor getNot2() {
		return not2;
	}

	/**
	 * @return the not3
	 */
	public Inversor getNot3() {
		return not3;
	}
}
