package org.common.func;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Functions analogous to those available in the Ruby's Enumerable module.
 * Return values can be the following:
 * ArrayList, HashSet
 * 
 * This class is called <b>Enumerables</b> and not <b>Collections</b> in order not to confuse
 * it with {@link java.util.Collections}
 * 
 */
public class Enumerables {

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

	@SuppressWarnings("unchecked")
	public static <T, U, Y extends Collection<U>> Y collect(Collection<T> c, Function<T, U> conversion, Class<?>... resultType) {
		verifyArguments(c, conversion);
		
		Y result = (Y) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<U>());
		
		for (T e : c) {
			result.add(conversion.call(e));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T, U, Y extends Collection<T>> Collection<Pair<U, Y>> chunk(Collection<T> c, Function<T, U> conversion, Class<?>... resultType) {
		verifyArguments(c, conversion);
		
		List<Pair<U, Y>> pairs = (List<Pair<U, Y>>) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<Pair<U, Y>>());

		Y previousChunk = (Y) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<T>());
		U previousChunkKey = null;
		for (T e : c) {
			U currentChunkKey = conversion.call(e);
			if ((null == previousChunkKey) || !currentChunkKey.equals(previousChunkKey)) {		
				if (previousChunk.size() > 0) {
					pairs.add(new Pair<U, Y>(previousChunkKey, previousChunk));
				};
				previousChunk = (Y) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<T>());
				previousChunkKey = currentChunkKey;
			};
			previousChunk.add(e);
		};
		if (previousChunk.size() > 0) {
			pairs.add(new Pair<U, Y>(previousChunkKey, previousChunk));
		};

		return pairs;
	}
	
	@SuppressWarnings("unchecked")
	public static <T, Y extends Collection<T>> Y concat(Collection<T> c1, Collection<T> c2, Class<?>... resultType) {		
		Y result = (Y) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<T>());

		result.addAll(c1);
		result.addAll(c2);
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T, U, Y extends Collection<U>> Y collectConcat(Collection<T> c, Function<T, Collection<U>> conversion, Class<?>... resultType) {
		Y result = (Y) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<U>());
		Collection<Collection<U>> collectedParts = collect(c, conversion);

		for (Collection<U> part : collectedParts) {
			result = concat(result, part, resultType);
		};
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T, U, Y extends Collection<U>> Y map(Collection<T> c, Function<T, U> f, Class<?>... resultType) {
		verifyArguments(c, f);
		Y result = (Y) (resultType.length > 0 ? instantiate(resultType[0]) : new ArrayList<U>());

		for (T e : c) {
			result.add(f.call(e));
		};
		return result;
	}
	
	public static <T, U> U reduce(Collection<T> c, Function<Pair<U, T>, U> f, U acc) {
		verifyArguments(c, f, acc);
		for (T e : c) {
			acc = f.call(new Pair<U, T>(acc, e));
		};
		return acc;
	}
	
	//TODO: join

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

    //TODO: Re-factor commonality between the different functions

	private static <T> T instantiate(Class<T> type) {
		try {
			return type.newInstance();
		} catch (InstantiationException exception) {
			throw new RuntimeException(exception);
		} catch (IllegalAccessException exception) {
			throw new RuntimeException(exception);
		}
	};
	
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