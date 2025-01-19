package itertools;

import java.util.Iterator;
import java.util.function.BiFunction;

/**
 * An iterator over the results of combining each pair of elements from a pair of given iterators
 * using a given function.
 *
 * @param <T> The type of elements in the "left-hand" iterator.
 * @param <U> The type of elements in the "right-hand" iterator.
 * @param <R> The type of elements in the result iterator.
 */
class ZipIterator<T, U, R> implements Iterator<R> {
    /** The "left-hand" iterator. */
    private Iterator<T> lit;

    /** The "right-hand" iterator. */
    private Iterator<U> rit;

    /** The function to use to combine elements from `lit` and `rit`. */
    private BiFunction<T, U, R> f;

    /**
     * Constructs an iterator over the results of combining each pair of elements from a pair of
     * given iterators using a given function.
     *
     * @param lit The "left-hand" iterator.
     * @param rit The "right-hand" iterator.
     * @param f A function to use to combine elements from `lit` and `rit`.
     */
    public ZipIterator(Iterator<T> lit, Iterator<U> rit, BiFunction<T, U, R> f) {
        this.lit = lit;
        this.rit = rit;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        // Have elements only so long as both input iterators are not exhausted.
        return lit.hasNext() && rit.hasNext();
    }

    @Override
    public R next() {
        // Lazily combine elements using `f` as each element is consumed.
        return f.apply(lit.next(), rit.next());
    }
}
