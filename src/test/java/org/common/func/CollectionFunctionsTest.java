package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.common.func.CollectionFunctions;
import org.junit.Test;

public class CollectionFunctionsTest {

	private static final CollectionFunctions.Function<Object, Boolean> FALSE_FUNCTION = new CollectionFunctions.Function<Object, Boolean>() {
		public Boolean call(Object input) {
			return false;
		}
	};
	
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
	
	//TODO: Proceed with implementing the "chunk" function
//	[3,1,4,1,5,9,2,6,5,3,5].chunk {|n|
//		  n.even?
//		}.each {|even, ary|
//		  p [even, ary]
//		}
//		#=> [false, [3, 1]]
//		#   [true, [4]]
//		#   [false, [1, 5, 9]]
//		#   [true, [2, 6]]
//		#   [false, [5, 3, 5]]
}	