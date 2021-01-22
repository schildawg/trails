package org.trails.lexer;

import lombok.val;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Verifies {@link Lexer}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class LexerTest {
    /**
     * Tests parsing operators.
     */
    @Test
    public void testParseOperators() throws Exception {
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
    public void testParseAnd() throws Exception {
        val reader = new LexerReader(new StringReader("&&"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.AND, token.getTag());
    }

    /**
     * Tests parsing OR.
     */
    @Test
    public void testParseOr() throws Exception {
        val reader = new LexerReader(new StringReader("||"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.OR, token.getTag());
    }

    /**
     * Tests parsing NE.
     */
    @Test
    public void testParseNe() throws Exception {
        val reader = new LexerReader(new StringReader("!="));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.NE, token.getTag());
    }

    /**
     * Tests parsing LE.
     */
    @Test
    public void testParseLe() throws Exception {
        val reader = new LexerReader(new StringReader("<="));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.LE, token.getTag());
    }

    /**
     * Tests parsing GE.
     */
    @Test
    public void testParseGe() throws Exception {
        val reader = new LexerReader(new StringReader(">="));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( Tag.GE, token.getTag());
    }

    /**
     * Tests parsing a integer number.
     */
    @Test
    public void testParseNumber() throws Exception {
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
    public void testParseReal() throws Exception {
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
    public void testParseId() throws Exception {
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
    public void testParseCharacter() throws Exception {
        val reader = new LexerReader(new StringReader("\n"));
        val lexer = makeUut();

        val token = lexer.scan(reader);

        assertEquals( 65535, token.getTag());
    }

    private Lexer makeUut() {
        return new Lexer();
    }
}
