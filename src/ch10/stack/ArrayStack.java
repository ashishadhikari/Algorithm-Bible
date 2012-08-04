package ch10.stack;

import java.util.Arrays;
import java.util.Collection;

public class ArrayStack<E> implements Stack<E> {

	Object[] arr;

	int top;

	public ArrayStack() {
		this(1);
	}

	public ArrayStack(int i) {
		top = -1;
		arr = new Object[i];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public void push(E item) {
		ensureCapacity();
		top++;
		arr[top] = item;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack Empty");
		} else {
			top--;
			@SuppressWarnings("unchecked")
			E topElement = (E) arr[top + 1];
			arr[top + 1] = null;
			return topElement;
		}
	}
	
	@Override
	public void pushAll(Iterable<? extends E> src) {
		for (E e : src) {
			push(e);
		}
	}
	
	@Override
	public void popAll(Collection<? super E> dst) {
		while (!isEmpty()) {
			dst.add(pop());
		}
	}

	private void ensureCapacity() {
		if (top == arr.length - 1) {
			arr = Arrays.copyOf(arr, arr.length * 2);
		}

	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(Arrays.copyOf(arr, top + 1));
	}

}
