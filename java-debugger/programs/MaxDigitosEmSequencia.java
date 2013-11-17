public class MaxDigitosEmSequencia {
	public int contaMaxDigEMSeq(int n) {
		int c, dat, dant;
		int max;
		int num;

		num = n;

		max = 2;
		c = 1;

		if (num < 10)
			return max;

		dat = num % 10;
		num = num / 10;

		while (num > 0) {
			dant = dat;
			dat = num % 10;
			num = num / 10;

			if (dat == dant) {
				c++;
				if (c > max)
					max = c;
			} else {
				c = 1;
			}
		}
		return max;
	}
}
