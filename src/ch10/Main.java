package ch10;

import ch10.linkedlist.*;
import ch10.queue.Queue;
import ch10.queue.SinglyLinkedListQueue;
import ch10.stack.SinglyLinkedListStack;
import ch10.stack.Stack;

public class Main {

	public static void main(String[] args) {
		LinkedListSingly<Integer> ll = new LinkedListSingly<Integer>();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.insert(4);
		System.out.println(ll);
		ll.reverse();
		System.out.println(ll);
	}
}
