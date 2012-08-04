package ch08;

public class Util {
	
	public static void exchange(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

}
