package ch06;

import java.util.Arrays;

public class Heap {

	public static int[] buildMaxHeap(int[] A) {
		int[] heap = new int[A.length + 1];
		System.arraycopy(A, 0, heap, 1, A.length);
		for (int i = A.length / 2; i > 0; i--) {
			maxHeapify(heap, heap.length - 1, i);
		}
		int[] answer = new int[A.length];
		System.arraycopy(heap, 1, answer, 0, answer.length);
		return answer;
	}

	public static void maxHeapify(int[] A, int heapSize, int i) {
//		if (i == 0) return;
		int l = left(i);
		int r = right(i);
		int largest = i;
		if (l <= heapSize && A[l] > A[i]) {
			largest = l;
		}
		if (r <= heapSize && A[r] > A[largest]) {
			largest = r;
		}
		if (largest != i) {
			int tmp = A[i];
			A[i] = A[largest];
			A[largest] = tmp;
			maxHeapify(A, heapSize, largest);
		}
	}

	private static int left(int i) {
		return i << 1;
	}

	private static int right(int i) {
		return (i << 1) + 1;
	}

	private static int parent(int i) {
		return i >> 1;
	}

	public static void main(String[] args) {
		int[] a = {1, 2, 3};
		int[] heap = buildMaxHeap(a);
		System.out.println(Arrays.toString(heap));
	}

}
