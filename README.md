# java11-spliterator
Overview of spliterator inferface.

_Reference_: https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html  
_Reference_: https://docs.oracle.com/javase/8/docs/api/java/util/stream/StreamSupport.html  
_Reference_: https://www.baeldung.com/java-spliterator

# preface

## Spliterator
An object for traversing and partitioning elements of a source.  The source
of elements covered by a `Spliterator` could be, for example, an array, a
`Collection`, an IO channel, or a generator function.

### methods


If this Spliterator is 
`ORDERED` the action is performed on the next element in encounter.





* `boolean tryAdvance(Consumer<? super T> action);` - 
If a remaining element exists, performs the given action on it,
returning true; else returns false.  
order.
* `default void forEachRemaining(Consumer<? super T> action)` - 
Performs the given action for each remaining element, sequentially in
the current thread, until all elements have been processed or the action
throws an exception.
* `Spliterator<T> trySplit();` - If this spliterator can be 
partitioned, returns a Spliterator covering elements, that will, 
upon return from this method, not be covered by this Spliterator.
    
  **returns** a `Spliterator` covering some portion of the
  elements, or null if this spliterator cannot be 
  
* `long estimateSize();` - Returns an estimate of the number of elements 
that would be encountered by a `forEachRemaining` traversal, or 
returns `Long.MAX_VALUE` if infinite, unknown, or too expensive to 
compute.

   **If this Spliterator is `SIZED` and has not yet been partially
   traversed or split, or this Spliterator is `SUBSIZED` and has
   not yet been partially traversed, this estimate must be an accurate
   count of elements that would be encountered by a complete traversal.**
   Otherwise, this estimate may be arbitrarily inaccurate, but must decrease
   as specified across invocations of `trySplit`.
   
* `default long getExactSizeIfKnown()` - default implementation is
self explanatory
    ```
    default long getExactSizeIfKnown() {
            return (characteristics() & SIZED) == 0 ? -1L : estimateSize();
    }
    ```
* `int characteristics();` - returns a set of characteristics of this 
Spliterator and its elements. The result is represented as ORed values 
from:
    * `ORDERED`
    
       Characteristic value signifying that an encounter order is 
       defined for elements. If so, this Spliterator guarantees 
       that method trySplit splits a strict prefix of elements, that 
       method tryAdvance steps by one element in prefix order, and that
       forEachRemaining performs actions in encounter order.
    * `DISTINCT`
    
       Characteristic value signifying that, for each pair of
       encountered elements !x.equals(y).
    * `SORTED`
    
       Characteristic value signifying that encounter order follows a defined
       sort order. If so, method `getComparator()` returns the associated
       Comparator, or null if all elements are Comparable and
       are sorted by their natural ordering.
    * `SIZED`
    
       Characteristic value signifying that the value returned from
       estimateSize() prior to traversal or splitting represents a
       finite size that, in the absence of structural source modification,
       represents an exact count of the number of elements that would be
       encountered by a complete traversal.
    * `NONNULL`
    
       Characteristic value signifying that the source guarantees that
       encountered elements will not be null.
    * `IMMUTABLE`
    
       Characteristic value signifying that the element source cannot be
       structurally modified; that is, elements cannot be added, replaced, 
       or removed, so such changes cannot occur during traversal.
    * `CONCURRENT`
    
       Characteristic value signifying that the element source may be safely
       concurrently modified (allowing additions, replacements, and/or removals)
       by multiple threads without external synchronization. If so, the
       Spliterator is expected to have a documented policy concerning the impact
       of modifications during traversal.
       
       A top-level Spliterator should not report both CONCURRENT and
       SIZED, since the finite size, if known, may change if the source
       is concurrently modified during traversal.
       
    * `SUBSIZED`
       Characteristic value signifying that all Spliterators resulting 
       from trySplit() will be both SIZED and SUBSIZED.
       (This means that all child Spliterators, whether direct or 
       indirect, will be SIZED.)
  
       Some spliterators, such as the top-level spliterator for an
       approximately balanced binary tree, will report SIZED but not
       SUBSIZED, since it is common to know the size of the entire tree
       but not the exact sizes of subtrees.
* `default boolean hasCharacteristics(int characteristics)`
* `default Comparator<? super T> getComparator()` - If this 
    Spliterator's source is SORTED by a Comparator, returns that 
    Comparator. If the source is SORTED in Comparable natural order, 
    returns null.  Otherwise, if the source is not SORTED, 
    throws IllegalStateException.
    
## StreamSupport
Low-level utility methods for creating and manipulating streams.

This class is mostly for library writers presenting stream views
of data structures; most static stream methods intended for end users are in
the various Stream classes.

* `public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel)`
* `public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier,
                                          int characteristics,
                                          boolean parallel)`
* `public static IntStream intStream(Spliterator.OfInt spliterator, boolean parallel)`
* `public static IntStream intStream(Supplier<? extends Spliterator.OfInt> supplier,
                                         int characteristics,
                                         boolean parallel)`
* similar methods for `long` and `double`
# Spliterators
Static classes and methods for operating on or creating instances of
Spliterator and its primitive specializations like `Spliterator.OfInt`.

This method is provided as an implementation convenience for
Spliterators which store portions of their elements in arrays, and need
fine control over Spliterator characteristics.  Most other situations in
which a Spliterator for an array is needed should use
Arrays#spliterator(T/int/double/long[]).

The returned spliterator always reports the characteristics
`SIZED` and `SUBSIZED`.  The caller may provide additional
characteristics for the spliterator to report; it is common to
additionally specify `IMMUTABLE` and `ORDERED`.

```

```
```
Spliterator.OfInt spliterator = Arrays.spliterator(new int[]{1, 2, 3});
```
## Spliterator.OfPrimitive