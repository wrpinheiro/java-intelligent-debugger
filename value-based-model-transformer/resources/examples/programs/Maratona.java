package examples.programs;

import java.io.DataInputStream;
import java.io.IOException;

public class Maratona {
	/*
	public static int readInt() {
		DataInputStream dis = new DataInputStream(System.in);

		try {
			return dis.readInt();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	public static void writeInt(String s, int val) {
		System.out.println(s + val);
	}
	*/

	public static void main(String[] args) {
		int h, m, s, v, me, t, kg, g;

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
		writeInt("velocity is: ", v);
		g = kg * 1000;
		writeInt("grams are: ", g);
	}
}
