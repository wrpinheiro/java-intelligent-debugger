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
import br.com.wrpinheiro.jid.programdiagnosis.components.Inversor;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * A system comprised of two in line inversors;
 * 
 * @author wrp
 * 
 *         Sep 3, 2008
 */
public class InversorSystem extends IntegerSystem {
	private final IntegerConnection a = new IntegerConnection(this, "A", "A");
	private final IntegerConnection b = new IntegerConnection(this, "B", "B");
	private final IntegerConnection c = new IntegerConnection(this, "C", "C");

	private final Inversor i1 = new Inversor(this, "I1", 0, null);
	private final Inversor i2 = new Inversor(this, "I2", 0, null);
	
	public InversorSystem() {
		super("InversorSystem", null);
		
		a.addPort(i1.getIn());
		b.addPort(i1.getOut());
		b.addPort(i2.getIn());
		c.addPort(i2.getOut());
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
	 * @return the i1
	 */
	public Inversor getI1() {
		return i1;
	}

	/**
	 * @return the i2
	 */
	public Inversor getI2() {
		return i2;
	}
}
