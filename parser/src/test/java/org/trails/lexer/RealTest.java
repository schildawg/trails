package org.trails.lexer;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Verifies {@link Real}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
public class RealTest {
    /**
     * Tests constructor.
     */
    @Test
    public void testConstructor() throws Exception {
        val real = new Real(3.333f);

        assertEquals(3.333f, real.getValue(), 0.0001);
    }

    /**
     * Tests toString().
     */
    @Test
    public void testToString() throws Exception {
        val real = new Real(3.333f);

        assertEquals("3.333", real.toString());
    }

}
