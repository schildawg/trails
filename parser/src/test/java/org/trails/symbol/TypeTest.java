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
package org.trails.symbol;

import org.junit.jupiter.api.Test;
import org.trails.lexer.Tag;
import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Verifies {@link Type}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/22/2021
 * @since   1.0
 */
public class TypeTest {
    /**
     * Tests constructor.
     */
    @Test
    void testConstructor() throws Exception {
       val type = new Type("Widget", Tag.BASIC);

       assertEquals("Widget", type.getLexeme());
       assertEquals(Tag.BASIC, type.getTag());
    }

    /**
     * Tests Integer.
     */
    @Test
    void testInteger()  {
        assertEquals("Integer", Type.Int.getLexeme());
        assertEquals(Tag.BASIC, Type.Int.getTag());

        assertTrue(Type.Companion.numeric(Type.Int));
    }

    /**
     * Tests Float.
     */
    @Test
    void testFloat()  {
        assertEquals("float", Type.Float.getLexeme());
        assertEquals(Tag.BASIC, Type.Float.getTag());

        assertTrue(Type.Companion.numeric(Type.Float));
    }

    /**
     * Tests Char.
     */
    @Test
    void testChar()  {
        assertEquals("char", Type.Char.getLexeme());
        assertEquals(Tag.BASIC, Type.Char.getTag());

        assertTrue(Type.Companion.numeric(Type.Char));
    }

    /**
     * Tests Bool.
     */
    @Test
    void testBool()  {
        assertEquals("bool", Type.Bool.getLexeme());
        assertEquals(Tag.BASIC, Type.Bool.getTag());

        assertFalse(Type.Companion.numeric(Type.Bool));
    }

    /**
     * Tests String.
     */
    @Test
    void testString()  {
        assertEquals("String", Type.String.getLexeme());
        assertEquals(Tag.BASIC, Type.String.getTag());

        assertFalse(Type.Companion.numeric(Type.String));
    }

    /**
     * Asserts null is returned for non-numeric types.
     */
    @Test
    void testMaxNullNonNumeric() {
        assertNull(Type.Companion.max(Type.String, Type.Int));
        assertNull(Type.Companion.max(Type.Int, Type.String));
        assertNull(Type.Companion.max(Type.String, Type.String));
    }

    /**
     * Asserts float is bigger than int.
     */
    @Test
    void testMaxFloat() {
        assertEquals(Type.Float, Type.Companion.max(Type.Float, Type.Int));
        assertEquals(Type.Float, Type.Companion.max(Type.Int, Type.Float));
    }

    /**
     * Asserts int is bigger than char.
     */
    @Test
    void testMaxInt() {
        assertEquals(Type.Int, Type.Companion.max(Type.Char, Type.Int));
        assertEquals(Type.Int, Type.Companion.max(Type.Int, Type.Char));
    }

    /**
     * Asserts char is bigger than ?.
     */
    @Test
    void testMaxChar() {
        // TODO: code not testable...
    }
}
