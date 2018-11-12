import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static java.util.Spliterator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2018-11-12.
 */
public class SpliteratorTest {

    @Test
    public void tryAdvance_nonEmpty() {
//        given
        var integers = List.of(1, 2, 3, 4);
        var spliterator = integers.spliterator();

//        expect
        assertTrue(spliterator.tryAdvance(System.out::println));
    }

    @Test
    public void tryAdvance_empty() {
//        given
        var integers = List.of();
        var spliterator = integers.spliterator();

//        expect
        assertFalse(spliterator.tryAdvance(System.out::println));
    }

    @Test
    public void forEachRemaining() {
//        given
        var integers = List.of(1, 2, 3, 4);
        var spliterator = integers.spliterator();

//        print all
        spliterator.forEachRemaining(System.out::println);
    }

    @Test
    public void trySplit() {
//        given
        var integers = List.of(1, 2, 3, 4, 5);
        var spliterator = integers.spliterator();
        var splitted = spliterator.trySplit();

//        expect
        assertThat(spliterator.getExactSizeIfKnown(), is(3L));
        spliterator.forEachRemaining(System.out::println);
        assertThat(spliterator.getExactSizeIfKnown(), is(0L));

        System.out.println("---");

        assertThat(splitted.getExactSizeIfKnown(), is(2L));
        splitted.forEachRemaining(System.out::println);
        assertThat(splitted.getExactSizeIfKnown(), is(0L));
    }

    @Test
    public void estimateSize() {
//        given
        var integers = List.of(1, 2, 3, 4, 5);
        var spliterator = integers.spliterator();

//        expect
        assertThat(spliterator.estimateSize(), is(5L));
    }

    @Test
    public void getExactSizeIfKnown() {
//        given
        var integers = List.of(1, 2, 3, 4, 5);
        var spliterator = integers.spliterator();

//        expect
        assertThat(spliterator.getExactSizeIfKnown(), is(5L));
    }

    @Test
    public void list_hasCharacteristics() {
//        given
        var integers = List.of(1, 2, 3, 4, 5);
        var spliterator = integers.spliterator();

//        expect
        assertTrue(spliterator.hasCharacteristics(ORDERED));
        assertFalse(spliterator.hasCharacteristics(DISTINCT));
        assertFalse(spliterator.hasCharacteristics(SORTED));
        assertTrue(spliterator.hasCharacteristics(SIZED));
        assertFalse(spliterator.hasCharacteristics(NONNULL));
        assertFalse(spliterator.hasCharacteristics(IMMUTABLE));
        assertFalse(spliterator.hasCharacteristics(CONCURRENT));
        assertTrue(spliterator.hasCharacteristics(SUBSIZED));
    }

    @Test
    public void treeSet_hasCharacteristics() {
//        given
        var integers = new TreeSet<Integer>();
        var spliterator = integers.spliterator();

//        expect
        assertTrue(spliterator.hasCharacteristics(ORDERED));
        assertTrue(spliterator.hasCharacteristics(DISTINCT));
        assertTrue(spliterator.hasCharacteristics(SORTED));
        assertTrue(spliterator.hasCharacteristics(SIZED));
        assertFalse(spliterator.hasCharacteristics(NONNULL));
        assertFalse(spliterator.hasCharacteristics(IMMUTABLE));
        assertFalse(spliterator.hasCharacteristics(CONCURRENT));
        assertFalse(spliterator.hasCharacteristics(SUBSIZED));
    }

    @Test
    public void treeSet_getComparator() {
//        given
        var integers = new TreeSet<Integer>(Comparator.naturalOrder());
        var spliterator = integers.spliterator();

//        expect
        assertThat(spliterator.getComparator(), is(Comparator.naturalOrder()));
    }
}
