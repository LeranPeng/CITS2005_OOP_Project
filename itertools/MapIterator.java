package itertools;

import java.util.Iterator;
import java.util.function.Function;

/**
 * An iterator over the elements of a given iterator with a given function applied to each element.
 *
 * @param <T> The type of elements in the input iterator.
 * @param <R> The type of elements in the result iterator.
 */
class MapIterator<T, R> implements Iterator<R> {
    /** The iterator to map over. */
    private Iterator<T> it;

    /** The function to apply to each element. */
    private Function<T, R> f;

    /**
     * Constructs an iterator over the elements of the given iterator with the given function
     * applied to each element.
     *
     * @param it The iterator to map over.
     * @param f The function to apply to each element.
     */
    public MapIterator(Iterator<T> it, Function<T, R> f) {
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
}
