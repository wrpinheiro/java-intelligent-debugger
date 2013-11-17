public class VerificaPar extends BaseProgram {
	public static void main(String[] args) {
		int x = readInt();
		int ehPar = 0;

		if (x % 2 != 0)
			ehPar = 1;

		writeInt("eh par?", ehPar);
	}
}
