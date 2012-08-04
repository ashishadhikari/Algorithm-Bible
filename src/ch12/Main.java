package ch12;

import java.util.Arrays;
import java.util.Collection;

public class Main {

	public static void main(String... args) {
		Tree<Integer> tree = new Tree<Integer>();
		tree.insertAllRecursive(Arrays.asList(4, 2, 6, 1, 3, 5, 7));
		System.out.println(tree);
	}
	
}
