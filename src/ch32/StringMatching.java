package ch32;

public class StringMatching {

	public static void naiveStringMatcher(String t, String p) {
		int n = t.length();
		int m = p.length();
		for (int s = 0; s <= n-m; s++) {
			if (p.equals(t.substring(s, s + m)))
				System.out.println("Pattern occurs with shift " + s);
		}
	}
	
}
