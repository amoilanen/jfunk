package org.common.jfunk;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility functions for working with sets.
 * 
 */
public class Sets {

	//union
	//subtract
	//etc.
	
	public static <T> Set<T> set(T... elems) {
		Set<T> result = new HashSet<T>();

		for (T e : elems) {
			result.add(e);
		};
		return result;
	}
}
