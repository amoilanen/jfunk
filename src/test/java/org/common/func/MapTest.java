package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.func.Enumerables.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

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
		    map(Arrays.asList(4, 3, 1, 5, 2), toString));
	};

	@Test
	public void ifMapIsCalledExceplicitSpecificationOfResultTypeAsSet() {
		Collection<String> result = map(Arrays.asList(4, 3, 1, 5, 2), toString, HashSet.class);
		
		assertEquals(HashSet.class, result.getClass());
		assertEquals(Sets.set("aaaa", "aaa", "aa", "aaaaa", "a"), result);
	};

	@Test
	public void ifMapIsCalledExceplicitSpecificationOfResultTypeAsArrayList() {		
		Collection<String> result = map(Arrays.asList(4, 3, 1, 5, 2), toString, ArrayList.class);
		
		assertEquals(ArrayList.class, result.getClass());
		assertEquals(Arrays.asList("aaaa", "aaa", "a", "aaaaa", "aa"), result);
	};

	@Test
	public void ifMapIsCalledExceplicitSpecificationOfResultTypeAsLinkedList() {
		Collection<String> result = map(Arrays.asList(4, 3, 1, 5, 2), toString, LinkedList.class);
		
		assertEquals(LinkedList.class, result.getClass());
		assertEquals(Arrays.asList("aaaa", "aaa", "a", "aaaaa", "aa"), result);
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