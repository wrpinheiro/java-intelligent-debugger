public class Mediana4 extends BaseProgram {
	public static void main(String[] args) {
		int x, y, z, m;
		x = readInt();
		y = readInt();
		z = readInt();

		m = z;
		if (y < x) {
			if (y > z) {
				m = y;
			}
		}
			
		if (x < y) {
			if (x > z) {
				m = x;
			}
		}

		if (x < z) {
			if (z > y) {
				m = z;
			}
		}

		writeInt("numero medio eh: ", m);
	}
}
