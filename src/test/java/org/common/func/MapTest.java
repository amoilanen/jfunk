package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.common.func.Enumerables.Function;
import org.junit.Test;

public class MapTest {
	
	private final static Function<Integer, String> toString = new Function<Integer, String>() {

    	private static final String STRING = "a";
    	
		public String call(Integer input) {
			StringBuilder result = new StringBuilder();
			
			for (int i = 0 ; i < input; i++) {
				result.append(STRING);
			};
			return result.toString();
		};
    };

	@Test
	public void ifMapIsCalledWithCollectionFunctionIsAppliedToAllElements() {
		assertEquals(Arrays.asList("aaaa", "aaa", "a", "aaaaa", "aa"),
		    Enumerables.map(Arrays.asList(4, 3, 1, 5, 2), toString));
	};

	@Test
	public void ifMapWithEmptyCollectionThenEmptyCollectionIsReturned() {
		assertEquals(Collections.emptyList(),
				Enumerables.map(Collections.<Integer>emptyList(), toString));
	};

	@Test
	public void ifMapWithNoFunctionIsCalledThenExceptionIsThrown() {
		try {
		    Enumerables.map(Arrays.asList(4, 3, 1, 5, 2), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
	};
}