package org.common.jfunk.test;

import org.common.jfunk.Function;
import org.common.jfunk.Pair;
import org.common.jfunk.Predicate;


public class Functions {

	public static final Predicate<Object> falseValue = new Predicate<Object>() {
		public Boolean call(Object input) {
			return false;
		}
	};

	public final static Predicate<Integer> isEven = new Predicate<Integer>() {
		public Boolean call(Integer input) {
			return 0 == input.intValue() % 2;
		}
	};
	
	public final static Function<Integer, String> toString = new Function<Integer, String>() {

    	private static final String STRING = "a";
    	
		public String call(Integer input) {
			StringBuilder result = new StringBuilder();
			
			for (int i = 0 ; i < input; i++) {
				result.append(STRING);
			};
			return result.toString();
		};
    };

	public final static Function<Pair<Integer, Integer>, Integer> sum = new Function<Pair<Integer, Integer>, Integer>() {
    	
		public Integer call(Pair<Integer, Integer> input) {
			return input.getFirst() + input.getSecond();
		};
    };

    public final static Function<Pair<Integer, Integer>, Integer> mult = new Function<Pair<Integer, Integer>, Integer>() {
    	
    	public Integer call(Pair<Integer, Integer> input) {
    		return input.getFirst() * input.getSecond();
    	};
    };
}