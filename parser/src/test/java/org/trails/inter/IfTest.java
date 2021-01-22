package org.trails.inter;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lombok.val;

/**
 * Verifies {@link If}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/12/2021
 * @since   1.0
 */
public class IfTest {
    /**
     * Tests parsing an ampersand.
     */
    @Test
    public void testConstructor() throws Exception {
        new If(null, null);
    }

}
