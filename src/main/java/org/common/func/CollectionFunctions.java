package org.common.func;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Functions analogous to those available in the Ruby's Enumerable module.
 */
public class CollectionFunctions {

	public static <T> Boolean all(Collection<T> c, Function<T, Boolean> cond) {
		verifyArguments(c, cond);		
		for (T e : c) {
			if (!cond.call(e)) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> Boolean any(Collection<T> c, Function<T, Boolean> cond) {
		verifyArguments(c, cond);
		for (T e : c) {
			if (cond.call(e)) {
				return true;
			}
		}
		return false;	
	}

	public static <T, U> Collection<U> collect(Collection<T> c, Function<T, U> conversion) {
		verifyArguments(c, conversion);
		List<U> result = new ArrayList<U>(c.size());
		
		for (T e : c) {
			result.add(conversion.call(e));
		}
		return result;
	}
	
	public static <T, U> Collection<Pair<U, Collection<T>>> chunk(Collection<T> c, Function<T, U> conversion) {
		verifyArguments(c, conversion);
		List<Pair<U, Collection<T>>> pairs = new ArrayList<Pair<U, Collection<T>>>();
		
		List<T> previousChunk = new ArrayList<T>();
		U previousChunkKey = null;
		for (T e : c) {
			U currentChunkKey = conversion.call(e);
			if ((null == previousChunkKey) || !currentChunkKey.equals(previousChunkKey)) {		
				if (previousChunk.size() > 0) {
					pairs.add(new Pair<U, Collection<T>>(previousChunkKey, previousChunk));
				};
				previousChunk = new ArrayList<T>();
				previousChunkKey = currentChunkKey;
			};
			previousChunk.add(e);
		};
		if (previousChunk.size() > 0) {
			pairs.add(new Pair<U, Collection<T>>(previousChunkKey, previousChunk));
		};

		return pairs;
	}
	
	public static <T> Collection<T> concat(Collection<T> c1, Collection<T> c2) {
		List<T> result = new ArrayList<T>(c1);

		result.addAll(c2);
		return result;
	}

	public static <T, U> Collection<U> collectConcat(Collection<T> c, Function<T, Collection<U>> conversion) {
		Collection<Collection<U>> collectedParts = collect(c, conversion);
		Collection<U> result = new ArrayList<U>();

		for (Collection<U> part : collectedParts) {
			result = concat(result, part);
		};
		return result;
	}

//	#count
//	#cycle
//	#detect
//	#drop
//	#drop_while
//	#each_cons
//	#each_entry
//	#each_slice
//	#each_with_index
//	#each_with_object
//	#entries
//	#find
//	#find_all
//	#find_index
//	#first
//	#flat_map
//	#grep
//	#group_by
//	#include?
//	#inject
//	#map
//	#max
//	#max_by
//	#member?
//	#min
//	#min_by
//	#minmax
//	#minmax_by
//	#none?
//	#one?
//	#partition
//	#reduce
//	#reject
//	#reverse_each
//	#select
//	#slice_before
//	#sort
//	#sort_by
//	#take
//	#take_while
//	#to_a
//	#zip
	
	//TODO: Add a separate utility that will convert the returned collections to a specific type, such as Set, or List, or array
    //TODO: Re-factor commonality between the different functions
	
	private static void verifyArguments(Object... args) {
		for (int i = 0; i < args.length; i++) {
			if (null == args[i]) {
				throw new IllegalArgumentException("Null argument number " + (i + 1));
			}
		}
	}
	
	public static interface Function<T, E> {
		E call(T input);
	}
}