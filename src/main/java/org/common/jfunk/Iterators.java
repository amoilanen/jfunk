package org.common.jfunk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Utility functions for working with iterators.
 * 
 */
public class Iterators {
    
    /**
     * Maps the provided function {@code f} over iterator {@code i} in a lazy fashion: the
     * elements of the iterator {@code i} are not requested until the corresponding element
     * has been requested on the iterator returned from {@code map}.
     * 
     * @param i - iterator to map the function over
     * @param f - function to map
     * @return iterator each element of which is a result of an application of the function {@code f}
     * to the corresponding element of the original iterator {@code i}
     */
    public static <T, U> Iterator<U> map(Iterator<T> i, Function<T, U> f) {
        return new MappedIterator<T, U>(i, f);
    }
    
    /**
     * Filters the provided iterator {@code i} with the predicate {@code p} in a lazy fashion: the
     * elements of the iterator {@code i} are not requested until the corresponding element
     * has been requested on the iterator returned from {@code filter}.
     * 
     * @param i - iterator to filter
     * @param p - predicate which will be used to determine which elements should be filtered out
     * @return iterator over all the elements of the iterator {@code i} for which the predicate {@code p}
     * returns {@code true}
     */
    public static <T> Iterator<T> filter(Iterator<T> i, Predicate<T> p) {        
        return new FilteredIterator<T>(i, p);
    }
    
    /**
     * Merges the provides iterators {@code iterators}.<br/>
     * <h3>Example:</h3>
     * 
     * First iterator [1, 2, 3, 4, 5] <br/>
     * Second iterator [6, 7, 8, 9, 10] <br/>
     * <br/>
     * then the merged iterator is [1, 6, 2, 7, 3, 8, 4, 9, 5, 10]<br/>
     * 
     * @param iterators - iterators to merge
     * @return iterator over all the elements of the argument iterators which were merged like in the provided example:
     * first go the first elements of the iterators, then the second elements and so forth
     */
    public static <T> Iterator<T> merge(Iterator<?>... iterators) {
        final List<Iterator<?>> iters = Arrays.<Iterator<?>>asList(iterators);
        
        return new MergedIterator<T>(iters);
    }

    /**
     * Concatenates the provided iterators {@code iterators}.<br/>
     * <h3>Example:</h3>
     * 
     * First iterator [1, 2, 3, 4, 5] <br/>
     * Second iterator [6, 7, 8, 9, 10] <br/>
     * <br/>
     * then the concatenated iterator is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]<br/>
     * 
     * @param iterators - iterators to concatenate
     * @return iterator over all the elements of the argument iterators which were concatenated like in the provided example:
     * first go the elements of the first iterator, then the elements of the second iterator and so forth
     */
    public static <T> Iterator<T> concat(Iterator<?>... iterators) {        
        @SuppressWarnings("unchecked")
        Iterator<T> result = iterators.length > 0 ? (Iterator<T>) iterators[0] : Collections.<T>emptyList().iterator();
        
        for (int i = 1; i < iterators.length; i++) {
            result = concatTwo(result, iterators[i]);
        };
        return result;
    }
    
    /**
     * Returns all the elements of the provided iterator {@code i} as a list.
     * 
     * @param i - iterator elements of which should be returned in a list
     * @return list of all the elements in the provided iterator
     */
    public static <T> List<T> toList(Iterator<T> i) {
        List<T> result = new ArrayList<T>();
        
        while (i.hasNext()) {
            result.add(i.next());
        };
        return result;
    }

    /**
     * Returns all the elements of the provided iterator {@code i} as an array.
     * 
     * @param i - iterator elements of which should be returned in an array
     * @return array of all the elements in the provided iterator
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Iterator<T> i, Class<T> clazz) {
        return (T[]) toList(i).toArray((T[]) Array.newInstance(clazz, 0));
    }

    //TODO: Implement the following remaining methods
    //forEach
    //drop
    //takeWhile
    
    private static <T> Iterator<T> concatTwo(final Iterator<?> iterator1, final Iterator<?> iterator2) {
        return new Iterator<T>() {

            public boolean hasNext() {
                return iterator1.hasNext() || iterator2.hasNext();
            }

            @SuppressWarnings("unchecked")
            public T next() {
                return (T) (iterator1.hasNext() ? iterator1.next() : iterator2.next());                
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }

    private static class MappedIterator<T, U> implements Iterator<U> {
        
        
        private final Iterator<T> iterator;
        
        private final Function<T, U> function;
        
        public MappedIterator(Iterator<T> iterator, Function<T, U> function) {
            this.iterator = iterator;
            this.function = function;
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public U next() {
            return function.call(iterator.next());
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    private static class FilteredIterator<T> implements Iterator<T> {
        
        private final Iterator<T> iterator;
        
        private final Predicate<T> predicate;
        
        private T cachedElement;
        
        private boolean hasReadCachedElement;
        
        public FilteredIterator(Iterator<T> iterator, Predicate<T> predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
            this.cachedElement = null;
            this.hasReadCachedElement = true;
        }
        
        public boolean hasNext() {
            return hasNextElementThatSatisfiesFilter();
        }

        public T next() {
            if (!hasReadCachedElement || hasNextElementThatSatisfiesFilter()) {
                hasReadCachedElement = true;
                return cachedElement;
            }
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        private boolean hasNextElementThatSatisfiesFilter() {
            if (!hasReadCachedElement) {
                return true;
            }
            while (iterator.hasNext()) {
                T element = iterator.next();

                if (predicate.call(element)) {
                    cachedElement = element;
                    hasReadCachedElement = false;
                    return true;
                }
            }
            return false;
        }
    }
    
    private static class MergedIterator<T> implements Iterator<T> {

        private final List<Iterator<?>> merged;

        private int iteratorsSize;
        
        private int index;
        
        private MergedIterator(List<Iterator<?>> merged) {
            this.merged = merged;
            this.iteratorsSize = merged.size();
            this.index = 0;
        }
        
        public boolean hasNext() {
            return Enumerables.any(merged, new Predicate<Iterator<?>>() {

                public Boolean call(Iterator<?> x) {
                    return x.hasNext();
                }
            });
        }

        @SuppressWarnings("unchecked")
        public T next() {
            int initialIndex = index;

            do {
                Iterator<?> currentIterator = merged.get(index);                
                index++;
                if (iteratorsSize == index) {
                    index = 0;
                };
                if (currentIterator.hasNext()) {
                    return (T) currentIterator.next();
                };
            } while ((initialIndex != index) && (index >= 0) && (index < iteratorsSize));
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
