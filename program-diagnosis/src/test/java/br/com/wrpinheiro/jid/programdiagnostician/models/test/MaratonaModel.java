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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerDivisor;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.BlockComponent;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * @author wrp
 * 
 * 10/01/2009
 */
public class MaratonaModel extends IntegerSystem {
	/*
	 1    #include<stdio.h>
	 2    int main() {
	 3       int h,m,s,v,me,t,kg,g;
	 4       printf("input hours \n:";
	 5       scanf("%d",&h);
	 6       printf("input minutes \n");
	 7       scanf("%d",&m);
	 8       printf("input seconds \n");
	 9       scanf("%d",&s);
	 10      printf("input meters \n");
	 11      scanf("%d", &me);
	 12      printf("input kg \n");
	 13      scanf("%d",&kg);
	 14      t=h*3600+m*600+s;
	 15      v=me/t;
	 16      printf("velocity %d \n",v);
	 17      g=kg*100;
	 18      printf("grams %d \n",g);
	 19      return 0;
	 20   }
	 */
	
	private final IntegerConnection input0 = new IntegerConnection(this,
			"input0", "");
	private final IntegerConnection input1 = new IntegerConnection(this,
			"input1", "");
	private final IntegerConnection input2 = new IntegerConnection(this,
			"input2", "");
	private final IntegerConnection input3 = new IntegerConnection(this,
			"input3", "");
	private final IntegerConnection input4 = new IntegerConnection(this,
			"input4", "");

	private final IntegerConnection output0 = new IntegerConnection(this,
			"output0", "");
	private final IntegerConnection output1 = new IntegerConnection(this,
			"output1", "");

	private final IntegerConnection h = new IntegerConnection(this, "h0", "");
	private final IntegerConnection m = new IntegerConnection(this, "m0", "");
	private final IntegerConnection s = new IntegerConnection(this, "s0", "");
	private final IntegerConnection me = new IntegerConnection(this, "me0", "");
	private final IntegerConnection kg = new IntegerConnection(this, "kg0", "");
	private final IntegerConnection t = new IntegerConnection(this, "t0", "");
	private final IntegerConnection v = new IntegerConnection(this, "v0", "");
	private final IntegerConnection g = new IntegerConnection(this, "g0", "");

	private final IntegerConnection c3600 = new IntegerConnection(this,
			"0", "");
	private final IntegerConnection c60 = new IntegerConnection(this,
			"600", "");
	private final IntegerConnection c1000 = new IntegerConnection(this,
			"1000", "");

	private final IntegerConnection temp0 = new IntegerConnection(this,
			"TEMP0", "");
	private final IntegerConnection temp1 = new IntegerConnection(this,
			"TEMP1", "");
	private final IntegerConnection temp2 = new IntegerConnection(this,
			"TEMP2", "");
	private final IntegerConnection temp3 = new IntegerConnection(this,
			"TEMP3", "");
	private final IntegerConnection temp4 = new IntegerConnection(this,
			"TEMP4", "");
	private final IntegerConnection temp5 = new IntegerConnection(this,
			"TEMP5", "");

	private final IntegerAssignment assignInput0 = new IntegerAssignment(this,
			"C0", 5, null);
	private final IntegerAssignment assignInput1 = new IntegerAssignment(this,
			"C1", 7, null);
	private final IntegerAssignment assignInput2 = new IntegerAssignment(this,
			"C2", 9, null);
	private final IntegerAssignment assignInput3 = new IntegerAssignment(this,
			"C3", 11, null);
	private final IntegerAssignment assignInput4 = new IntegerAssignment(this,
			"C4", 13, null);
	
	private final IntegerMultiplier m1 = new IntegerMultiplier(this, "C5", 14, null);
	private final IntegerMultiplier m2 = new IntegerMultiplier(this, "C6", 14, null);
	private final IntegerMultiplier m3 = new IntegerMultiplier(this, "C7", 17, null);
	private final IntegerAdder a1 = new IntegerAdder(this, "C8", 14, null);
	private final IntegerAdder a2 = new IntegerAdder(this, "C9", 14, null);

	private final IntegerDivisor d1 = new IntegerDivisor(this, "C10", 15, null);

	private final IntegerAssignment assign1 = new IntegerAssignment(this,
			"C11", 14, null);
	private final IntegerAssignment assign2 = new IntegerAssignment(this,
			"C12", 15, null);
	private final IntegerAssignment assign3 = new IntegerAssignment(this,
			"C13", 17, null);

	private final IntegerAssignment assignOutput0 = new IntegerAssignment(this,
			"C14", 16, null);
	private final IntegerAssignment assignOutput1 = new IntegerAssignment(this,
			"C15", 18, null);

	public MaratonaModel() {
		super("MaratonaModel", null);
		
		// assign the input values to the variables.
		input0.addPort(assignInput0.getIn());
		h.addPort(assignInput0.getOut());

		input1.addPort(assignInput1.getIn());
		m.addPort(assignInput1.getOut());

		input2.addPort(assignInput2.getIn());
		s.addPort(assignInput2.getOut());

		input3.addPort(assignInput3.getIn());
		me.addPort(assignInput3.getOut());

		input4.addPort(assignInput4.getIn());
		kg.addPort(assignInput4.getOut());

		h.addPort(m1.getIn1());
		c3600.addPort(m1.getIn2());
		temp0.addPort(m1.getResult());

		m.addPort(m2.getIn1());
		c60.addPort(m2.getIn2());
		temp1.addPort(m2.getResult());

		temp0.addPort(a1.getIn1());
		temp1.addPort(a1.getIn2());
		temp2.addPort(a1.getResult());

		temp2.addPort(a2.getIn1());
		s.addPort(a2.getIn2());
		temp3.addPort(a2.getResult());

		temp3.addPort(assign1.getIn());
		t.addPort(assign1.getOut());

		me.addPort(d1.getIn1());
		t.addPort(d1.getIn2());
		temp4.addPort(d1.getResult());

		temp4.addPort(assign2.getIn());
		v.addPort(assign2.getOut());

		v.addPort(assignOutput0.getIn());
		output0.addPort(assignOutput0.getOut());

		kg.addPort(m3.getIn1());
		c1000.addPort(m3.getIn2());
		temp5.addPort(m3.getResult());

		temp5.addPort(assign3.getIn());
		g.addPort(assign3.getOut());

		g.addPort(assignOutput1.getIn());
		output1.addPort(assignOutput1.getOut());
		
		BlockComponent abs = new DummyBlockComponent(this, "C16", DefaultSets.EMPTY, null);
		assignInput0.replaceParent(abs);
		assignInput1.replaceParent(abs);
		assignInput2.replaceParent(abs);
		//abs.refine();
	}

	public IntegerConnection getInput0() {
		return input0;
	}

	public IntegerConnection getInput1() {
		return input1;
	}

	public IntegerConnection getInput2() {
		return input2;
	}

	public IntegerConnection getInput3() {
		return input3;
	}

	public IntegerConnection getInput4() {
		return input4;
	}

	public IntegerConnection getOutput0() {
		return output0;
	}

	public IntegerConnection getOutput1() {
		return output1;
	}

	public IntegerConnection getH() {
		return h;
	}

	public IntegerConnection getM() {
		return m;
	}

	public IntegerConnection getS() {
		return s;
	}

	public IntegerConnection getMe() {
		return me;
	}

	public IntegerConnection getKg() {
		return kg;
	}

	public IntegerConnection getT() {
		return t;
	}

	public IntegerConnection getV() {
		return v;
	}

	public IntegerConnection getG() {
		return g;
	}

	public IntegerConnection getC3600() {
		return c3600;
	}

	public IntegerConnection getC600() {
		return c60;
	}

	public IntegerConnection getC1000() {
		return c1000;
	}

	public IntegerConnection getTemp0() {
		return temp0;
	}

	public IntegerConnection getTemp1() {
		return temp1;
	}

	public IntegerConnection getTemp2() {
		return temp2;
	}

	public IntegerConnection getTemp3() {
		return temp3;
	}

	public IntegerConnection getTemp4() {
		return temp4;
	}

	public IntegerConnection getTemp5() {
		return temp5;
	}

	public IntegerAssignment getAssignInput0() {
		return assignInput0;
	}

	public IntegerAssignment getAssignInput1() {
		return assignInput1;
	}

	public IntegerAssignment getAssignInput2() {
		return assignInput2;
	}

	public IntegerAssignment getAssignInput3() {
		return assignInput3;
	}

	public IntegerAssignment getAssignInput4() {
		return assignInput4;
	}

	public IntegerMultiplier getM1() {
		return m1;
	}

	public IntegerMultiplier getM2() {
		return m2;
	}

	public IntegerMultiplier getM3() {
		return m3;
	}

	public IntegerAdder getA1() {
		return a1;
	}

	public IntegerAdder getA2() {
		return a2;
	}

	public IntegerDivisor getD1() {
		return d1;
	}

	public IntegerAssignment getAssign1() {
		return assign1;
	}

	public IntegerAssignment getAssign2() {
		return assign2;
	}

	public IntegerAssignment getAssign3() {
		return assign3;
	}

	public IntegerAssignment getAssignOutput0() {
		return assignOutput0;
	}

	public IntegerAssignment getAssignOutput1() {
		return assignOutput1;
	}

}
