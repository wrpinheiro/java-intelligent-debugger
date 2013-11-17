public class SomaMultiplos4 extends BaseProgram {

	public static void main(String[] args) {
		int n, i, sum, term;

		n = readInt();

		sum = 0;
		i = 100;
		term = i;

		while (term <= n) {
			term = i + 5;
			sum = sum + term;
		}
		writeInt("Sum is: ", sum);
	}
}
