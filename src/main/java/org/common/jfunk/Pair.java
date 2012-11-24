package org.common.jfunk;

/**
 * Generic pair, see http://en.wikipedia.org/wiki/Cons
 *
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1, T2> {

    //Head
    public final T1 h;

    //Tail
    public final T2 t;

    public Pair(T1 h, T2 t) {
        this.h = h;
        this.t = t;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((h == null) ? 0 : h.hashCode());
        result = prime * result
                + ((t == null) ? 0 : t.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("rawtypes")
        Pair other = (Pair) obj;
        if (h == null) {
            if (other.h != null)
                return false;
        } else if (!h.equals(other.h))
            return false;
        if (t == null) {
            if (other.t != null)
                return false;
        } else if (!t.equals(other.t))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pair [h=" + h + ", t=" + t + "]";
    }

    public static <T1, T2> Pair<T1, T2> pair(T1 h, T2 t) {
        return new Pair<T1, T2>(h, t);
    };
}
