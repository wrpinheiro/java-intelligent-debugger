public class SomaNNumerosComFor extends BaseProgram {

	public static void main(String[] args) {
		int n, i, sum, number;

		n = readInt();

		sum = 0;

		for (i = 1; i < n; i = i + 1) {
			number = readInt();
			sum = sum + number;
		}

		writeInt("Sum is: ", sum);
	}
}
