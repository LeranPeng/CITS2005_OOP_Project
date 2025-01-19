# CITS2005_OOP_Project

## itertools
Iterators are a powerful tool in Object Oriented Programming. Java already
provides a simple iterator interface (java.util.Iterator), but it is possible to make
much more powerful and versatile iterators. The itertools package is a library of
tools for working with iterators. You have been asked to implement a number of
methods within itertools. You are permitted and expected to add your own new
.java source files within itertools as appropriate.
The itertools library provides the `DoubleEndedIterator<T>` interface, which
represents an iterator that can be consumed either from the front or the back, and
the `RangeIterator` class, as an example implementation of
`DoubleEndedIterator`.
A number of methods in this package use interfaces from Java's java.util.function
package, which represents different kinds of mathematical functions as objects so
we can pass them as arguments to methods. Example implementations of these
interfaces are included in the template source and tests.


### TASK(1): Implement take

See /itertools/Itertools.java

Given an iterator and a number of elements, the `take` method returns an iterator
over that number of elements taken from the iterator (or as many as it contains, if
less than that number).
Elements should be consumed from the given iterator only as needed.
This concept is called "laziness", in that it does not perform computation until it is
required. In the case of iterators, this means that you should not simply copy
elements into a data structure and then provide an iterator over that data structure,
as this would pull a number of elements from the given iterator that may never be
required.


### TASK(2): Implement reversed

See /itertools/Itertools.java

The `reversed` method returns a (double ended) iterator in the reverse order of the one given.
Elements should be consumed from the given iterator only as needed.


### TASK(3): Implement filter

See /itertools/Itertools.java

The `filter` method returns an iterator over only the elements of a given iterator that
satisfy a given predicate. A predicate is a function used to determine if a particular
property holds for an item. An example predicate could be "this integer is even", for
which 4 would satisfy the predicate but 7 would not.
Elements should be consumed from the given iterator only as needed (though it
may be necessary to consume elements to determine whether there is a next
element that satisfies the predicate).
Java's `java.util.function.Predicate` interface can be used by calling `pred.test(x)`,
and will return `true` if and only if `x` satisfies the predicate.

### TASK(4): Implement map (single ended)

See /itertools/Itertools.java

The `map` method returns an iterator over the elements of a given iterator with a
given function applied to each element.
That is, given a function `f` and an iterator over the elements `a, b, c, ...`, returns
an iterator over `f(a), f(b), f(c), ...`.
This allows us to "transform" an iterator, applying a function to each element as it
is retrieved, rather than having to consume the iterator, transforming and storing
each element, and then iterating over the stored collection.
Elements should be consumed from the given iterator only as needed.
Java's `java.util.function.Function` interface can be used by calling `f.apply(x)` and
will return `f(x)`.


### TASK(5): Implement map (double ended)

See /itertools/Itertools.java

Implement a double ended version of `map`.


### TASK(6): Implement zip

See /itertools/Itertools.java

The `zip` method returns an iterator over the results of combining each pair of
elements from a pair of given iterators using a given function.
That is, given a function `f` and iterators over the elements `a, b, c, ...` and `x, y, z,
...` returns an iterator over `f(a, x), f(b, y), f(c, z), ...`.
The iterator ends when either input iterator ends.
Elements should be consumed from the given iterators only as needed.
Java's `java.util.function.BiFunction` interface can be used by calling `f.apply(x, y)`
and will return `f(x, y)`.
The iterator ends when either input iterator ends.
Elements should be consumed from the given iterators only as needed.
Java's `java.util.function.BiFunction` interface can be used by calling `f.apply(x, y)`
and will return `f(x, y)`.


### TASK(7): Implement reduce
See src/itertools/Itertools.java
The `reduce` method returns the result of combining all the elements from the
given iterator using the given function.
Each element is combined with the current value using the given function.
For example, given a function `f`, an initial value `x`, and an iterator over the
elements `a, b, c`, returns `f(f(f(x, a), b), c)`.
An example of a common reduction would be "sum", where we reduce an iterator
over integers using the addition function to compute the sum of every element in
the iterator.
Java's `java.util.function.BiFunction` interface can be used by calling `f.apply(x, y)`
and will return `f(x, y)`.
