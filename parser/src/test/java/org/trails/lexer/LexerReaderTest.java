package org.trails.lexer;

import lombok.val;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Verifies {@link LexerReader}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
public class LexerReaderTest {
    /**
     * Tests scanning.
     */
    @Test
    public void testScan() throws Exception {
        val reader = new LexerReader(new StringReader("& | = ! < >"));

        reader.read();

        assertEquals('&', reader.getPeek());
        assertEquals(1, reader.getLine());
    }

    /**
     * Tests scan new line.
     */
    @Test
    public void testScanNewLine() throws Exception {
        val reader = new LexerReader(new StringReader("\n"));

        reader.read();

        assertEquals('\n', reader.getPeek());
        assertEquals(2, reader.getLine());
    }

    /**
     * Tests scan expected.
     */
    @Test
    public void testScanExpected() throws Exception {
        val reader = new LexerReader(new StringReader("a"));

        boolean result = reader.read('a');

        assertEquals(' ', reader.getPeek());
        assertTrue(result);
    }

    /**
     * Tests scan not expected.
     */
    @Test
    public void testScanNotExpected() throws Exception {
        val reader = new LexerReader(new StringReader("a"));

        boolean result = reader.read('b');

        assertEquals('a', reader.getPeek());
        assertFalse(result);
    }

    /**
     * Tests skip whitespace.
     */
    @Test
    public void testSkipWhitespace() throws Exception {
        val reader = new LexerReader(new StringReader(" \t\n\ra"));

        reader.skipWhitespace();

        assertEquals('a', reader.getPeek());
    }

    /**
     * Tests clear peek.
     */
    @Test
    public void testClearPeek() throws Exception {
        val reader = new LexerReader(new StringReader("& | = ! < >"));

        reader.read();
        reader.clearPeek();

        assertEquals(' ', reader.getPeek());
    }
}
