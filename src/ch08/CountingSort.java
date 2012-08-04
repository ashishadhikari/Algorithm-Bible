package ch08;

public class CountingSort {
	
	public static int[] sort(int[] A, int k) {
		int[] B = new int[A.length];
		int[] C = new int[k + 1];
		for (int i = 0; i < A.length; i++) {
			C[A[i]]++;
		}
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i - 1];
		}
		for (int i = A.length - 1; i >= 0; i--) {
			C[A[i]]--;
			B[C[A[i]]] = A[i];
		}
		return B;
	}
	
	public static int[] stoozeSort(int[] A, int i, int j) {
		if (A[i] > A[j])
			Util.exchange(A, i, j);
		if (i + 1 >= j)
			return A;
		int k = (j - i + 1) / 3;
		stoozeSort(A, i, j - k);
		stoozeSort(A, i + k, j);
		stoozeSort(A, i, j - k);
		return A;
	}

}
