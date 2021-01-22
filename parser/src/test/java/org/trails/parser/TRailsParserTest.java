package org.trails.parser;

import junit.framework.TestCase;
import lombok.val;
import org.trails.lexer.Lexer;
import org.trails.lexer.LexerReader;
import org.trails.lexer.Number;

import java.io.FileReader;

/**
 * Verifies {@link TRailsParser}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/14/2021
 * @since   1.0
 */
public class TRailsParserTest extends TestCase {
    /**
     * Tests parse.
     */
    public void testParse() throws Exception {
       val lexer = new Lexer();
       val reader = new LexerReader(new FileReader("src/test/resources/org/trails/parser/Book.trails"));

       val parser = new TRailsParser(lexer, reader);

       parser.program();

       assertEquals("org.schildawg.book", parser.getModule());
       assertEquals("Book", parser.getEntity());

       val fields = parser.getFields();

       assertTrue(fields.contains("field1"));
       assertTrue(fields.contains("field2"));
    }
}