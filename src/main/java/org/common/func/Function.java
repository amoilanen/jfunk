package org.common.func;

public interface Function<T, E> {
	E call(T x);
}