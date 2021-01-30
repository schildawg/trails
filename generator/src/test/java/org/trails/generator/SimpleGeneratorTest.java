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
package org.trails.generator;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies {@link SimpleGenerator}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/30/2021
 * @since   1.0
 */
class SimpleGeneratorTest {
    /**
     * Tests writing a line.
     */
    @Test
    void testWriteLn() {
        val writer = new StringWriter();

        val generator = new SimpleGenerator(writer);

        generator.writeLn("TEST");

        assertEquals("TEST\n", writer.toString());
    }

    /**
     * Tests writing a blank line.
     */
    @Test
    void testWriteLnBlank() {
        val writer = new StringWriter();

        val generator = new SimpleGenerator(writer);

        generator.writeLn();

        assertEquals("\n", writer.toString());
    }

    /**
     * Tests indenting.
     */
    @Test
    void testIndent() {
        val writer = new StringWriter();

        val generator = new SimpleGenerator(writer);

        generator.indent();
        generator.writeLn("TEST");

        assertEquals("    TEST\n", writer.toString());
    }

    /**
     * Tests unindenting.
     */
    @Test
    void testUnindent() {
        val writer = new StringWriter();

        val generator = new SimpleGenerator(writer);

        generator.indent();
        generator.unindent();
        generator.writeLn("TEST");

        assertEquals("TEST\n", writer.toString());
    }

    /**
     * Tests unindenting stops at zero.
     */
    @Test
    void testUnindentStopAtZero() {
        val writer = new StringWriter();

        val generator = new SimpleGenerator(writer);

        generator.indent();
        generator.unindent();
        generator.unindent();
        generator.writeLn("TEST");

        assertEquals("TEST\n", writer.toString());
    }
}
