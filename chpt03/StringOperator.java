package chpt03;

import java.util.Scanner;

class NoSuchElementException extends Exception{
	NoSuchElementException(String Info){
		super(Info);    // Exception≥ı ºªØ
	}
}

public class StringOperator {
	public int Num;
	public static String ErrorInfo = "Input not a integer, please input again";
	
	public static String blowup(String str) {
		String ans = new String();
		for(int i = 0;i < str.length() - 1;++i) {
			if(str.charAt(i) <= '9' && str.charAt(i) >= '0'&& str.charAt(i + 1) <= 'z' && str.charAt(i + 1) >= 'a') {
				for(int j = 0;j < str.charAt(i) - '0';++j) {
					ans += str.charAt(i + 1);
				}
			}else /*if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z')*/ {
				ans += str.charAt(i);
			}
		}
		ans += str.charAt(str.length() - 1);
		return ans;
	}
	
	public static int maxRun(String str) {
		int []freqs = new int[26];
		int []freqb = new int[26];
		int max = 0;
		for(int i = 0;i < str.length();++i) {
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				freqs[str.charAt(i) - 'a'] += 1;
			}else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				freqs[str.charAt(i) - 'A'] += 1;
			}
		}
		for(int i1 = 0;i1 < 26;++i1) {
			if(freqs[i1] > max) {
				max = freqs[i1];
			}
			if(freqb[i1] > max) {
				max = freqb[i1];
			}
		}
		return max;
	}
	
	public static boolean stringIntersect (String a, String b){
		a.replace("" , "");
		if(a.contains(b)) {
			return true;
		}
		return false;
	}
	public static StringOperator parseIntOrFail(String input) throws NoSuchElementException {
		StringOperator SOP = new StringOperator();
		try {
			int num = Integer.parseInt(input);
			SOP.Num = num;
		} catch (Exception e) {
			throw new NoSuchElementException(SOP.ErrorInfo);
		}
		return SOP;
	}
	
	public static void main(String []args) {
		boolean flag = true;
		Scanner input = new Scanner(System.in);
		StringOperator Sop = null;
		System.out.println("Please Input a Integer:");
		while(flag) {
			try {
				String s = input.next();
				Sop = parseIntOrFail(s);
				flag = false;
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(Sop.Num);
		System.out.println(blowup("12z"));
		System.out.println(maxRun("xxyyyz"));
		System.out.println(stringIntersect("Hello World", "Hello"));
	}

}
