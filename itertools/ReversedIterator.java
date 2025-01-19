package itertools;

/**
 * A (double ended) iterator in the reverse order of the one given.
 *
 * @param <T> The type of elements in the iterator.
 */
class ReversedIterator<T> implements DoubleEndedIterator<T> {
    /** The wrapped iterator to reverse. */
    private DoubleEndedIterator<T> it;

    /**
     * Constructs a (double ended) iterator in the reverse order of the one given.
     *
     * @param it The iterator
     */
    public ReversedIterator(DoubleEndedIterator<T> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public T next() {
        // `next()` should take from the back of the wrapped iterator.
        return it.reverseNext();
    }

    @Override
    public T reverseNext() {
        // `reverseNext()` should take from the front of the wrapped iterator.
        return it.next();
    }
}
