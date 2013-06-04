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
package br.com.wrpinheiro.jid.diagnostician.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 *         29/03/2008
 */
public class SimpleConnectionTest {

	@Test
	public void testFreeConnection() {

		IntegerSystem system = new IntegerSystem("SimpleConnectionTest",
				null);

		IntegerBinaryOperator add = new IntegerAdder(system, "A1", 0, null);

		IntegerConnection ic1 = new IntegerConnection(system, "IC1", "");
		IntegerConnection ic2 = new IntegerConnection(system, "IC2", "");

		ic1.addPort(add.getIn1());
		ic2.addPort(add.getResult());

		try {
			ic1.setValue(new Integer(7));
			add.getIn2().setValue(new Integer(4), true);

			assertNull(add.getResult().getValue());

			add.propagate();

			assertEquals(new Integer(11), ic2.getValue());
		} catch (ConflictException e) {
			fail();
		}
	}
}
