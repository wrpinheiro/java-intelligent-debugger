public class Maximiza extends BaseProgram {
  /**
   * Dados dois naturais m e n determinar, entre todos os pares de números
   * naturais (x,y) tais que x < m e y < n, um par para o qual o valor da
   * expressão xy - x2 + y seja máximo e calcular também esse máximo.
   * 
   * @param n
   *          um inteiro.
   */
  public void max(int m, int n) {
    int x, y;         /* usados para o calculo do maximo */
    int valor;        /* guarda x*y - x*x + y */
    int xmax, ymax;   /* argumentos que maximizam a funcao */
    int valorMax;     /* guarda xmax*ymax - xmax*xmax + ymax */

    /* inicializacoes */
    xmax = 0;
    ymax = 0;
    valorMax = 0;  /* valormax ==  xmax*ymax - xmax*xmax + ymax */

    /* testa tos os possiveis valores da funcao */
    for (x = 0; x <= n; x++) {
        for (y = 0; y <= m; y++) {
            valor = x*y - x*x + y;
            if (valor > valorMax) {
                valorMax = valor;
                xmax = x;
                ymax = y;
            }
        }  
    }

    writeInt("Valor max: ", valorMax);
    writeInt("X = ", xmax);
    writeInt("Y = ", ymax);
  }
}
