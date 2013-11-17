public class Calculadora extends BaseProgram {
  public static int soma(int x, int y) {
    return x + y;
  }
  
  public static void main(String[] args) {
   int x = readInt();
   int y = readInt();
   int z;
   
   z = soma(x, y);
   
   writeInt("A soma e' ", z);
  }
}