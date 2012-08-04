package ch10.stack;

import java.util.Collection;

public interface Stack<E> {

	boolean isEmpty();

	void push(E item);

	E pop();
	
	void pushAll(Iterable<? extends E> src);
	
	void popAll(Collection<? super E> dst);

}