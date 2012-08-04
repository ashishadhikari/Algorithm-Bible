/**
 * 
 */
package ch10.stack;

import java.util.Collection;

import ch10.queue.ArrayQueue;
import ch10.queue.Queue;

/**
 * @author ashish
 *
 */
public class QueuesMakeStack<E> implements Stack<E> {

	private Queue<E> main;
	private Queue<E> copy;
	
	public QueuesMakeStack() {
		this(16);
	}
	
	public QueuesMakeStack(int i) {
		main = new ArrayQueue<E>(i);
		copy = new ArrayQueue<E>(i);
	}

	@Override
	public boolean isEmpty() {
		return main.isEmpty();
	}

	@Override
	public void push(E item) {
		main.enqueue(item);
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		} else {
			E item = main.dequeue();
			while (!main.isEmpty()) {
				copy.enqueue(item);
				item = main.dequeue();
			}
			Queue<E> tmp = main;
			main = copy;
			copy = tmp;
			return item;
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
	
	@Override
	public String toString() {
		return "Underlying Queue: " + main;
	}

}
