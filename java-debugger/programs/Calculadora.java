/**
 * Classe de teste.
 *
 * @author wrp
 */
public class Calculadora {
	private int outraSoma(int a, int b) {
		return a + b;
	}

	public int soma(int x, int y) {
		return outraSoma(x, -y);
	}

	public int mult(final int x, int y) {
		return x * y;
	}

	public int subtracao(int x, int y) {
		return soma(x, -y);
	}
}
