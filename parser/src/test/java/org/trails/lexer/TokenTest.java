package org.trails.lexer;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Verifies {@link Token}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
public class TokenTest {
    /**
     * Tests constructor.
     */
    @Test
    public void testConstructor() throws Exception {
        val token = new Token(42);

        assertEquals(42, token.getTag());
    }

    /**
     * Tests character constructor.
     */
    @Test
    public void testConstructor2() throws Exception {
        val token = new Token('%');

        assertEquals('%', token.getTag());
    }

    /**
     * Tests toString().
     */
    @Test
    public void testToString() throws Exception {
        val token = new Token(42);

        assertEquals("*", token.toString());
    }

}
