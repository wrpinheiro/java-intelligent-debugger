public class Perfeito {
  /**
   * Recebe como entrada um número inteiro n e imprime na tela os fatores primos
   * obtidos na decomposição de n. Também imprime a multiplicidade de cada
   * fator.
   * 
   * Utilize o método writeInt(<texto>, <inteiro>) para imprimir o fator e a sua
   * multiplicidade.
   * 
   * @param n
   *          um inteiro > 0.
   */
  public int verificaPerfeito(int n) {
    int soma;      /* soma dos valores dos divisores de n */
    int divisor;   /* candidato a divisor de n            */
    int resultado; /* resultado do calculo                */

    soma = 1;

    for (divisor = 1; divisor < n; divisor = divisor + 1) {
      if (n % divisor == 0)
        soma = soma + divisor;
    }

    if (n == soma)
      resultado = 1;
    else resultado = 0;

    return resultado;
  }
}
