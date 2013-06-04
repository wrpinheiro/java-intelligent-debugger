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
import br.com.wrpinheiro.jid.programdiagnosis.ConflictException;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerBinaryOperator;

/**
 * @author wrp
 * 
 *         Sep 2, 2008
 */
public class BooleanOperatorTest {

	protected void testOperation(IntegerBinaryOperator binOp, Integer in1,
			Integer in2, Integer in3, Integer out1, Integer out2, Integer out3)
			throws ConflictException {

		binOp.clearValues();
		assertNull(binOp.getIn1().getValue());
		assertNull(binOp.getIn2().getValue());
		assertNull(binOp.getResult().getValue());

		binOp.getIn1().setValue(in1, true);
		binOp.getIn2().setValue(in2, true);
		binOp.getResult().setValue(in3, true);

		binOp.propagate();

		if (out1 == null)
			assertNull(binOp.getIn1().getValue());
		else
			assertEquals(out1, binOp.getIn1().getValue());

		if (out2 == null)
			assertNull(binOp.getIn2().getValue());
		else
			assertEquals(out2, binOp.getIn2().getValue());

		if (out3 == null)
			assertNull(binOp.getResult().getValue());
		else
			assertEquals(out3, binOp.getResult().getValue());
	}

}
