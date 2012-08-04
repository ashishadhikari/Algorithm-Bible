package ch01;

public class MergeSort implements Sort {

	public int[] sort(int[] a) {
		mergeSort(a, 0, a.length - 1);
		return a;
	}

	private void mergeSort(int[] a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			mergeWithoutSentinelNode(a, p, q, r);
		}
	}

	private void merge(int[] a, int p, int q, int r) { // p=0, q=0, r=1
		int n1 = q - p + 1; // =1
		int n2 = r - q; // 1
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0; i < n1; i++) {
			L[i] = a[p + i]; // L[0] = a[0]
		}
		L[n1] = Integer.MAX_VALUE;
		for (int i = 0; i < n2; i++) {
			R[i] = a[q + i + 1];
		}
		R[n2] = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		for (int k = p; k <= r; k++) {
			if (L[i] < R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
		}
	}

	private void mergeWithoutSentinelNode(int[] a, int p, int q, int r) {
		int[] L = new int[q - p + 1];
		int[] R = new int[r - q];
		for (int i = 0; i < L.length; i++) {
			L[i] = a[p + i];
		}
		for (int i = 0; i < R.length; i++) {
			R[i] = a[q + i + 1];
		}
		int i = 0;
		int j = 0;
		int k = p;
		while (i < L.length && j < R.length) {
			if (L[i] < R[j])
				a[k++] = L[i++];
			else
				a[k++] = R[j++];
		}
		while (i < L.length) {
			a[k++] = L[i++];
		}
		while (j < R.length) {
			a[k++] = R[j++];
		}
	}

}
