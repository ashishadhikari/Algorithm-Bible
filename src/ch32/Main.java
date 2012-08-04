package ch32;

public class Main {

	public static void main(String[] args) {
		String text = "abcabaabcabac";
		String pattern = "abaa";
		StringMatching.naiveStringMatcher(text, pattern);
	}
	
}
