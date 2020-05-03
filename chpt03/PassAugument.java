package chpt03;

public class PassAugument {
	public static void stringAppend(String a) {
		a += "(Test1)";
	}
	public static String stringAppendRet(String a) {
		a += "(Test2)";
		return a;
	}
	public static void stringBfAppend(StringBuffer a) {
		a.append("(Test3)");
	}

	public static void main(String[] args) {
		String s1 = new String("Hello");
		StringBuffer s2 = new StringBuffer("Hello");
		
		// Test1
		System.out.println("Test1");
		System.out.println(s1);
		stringAppend(s1);
		System.out.println(s1);
		
		// Test2
		System.out.println();
		System.out.println("Test2");
		System.out.println(s1);
		s1 = stringAppendRet(s1);
		System.out.println(s1);
		
		// Test3
		System.out.println();
		System.out.println("Test3");
		System.out.println(s2);
		stringBfAppend(s2);
		System.out.println(s2);
		
	}

}
