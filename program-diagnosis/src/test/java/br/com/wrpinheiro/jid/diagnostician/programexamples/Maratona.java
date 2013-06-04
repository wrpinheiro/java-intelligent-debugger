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

import java.io.DataInputStream;
import java.io.IOException;

public class Maratona {
	public static int readInt() {
		try {
			DataInputStream bus = new DataInputStream(System.in);
			int i = bus.readInt();
			bus.close();
			return i;
		} catch (IOException ex) {
			throw new RuntimeException();
		}
	}

	public static void writeInt(int v) {
		System.out.println(v);
	}

	public static void main(String[] args) {
		int h, m, s, v, me, t, kg, g;

		// horas
		// h = Integer.valueOf(args[0]);
		// minutos
		// m = Integer.valueOf(args[1]);
		// segundos
		// s = Integer.valueOf(args[2]);
		// metros
		// me = Integer.valueOf(args[3]);
		// quilos
		// kg = Integer.valueOf(args[4]);

		// horas
		h = readInt();
		// minutos
		m = readInt();
		// segundos
		s = readInt();
		// metros
		me = readInt();
		// quilos
		kg = readInt();

		t = h * 3600 + m * 600 + s;
		v = me / t;
		writeInt(v);
		//System.out.printf("velocity %d \n", v);
		g = kg * 1000;
		writeInt(g);
		System.out.printf("grams %d \n", g);
	}
}
