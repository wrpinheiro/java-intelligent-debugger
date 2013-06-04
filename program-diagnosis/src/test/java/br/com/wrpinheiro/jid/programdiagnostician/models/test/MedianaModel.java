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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerGreater;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerLess;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.Conditional;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * @author wrp
 * 
 * 17/01/2009
 * 
 */
public class MedianaModel extends IntegerSystem {
	// 01 #include <stdio.h>
	// 02 int main () {
	// 03 	int x,y,z,m;
	// 04 	printf("input x \n");
	// 05 	scanf("%d",&x);
	// 06 	printf("input y \n");
	// 07 	scanf("%d%" ,&y);
	// 08 	printf("input z \n");
	// 09 	scanf("%d%" ,&z);
	// 10 	m=z;
	// 11 	if(y<z){
	// 12 		if(x<y){
	// 13 			m=y;
	// 14 		} else {
	// 15 			if (x<z){
	// 16 				m=y;
	// 17 			}
	// 18 		}
	// 19 	} else {
	// 20 		if (x>y) {
	// 21 			m=y;
	// 22 		} else {
	// 23 			if (x>z) {
	// 24 				m=x;
	// 25 			}
	// 26 		}
	// 27 	}
	// 28 	printf("middle number is %d \n",m);
	// 29 	return 0;
	// 30 }
	private final IntegerAssignment c0 = new IntegerAssignment(this, "C0", 5, null);
	private final IntegerAssignment c1 = new IntegerAssignment(this, "C1", 7, null);
	private final IntegerAssignment c2 = new IntegerAssignment(this, "C2", 9, null);
	private final IntegerAssignment c3 = new IntegerAssignment(this, "C3", 10, null);
	private final IntegerLess c4 = new IntegerLess(this, "C4", 11, null);
	private final Conditional c5 = new Conditional(this, "C5", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c5_then = new DummyBlockComponent(this, "c5_then", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c5_else = new DummyBlockComponent(this, "c5_else", DefaultSets.EMPTY, null);
	private final IntegerLess c6 = new IntegerLess(c5_then, "C6", 12, null);
	private final Conditional c7 = new Conditional(c5_then, "C7", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c7_then = new DummyBlockComponent(c5_then, "c7_then", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c7_else = new DummyBlockComponent(c5_then, "c7_else", DefaultSets.EMPTY, null);
	private final IntegerAssignment c8 = new IntegerAssignment(c7_then, "C8", 13, null);
	private final IntegerLess c9 = new IntegerLess(c7_else, "C9", 15, null);
	private final Conditional c10 = new Conditional(c7_else, "C10", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c10_then = new DummyBlockComponent(c7_else, "c10_then", DefaultSets.EMPTY, null);
	private final IntegerAssignment c11 = new IntegerAssignment(c10_then, "C11", 16, null);
	private final IntegerGreater c12 = new IntegerGreater(c5_else, "C12", 20, null);
	private final Conditional c13 = new Conditional(c5_else, "C13", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c13_then = new DummyBlockComponent(c5_else, "c13_then", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c13_else = new DummyBlockComponent(c5_else, "c13_else", DefaultSets.EMPTY, null);
	private final IntegerAssignment c14 = new IntegerAssignment(c13_then, "C14", 21, null);
	private final IntegerGreater c15 = new IntegerGreater(c13_else, "C15", 23, null);
	private final Conditional c16 = new Conditional(c13_else, "C16", DefaultSets.EMPTY, null);
	private final DummyBlockComponent c16_then = new DummyBlockComponent(c13_else, "c16_then", DefaultSets.EMPTY, null);
	private final IntegerAssignment c17 = new IntegerAssignment(c16_then, "C17", 24, null);
	private final IntegerAssignment c18 = new IntegerAssignment(this, "C18", 28, null);

	private final IntegerConnection input0 = new IntegerConnection(this,
			"input0", "");
	private final IntegerConnection input1 = new IntegerConnection(this,
			"input1", "");
	private final IntegerConnection input2 = new IntegerConnection(this,
			"input2", "");

	private final IntegerConnection x0 = new IntegerConnection(this, "x0", "");
	private final IntegerConnection y1 = new IntegerConnection(this, "y1", "");
	private final IntegerConnection z2 = new IntegerConnection(this, "z2", "");
	private final IntegerConnection m3 = new IntegerConnection(this, "m3", "");

	private final IntegerConnection x0_l = new IntegerConnection(c5_then,
			"x0_l", "");
	private final IntegerConnection y1_l = new IntegerConnection(c5_then,
			"y1_l", "");
	private final IntegerConnection z2_l = new IntegerConnection(c5_then,
			"z2_l", "");
	private final IntegerConnection m3_l = new IntegerConnection(c5_then,
			"m3_l", "");

	private final IntegerConnection x0_ll = new IntegerConnection(c7_then,
			"x0_ll", "");
	private final IntegerConnection y1_ll = new IntegerConnection(c7_then,
			"y1_ll", "");
	private final IntegerConnection z2_ll = new IntegerConnection(c7_then,
			"z2_ll", "");
	private final IntegerConnection m3_ll = new IntegerConnection(c7_then,
			"m3_ll", "");

	private final IntegerConnection x0_lll = new IntegerConnection(c7_else,
			"x0_lll", "");
	private final IntegerConnection y1_lll = new IntegerConnection(c7_else,
			"y1_lll", "");
	private final IntegerConnection z2_lll = new IntegerConnection(c7_else,
			"z2_lll", "");
	private final IntegerConnection m3_lll = new IntegerConnection(c7_else,
			"m3_lll", "");

	private final IntegerConnection y1_llll = new IntegerConnection(c10_then,
			"y1_llll", "");

	private final IntegerConnection x0_iv = new IntegerConnection(c5_else,
			"x0_iv", "");
	private final IntegerConnection y1_iv = new IntegerConnection(c5_else,
			"y1_iv", "");
	private final IntegerConnection z2_iv = new IntegerConnection(c5_else,
			"z2_iv", "");
	private final IntegerConnection m3_iv = new IntegerConnection(c5_else,
			"m3_iv", "");

	private final IntegerConnection x0_v = new IntegerConnection(c13_then,
			"x0_v", "");
	private final IntegerConnection y1_v = new IntegerConnection(c13_then,
			"y1_v", "");
	private final IntegerConnection z2_v = new IntegerConnection(c13_then,
			"z2_v", "");
	private final IntegerConnection m3_v = new IntegerConnection(c13_then,
			"m3_v", "");

	private final IntegerConnection x0_vi = new IntegerConnection(c13_else,
			"x0_vi", "");
	private final IntegerConnection y1_vi = new IntegerConnection(c13_else,
			"y1_vi", "");
	private final IntegerConnection z2_vi = new IntegerConnection(c13_else,
			"z2_vi", "");
	private final IntegerConnection m3_vi = new IntegerConnection(c13_else,
			"m3_vi", "");

	private final IntegerConnection x0_vii = new IntegerConnection(c16_then,
			"x0_vii", "");

	private final IntegerConnection m5 = new IntegerConnection(this, "m5", "");

	private final IntegerConnection m7 = new IntegerConnection(c5_then, "m7", "");
	private final IntegerConnection m8 = new IntegerConnection(c7_then, "m8", "");
	private final IntegerConnection m10 = new IntegerConnection(c7_else, "m10", "");
	private final IntegerConnection m11 = new IntegerConnection(c10_then, "m11", "");

	private final IntegerConnection m13 = new IntegerConnection(c5_else, "m13", "");
	private final IntegerConnection m14 = new IntegerConnection(c13_then, "m14", "");
	private final IntegerConnection m16 = new IntegerConnection(c13_else, "m16", "");
	private final IntegerConnection m17 = new IntegerConnection(c16_then, "m17", "");

	private final IntegerConnection aux4 = new IntegerConnection(this, "aux4", "");
	private final IntegerConnection aux6 = new IntegerConnection(c5_then,
			"aux6", "");
	private final IntegerConnection aux9 = new IntegerConnection(c7_else,
			"aux9", "");
	private final IntegerConnection aux12 = new IntegerConnection(c5_else,
			"aux12", "");
	private final IntegerConnection aux15 = new IntegerConnection(c13_else,
			"aux15", "");

	private final IntegerConnection output0 = new IntegerConnection(this,
			"output0", "");

	public MedianaModel() {
		super("MedianaModel", null);
		c5.addInputPort("y");
		c5.addInputPort("z");
		c5.addInputPort("m");
		c5.addOutputPort("m");
		c5.setThenSystem(c5_then);
		c5.setElseSystem(c5_else);
		c5_then.addInputPort("x");
		c5_then.addInputPort("y");
		c5_then.addInputPort("z");
		c5_then.addInputPort("m");
		c5_then.addOutputPort("m");
		c5_else.addInputPort("x");
		c5_else.addInputPort("y");
		c5_else.addInputPort("z");
		c5_else.addInputPort("m");
		c5_else.addOutputPort("m");

		input0.addPort(c0.getIn());		
		input1.addPort(c1.getIn());
		input2.addPort(c2.getIn());

		x0.addPort(c0.getOut());
		x0.addPort(c5.getInputPort("x"));
		
		y1.addPort(c1.getOut());
		y1.addPort(c4.getIn1());
		y1.addPort(c5.getInputPort("y"));
		
		z2.addPort(c2.getOut());
		z2.addPort(c3.getIn());
		z2.addPort(c4.getIn2());
		z2.addPort(c5.getInputPort("z"));

		m3.addPort(c3.getOut());
		m3.addPort(c5.getInputPort("m"));

		c7.addInputPort("x");
		c7.addInputPort("y");
		c7.addInputPort("z");
		c7.addInputPort("m");
		c7.addOutputPort("m");
		c7.setThenSystem(c7_then);
		c7.setElseSystem(c7_else);
		c7_then.addInputPort("x");
		c7_then.addInputPort("y");
		c7_then.addInputPort("z");
		c7_then.addInputPort("m");
		c7_then.addOutputPort("m");
		c7_else.addInputPort("x");
		c7_else.addInputPort("y");
		c7_else.addInputPort("z");
		c7_else.addInputPort("m");
		c7_else.addOutputPort("m");

		x0_l.addPort(c5_then.getInputPort("x"));
		x0_l.addPort(c6.getIn1());
		x0_l.addPort(c7.getInputPort("x"));
		y1_l.addPort(c5_then.getInputPort("y"));
		y1_l.addPort(c6.getIn2());
		y1_l.addPort(c7.getInputPort("y"));
		z2_l.addPort(c5_then.getInputPort("z"));
		z2_l.addPort(c7.getInputPort("z"));
		m3_l.addPort(c5_then.getInputPort("m"));
		m3_l.addPort(c7.getInputPort("m"));

		x0_ll.addPort(c7_then.getInputPort("x"));
		y1_ll.addPort(c7_then.getInputPort("y"));
		y1_ll.addPort(c8.getIn());
		z2_ll.addPort(c7_then.getInputPort("z"));
		m3_ll.addPort(c7_then.getInputPort("m"));

		c10.addInputPort("x");
		c10.addInputPort("y");
		c10.addInputPort("z");
		c10.addInputPort("m");
		c10.addOutputPort("m");
		c10.setThenSystem(c10_then);
		c10_then.addInputPort("x");
		c10_then.addInputPort("y");
		c10_then.addInputPort("z");
		c10_then.addInputPort("m");
		c10_then.addOutputPort("m");

		x0_lll.addPort(c7_else.getInputPort("x"));
		x0_lll.addPort(c9.getIn1());
		x0_lll.addPort(c10.getInputPort("x"));

		y1_lll.addPort(c7_else.getInputPort("y"));
		y1_lll.addPort(c10.getInputPort("y"));
		
		z2_lll.addPort(c7_else.getInputPort("z"));
		z2_lll.addPort(c9.getIn2());
		z2_lll.addPort(c10.getInputPort("z"));
		
		m3_lll.addPort(c7_else.getInputPort("m"));
		m3_lll.addPort(c10.getInputPort("m"));

		y1_llll.addPort(c10_then.getInputPort("y"));
		y1_llll.addPort(c11.getIn());
		
		c13.addInputPort("x");
		c13.addInputPort("y");
		c13.addInputPort("z");
		c13.addInputPort("m");
		c13.addOutputPort("m");
		c13.setThenSystem(c13_then);
		c13.setElseSystem(c13_else);
		c13_then.addInputPort("x");
		c13_then.addInputPort("y");
		c13_then.addInputPort("z");
		c13_then.addInputPort("m");
		c13_then.addOutputPort("m");
		c13_else.addInputPort("x");
		c13_else.addInputPort("y");
		c13_else.addInputPort("z");
		c13_else.addInputPort("m");
		c13_else.addOutputPort("m");

		x0_iv.addPort(c5_else.getInputPort("x"));
		x0_iv.addPort(c12.getIn1());
		x0_iv.addPort(c13.getInputPort("x"));
		y1_iv.addPort(c5_else.getInputPort("y"));
		y1_iv.addPort(c12.getIn2());
		y1_iv.addPort(c13.getInputPort("y"));
		z2_iv.addPort(c5_else.getInputPort("z"));
		z2_iv.addPort(c13.getInputPort("z"));
		m3_iv.addPort(c5_else.getInputPort("m"));
		m3_iv.addPort(c13.getInputPort("m"));

		x0_v.addPort(c13_then.getInputPort("x"));
		y1_v.addPort(c13_then.getInputPort("y"));
		y1_v.addPort(c14.getIn());
		z2_v.addPort(c13_then.getInputPort("z"));
		m3_v.addPort(c13_then.getInputPort("m"));

		c16.addInputPort("x");
		c16.addInputPort("y");
		c16.addInputPort("z");
		c16.addInputPort("m");
		c16.addOutputPort("m");
		c16.setThenSystem(c16_then);
		c16_then.addInputPort("x");
		c16_then.addInputPort("y");
		c16_then.addInputPort("z");
		c16_then.addInputPort("m");
		c16_then.addOutputPort("m");
		
		x0_vi.addPort(c13_else.getInputPort("x"));
		x0_vi.addPort(c15.getIn1());		
		x0_vi.addPort(c16.getInputPort("x"));
		y1_vi.addPort(c13_else.getInputPort("y"));
		y1_vi.addPort(c16.getInputPort("y"));
		z2_vi.addPort(c13_else.getInputPort("z"));
		z2_vi.addPort(c15.getIn2());
		z2_vi.addPort(c16.getInputPort("z"));
		m3_vi.addPort(c13_else.getInputPort("m"));
		m3_vi.addPort(c16.getInputPort("m"));

		x0_vii.addPort(c16_then.getInputPort("x"));
		x0_vii.addPort(c17.getIn());

		m5.addPort(c5.getOutputPort("m"));		
		m5.addPort(c18.getIn());

		m7.addPort(c5_then.getOutputPort("m"));
		m7.addPort(c7.getOutputPort("m"));

		m8.addPort(c7_then.getOutputPort("m"));
		m8.addPort(c8.getOut());

		m10.addPort(c7_else.getOutputPort("m"));
		m10.addPort(c10.getOutputPort("m"));
		
		m11.addPort(c10_then.getOutputPort("m"));
		m11.addPort(c11.getOut());

		m13.addPort(c5_else.getOutputPort("m"));
		m13.addPort(c13.getOutputPort("m"));
		
		m14.addPort(c13_then.getOutputPort("m"));
		m14.addPort(c14.getOut());
		
		m16.addPort(c13_else.getOutputPort("m"));
		m16.addPort(c16.getOutputPort("m"));
		
		m17.addPort(c16_then.getOutputPort("m"));
		m17.addPort(c17.getOut());
		
		aux4.addPort(c4.getResult());
		aux4.addPort(c5.getCondResult());
		
		aux6.addPort(c6.getResult());
		aux6.addPort(c7.getCondResult());
		
		aux9.addPort(c9.getResult());
		aux9.addPort(c10.getCondResult());
		
		aux12.addPort(c12.getResult());
		aux12.addPort(c13.getCondResult());
		
		aux15.addPort(c15.getResult());
		aux15.addPort(c16.getCondResult());

		output0.addPort(c18.getOut());
	}
}
