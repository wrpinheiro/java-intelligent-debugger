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
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 *
 * 31/01/2009
 */
public class SomaMultiplosModel extends IntegerSystem {
	/*

	Calcula a soma dos múltiplos de 5 entre 100 e n.

	- 01 #include <stdio.h>
	- 02 int main () {
	- 03   int n, i, sum,m;
	- 04   printf("input n\n");
	- 05   scanf("%d", &n);
	- 06   sum = 0;
	- 07   i=100;
	- 08   m=0;
	09   while(i < n) {
	10     m=i%5;
	11     if(m==0){
	12       sum = sum + i;
	13     }
	14     i = i+ 1;
	15   }
	16   printf ("Sum %d\n", sum);
	17   return 0;
	18 }
	 */
	private IntegerConnection input0 = new IntegerConnection(this, "input0", "");
	private IntegerConnection n0 = new IntegerConnection(this, "n0", "");
	private IntegerAssignment c0 = new IntegerAssignment(this, "C0", 5, null);

	private IntegerConnection const0 = new IntegerConnection(this, "const0", ""); 
	private IntegerAssignment c1 = new IntegerAssignment(this, "C1", 6, null);
	private IntegerConnection sum1 = new IntegerConnection(this, "sum1", "");

	private IntegerConnection const100 = new IntegerConnection(this, "const100", "");
	private IntegerConnection i2 = new IntegerConnection(this, "i2", "");
	private IntegerAssignment c2 = new IntegerAssignment(this, "C2", 7, null);

	private IntegerConnection m3 = new IntegerConnection(this, "m2", "");
	private IntegerAssignment c3 = new IntegerAssignment(this, "C3", 8, null);

	public SomaMultiplosModel() {
		super("SomaMultiplosModel", null);
		
		input0.addPort(c0.getIn());
		n0.addPort(c0.getOut());

		const0.addPort(c1.getIn());
		sum1.addPort(c1.getOut());

		const100.addPort(c2.getIn());
		i2.addPort(c2.getOut());
		
		const0.addPort(c3.getIn());
		m3.addPort(c3.getOut());
	}
}
