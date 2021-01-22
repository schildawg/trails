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
public class NumberTest {
    /**
     * Tests constructor.
     */
    @Test
    public void testConstructor() throws Exception {
        val number = new Number(123);

        assertEquals(123, number.getValue());
    }

    /**
     * Tests toString().
     */
    @Test
    public void testToString() throws Exception {
        val number = new Number(123);

        assertEquals("123", number.toString());
    }

}
