public class Multa extends BaseProgram {
	public static void main(String[] args) {
		int valor, multa, mora, novoValor;
		valor = readInt();
		multa = readInt();
		mora = readInt();
		novoValor = valor;
		if (multa == 1)
			novoValor = novoValor + 50;
		else novoValor = novoValor -25;
		if (mora == 1)
			novoValor = novoValor - 5;
		else novoValor = novoValor + 15;
		writeInt("valor dar parcela é: ", novoValor);
	}
}
