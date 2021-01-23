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
import lombok.var;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies {@link Lexer}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LexerTest {
    /**
     * Tests the reader throwing an exception.
     */
    @Test
    void testReaderThrowsException() throws Exception {
        val stringReader = Mockito.mock(StringReader.class);
        Mockito.when(stringReader.read()).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            val reader = new LexerReader(stringReader);
            val lexer = makeUut();

            lexer.scan(reader);
        });
    }

    /**
     * Tests parsing operators.
     */
    @Test
    void testParseOperators() throws Exception {
        val reader = new LexerReader(new StringReader("& | = ! < >"));
        val lexer = makeUut();

        var token = lexer.scan(reader);
        assertEquals( '&', token.getTag());

        token = lexer.scan(reader);
        assertEquals( '|', token.getTag());

        token = lexer.scan(reader);
        assertEquals( '=', token.getTag());

        token = lexer.scan(reader);
        assertEquals( '!', token.getTag());

        token = lexer.scan(reader);
        assertEquals( '<', token.getTag());

        token = lexer.scan(reader);
        assertEquals( '>', token.getTag());
    }

    /**
     * Tests parsing AND.
     */
    @Test
    void testParseAnd() throws Exception {
        val reader = new LexerReader(new StringReader("&&"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.AND, token.getTag());
    }

    /**
     * Tests parsing OR.
     */
    @Test
    void testParseOr() throws Exception {
        val reader = new LexerReader(new StringReader("||"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.OR, token.getTag());
    }

    /**
     * Tests parsing NE.
     */
    @Test
    void testParseNe() throws Exception {
        val reader = new LexerReader(new StringReader("!="));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.NE, token.getTag());
    }

    /**
     * Tests parsing LE.
     */
    @Test
    void testParseLe() throws Exception {
        val reader = new LexerReader(new StringReader("<="));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.LE, token.getTag());
    }

    /**
     * Tests parsing GE.
     */
    @Test
    void testParseGe() throws Exception {
        val reader = new LexerReader(new StringReader(">="));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.GE, token.getTag());
    }

    /**
     * Tests parsing a integer number.
     */
    @Test
    void testParseNumber() throws Exception {
        val reader = new LexerReader(new StringReader("42"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertTrue(token instanceof Number);
        Number number = (Number) token;

        assertEquals( Tag.NUM, number.getTag());
        assertEquals( 42, number.getValue());
    }

    /**
     * Tests parsing a floating point number.
     */
    @Test
    void testParseReal() throws Exception {
        val reader = new LexerReader(new StringReader("3.14"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertTrue(token instanceof Real);
        Real real = (Real) token;

        assertEquals( Tag.REAL, real.getTag());
        assertEquals( 3.14f, real.getValue(), 0.001);
    }

    /**
     * Tests parsing an ID.
     */
    @Test
    void testParseId() throws Exception {
        val reader = new LexerReader(new StringReader("Joel16 Joel16"));
        val lexer = makeUut();

        var token = lexer.scan(reader);

        assertTrue(token instanceof Word);
        Word word = (Word) token;

        assertEquals( Tag.ID, word.getTag());
        assertEquals( "Joel16", word.getLexeme());

        // and again...
        token = lexer.scan(reader);
        word = (Word) token;

        assertEquals( Tag.ID, word.getTag());
        assertEquals( "Joel16", word.getLexeme());
    }

    /**
     * Tests parsing character.
     */
    @Test
    void testParseCharacter() throws Exception {
        val reader = new LexerReader(new StringReader("\n"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( 65535, token.getTag());
    }

    private Lexer makeUut() {
        return new Lexer();
    }
}
