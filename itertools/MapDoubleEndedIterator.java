package itertools;

import java.util.function.Function;

/**
 * A double ended version of {@link MapIterator}.
 *
 * @param      <T> The type of elements in the input iterator.
 * @param      <R> The type of elements in the result iterator.
 */
class MapDoubleEndedIterator<T, R> implements DoubleEndedIterator<R> {
    /** The iterator to map over. */
    private DoubleEndedIterator<T> it;

    /** The function to apply to each element. */
    private Function<T, R> f;

    /**
     * Constructs an iterator over the elements of the given iterator with the given function
     * applied to each element.
     *
     * @param it The iterator to map over.
     * @param f The function to apply to each element.
     */
    public MapDoubleEndedIterator(DoubleEndedIterator<T> it, Function<T, R> f) {
        this.it = it;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public R next() {
        // Lazily apply `f` as each element is consumed.
        return f.apply(it.next());
    }

    @Override
    public R reverseNext() {
        // Lazily apply `f` as each element is consumed.
        return f.apply(it.reverseNext());
    }
}
