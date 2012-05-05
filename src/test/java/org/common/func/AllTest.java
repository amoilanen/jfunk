package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class AllTest {

	private static final CollectionFunctions.Function<Object, Boolean> FALSE_FUNCTION = new CollectionFunctions.Function<Object, Boolean>() {
		public Boolean call(Object input) {
			return false;
		}
	};
	
	//all
	@Test
	public void ifAllCalledWithEmptyCollectionThenTrue() {
		assertTrue(CollectionFunctions.all(Collections.<Object>emptyList(), FALSE_FUNCTION));
	}
	
	@Test
	public void ifAllCalledWithNullArgumentsThenExceptionIsRaised() {
		try {
			CollectionFunctions.all(Collections.emptyList(), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			CollectionFunctions.all(null, FALSE_FUNCTION);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
	
	@Test
	public void ifAllCalledAllElementsSatisfyConditionThenTrue() {
		assertTrue(CollectionFunctions.all(Arrays.asList("one", "two", "three"), new CollectionFunctions.Function<String, Boolean> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
	
	@Test
	public void ifAllCalledNotAllCollectionElementsSatisfyConditionThenFalse() {
		assertFalse(CollectionFunctions.all(Arrays.asList("one", "", "three"), new CollectionFunctions.Function<String, Boolean> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
}