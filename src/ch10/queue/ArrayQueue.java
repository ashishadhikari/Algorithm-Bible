package ch10.queue;

import java.util.Arrays;
import java.util.Collection;

public class ArrayQueue<E> implements Queue<E> {

	Object[] arr;

	int head;
	int tail;

	public ArrayQueue() {
		this(16);
	}

	public ArrayQueue(int i) {
		arr = new Object[i + 1];
		head = 0;
		tail = 0;
	}

	@Override
	public boolean isEmpty() {
		return head == tail;
	}

	public boolean isFull() {
		return head == (tail + 1) % arr.length;
	}

	@Override
	public void enqueue(E item) {
		if (isFull()) {
			throw new RuntimeException("Queue is already full");
		} else {
			arr[tail] = item;
			tail = (tail + 1) % arr.length;
		}
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Queue is empty");
		} else {
			@SuppressWarnings("unchecked")
			E item = (E) arr[head];
			arr[head] = null;
			head = (head + 1) % arr.length;
			return item;
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
		return Arrays.deepToString(arr) + " h=" + head + ",t=" + tail;
	}

}
