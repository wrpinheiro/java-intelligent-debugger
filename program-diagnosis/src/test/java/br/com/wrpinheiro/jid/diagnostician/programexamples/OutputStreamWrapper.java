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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wrp
 * 
 * 05/01/2009
 */
public class OutputStreamWrapper extends OutputStream {
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		baos.write(b);
	}

	public DataInputStream getDataInputStream() {
		return new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
	}

	@Test
	public void test() throws IOException {
		OutputStreamWrapper w = new OutputStreamWrapper();

		DataOutputStream dos = new DataOutputStream(w);
		dos.writeInt(10);
		dos.writeInt(-5);
		dos.writeInt(32);

		dos.flush();
		dos.close();

		DataInputStream dis = w.getDataInputStream();

		Assert.assertEquals(10, dis.readInt());
		Assert.assertEquals(-5, dis.readInt());
		Assert.assertEquals(32, dis.readInt());
	}
}
