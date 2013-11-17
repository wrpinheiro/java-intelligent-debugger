public class Palindrome {
  /**
   * Recebe como entrada um número natural n > 10 e devolve 1 caso n seja palíndrome ou 0 caso contrário.
   * 
   * @param n um inteiro.
   */
  public int verificaPalindrome(int n) {
    int aux;
    int reverso;
    int d;
    int resultado;

    aux = n;
    reverso = 1;

    while (aux != 0) {
      d = aux % 10;
      reverso = reverso * 10 + d;
      aux = aux / 10;
    }

    if (reverso == n)
      resultado = 1;
    else resultado = 0;
    
    return resultado;
  }
}
