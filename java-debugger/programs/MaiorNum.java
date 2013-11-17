public class MaiorNum extends BaseProgram {
	int main() {
		int a, b, greater;
		a = readInt();
		b = readInt();
		if (a < b) {
			greater = 3 * a;
		} else {
			greater = 3 * b;
		}
		writeInt("the greater is: ", greater);
		return 0;
	}
}
