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
package br.com.wrpinheiro.jid.diagnostician.programexamples;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wrp
 * 
 * 05/01/2009
 */
public class MaratonaTest {
	@Test
	public void testMaratona() throws IOException {
		InputStreamWrapper in = new InputStreamWrapper();
		OutputStreamWrapper out = new OutputStreamWrapper();
		PrintStream ps = new PrintStream(out);

		System.setIn(in);
		System.setOut(ps);

		in.addInt(2);
		in.addInt(10);
		in.addInt(0);
		in.addInt(23400);
		in.addInt(70);

		Maratona.main(null);

		ps.flush();
		DataInputStream dis = out.getDataInputStream();

		BufferedReader d = new BufferedReader(new InputStreamReader(dis));

		Assert.assertEquals("1", d.readLine());
		Assert.assertEquals("70000", d.readLine());

		dis.close();
	}
}
