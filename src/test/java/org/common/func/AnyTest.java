package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class AnyTest {

	private static final CollectionFunctions.Function<Object, Boolean> FALSE_FUNCTION = new CollectionFunctions.Function<Object, Boolean>() {
		public Boolean call(Object input) {
			return false;
		}
	};

	@Test
	public void ifAnyCalledWithEmptyCollectionThenFalse() {
		assertFalse(CollectionFunctions.any(Collections.<Object>emptyList(), FALSE_FUNCTION));
	}
	
	@Test
	public void ifAnyCalledWithNullArgumentsThenExceptionIsRaised() {
		try {
			CollectionFunctions.any(Collections.emptyList(), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			CollectionFunctions.any(null, FALSE_FUNCTION);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
	
	@Test
	public void ifAnyCalledThereIsElementSatisfyingConditionThenTrue() {
		assertTrue(CollectionFunctions.any(Arrays.asList("", "two", ""), new CollectionFunctions.Function<String, Boolean> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
	
	@Test
	public void ifAnyCalledNoElementSatisfyingConditionThenFalse() {
		assertFalse(CollectionFunctions.any(Arrays.asList("", "", ""), new CollectionFunctions.Function<String, Boolean> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
}