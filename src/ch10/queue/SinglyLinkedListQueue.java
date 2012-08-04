package ch10.queue;

import java.util.Collection;

import ch10.linkedlist.LinkedListSingly;
import ch10.linkedlist.LinkedListSingly.Node;

public class SinglyLinkedListQueue<E> implements Queue<E> {

	private LinkedListSingly<E> ll = new LinkedListSingly<E>();
	private Node<E> tailNode;

	@Override
	public boolean isEmpty() {
		return ll.head() == null;
	}

	@Override
	public void enqueue(E item) {
		if (tailNode != null) {
			Node<E> node = new Node<E>(item);
			tailNode.setNext(node);
			tailNode = node;
		} else {
			ll.insert(item);
			tailNode = ll.head();
		}
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new RuntimeException("Queue is already empty");
		Node<E> node = ll.head();
		E item = node.getKey();
		ll.delete(node);
		return item;
	}

	@Override
	public void enqueueAll(Iterable<? extends E> src) {
		for (E e : src) {
			enqueue(e);
		}
	}

	@Override
	public void dequeueAll(Collection<? super E> dst) {
		while (!isEmpty()) {
			dst.add(dequeue());
		}
	}

	@Override
	public String toString() {
		return ll.toString() + " tail: " + String.valueOf(tailNode);

	}

}
