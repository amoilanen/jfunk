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

    //forEach
    //drop
    //takeWhile
    //map
    
    
    public static <T> Iterator<T> filter(Iterator<T> iterator, Predicate<T> p) {        
        return new FilteredIterator<T>(iterator, p);
    }
    
    public static <T> Iterator<T> merge(Iterator<?>... iterators) {
        final List<Iterator<?>> iters = Arrays.<Iterator<?>>asList(iterators);
        
        return new MergedIterator<T>(iters);
    }

    public static <T> Iterator<T> concat(Iterator<?>... iterators) {        
        @SuppressWarnings("unchecked")
        Iterator<T> result = iterators.length > 0 ? (Iterator<T>) iterators[0] : Collections.<T>emptyList().iterator();
        
        for (int i = 1; i < iterators.length; i++) {
            result = concatTwo(result, iterators[i]);
        };
        return result;
    }
    
    public static <T> List<T> toList(Iterator<T> iter) {
        List<T> result = new ArrayList<T>();
        
        while (iter.hasNext()) {
            result.add(iter.next());
        };
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Iterator<T> iter, Class<T> clazz) {
        return (T[]) toList(iter).toArray((T[]) Array.newInstance(clazz, 0));
    }

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
