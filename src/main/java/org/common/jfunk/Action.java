package org.common.jfunk;

public abstract class Action<T> implements Function<T, Void> {
	
	public Void call(T x) {
		perform(x);
		return null;
	}

	public abstract void perform(T x);
}