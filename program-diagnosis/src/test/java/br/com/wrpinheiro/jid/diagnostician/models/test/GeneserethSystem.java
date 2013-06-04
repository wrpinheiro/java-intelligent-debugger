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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * A model representing a circuit comprised of two multipliers and three adders.
 * 
 * @author wrp
 * 
 *         01/09/2008
 */
public class GeneserethSystem extends IntegerSystem {
	private final IntegerConnection a = new IntegerConnection(this, "A", "A");
	private final IntegerConnection b = new IntegerConnection(this, "B", "B");
	private final IntegerConnection c = new IntegerConnection(this, "C", "C");
	private final IntegerConnection d = new IntegerConnection(this, "D", "D");
	private final IntegerConnection e = new IntegerConnection(this, "E", "E");

	private final IntegerConnection f = new IntegerConnection(this, "F", "F");
	private final IntegerConnection g = new IntegerConnection(this, "G", "G");

	private final IntegerConnection x = new IntegerConnection(this, "X", "X");
	private final IntegerConnection y = new IntegerConnection(this, "Y", "Y");
	private final IntegerConnection z = new IntegerConnection(this, "Z", "Z");

	private final IntegerMultiplier m1 = new IntegerMultiplier(this, "M1", 0, null);
	private final IntegerMultiplier m2 = new IntegerMultiplier(this, "M2", 0, null);
	private final IntegerMultiplier m3 = new IntegerMultiplier(this, "M3", 0, null);
	private final IntegerAdder a1 = new IntegerAdder(this, "A1", 0, null);
	private final IntegerAdder a2 = new IntegerAdder(this, "A2", 0, null);

	public GeneserethSystem() {
		super("GeneserethSystem", null);

		a.addPort(m1.getIn1());
		b.addPort(m2.getIn1());
		c.addPort(m1.getIn2());
		c.addPort(m3.getIn1());
		d.addPort(m2.getIn2());
		e.addPort(m3.getIn2());

		x.addPort(m1.getResult());
		x.addPort(a1.getIn1());

		y.addPort(m2.getResult());
		y.addPort(a1.getIn2());
		y.addPort(a2.getIn1());

		z.addPort(m3.getResult());
		z.addPort(a2.getIn2());

		f.addPort(a1.getResult());
		g.addPort(a2.getResult());
	}

	/**
	 * @return the a
	 */
	public IntegerConnection getA() {
		return a;
	}

	/**
	 * @return the b
	 */
	public IntegerConnection getB() {
		return b;
	}

	/**
	 * @return the c
	 */
	public IntegerConnection getC() {
		return c;
	}

	/**
	 * @return the d
	 */
	public IntegerConnection getD() {
		return d;
	}

	/**
	 * @return the e
	 */
	public IntegerConnection getE() {
		return e;
	}

	/**
	 * @return the f
	 */
	public IntegerConnection getF() {
		return f;
	}

	/**
	 * @return the g
	 */
	public IntegerConnection getG() {
		return g;
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
	 * @return the m1
	 */
	public IntegerMultiplier getM1() {
		return m1;
	}

	/**
	 * @return the m2
	 */
	public IntegerMultiplier getM2() {
		return m2;
	}

	/**
	 * @return the m3
	 */
	public IntegerMultiplier getM3() {
		return m3;
	}

	/**
	 * @return the a1
	 */
	public IntegerAdder getA1() {
		return a1;
	}

	/**
	 * @return the a2
	 */
	public IntegerAdder getA2() {
		return a2;
	}
}
