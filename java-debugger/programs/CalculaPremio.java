public class CalculaPremio extends BaseProgram {
	public int calculaPremio(int premiado, int premio) {
		int premioFinal;

		if (premiado == 1) 
			premioFinal = premio * 12 * premio / 100 + 50;
		else
			premioFinal = premio * 7 * premio / 100;

		return premioFinal;
	}
/*	
	public static void main(String[] args) {
		CalculaPremio cp = new CalculaPremio();
		System.out.println(cp.calculaPremio(1, 100));
	}
*/
}
