package ch12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch10.stack.ArrayStack;
import ch10.stack.Stack;

public class Tree<E extends Comparable<? super E>> {

	Node<E> root;

	public Node<E> searchRecursive(E key) {
		return search(root, key);
	}

	private Node<E> search(Node<E> node, E key) {
		if (node == null || key.equals(node.key))
			return node;
		if (key.compareTo(node.key) < 0)
			return search(node.left, key);
		else
			return search(node.right, key);
	}

	public Node<E> searchIterative(E key) {
		Node<E> node = root;
		while (node != null && !key.equals(node.key)) {
			if (key.compareTo(node.key) < 0)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	public Node<E> minimum() {
		return minimum(root);
	}

	public Node<E> minimum(Node<E> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public Node<E> minimumRecursive() {
		return minimumRecursive(root);
	}

	public Node<E> minimumRecursive(Node<E> node) {
		if (node.left == null)
			return node;
		else
			return minimumRecursive(node.left);
	}

	public Node<E> maximum() {
		return maximum(root);
	}

	public Node<E> maximum(Node<E> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	public Node<E> maximumRecursive() {
		return maximumRecursive(root);
	}

	public Node<E> maximumRecursive(Node<E> node) {
		if (node.right == null)
			return node;
		else
			return maximumRecursive(node.right);
	}

	public Node<E> successor(Node<E> node) {
		if (node.right != null)
			return minimum(node.right);
		Node<E> parent = node.parent;
		while (parent != null && parent.right == node) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	public Node<E> predecessor(Node<E> node) {
		if (node.left != null)
			return maximum(node.left);
		Node<E> parent = node.parent;
		while (parent != null && parent.left == node) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	public void insert(E key) {
		Node<E> newNode = new Node<E>(key);
		Node<E> node = root;
		Node<E> parent = null;
		while (node != null) {
			parent = node;
			if (key.compareTo(node.key) < 0)
				node = node.left;
			else
				node = node.right;
		}
		newNode.parent = parent;
		if (parent == null)
			root = newNode;
		else if (key.compareTo(parent.key) < 0)
			parent.left = newNode;
		else
			parent.right = newNode;
	}

	public void insertRecursive(E key) {
		root = insertRecursive(root, key);
	}

	private Node<E> insertRecursive(Node<E> node, E key) {
		if (node == null) {
			return new Node<E>(key);
		} else if (key.compareTo(node.key) < 0) {
			node.left = insertRecursive(node.left, key);
			node.left.parent = node;
		} else {
			node.right = insertRecursive(node.right, key);
			node.right.parent = node;
		}
		return node;
	}

	public void insertAll(Collection<? extends E> nodes) {
		for (E e : nodes) {
			insert(e);
		}
	}

	public void insertAllRecursive(Collection<? extends E> nodes) {
		for (E e : nodes) {
			insertRecursive(e);
		}
	}

	public List<Node<E>> inOrder() {
		List<Node<E>> nodes = new ArrayList<Node<E>>();
		inOrder(root, nodes);
		return nodes;
	}

	public void inOrder(Node<E> node, Collection<Node<E>> nodes) {
		if (node != null) {
			inOrder(node.left, nodes);
			nodes.add(node);
			inOrder(node.right, nodes);
		}
	}

	public List<E> inOrderKeys() {
		List<E> keys = new ArrayList<E>();
		for (Node<E> node : inOrder()) {
			keys.add(node.key);
		}
		return keys;
	}

	public List<Node<E>> inOrderIterative() {
		List<Node<E>> nodes = new ArrayList<Tree.Node<E>>();
		Stack<Node<E>> stack = new ArrayStack<Node<E>>();
		Node<E> current = root;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			if (!stack.isEmpty()) {
				current = stack.pop();
				nodes.add(current);
				current = current.right;
			}
		}
		return nodes;
	}

	public List<E> inOrderNodesIterative() {
		List<E> keys = new ArrayList<E>();
		for (Node<E> node : inOrderIterative()) {
			keys.add(node.key);
		}
		return keys;
	}

	public List<Node<E>> preOrder() {
		List<Node<E>> nodes = new ArrayList<Node<E>>();
		preOrder(root, nodes);
		return nodes;
	}

	public void preOrder(Node<E> node, Collection<Node<E>> nodes) {
		if (node != null) {
			nodes.add(node);
			preOrder(node.left, nodes);
			preOrder(node.right, nodes);
		}
	}

	public List<E> preOrderKeys() {
		List<E> keys = new ArrayList<E>();
		for (Node<E> node : preOrder()) {
			keys.add(node.key);
		}
		return keys;
	}

	public List<Node<E>> postOrder() {
		List<Node<E>> nodes = new ArrayList<Tree.Node<E>>();
		postOrder(root, nodes);
		return nodes;
	}

	public void postOrder(Node<E> node, Collection<Node<E>> nodes) {
		if (node != null) {
			postOrder(node.left, nodes);
			postOrder(node.right, nodes);
			nodes.add(node);
		}
	}

	public List<E> postOrderKeys() {
		List<E> keys = new ArrayList<E>();
		for (Node<E> node : postOrder()) {
			keys.add(node.key);
		}
		return keys;
	}

	public void transplant(Node<E> u, Node<E> v) {
		if (u.parent == null)
			root = v;
		else if (u == u.parent.left)
			u.parent.left = v;
		else
			u.parent.right = v;
		if (v != null)
			v.parent = u.parent;
	}

	public void delete(Node<E> node) {
		if (node.left == null)
			transplant(node, node.right);
		else if (node.right == null)
			transplant(node, node.left);
		else {
			Node<E> successor = minimum(node.right);
			if (successor.parent != node) {
				transplant(successor, successor.right);
				successor.right = node.right;
				successor.right.parent = successor;
			}
			transplant(node, successor);
			successor.left = node.left;
			successor.left.parent = successor;
		}
	}

	@Override
	public String toString() {
		return String.format("InOrder:   %s%nPreOrder:  %s%nPostOrder: %s%n",
				inOrderKeys(), preOrderKeys(), postOrderKeys());
	}

	public static class Node<T extends Comparable<? super T>> {

		T key;
		Node<T> left;
		Node<T> right;
		Node<T> parent;

		public Node(T key) {
			super();
			this.key = key;
		}

		@Override
		public String toString() {
			return String
					.format("[left: %s] <-- [Node: %s] --> [right: %s] |^ [parent: %s]",
							left != null ? left.key : "NULL", key != null ? key
									: "NULL", right != null ? right.key
									: "NULL", parent != null ? parent.key
									: "NULL");
		}

	}

}
