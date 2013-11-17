public class Mediana extends BaseProgram {
	public static void main(String[] args) {
		int x, y, z, m;
		x = readInt();
		y = readInt();
		z = readInt();

		m = z;
		if (y < z) {
			if (x < y) {
				m = y;
			} else {
				if (x < z) {
					m = y;
				}
			}
		} else {
			if (x > y) {
				m = y;
			} else {
				if (x > z) {
					m = x;
				}
			}
		}
		writeInt("numero medio eh: ", m);
	}
}
