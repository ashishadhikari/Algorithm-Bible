package ch10.stack;

import java.util.Collection;

import ch10.linkedlist.LinkedListSingly;
import ch10.linkedlist.LinkedListSingly.Node;

public class SinglyLinkedListStack<E> implements Stack<E> {

	private LinkedListSingly<E> ll = new LinkedListSingly<E>();

	@Override
	public boolean isEmpty() {
		return ll.head() == null;
	}

	@Override
	public void push(E item) {
		ll.insert(item);
	}

	@Override
	public E pop() {
		if (isEmpty())
			throw new RuntimeException("Stack is already empty");
		Node<E> node = ll.head();
		E item = node.getKey();
		ll.delete(node);
		return item;
	}

	@Override
	public void pushAll(Iterable<? extends E> src) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void popAll(Collection<? super E> dst) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return ll.toString();
	}

}
