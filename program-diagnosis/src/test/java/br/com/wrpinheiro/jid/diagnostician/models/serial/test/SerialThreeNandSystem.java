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
import br.com.wrpinheiro.jid.programdiagnosis.components.Nand;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * A model for the full adder circuit.
 * 
 * @author wrp
 * 
 * Sep 2, 2008
 */
public class SerialThreeNandSystem extends IntegerSystem {
	private final IntegerConnection i1 = new IntegerConnection(this, "I1", "I");
	private final IntegerConnection i2 = new IntegerConnection(this, "I2", "I");
	private final IntegerConnection i3 = new IntegerConnection(this, "I3", "I");
	private final IntegerConnection o1 = new IntegerConnection(this, "O1", "O");

	private final IntegerConnection w = new IntegerConnection(this, "W", "W");
	private final IntegerConnection y = new IntegerConnection(this, "Y", "Y");

	private final Nand nand1 = new Nand(this, "NAND1", 0, null);
	private final Nand nand2 = new Nand(this, "NAND2", 0, null);
	private final Nand nand3 = new Nand(this, "NAND3", 0, null);

	public SerialThreeNandSystem() {
		super("FlatSerialThreeAndNotSystem", null);

		i1.addPort(nand1.getIn1());
		i1.addPort(nand3.getIn2());
		i2.addPort(nand1.getIn2());
		i3.addPort(nand2.getIn2());

		o1.addPort(nand3.getResult());

		w.addPort(nand1.getResult());
		w.addPort(nand2.getIn1());

		y.addPort(nand2.getResult());
		y.addPort(nand3.getIn1());
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
	 * @return the w
	 */
	public IntegerConnection getW() {
		return w;
	}

	/**
	 * @return the y
	 */
	public IntegerConnection getY() {
		return y;
	}

	/**
	 * @return the nand1
	 */
	public Nand getNand1() {
		return nand1;
	}

	/**
	 * @return the nand2
	 */
	public Nand getNand2() {
		return nand2;
	}

	/**
	 * @return the nand3
	 */
	public Nand getNand3() {
		return nand3;
	}
}
