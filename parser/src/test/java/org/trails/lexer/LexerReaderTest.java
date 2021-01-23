/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trails.lexer;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies {@link LexerReader}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LexerReaderTest {
    /**
     * Tests scanning.
     */
    @Test
    void testScan() throws Exception {
        val reader = new LexerReader(new StringReader("& | = ! < >"));

        reader.read();

        assertEquals('&', reader.getPeek());
        assertEquals(1, reader.getLine());
    }

    /**
     * Test IOException thrown when reader fails.
     */
    @Test
    void testScanIOException() throws Exception {
        val stringReader = Mockito.mock(StringReader.class);
        Mockito.when(stringReader.read()).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            val reader = new LexerReader(stringReader);

            reader.read();
        });
    }

    /**
     * Tests scan new line.
     */
    @Test
    void testScanNewLine() throws Exception {
        val reader = new LexerReader(new StringReader("\n"));

        reader.read();

        assertEquals('\n', reader.getPeek());
        assertEquals(2, reader.getLine());
    }

    /**
     * Tests scan expected.
     */
    @Test
    void testScanExpected() throws Exception {
        val reader = new LexerReader(new StringReader("a"));

        boolean result = reader.read('a');

        assertEquals(' ', reader.getPeek());
        assertTrue(result);
    }

    /**
     * Test IOException thrown when reader fails.
     */
    @Test
    void testScanExpectedIOException() throws Exception {
        val stringReader = Mockito.mock(StringReader.class);
        Mockito.when(stringReader.read()).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            val reader = new LexerReader(stringReader);

            reader.read('a');
        });
    }

    /**
     * Tests scan not expected.
     */
    @Test
    void testScanNotExpected() throws Exception {
        val reader = new LexerReader(new StringReader("a"));

        boolean result = reader.read('b');

        assertEquals('a', reader.getPeek());
        assertFalse(result);
    }

    /**
     * Tests skip whitespace.
     */
    @Test
    void testSkipWhitespace()  {
        val reader = new LexerReader(new StringReader(" \t\n\ra"));

        reader.skipWhitespace();

        assertEquals('a', reader.getPeek());
    }

    /**
     * Tests clear peek.
     */
    @Test
    void testClearPeek() throws Exception {
        val reader = new LexerReader(new StringReader("& | = ! < >"));

        reader.read();
        reader.clearPeek();

        assertEquals(' ', reader.getPeek());
    }
}
