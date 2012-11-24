package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import static org.common.func.TestFunctions.falseValue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class CollectTest {
	
	@Test
	public void ifCollectCalledWithEmptyCollectionThenEmptyCollectionIsReturned() {
		assertEquals(Collections.<Boolean>emptyList(), Enumerables.collect(Collections.<Object>emptyList(), falseValue));
	}
	
	@Test
	public void ifCollectIsCalledWithNullArgumentsThenExceptionIsRaised() {
		try {
			Enumerables.collect(Collections.emptyList(), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			Enumerables.collect(null, falseValue);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
	
	@Test
	public void ifCollectCalledWithNonEmptyCollectionThenConversionIsAppliedToEachElement() {
		assertEquals(Arrays.asList(1, 4, 9), Enumerables.collect(Arrays.asList("a", "ab", "abc"), new Enumerables.Function<String, Integer>() {

			public Integer call(String input) {
				return input.length() * input.length();
			}
		}));
	}
}