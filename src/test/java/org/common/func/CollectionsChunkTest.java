package org.common.func;

import static junit.framework.Assert.assertEquals;
import static org.common.func.Pair.pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CollectionsChunkTest {

	private final static CollectionFunctions.Function<Integer, Boolean> isEven = new CollectionFunctions.Function<Integer, Boolean>() {
		public Boolean call(Integer input) {
			return 0 == input.intValue() % 2;
		}
	};
	
	@SuppressWarnings("unchecked")
	@Test
	public void ifChunkIsCalledThenElementsAreGroupedByValuesReturnedFromFunction() {
		assertEquals(Arrays.asList(
				pair(Boolean.FALSE, Arrays.asList(9, 3)),
		        pair(Boolean.TRUE, Arrays.asList(2, 4, 6)),
		        pair(Boolean.FALSE, Arrays.asList(1, 5, 7, 9)),
		        pair(Boolean.TRUE, Arrays.asList(2))), CollectionFunctions.chunk(Arrays.asList(9, 3, 2, 4, 6, 1, 5, 7, 9, 2), isEven));
	};

	@Test
	public void ifChunkIsCalledWithEmptyCollectionThenEmptyChunkedCollectionIsReturned() {
		assertEquals(new ArrayList<Pair<Boolean, List<Integer>>>(), CollectionFunctions.chunk(new ArrayList<Integer>(), isEven));
	};
	
	@SuppressWarnings("unchecked")
	@Test
	public void ifChunkIsCalledAndThereIsSingleChunkThenSingleChunkIsReturned() {
		assertEquals(Arrays.asList(
				pair(Boolean.FALSE, Arrays.asList(1, 3, 5, 7, 9))), CollectionFunctions.chunk(Arrays.asList(1, 3, 5, 7, 9), isEven));
	};

	@SuppressWarnings("unchecked")
	@Test
	public void ifChunkIsCalledAndEachChunkContainsOnlyOneElementThenElementsAreGroupedByValuesReturnedFromFunction() {
		assertEquals(Arrays.asList(
				pair(Boolean.FALSE, Arrays.asList(1)),
		        pair(Boolean.TRUE, Arrays.asList(2)),
		        pair(Boolean.FALSE, Arrays.asList(3))), CollectionFunctions.chunk(Arrays.asList(1, 2, 3), isEven));
	};
	
	//TODO: Chunk key is null?
}