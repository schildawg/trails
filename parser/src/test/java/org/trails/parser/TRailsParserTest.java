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

import org.junit.jupiter.api.Test;
import lombok.val;
import org.trails.lexer.Lexer;
import org.trails.lexer.LexerReader;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

       parser.program();

       assertEquals("org.schildawg.book", parser.getModule());
       assertEquals("Book", parser.getEntity());

       val fields = parser.getFields();

       assertTrue(fields.contains("field1"));
       assertTrue(fields.contains("field2"));
    }
}