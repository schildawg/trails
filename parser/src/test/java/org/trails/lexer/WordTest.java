package org.trails.lexer;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Verifies {@link Number}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
public class WordTest {
    /**
     * Tests constructor.
     */
    @Test
    public void testConstructor() throws Exception {
        val word = new Word("String", Tag.ID);

        assertEquals(Tag.ID, word.getTag());
        assertEquals("String", word.getLexeme());
    }

    /**
     * Tests toString().
     */
    @Test
    public void testToString() throws Exception {
        val word = new Word("String", Tag.ID);

        assertEquals("String", word.toString());
    }

}
