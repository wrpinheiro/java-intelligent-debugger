public class SomaNNumeros extends BaseProgram {

	public static void main(String[] args) {
		int n, i, sum, number;

		n = readInt();
		sum = 0;
		i = 0;

		while (i < n) {
			number = readInt();
			sum = sum + number;
			i = i + 1;
		}

		writeInt("Sum is: ", sum);
	}
}
