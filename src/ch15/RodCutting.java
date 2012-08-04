package ch15;

import java.util.Arrays;

public class RodCutting {

	public static int cutRod(int[] p, int n) {
		if (n == 0)
			return 0;
		int q = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			q = Math.max(q, p[i] + cutRod(p, n - i - 1));
		}
		return q;
	}

	public static int memoizedCutRod(int[] p, int n) {
		int[] r = new int[n + 1];
		Arrays.fill(r, Integer.MIN_VALUE);
		return memoizedCutRodAux(p, n, r);
	}

	private static int memoizedCutRodAux(int[] p, int n, int[] r) {
		if (r[n] >= 0)
			return r[n];
		if (n == 0)
			return 0;
//		System.out.println("n=" + n);
		int q = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			q = Math.max(q, p[i] + memoizedCutRodAux(p, n - i - 1, r));
		}
		r[n] = q;
		return q;
	}

	public static void main(String[] args) {
		int[] price = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
		int max = cutRod(price, price.length);
		System.out.println("Max: " + max);
		for (int i = 1; i <= price.length; i++) {
			System.out.print(cutRod(price, i) + ", ");
		}
		max = memoizedCutRod(price, price.length);
		System.out.println("Max: " + max);
		for (int i = 1; i <= price.length; i++) {
			System.out.print(memoizedCutRod(price, i) + ", ");
		}
		
		
	}

}
