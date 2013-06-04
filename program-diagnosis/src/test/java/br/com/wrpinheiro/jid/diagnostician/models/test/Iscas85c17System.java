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
import br.com.wrpinheiro.jid.programdiagnosis.components.Nand;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class Iscas85c17System extends IntegerSystem {
	private final Nand n10 = new Nand(this, "NA10", 0, null);
	private final Nand n11 = new Nand(this, "NA11", 0, null);

	private final Nand n16 = new Nand(this, "NA16", 0, null);
	private final Nand n19 = new Nand(this, "NA19", 0, null);

	private final Nand n22 = new Nand(this, "NA22", 0, null);
	private final Nand n23 = new Nand(this, "NA23", 0, null);

	private final IntegerConnection i1 = new IntegerConnection(this, "I1", "I");
	private final IntegerConnection i2 = new IntegerConnection(this, "I2", "I");
	private final IntegerConnection i3 = new IntegerConnection(this, "I3", "I");
	private final IntegerConnection i6 = new IntegerConnection(this, "I6", "I");
	private final IntegerConnection i7 = new IntegerConnection(this, "I7", "I");
	private final IntegerConnection i22 = new IntegerConnection(this, "I22", "I");
	private final IntegerConnection i23 = new IntegerConnection(this, "I23", "I");

	private final IntegerConnection o10 = new IntegerConnection(this, "O10", "O");
	private final IntegerConnection o11 = new IntegerConnection(this, "O11", "O");

	private final IntegerConnection o16 = new IntegerConnection(this, "O16", "O");
	private final IntegerConnection o19 = new IntegerConnection(this, "O19", "O");

	public Iscas85c17System() {
		super("Iscas85c17System", null);

		i1.addPort(n10.getIn2());
		i2.addPort(n16.getIn2());
		i3.addPort(n10.getIn1());
		i3.addPort(n11.getIn2());
		i6.addPort(n11.getIn1());
		i7.addPort(n19.getIn1());

		o10.addPort(n10.getResult());
		o10.addPort(n22.getIn2());

		o11.addPort(n11.getResult());
		o11.addPort(n19.getIn2());
		o11.addPort(n16.getIn1());

		o16.addPort(n16.getResult());
		o16.addPort(n22.getIn1());
		o16.addPort(n23.getIn2());

		o19.addPort(n19.getResult());
		o19.addPort(n23.getIn1());

		i22.addPort(n22.getResult());
		i23.addPort(n23.getResult());

	}

	/**
	 * @return the n10
	 */
	public Nand getN10() {
		return n10;
	}

	/**
	 * @return the n11
	 */
	public Nand getN11() {
		return n11;
	}

	/**
	 * @return the n16
	 */
	public Nand getN16() {
		return n16;
	}

	/**
	 * @return the n19
	 */
	public Nand getN19() {
		return n19;
	}

	/**
	 * @return the n22
	 */
	public Nand getN22() {
		return n22;
	}

	/**
	 * @return the n23
	 */
	public Nand getN23() {
		return n23;
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
	 * @return the i6
	 */
	public IntegerConnection getI6() {
		return i6;
	}

	/**
	 * @return the i7
	 */
	public IntegerConnection getI7() {
		return i7;
	}

	/**
	 * @return the i22
	 */
	public IntegerConnection getI22() {
		return i22;
	}

	/**
	 * @return the i23
	 */
	public IntegerConnection getI23() {
		return i23;
	}

	/**
	 * @return the o10
	 */
	public IntegerConnection getO10() {
		return o10;
	}

	/**
	 * @return the o11
	 */
	public IntegerConnection getO11() {
		return o11;
	}

	/**
	 * @return the o16
	 */
	public IntegerConnection getO16() {
		return o16;
	}

	/**
	 * @return the o19
	 */
	public IntegerConnection getO19() {
		return o19;
	}
}
