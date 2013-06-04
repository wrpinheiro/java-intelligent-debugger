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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerLess;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.Conditional;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * @author wrp
 * 
 * 10/01/2009
 */

/*
 * 01  int main () {
 * 02    int a,b,greater;
 * 03    a = readInt();
 * 04    b =readInt();    
 * 05    if(a<b){
 * 06      greater=3*a;
 * 07    }
 * 08    else{
 * 09      greater=3*b;
 * 10    }
 * 11    writeInt("the greater is: ", greater);
 * 12    return 0;
 * 13  }
 */
public class MaxModel extends IntegerSystem {
	private IntegerConnection input_a = new IntegerConnection(this, "input_a", "");
	private IntegerConnection input_b = new IntegerConnection(this, "input_b", "");

	private IntegerConnection a1 = new IntegerConnection(this, "a1", "");
	private IntegerConnection b2 = new IntegerConnection(this, "b2", "");

	private IntegerConnection aux4 = new IntegerConnection(this, "aux4", "");
	private IntegerConnection greater3 = new IntegerConnection(this, "greater3", "");
	private IntegerConnection output_greater = new IntegerConnection(this,
			"output_greater", "");

	private IntegerAssignment c0 = new IntegerAssignment(this, "c0", 3, null);
	private IntegerAssignment c1 = new IntegerAssignment(this, "c1", 4, null);
	private IntegerLess c2 = new IntegerLess(this, "c2", 5, null);
	private Conditional c3 = new Conditional(this, "c3", DefaultSets.EMPTY, null);

	private DummyBlockComponent c3_then = new DummyBlockComponent(c3, "c3_then", DefaultSets.EMPTY, null);
	private IntegerConnection a1_l = new IntegerConnection(c3_then, "a1_l", "");
	private IntegerConnection b2_l = new IntegerConnection(c3_then, "b2_l", "");
	private IntegerConnection const6 = new IntegerConnection(c3_then, "const6", "");
	private IntegerConnection aux6 = new IntegerConnection(c3_then, "aux6", "");
	private IntegerConnection greater5 = new IntegerConnection(c3_then,
			"greater5", "");
	private IntegerAssignment c4 = new IntegerAssignment(c3_then, "c4", 6, null);
	private IntegerMultiplier c5 = new IntegerMultiplier(c3_then, "c5", 6, null);

	private DummyBlockComponent c3_else = new DummyBlockComponent(c3, "c3_else", DefaultSets.EMPTY, null);
	private IntegerConnection a1_ll = new IntegerConnection(c3_else, "a1_ll", "");
	private IntegerConnection b2_ll = new IntegerConnection(c3_else, "b2_ll", "");
	private IntegerConnection const9 = new IntegerConnection(c3_else, "const9", "");
	private IntegerConnection aux9 = new IntegerConnection(c3_else, "aux9", "");
	private IntegerConnection greater8 = new IntegerConnection(c3_else,
			"greater8", "");
	private IntegerAssignment c6 = new IntegerAssignment(c3_else, "c6", 9, null);
	private IntegerMultiplier c7 = new IntegerMultiplier(c3_else, "c7", 9, null);

	private IntegerAssignment c8 = new IntegerAssignment(this, "c8", 11, null);

	public MaxModel() {
		super("MaxModel", null);
		input_a.addPort(c0.getIn());
		input_b.addPort(c1.getIn());

		c3.setThenSystem(c3_then);
		c3.setElseSystem(c3_else);

		c3.addInputPort("a");
		c3.addInputPort("b");
		c3.addOutputPort("greater");

		a1.addPort(c0.getOut());
		a1.addPort(c2.getIn1());
		a1.addPort(c3.getInputPort("a"));

		b2.addPort(c1.getOut());
		b2.addPort(c2.getIn2());
		b2.addPort(c3.getInputPort("b"));

		aux4.addPort(c2.getResult());
		aux4.addPort(c3.getCondResult());

		greater3.addPort(c3.getOutputPort("greater"));
		greater3.addPort(c8.getIn());

		output_greater.addPort(c8.getOut());

		createThenConnection();
		createElseConnections();
	}

	private void createElseConnections() {
		c3_then.addInputPort("a");
		c3_then.addInputPort("b");
		c3_then.addOutputPort("greater");

		const6.addPort(c5.getIn1());
		a1_l.addPort(c5.getIn2());
		aux6.addPort(c5.getResult());
		aux6.addPort(c4.getIn());
		greater5.addPort(c4.getOut());

		a1_l.addPort(c3_then.getInputPort("a"));
		b2_l.addPort(c3_then.getInputPort("b"));
		greater5.addPort(c3_then.getOutputPort("greater"));
	}

	private void createThenConnection() {
		c3_else.addInputPort("a");
		c3_else.addInputPort("b");
		c3_else.addOutputPort("greater");

		const9.addPort(c7.getIn1());
		b2_ll.addPort(c7.getIn2());
		aux9.addPort(c7.getResult());
		aux9.addPort(c6.getIn());
		greater8.addPort(c6.getOut());

		a1_ll.addPort(c3_else.getInputPort("a"));
		b2_ll.addPort(c3_else.getInputPort("b"));
		greater8.addPort(c3_else.getOutputPort("greater"));
	}
}
