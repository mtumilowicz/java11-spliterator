import org.junit.Test;

import java.util.Spliterators;

import static java.util.Spliterator.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mtumilowicz on 2018-11-12.
 */
public class SpliteratorsTest {

    @Test
    public void ofIntSpliterator_ownCharacteristics() {
//        given
        var integers = new int[]{1, 2, 3, 4, 5};

        OfInt spliterator = Spliterators.spliterator(integers,
                ORDERED |
                        DISTINCT |
                        SORTED |
                        SIZED |
                        NONNULL |
                        IMMUTABLE |
                        SUBSIZED);

//        expect
        assertTrue(spliterator.hasCharacteristics(ORDERED));
        assertTrue(spliterator.hasCharacteristics(DISTINCT));
        assertTrue(spliterator.hasCharacteristics(SORTED));
        assertTrue(spliterator.hasCharacteristics(SIZED));
        assertTrue(spliterator.hasCharacteristics(NONNULL));
        assertTrue(spliterator.hasCharacteristics(IMMUTABLE));
        assertFalse(spliterator.hasCharacteristics(CONCURRENT));
        assertTrue(spliterator.hasCharacteristics(SUBSIZED));
    }
}
