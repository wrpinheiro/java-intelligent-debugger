import java.util.Scanner;

public class BaseProgram {
	//private static TestCase tc = new TestCase();
	//private static int currRead = 0;
	//private static int currWrite = 0;

	public static int readInt() {
		//int value = Integer.parseInt(tc.getInputs().get("input" + currRead));
		//currRead++;

		//return value;
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}

	public static void writeInt(String s, int val) {
		System.out.print(s);
		System.out.println(val);

		//tc.getExpectedOutputs().put("output" + currWrite, val);
	}
}
