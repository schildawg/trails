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
package org.trails.parser;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.trails.lexer.Lexer;
import org.trails.lexer.LexerReader;
import org.trails.model.Field;
import org.trails.symbol.Type;

import java.io.FileReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Verifies {@link TRailsParser}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/14/2021
 * @since   1.0
 */
public class TRailsParserTest {
    /**
     * Tests parse.
     */
    @Test
    void testParse() throws Exception {
       val lexer = new Lexer();
       val reader = new LexerReader(new FileReader("src/test/resources/org/trails/parser/Book.trails"));

       val parser = new TRailsParser(lexer, reader);

       val entity = parser.program();

       assertEquals("org.schildawg.book", entity.getModule());
       assertEquals("Book", entity.getName());

       val fields = entity.getFields();

       assertTrue(fields.contains(new Field("field1", Type.String)));
       assertTrue(fields.contains(new Field("field2", Type.Int)));
    }

    /**
     * Tests parsing error.
     */
    @Test
    void testParseError()  {
        val lexer = new Lexer();
        val reader = new LexerReader(new StringReader("bad input.."));

        val parser = new TRailsParser(lexer, reader);

        Assertions.assertThrows(Error.class, () -> {
            try {
                parser.program();
            }
            catch (Error e) {
                assertEquals("syntax error", e.getMessage());
                throw e;
            }
            fail("Expected syntax error is thrown.");
        });
    }
}