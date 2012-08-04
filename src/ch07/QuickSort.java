package ch07;

public class QuickSort {

	public static int[] sort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			sort(A, p, q - 1);
			sort(A, q + 1, r);
		}
		return A;
	}
	
	public static int[] sort2(int[] A, int p, int r) {
		if (p < r) {
			int[] q = partition2(A, p, r);
			sort2(A, p, q[0] - 1);
			sort2(A, q[1] + 1, r);
		}
		return A;
	}
	
	public static int[] sortTailRecursive(int[] A, int p, int r) {
		while (p < r) {
			int q = partition(A, p, r);
			sort(A, p, q - 1);
			p = q + 1;
		}
		return A;
	}

	private static int partition(int[] A, int p, int r) {
		int x = A[r];
		int i = -1;
		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= x) {
				i++;
				exchange(A, i, j);
			}
		}
		exchange(A, i + 1, r);
		return i + 1;
	}

	private static void exchange(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	private static int[] partition2(int[] A, int p, int r) {
		int x = A[r];
		int i = -1;
		int k = i;
		for (int j = p; j <= r - 1; j++) {
			if (A[j] == x) {
				k++;
				exchange(A, j, k);
			} else if (A[j] < x) {
				i++;
				k++;
				exchange(A, j, k);
				exchange(A, i, k);
			}
		}
		i++;
		k++;
		exchange(A, k, r);
		return new int[] { i, k };
	}

}
