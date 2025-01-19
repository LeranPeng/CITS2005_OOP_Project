package itertools;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over a given length prefix of another iterator.
 *
 * @param <T> The type of elements in the iterator.
 */
class TakeIterator<T> implements Iterator<T> {
    /** The wrapped iterator of which to take a prefix. */
    private Iterator<T> it;

    /** The number of elements remaining to take. */
    private int count;

    /**
     * Given an iterator and a number of elements, constructs an iterator over that number of
     * elements taken from the iterator (or as many as it contains, if less than that number).
     *
     * <p>Elements are consumed from the given iterator only as needed.
     *
     * @param it The iterator from which to take elements.
     * @param count The maximum number of elements to take.
     */
    public TakeIterator(Iterator<T> it, int count) {
        this.it = it;
        this.count = count;
    }

    @Override
    public boolean hasNext() {
        // End when either we have taken the desired number of elements or hit the end of the
        // wrapped iterator, whichever comes sooner.
        return count > 0 && it.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        count--;
        return it.next();
    }
}
