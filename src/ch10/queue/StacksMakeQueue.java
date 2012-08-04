package ch10.queue;

import java.util.ArrayList;
import java.util.Collection;

import ch10.stack.ArrayStack;
import ch10.stack.Stack;

public class StacksMakeQueue<E> implements Queue<E> {

	/*
	 * Top of left is tail, where we enqueue. Top of right is head, where we
	 * dequeue.
	 */
	private Stack<E> left;
	private Stack<E> right;

	public StacksMakeQueue() {
		this(1);
	}

	public StacksMakeQueue(int i) {
		left = new ArrayStack<E>(i);
		right = new ArrayStack<E>(i);
	}

	@Override
	public boolean isEmpty() {
		return left.isEmpty() && right.isEmpty();
	}

	@Override
	public void enqueue(E item) {
		prepareToEnqueue();
		left.push(item);
	}

	private void prepareToEnqueue() {
		if (!right.isEmpty()) {
			Collection<E> items = new ArrayList<E>();
			right.popAll(items);
			left.pushAll(items);
		}
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Queue is empty");
		} else {
			prepareToDequeue();
			return right.pop();
		}
	}

	private void prepareToDequeue() {
		if (!left.isEmpty()) {
			Collection<E> items = new ArrayList<E>();
			left.popAll(items);
			right.pushAll(items);
		}
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
		return String.format("Left: %s%nRight: %s", left, right);
	}

}
