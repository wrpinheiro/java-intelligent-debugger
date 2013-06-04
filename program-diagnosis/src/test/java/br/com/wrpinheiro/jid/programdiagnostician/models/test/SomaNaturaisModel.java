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
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerLessOrEqual;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.BlockComponent;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.Conditional;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.While;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * @author wrp
 * 
 * 31/01/2009
 */
public class SomaNaturaisModel extends IntegerSystem {
	/*
	Soma os n primeiros naturais.

	01 #include <stdio.h>
	02 int main () {
	03   int n, i, soma;
	04   printf ("digite o valor de n\n");
	05   scanf("%d", &n);
	06   soma = 0;
	07   i=1;
	08   while(i <= n) {
	10     soma = soma + 1;
	11     i = i+ 1;
	12   }
	13   printf ("A soma eh %d\n", soma);
	14   return 0;
	15 }
	 */
	private IntegerAssignment c0 = new IntegerAssignment(this, "C0", 5, null);
	private IntegerConnection input0 = new IntegerConnection(this, "input0", "");
	private IntegerConnection n0 = new IntegerConnection(this, "n0", "");

	private IntegerAssignment c1 = new IntegerAssignment(this, "C1", 6, null);
	private IntegerConnection const0 = new IntegerConnection(this, "const0", "");
	private IntegerConnection soma1 = new IntegerConnection(this, "soma1", "");

	private IntegerAssignment c2 = new IntegerAssignment(this, "C2", 7, null);
	private IntegerConnection const1 = new IntegerConnection(this, "const1", "");
	private IntegerConnection i2 = new IntegerConnection(this, "i2", "");

	private BlockComponent loopBlock = new While(this, "LOOP-BLOCK", DefaultSets.EMPTY, null);

	private IntegerLessOrEqual c3 = new IntegerLessOrEqual(loopBlock, "C3", 8, null);
	private IntegerConnection aux3 = new IntegerConnection(loopBlock, "aux3", "");

	private Conditional c4 = new Conditional(loopBlock, "C4", DefaultSets.EMPTY, null);
	private DummyBlockComponent c4_then = new DummyBlockComponent(loopBlock, "c4_then", DefaultSets.EMPTY, null);

	private IntegerConnection soma_l = new IntegerConnection(c4_then, "soma_l", "");
	private IntegerAdder c5 = new IntegerAdder(c4_then, "C5", 10, null);
	private IntegerConnection aux5 = new IntegerConnection(c4_then, "aux5", "");
	private IntegerAssignment c6 = new IntegerAssignment(c4_then, "C6", 10, null);
	private IntegerConnection soma6 = new IntegerConnection(c4_then, "soma6", "");

	private IntegerConnection i_l = new IntegerConnection(c4_then, "i_l", "");
	private IntegerAdder c7 = new IntegerAdder(c4_then, "C7", 11, null);
	private IntegerConnection aux7 = new IntegerConnection(c4_then, "aux7", "");
	private IntegerAssignment c8 = new IntegerAssignment(c4_then, "C8", 11, null);
	private IntegerConnection i8 = new IntegerConnection(c4_then, "i8", "");

	private IntegerConnection soma4 = new IntegerConnection(this, "soma4", "");
	private IntegerConnection i4 = new IntegerConnection(this, "i4", "");

	private IntegerAssignment c9 = new IntegerAssignment(this, "C9", 13, null);
	private IntegerConnection output0 = new IntegerConnection(this, "output0", "");

	public SomaNaturaisModel() {
		// TODO O bloco do while nao esta indo para a lista de propagacao quando a 
		// porta eh modificada. Isso acontece pq o system associado as portas 
		// eh o proprio bloco. Uma alternativa para solucionar o problema eh criar uma
		// nova classe que herda de DummyBlockComponent e prover uma implementacao semelhante
		// aquela feita no Conditional.
		
		super("SomaNaturaisModel", null);
		
		loopBlock.addInputPort("soma");
		loopBlock.addInputPort("i");
		loopBlock.addInputPort("n");
		loopBlock.addOutputPort("soma");

		c4.setThenSystem(c4_then);
		c4.addInputPort("soma");
		c4.addInputPort("i");
		c4.addOutputPort("soma");
		c4.addOutputPort("i");

		c4_then.addInputPort("soma");
		c4_then.addInputPort("i");
		c4_then.addOutputPort("soma");
		c4_then.addOutputPort("i");
		
		input0.addPort(c0.getIn());
		n0.addPort(c0.getOut());
		n0.addPort(loopBlock.getInputPort("n"));

		const0.addPort(c1.getIn());
		soma1.addPort(c1.getOut());
		soma1.addPort(loopBlock.getInputPort("soma"));
		soma1.addPort(c4.getInputPort("soma"));

		const1.addPort(c2.getIn());
		i2.addPort(c2.getOut());
		i2.addPort(c3.getIn1());
		i2.addPort(c4.getInputPort("i"));
		i2.addPort(loopBlock.getInputPort("i"));

		n0.addPort(c3.getIn2());

		aux3.addPort(c3.getResult());
		aux3.addPort(c4.getCondResult());

		soma_l.addPort(c4_then.getInputPort("soma"));
		soma_l.addPort(c5.getIn1());
		const1.addPort(c5.getIn2());
		aux5.addPort(c5.getResult());
		aux5.addPort(c6.getIn());
		soma6.addPort(c6.getOut());
		soma6.addPort(c4_then.getOutputPort("soma"));

		i_l.addPort(c4_then.getInputPort("i"));
		i_l.addPort(c7.getIn1());
		const1.addPort(c7.getIn2());
		aux7.addPort(c7.getResult());
		aux7.addPort(c8.getIn());
		i8.addPort(c8.getOut());
		i8.addPort(c4_then.getOutputPort("i"));

		i4.addPort(c4.getOutputPort("i"));

		soma4.addPort(c4.getOutputPort("soma"));
		soma4.addPort(c9.getIn());
		soma4.addPort(loopBlock.getOutputPort("soma"));

		output0.addPort(c9.getOut());
	}
}