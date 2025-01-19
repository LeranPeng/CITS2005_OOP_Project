package itertools;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * An iterator with elements not satisfying a given {@link Predicate} dropped.
 *
 * @param <T> The type of elements returned by the iterator.
 */
public class FilterIterator<T> implements Iterator<T> {
    /** The wrapped iterator to be filtered. */
    private Iterator<T> it;

    /** The {@link Predicate} to use to determine whether to drop an element. */
    private Predicate<T> pred;

    /**
     * A buffer in which to store an element that has passed the predicate but has not yet been
     * returned. Necessary for {@link #hasNext()} to know if there is a next element.
     */
    private T buffer = null;

    /**
     * Constructs an iterator over the given iterator that only yields elements satisfying the
     * predicate.
     *
     * @param it The iterator.
     * @param pred The predicate.
     */
    public FilterIterator(Iterator<T> it, Predicate<T> pred) {
        this.it = it;
        this.pred = pred;
    }

    @Override
    public boolean hasNext() {
        // Need to know what next element is to know if it satisfies `pred`. Keep polling `it` until
        // we find one that satisfies.
        while (buffer == null) {
            if (!it.hasNext()) return false; // If `it` is out of elements, so are we.
            buffer = it.next();
            if (!pred.test(buffer)) {
                buffer = null; // Discard buffered element if it fails predicate.
            }
        }
        return true;
    }

    @Override
    public T next() {
        // `hasNext()` will populate buffer if there is anything left to return.
        if (!hasNext()) throw new NoSuchElementException();
        T result = buffer;
        buffer = null; // Must not leave already returned element in buffer.
        return result;
    }
}
