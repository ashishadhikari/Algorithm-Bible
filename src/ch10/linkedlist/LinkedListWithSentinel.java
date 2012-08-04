package ch10.linkedlist;

public class LinkedListWithSentinel<E> {

	private Node<E> nil;
	
	{
		nil = new Node<E>(null);
		nil.prev = nil;
		nil.next = nil;
	}

	public Node<E> search(E key) {
		Node<E> n = nil.next;
		while (n != nil && !key.equals(n.key)) {
			n = n.next;
		}
		return n;
	}

	public void insert(E key) {
		Node<E> n = new Node<E>(key);
		n.next = nil.next;
		n.prev = nil;
		nil.next.prev = n;
		nil.next = n;
	}

	public void delete(Node<E> node) {
		if (node == null || node == nil)
			return;
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	private static class Node<T> {

		private T key;
		private Node<T> prev;
		private Node<T> next;

		public Node(T key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return String.format("[prev: %s] <-- [Node: %s] --> [next: %s]",
					prev != null ? prev.key : "NULL", key != null ? key
							: "NULL", next != null ? next.key : "NULL");
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<E> n = nil.next;
		while (n != nil) {
			sb.append(n.key + " --> ");
			n = n.next;
		}
		sb.append("NULL");
		return sb.toString();
	}

}
