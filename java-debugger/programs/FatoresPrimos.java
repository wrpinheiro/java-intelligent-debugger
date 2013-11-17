public class FatoresPrimos extends BaseProgram {
  /**
   * Recebe como entrada um n�mero inteiro n e imprime na tela os fatores primos obtidos na decomposi��o de n.
   * Tamb�m imprime a multiplicidade de cada fator.
   * 
   * Utilize o m�todo writeInt(<texto>, <inteiro>) para imprimir o fator e a sua multiplicidade.
   * 
   * @param n um inteiro > 0.
   */
	public void decompoe(int n) {
    int fator;   /* candidato a fator do numero dado */
    int mult;    /* multiplicidade de fator */

    fator = 3;
    while (n > 1) {
      mult = 0;
      while (n % fator == 0) {
        mult ++;
        n = n / fator; 
      }
      
      if (mult != 0) {
        writeInt("Fator: ", fator);
        writeInt("Multiplicidade: ", mult); 
      }
      fator++; 
    }
	}
}
