public class SomaNumeros extends BaseProgram {

	public static void main(String[] args) {
		int n, i, j, sum;

		n = readInt();
		sum = 0;

		for (i = 0; i < n; i = i + 1)
			for (j = 0; j < i; j = j + 1)
				sum = sum + 1;

		writeInt("Sum is: ", sum);
	}
}
