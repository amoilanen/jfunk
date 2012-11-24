package org.common.func;

import static junit.framework.Assert.assertEquals;
import static org.common.func.Pair.pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.common.func.TestFunctions.isEven;

import org.junit.Test;

public class ChunkTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void ifChunkIsCalledThenElementsAreGroupedByValuesReturnedFromFunction() {
		assertEquals(Arrays.asList(
				pair(Boolean.FALSE, Arrays.asList(9, 3)),
		        pair(Boolean.TRUE, Arrays.asList(2, 4, 6)),
		        pair(Boolean.FALSE, Arrays.asList(1, 5, 7, 9)),
		        pair(Boolean.TRUE, Arrays.asList(2))), Enumerables.chunk(Arrays.asList(9, 3, 2, 4, 6, 1, 5, 7, 9, 2), isEven));
	};

	@Test
	public void ifChunkIsCalledWithEmptyCollectionThenEmptyChunkedCollectionIsReturned() {
		assertEquals(new ArrayList<Pair<Boolean, List<Integer>>>(), Enumerables.chunk(new ArrayList<Integer>(), isEven));
	};
	
	@Test
	public void ifChunkIsCalledAndThereIsSingleChunkThenSingleChunkIsReturned() {
		@SuppressWarnings("unchecked")
		List<Pair<Boolean, List<Integer>>> expected = Arrays.asList(
		    pair(Boolean.FALSE, Arrays.asList(1, 3, 5, 7, 9)));
		
		assertEquals(expected, Enumerables.chunk(Arrays.asList(1, 3, 5, 7, 9), isEven));
	};

	@Test
	public void ifChunkIsCalledAndEachChunkContainsOnlyOneElementThenElementsAreGroupedByValuesReturnedFromFunction() {
		@SuppressWarnings("unchecked")
		List<Pair<Boolean, List<Integer>>> expected = Arrays.asList(
			pair(Boolean.FALSE, Arrays.asList(1)),
		    pair(Boolean.TRUE, Arrays.asList(2)),
		    pair(Boolean.FALSE, Arrays.asList(3)));
		
		assertEquals(expected, Enumerables.chunk(Arrays.asList(1, 2, 3), isEven));
	};
	
	//TODO: Chunk key is null?
}