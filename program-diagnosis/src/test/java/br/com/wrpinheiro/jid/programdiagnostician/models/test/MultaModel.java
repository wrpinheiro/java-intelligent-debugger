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
package br.com.wrpinheiro.jid.programdiagnostician.models.test;

import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConstantConnection;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.Conditional;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * @author wrp
 * 
 *         10/01/2009
 */
public class MultaModel extends IntegerSystem {
	/*
	12  public static void main(String[] args) {
	13    int valor, atraso, multa, novoValor;
	14    valor = readInt();
	15    atraso = readInt();
	16    multa = readInt();
	17
	18    novoValor = valor;
	19
	20    if (atraso == 1)
	21      novoValor = novoValor + 50;
	22    else novoValor = novoValor -25;
	23
	24    if (multa == 1)
	25      novoValor = novoValor - 5;
	26    else novoValor = novoValor + 15;
	27
	28    writeInt(novoValor);
	29 }
	*/

	private final IntegerConnection constNeg25 = new IntegerConstantConnection(
			this, "-25", -25);
	private final IntegerConnection constNeg5 = new IntegerConstantConnection(
			this, "-5", -5);
	private final IntegerConnection const1 = new IntegerConstantConnection(
			this, "1", 1);
	private final IntegerConnection const15 = new IntegerConstantConnection(
			this, "15", 15);
	private final IntegerConnection const50 = new IntegerConstantConnection(
			this, "50", 50);

	private final IntegerConnection input0 = new IntegerConnection(this,
			"input0", "");
	private final IntegerConnection input1 = new IntegerConnection(this,
			"input1", "");
	private final IntegerConnection input2 = new IntegerConnection(this,
			"input2", "");

	private final IntegerConnection output0 = new IntegerConnection(this,
			"output0", "");

	private final IntegerConnection valor0 = new IntegerConnection(this,
			"valor0", "");
	private final IntegerConnection atraso1 = new IntegerConnection(this,
			"atraso1", "");
	private final IntegerConnection multa2 = new IntegerConnection(this,
			"multa2", "");
	private final IntegerConnection novoValor3 = new IntegerConnection(this,
			"novoValor3", "");

	private final IntegerConnection aux4 = new IntegerConnection(this, "aux4", "");
	private final IntegerConnection aux10 = new IntegerConnection(this, "aux10", "");

	private final IntegerAssignment assign0 = new IntegerAssignment(this, "C0",
			14, null);
	private final IntegerAssignment assign1 = new IntegerAssignment(this, "C1",
			15, null);
	private final IntegerAssignment assign2 = new IntegerAssignment(this, "C2",
			16, null);
	private final IntegerAssignment assign3 = new IntegerAssignment(this, "C3",
			18, null);

	private final IntegerEqual expr4 = new IntegerEqual(this, "C4", 20, null);

	private final Conditional cond5 = new Conditional(this, "C5", DefaultSets.EMPTY, null);

	private DummyBlockComponent c5_then = new DummyBlockComponent(cond5, "c5_then", DefaultSets.EMPTY, null);
	private DummyBlockComponent c5_else = new DummyBlockComponent(cond5, "c5_else", DefaultSets.EMPTY, null);

	private final IntegerAdder add6 = new IntegerAdder(c5_then, "C6", 21, null);
	private final IntegerConnection novoValor_l = new IntegerConnection(
			c5_then, "novoValor_l", "");
	private final IntegerConnection aux6 = new IntegerConnection(c5_then,
			"aux6", "");
	private final IntegerAssignment assign7 = new IntegerAssignment(c5_then,
			"C7", 21, null);
	private final IntegerConnection novoValor7 = new IntegerConnection(c5_then,
			"novoValor7", "");

	private final IntegerAdder add8 = new IntegerAdder(c5_else, "C8", 22, null);
	private final IntegerConnection novoValor_ll = new IntegerConnection(
			c5_else, "novoValor_ll", "");
	private final IntegerConnection aux8 = new IntegerConnection(c5_else,
			"aux8", "");
	private final IntegerAssignment assign9 = new IntegerAssignment(c5_else,
			"C9", 22, null);
	private final IntegerConnection novoValor9 = new IntegerConnection(c5_else,
			"novoValor9", "");

	private final IntegerConnection novoValor5 = new IntegerConnection(this,
			"novoValor5", "");
	private final IntegerConnection novoValor11 = new IntegerConnection(this,
			"novoValor11", "");

	private final IntegerEqual expr10 = new IntegerEqual(this, "C10", 24, null);

	private final Conditional cond11 = new Conditional(this, "C11", DefaultSets.EMPTY, null);

	private DummyBlockComponent c11_then = new DummyBlockComponent(cond11, "c11_then", DefaultSets.EMPTY, null);
	private DummyBlockComponent c11_else = new DummyBlockComponent(cond11, "c11_else", DefaultSets.EMPTY, null);

	private final IntegerAdder add12 = new IntegerAdder(c11_then, "C12", 25, null);
	private final IntegerConnection novoValor_lll = new IntegerConnection(
			c11_then, "novoValor_lll", "");
	private final IntegerConnection aux12 = new IntegerConnection(c11_then,
			"aux12", "");
	private final IntegerAssignment assign13 = new IntegerAssignment(c11_then,
			"C13", 25, null);
	private final IntegerConnection novoValor13 = new IntegerConnection(
			c11_then, "novoValor13", "");

	private final IntegerAdder add14 = new IntegerAdder(c11_else, "C14", 26, null);
	private final IntegerConnection novoValor_llll = new IntegerConnection(
			c11_else, "novoValor_llll", "");
	private final IntegerConnection aux14 = new IntegerConnection(c11_else,
			"aux14", "");
	private final IntegerAssignment assign15 = new IntegerAssignment(c11_else,
			"C15", 26, null);
	private final IntegerConnection novoValor15 = new IntegerConnection(
			c11_else, "novoValor15", "");

	private final IntegerAssignment assign16 = new IntegerAssignment(this,
			"C16", 28, null);

	public MultaModel() {
		super("MultaModel", null);
		
		//assign0.setAlwaysOk(true);
		//assign1.setAlwaysOk(true);
		//assign2.setAlwaysOk(true);
		//assign16.setAlwaysOk(true);
		
		// assign the input values to the variables.
		input0.addPort(assign0.getIn());
		valor0.addPort(assign0.getOut());

		input1.addPort(assign1.getIn());
		atraso1.addPort(assign1.getOut());

		input2.addPort(assign2.getIn());
		multa2.addPort(assign2.getOut());

		valor0.addPort(assign3.getIn());

		cond5.addInputPort("novoValor");
		cond5.addOutputPort("novoValor");
		
		novoValor3.addPort(assign3.getOut());
		novoValor3.addPort(cond5.getInputPort("novoValor"));

		atraso1.addPort(expr4.getIn1());
		const1.addPort(expr4.getIn2());

		aux4.addPort(expr4.getResult());
		aux4.addPort(cond5.getCondResult());

		cond5.setThenSystem(c5_then);
		cond5.setElseSystem(c5_else);

		c5_then.addInputPort("novoValor");
		c5_then.addOutputPort("novoValor");
		c5_else.addInputPort("novoValor");
		c5_else.addOutputPort("novoValor");

		novoValor_l.addPort(c5_then.getInputPort("novoValor"));
		novoValor_l.addPort(add6.getIn1());
		const50.addPort(add6.getIn2());

		aux6.addPort(add6.getResult());
		aux6.addPort(assign7.getIn());

		novoValor7.addPort(assign7.getOut());
		novoValor7.addPort(c5_then.getOutputPort("novoValor"));

		novoValor_ll.addPort(c5_else.getInputPort("novoValor"));
		novoValor_ll.addPort(add8.getIn1());
		constNeg25.addPort(add8.getIn2());

		aux8.addPort(add8.getResult());
		aux8.addPort(assign9.getIn());

		novoValor9.addPort(assign9.getOut());
		novoValor9.addPort(c5_else.getOutputPort("novoValor"));

		multa2.addPort(assign2.getOut());
		multa2.addPort(expr10.getIn1());
		const1.addPort(expr10.getIn2());

		aux10.addPort(expr10.getResult());
		aux10.addPort(cond11.getCondResult());

		cond11.setThenSystem(c11_then);
		cond11.setElseSystem(c11_else);

		cond11.addInputPort("novoValor");
		cond11.addOutputPort("novoValor");
		c11_then.addInputPort("novoValor");
		c11_then.addOutputPort("novoValor");
		c11_else.addInputPort("novoValor");
		c11_else.addOutputPort("novoValor");

		novoValor5.addPort(cond5.getOutputPort("novoValor"));
		novoValor5.addPort(cond11.getInputPort("novoValor"));

		novoValor_lll.addPort(c11_then.getInputPort("novoValor"));
		novoValor_lll.addPort(add12.getIn1());
		constNeg5.addPort(add12.getIn2());

		aux12.addPort(add12.getResult());
		aux12.addPort(assign13.getIn());

		novoValor13.addPort(assign13.getOut());
		novoValor13.addPort(c11_then.getOutputPort("novoValor"));

		novoValor_llll.addPort(c11_else.getInputPort("novoValor"));
		novoValor_llll.addPort(add14.getIn1());
		const15.addPort(add14.getIn2());

		aux14.addPort(add14.getResult());
		aux14.addPort(assign15.getIn());

		novoValor15.addPort(assign15.getOut());
		novoValor15.addPort(c11_else.getOutputPort("novoValor"));

		novoValor11.addPort(cond11.getOutputPort("novoValor"));
		novoValor11.addPort(assign16.getIn());

		output0.addPort(assign16.getOut());
	}
}
