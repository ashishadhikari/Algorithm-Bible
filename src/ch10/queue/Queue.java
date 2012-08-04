package ch10.queue;

import java.util.Collection;

public interface Queue<E> {

	boolean isEmpty();

	void enqueue(E item);

	E dequeue();

	void enqueueAll(Iterable<? extends E> src);

	void dequeueAll(Collection<? super E> dst);

}
