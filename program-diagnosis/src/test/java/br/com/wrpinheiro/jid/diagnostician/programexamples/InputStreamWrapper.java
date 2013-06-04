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
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wrp
 * 
 * 05/01/2009
 */
public class InputStreamWrapper extends InputStream {
	private boolean prepared = false;
	private InputStream is;
	private Queue<Integer> queue = new LinkedList<Integer>();

	public void addInt(Integer i) {
		this.queue.offer(i);
		this.prepared = false;
	}

	private void prepare() throws IOException {
		if (prepared)
			return;

		byte[] data;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		while (!queue.isEmpty()) {
			dos.writeInt(queue.poll());
		}
		dos.flush();

		data = baos.toByteArray();
		dos.close();
		is = new ByteArrayInputStream(data);

		this.prepared = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException {
		prepare();
		return this.is.read();
	}

	@Test
	public void test() throws IOException {
		InputStreamWrapper w = new InputStreamWrapper();
		w.addInt(10);
		w.addInt(35);
		w.addInt(-123);
		w.addInt(-5);
		w.addInt(89);

		DataInputStream dis = new DataInputStream(w);
		Assert.assertEquals(10, dis.readInt());
		Assert.assertEquals(35, dis.readInt());
		Assert.assertEquals(-123, dis.readInt());
		Assert.assertEquals(-5, dis.readInt());
		Assert.assertEquals(89, dis.readInt());
		
		dis.close();
	}
}
