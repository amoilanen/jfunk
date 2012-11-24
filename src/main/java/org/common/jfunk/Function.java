package org.common.jfunk;

public interface Function<T, E> {
	E call(T x);
}