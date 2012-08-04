package ch01;

import java.util.Arrays;

public class InsertionSort implements Sort {

	public int[] sort(int[] a) {
		for (int j = 1; j < a.length; j++) {
			int key = a[j];
			int i = j - 1;
			while (i >= 0 && a[i] > key) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = key;
		}
		return a;
	}

	public int[] sortDesc(int[] a) {
		for (int j = 1; j < a.length; j++) {
			int key = a[j];
			int i = j - 1;
			while (i >= 0 && a[i] < key) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = key;
		}
		return a;
	}
	
	public int[] sortRecursive(int[] a) {
		sortRecursiveHelper(a, a.length);
		return a;
	}
	
	private void sortRecursiveHelper(int[] a, int len) {
		if (len > 1) {
			sortRecursiveHelper(a, len - 1);
			putInPlace(a, len - 1);
		}
	}
	
	private void putInPlace(int[] a, int loc) {
		int key = a[loc];
		int i = loc - 1;
		while (i >= 0 && a[i] > key) {
			a[i + 1] = a[i];
			i--;
		}
		a[i + 1] = key;
	}

	public static void main(String[] args) {
		InsertionSort sort = new InsertionSort();
		int[] arr = { 3, 4, 2, 12, 56, 43, 21, 191, 3, 1, 7 };
//		int[] arr2 = { 3 };
		System.out.println(Arrays.toString(sort.sortDesc(arr)));
	}

}
