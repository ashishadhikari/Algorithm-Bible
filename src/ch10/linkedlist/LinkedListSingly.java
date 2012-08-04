package ch10.linkedlist;

public class LinkedListSingly<E> {

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
		head = n;
	}

	public void delete(Node<E> node) {
		if (node == null || head == null)
			return;
		if (node == head) {
			head = head.next;
		} else {
			Node<E> n = head.next;
			while (n != null && n != node) {
				n = n.next;
			}
		}
	}
	
	public void reverse() {
		if (head == null || head.next == null)
			return;
		Node<E> l = head;
		Node<E> r = l.next;
		l.next = null;
		while (r != null) {
			Node<E> b = r.next;
			r.next = l;
			l = r;
			r = b;
		}
		head = l;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<E> n = head;
		while (n != null) {
			sb.append(n.key).append(" --> ");
			n = n.next;
		}
		sb.append("NULL");
		return sb.toString();
	}

	public static class Node<T> {
		private T key;
		private Node<T> next;

		public Node(T key) {
			this.key = key;
		}

		

		public T getKey() {
			return key;
		}



		public void setKey(T key) {
			this.key = key;
		}



		public Node<T> getNext() {
			return next;
		}



		public void setNext(Node<T> next) {
			this.next = next;
		}



		@Override
		public String toString() {
			return String.format("[Node: %s] --> [next: %s]", key != null ? key
					: "NULL", next != null ? next.key : "NULL");
		}
	}

}
