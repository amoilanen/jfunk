package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class CollectTest {

	private static final CollectionFunctions.Function<Object, Boolean> FALSE_FUNCTION = new CollectionFunctions.Function<Object, Boolean>() {
		public Boolean call(Object input) {
			return false;
		}
	};
	
	@Test
	public void ifCollectCalledWithEmptyCollectionThenEmptyCollectionIsReturned() {
		assertEquals(Collections.<Boolean>emptyList(), CollectionFunctions.collect(Collections.<Object>emptyList(), FALSE_FUNCTION));
	}
	
	@Test
	public void ifCollectIsCalledWithNullArgumentsThenExceptionIsRaised() {
		try {
			CollectionFunctions.collect(Collections.emptyList(), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			CollectionFunctions.collect(null, FALSE_FUNCTION);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
	
	@Test
	public void ifCollectCalledWithNonEmptyCollectionThenConversionIsAppliedToEachElement() {
		assertEquals(Arrays.asList(1, 4, 9), CollectionFunctions.collect(Arrays.asList("a", "ab", "abc"), new CollectionFunctions.Function<String, Integer>() {

			public Integer call(String input) {
				return input.length() * input.length();
			}
		}));
	}
}