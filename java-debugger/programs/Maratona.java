public class Maratona extends BaseProgram {
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
