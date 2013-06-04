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
import br.com.wrpinheiro.jid.programdiagnosis.components.Inversor;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 * 15/09/2008
 */
public class TwoNandsDetailedSystem extends IntegerSystem {
	private final IntegerConnection cA = new IntegerConnection(this, "A", "A");
	private final IntegerConnection cB = new IntegerConnection(this, "B", "B");
	private final IntegerConnection cC = new IntegerConnection(this, "C", "C");
	private final IntegerConnection cD = new IntegerConnection(this, "D", "D");

	private final IntegerConnection cX = new IntegerConnection(this, "X", "X");
	private final IntegerConnection cY = new IntegerConnection(this, "Y", "Y");
	private final IntegerConnection cZ = new IntegerConnection(this, "Z", "Z");

	private final And and1 = new And(this, "A1", 0, null);
	private final And and2 = new And(this, "A2", 0, null);
	private final Inversor not1 = new Inversor(this, "Inv1", 0, null);
	private final Inversor not2 = new Inversor(this, "Inv2", 0, null);

	public TwoNandsDetailedSystem() {
		super("TwoNandsDetailedSystem", null);
		cA.addPort(and1.getIn1());
		cB.addPort(and1.getIn2());
		
		cX.addPort(and1.getResult());
		cX.addPort(not1.getIn());

		cY.addPort(not1.getOut());
		cY.addPort(and2.getIn1());
		
		cC.addPort(and2.getIn2());
		
		cZ.addPort(and2.getResult());
		cZ.addPort(not2.getIn());
		
		cD.addPort(not2.getOut());
	}

	/**
	 * @return the cA
	 */
	public IntegerConnection getCA() {
		return cA;
	}

	/**
	 * @return the cB
	 */
	public IntegerConnection getCB() {
		return cB;
	}

	/**
	 * @return the cC
	 */
	public IntegerConnection getCC() {
		return cC;
	}

	/**
	 * @return the cD
	 */
	public IntegerConnection getCD() {
		return cD;
	}

	/**
	 * @return the cX
	 */
	public IntegerConnection getCX() {
		return cX;
	}

	/**
	 * @return the cY
	 */
	public IntegerConnection getCY() {
		return cY;
	}

	/**
	 * @return the cZ
	 */
	public IntegerConnection getCZ() {
		return cZ;
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
}
