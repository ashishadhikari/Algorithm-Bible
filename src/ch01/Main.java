package ch01;

import java.util.Arrays;

import ch07.QuickSort;
import ch08.CountingSort;

public class Main {

	public static void main(String... args) {
		Sort sort = new InsertionSort();
		sort = new MergeSort();
		int[] arr = { 3, 4, 2, 12, 56, 43, 21, 21, 191, 3, 21, 1, 7 };
		// int[] arr2 = { 3 };
		System.out.println(Arrays.toString(sort.sort(arr)));
		System.out.println(Arrays.toString(new InsertionSort().sort(arr)));
		System.out.println(Arrays.toString(new InsertionSort()
				.sortRecursive(arr)));
		System.out.println(Arrays.toString(QuickSort.sortTailRecursive(arr, 0, arr.length - 1)));
		System.out.println(Arrays.toString(CountingSort.sort(arr, 200)));
		System.out.println(Arrays.toString(CountingSort.stoozeSort(arr, 0, arr.length - 1)));
	}

}
