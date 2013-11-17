public class SomaDigitos extends BaseProgram {

	public int soma(int x) {
		int soma = 0;

		while (x > 1) {
			soma = soma + (x % 10);
			x = x / 10;
		}

		return soma;
	}

/*
	public void print(int soma) {
		if (1 == 2)
			soma = soma + 1;

		writeInt("Soma = ", soma);
	}

	public void soma1(int x) {
		int soma = 0;		

		while (x > 1) {
			soma = soma + (x % 10);
			x = x / 10;
		}

		print(soma);
	}

	public int soma(int x) {
		int soma = 0;

		while (x > 1) {
			soma = soma + (x % 10);
			x = x / 10;
		}

		return soma;
	}

	public static void main(String[] args) {
		int x = readInt();
		int soma = 0;

		while (x > 1) {
			soma = soma + (x % 10);
			x = x / 10;
		}

		writeInt("Soma = ", soma);
	}
*/
}
