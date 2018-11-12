import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntConsumer;

import static java.util.Spliterator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2018-11-12.
 */
public class SpliteratorOfIntTest {
    @Test
    public void tryAdvance_nonEmpty() {
//        given
        var integers = new int[]{1, 2, 3, 4};
        var spliterator = Arrays.spliterator(integers);

//        expect
        assertTrue(spliterator.tryAdvance((IntConsumer) System.out::print));
    }

    @Test
    public void tryAdvance_empty() {
//        given
        var integers = new int[]{};
        var spliterator = Arrays.spliterator(integers);

//        expect
        assertFalse(spliterator.tryAdvance((IntConsumer) System.out::println));
    }

    @Test
    public void forEachRemaining() {
//        given
        var integers = new int[]{1, 2, 3, 4};
        var spliterator = Arrays.spliterator(integers);

//        print all
        spliterator.forEachRemaining((IntConsumer) System.out::println);
    }

    @Test
    public void trySplit() {
//        given
        var integers = new int[]{1, 2, 3, 4, 5};
        var spliterator = Arrays.spliterator(integers);
        var splitted = spliterator.trySplit();

//        expect
        assertThat(spliterator.getExactSizeIfKnown(), is(3L));
        spliterator.forEachRemaining((IntConsumer) System.out::println);
        assertThat(spliterator.getExactSizeIfKnown(), is(0L));

        System.out.println("---");

        assertThat(splitted.getExactSizeIfKnown(), is(2L));
        splitted.forEachRemaining((IntConsumer) System.out::println);
        assertThat(splitted.getExactSizeIfKnown(), is(0L));
    }

    @Test
    public void estimateSize() {
//        given
        var integers = new int[]{1, 2, 3, 4, 5};
        var spliterator = Arrays.spliterator(integers);

//        expect
        assertThat(spliterator.estimateSize(), is(5L));
    }

    @Test
    public void getExactSizeIfKnown() {
//        given
        var integers = new int[]{1, 2, 3, 4};
        var spliterator = Arrays.spliterator(integers);

//        expect
        assertThat(spliterator.getExactSizeIfKnown(), is(4L));
    }

    @Test
    public void hasCharacteristics() {
//        given
        var integers = new int[]{1, 2, 3, 4};
        var spliterator = Arrays.spliterator(integers);

//        expect
        assertTrue(spliterator.hasCharacteristics(ORDERED));
        assertFalse(spliterator.hasCharacteristics(DISTINCT));
        assertFalse(spliterator.hasCharacteristics(SORTED));
        assertTrue(spliterator.hasCharacteristics(SIZED));
        assertFalse(spliterator.hasCharacteristics(NONNULL));
        assertTrue(spliterator.hasCharacteristics(IMMUTABLE));
        assertFalse(spliterator.hasCharacteristics(CONCURRENT));
        assertTrue(spliterator.hasCharacteristics(SUBSIZED));
    }

    @Test(expected = IllegalStateException.class)
    public void getComparator() {
//        given
        var integers = new int[]{1, 2, 3, 4};
        var spliterator = Arrays.spliterator(integers);

//        expect
        spliterator.getComparator();
    }
}
