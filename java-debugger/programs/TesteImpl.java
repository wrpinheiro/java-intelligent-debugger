public class TesteImpl extends BaseProgram {
  public static void main(String[] args) {
    int k = 2 + 3 * 4 + 5;
    int x = k;

    int a, b;
    a = 3;
    b = 2;

    if ((a > b) && true)
      x /= 2;

    if (1000 <= x)
      --x;

    x = b--;

    writeInt("x = ", x);

  }
}
