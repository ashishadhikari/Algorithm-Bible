package ch04;

import java.util.Random;

public class MaxSubArray {

	private static final int PROBLEM_SIZE = 5000;
	private static final int MAX_VALUE = 100;
	private static final int DC_BIAS = 60;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = generate();
//		a = new int[] {-1, -2, 5, -2, 10};
		MaxSubArray msa = new MaxSubArray();
		long start = System.nanoTime();
		MaxSubArray.LeftRightSum lrs = msa.findMaxSubArray(a);
		long mid = System.nanoTime();
		MaxSubArray.LeftRightSum lrsBf = msa.findMaxSubArrayBruteForce(a);
		long end = System.nanoTime();
		MaxSubArray.LeftRightSum lrsI = msa.findMaxSubArrayIterative(a);
		long endI = System.nanoTime();
		if (lrs.equals(lrsBf) && lrs.equals(lrsI)) {
			System.out.println("Answer: " + lrs);
		} else {
			System.err.println("The answers don't match.");
			System.err.println("lrs  : " + lrs);
			System.err.println("lrsBf: " + lrsBf);
			System.err.println("lrsI : " + lrsI);
		}
		System.out.println("Efficient time   : " + (mid - start));
		System.out.println("Brute force time : " + (end - mid));
		System.out.println("Iterative time   : " + (endI - end));
	}

	private static int[] generate() {
		Random random = new Random();
		int[] a = new int[PROBLEM_SIZE];
		for (int i = 0; i < PROBLEM_SIZE; i++) {
			a[i] = random.nextInt(MAX_VALUE) - DC_BIAS;
		}
		return a;
	}

	public LeftRightSum findMaxSubArray(int[] a) {
		return findMaxSubArrayRecursive(a, 0, a.length - 1);
	}

	public LeftRightSum findMaxSubArrayIterative(int[] a) {
		LeftRightSum msa = new LeftRightSum(0, 0, a[0]);
		LeftRightSum msaei = msa;
		for (int i = 1; i < a.length; i++) {
			LeftRightSum pmsaei = new LeftRightSum(msaei.left, i, msaei.sum + a[i]);
			if ( pmsaei.sum > a[i]) {
				msaei = pmsaei;
			} else {
				msaei = new LeftRightSum(i, i, a[i]);
			}
			if (msaei.sum > msa.sum) {
				msa = msaei;
			}
		}
		return msa;
	}

	private LeftRightSum findMaxSubArrayRecursive(int[] a, int low, int high) {
		if (low == high) {
			return new LeftRightSum(low, high, a[low]);
		}
		int mid = (low + high) / 2;
		LeftRightSum left = findMaxSubArrayRecursive(a, low, mid);
		LeftRightSum right = findMaxSubArrayRecursive(a, mid + 1, high);
		LeftRightSum crossing = findMaxCrossingSubArray(a, low, mid, high);
		if (left.sum > right.sum && left.sum > crossing.sum) {
			return left;
		} else if (right.sum > left.sum && right.sum > crossing.sum) {
			return right;
		} else {
			return crossing;
		}
	}
	
	private LeftRightSum findMaxCrossingSubArray(int[] a, int low, int mid,
			int high) {
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int maxLeft = mid;
		for (int i = mid; i >= low; i--) {
			sum += a[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		int maxRight = mid + 1;
		for (int i = mid + 1; i <= high; i++) {
			sum += a[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		return new LeftRightSum(maxLeft, maxRight, leftSum + rightSum);
	}

	public LeftRightSum findMaxSubArrayBruteForce(int[] a) {
		return findMaxSubArrayBruteForceRecursive(a, a.length - 1);
	}

	private LeftRightSum findMaxSubArrayBruteForceRecursive(int[] a, int i) {
		if (i == 0) {
			return new LeftRightSum(i, i, a[i]);
		} else {
			LeftRightSum smaller = findMaxSubArrayBruteForceRecursive(a, i - 1);
			int l = i;
			int sum = 0;
			int sumMax = Integer.MIN_VALUE;
			for (int j = i; j >= 0; j--) {
				sum += a[j];
				if (sum > sumMax) {
					sumMax = sum;
					l = j;
				}
			}
			if (smaller.sum >= sumMax)
				return smaller;
			else
				return new LeftRightSum(l, i, sumMax);
		}
	}

	private static class LeftRightSum {

		final int left;
		final int right;
		final int sum;

		public LeftRightSum(int left, int right, int sum) {
			super();
			this.left = left;
			this.right = right;
			this.sum = sum;
		}

		@Override
		public String toString() {
			return "LeftRightSum [left=" + left + ", right=" + right + ", sum="
					+ sum + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + left;
			result = prime * result + right;
			result = prime * result + sum;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LeftRightSum other = (LeftRightSum) obj;
			if (left != other.left)
				return false;
			if (right != other.right)
				return false;
			if (sum != other.sum)
				return false;
			return true;
		}

	}

}
