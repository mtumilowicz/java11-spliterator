import org.junit.Test;

import java.util.List;
import java.util.Spliterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by mtumilowicz on 2018-11-12.
 */
public class SpliteratorTest {
    
    @Test
    public void tryAdvance_nonEmpty() {
//        given
        List<Integer> integers = List.of(1, 2, 3, 4);
        Spliterator<Integer> spliterator = integers.spliterator();
        
//        expect
        assertTrue(spliterator.tryAdvance(System.out::println));
    }

    @Test
    public void tryAdvance_empty() {
//        given
        List<Integer> integers = List.of();
        Spliterator<Integer> spliterator = integers.spliterator();

//        expect
        assertFalse(spliterator.tryAdvance(System.out::println));
    }

    @Test
    public void forEachRemaining() {
//        given
        List<Integer> integers = List.of(1, 2, 3, 4);
        Spliterator<Integer> spliterator = integers.spliterator();

//        print all
        spliterator.forEachRemaining(System.out::println);
    }

    @Test
    public void trySplit() {
//        given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        Spliterator<Integer> spliterator = integers.spliterator();

        Spliterator<Integer> splitted = spliterator.trySplit();

//        expect
        assertThat(spliterator.getExactSizeIfKnown(), is(3L));
        spliterator.forEachRemaining(System.out::println);
        assertThat(spliterator.getExactSizeIfKnown(), is(0L));
        
        System.out.println("---");
        
        assertThat(splitted.getExactSizeIfKnown(), is(2L));
        splitted.forEachRemaining(System.out::println);
        assertThat(splitted.getExactSizeIfKnown(), is(0L));
    }
}
