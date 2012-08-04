package ch10.linkedlist;

public class LinkedList<E> {

	private Node<E> head;

	public Node<E> head() {
		return head;
	}

	public Node<E> search(E key) {
		Node<E> n = head;
		while (n != null && !key.equals(n.key)) {
			n = n.next;
		}
		return n;
	}

	public void insert(E key) {
		Node<E> n = new Node<E>(key);
		n.next = head;
		if (head != null)
			head.prev = n;
		head = n;
	}

	public void delete(Node<E> node) {
		if (node == null)
			return;
		if (node.prev != null)
			node.prev.next = node.next;
		else
			head = node.next;
		if (node.next != null)
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
		Node<E> n = head;
		while (n != null) {
			sb.append(n.key + " --> ");
			n = n.next;
		}
		sb.append("NULL");
		return sb.toString();
	}

}
