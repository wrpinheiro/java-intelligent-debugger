public class SomaMultiplos extends BaseProgram {

	public static void main(String[] args) {
		int n, i, sum, m;

		n = readInt();

		sum = 0;
		i = 100;
		m = 0;
		while (i < n) {
			m = i % 5;
			if (m == 0) {
				sum = sum + i;
			}
			i = i + 1;
		}
		writeInt("Sum is: ", sum);
	}
}
