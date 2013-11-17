public class NumeroSegmentos extends BaseProgram {
	public static void main(String[] args) {
		int n,        /* comprimento da sequencia */
		    i,        /* contador de numeros da sequencia lidos */
		    atual,    /* ultimo numero da sequencia lido */
		    anterior, /* numero anterior da sequencia */
		    cont;     /* quantidade de segmentos de numeros iguais na sequencia */

		n = readInt();

		/* inicializacoes */
		anterior = readInt(); /* anterior comeca com o primeiro da sequencia */
		cont = 1; /* inicialmente temos um segmento */

		for (i = 2; i <= n; i = i + 1) {
			atual = readInt(); /* proximo da sequencia */
			if (atual != anterior) {
				cont = cont + 1; /* comecou um novo segmento de numeros iguais */
				anterior = atual;
			}
		}
		writeInt(
				"Quantidade de segmentos de numeros iguais da sequencia = ",
				cont);
	}
}
