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
package org.trails.utils;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Verifies {@link StringUtils}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/25/2021
 * @since   1.0
 */
class StringUtilsTest {
    /**
     * Tests splitting by camel case.
     */
    @Test
    void testSplitByCamelCase() {
        val split = StringUtils.splitByCamelCase("numberOfBooks");

        assertEquals(3, split.size());
        assertEquals("number", split.get(0));
        assertEquals("Of", split.get(1));
        assertEquals("Books", split.get(2));
    }

    /**
     * Tests splitting by camel case with numbers.
     */
    @Test
    void testSplitByCamelCaseWithNumbers() {
        val split = StringUtils.splitByCamelCase("ThisIsNumber42");

        assertEquals(4, split.size());
        assertEquals("This", split.get(0));
        assertEquals("Is", split.get(1));
        assertEquals("Number", split.get(2));
        assertEquals("42", split.get(3));
    }

    /**
     * Tests splitting by camel case with groupings of uppercase.
     */
    @Test
    void testSplitByCamelCaseWithUppercaseGroupings() {
        val split = StringUtils.splitByCamelCase("PHProtocol");

        assertEquals(2, split.size());
        assertEquals("PH", split.get(0));
        assertEquals("Protocol", split.get(1));
    }

    /**
     * Tests splitting by camel case for null.
     */
    @Test
    void testSplitByCamelCaseForNull() {
        val split = StringUtils.splitByCamelCase(null);

        assertTrue(split.isEmpty());
    }

    /**
     * Tests splitting by camel case for blank.
     */
    @Test
    void testSplitByCamelCaseForBlank() {
        val split = StringUtils.splitByCamelCase("");

        assertTrue(split.isEmpty());
    }

    /**
     * Tests converting to comment text
     */
    @Test
    void testConvertToCommentText() {
        val returnValue = StringUtils.toCommentText("numberOfBooks");

        assertEquals("number of books", returnValue);
    }

    /**
     * Tests converting to comment text null
     */
    @Test
    void testConvertToCommentTextNull() {
        val returnValue = StringUtils.toCommentText(null);

        assertNull(returnValue);
    }

    /**
     * Tests converting to comment text blank
     */
    @Test
    void testConvertToCommentTextBlank() {
        val returnValue = StringUtils.toCommentText("");

        assertEquals("", returnValue);
    }

    /**
     * Tests converting to pascal case
     */
    @Test
    void testConvertToPascalCase() {
        val returnValue = StringUtils.toPascalCase("stringTokenizer");

        assertEquals("StringTokenizer", returnValue);
    }

    /**
     * Tests converting to pascal case null returns null
     */
    @Test
    void testConvertToPascalCaseNull() {
        val returnValue = StringUtils.toPascalCase(null);

        assertNull(returnValue);
    }

    /**
     * Tests converting to pascal case blank
     */
    @Test
    void testConvertToPascalCaseBlank() {
        val returnValue = StringUtils.toPascalCase("");

        assertEquals("", returnValue);
    }

    /**
     * Tests converting to camel case
     */
    @Test
    void testConvertToCamelCase() {
        val returnValue = StringUtils.toCamelCase("NumberOfBooks");

        assertEquals("numberOfBooks", returnValue);
    }

    /**
     * Tests converting to camel case null returns null
     */
    @Test
    void testConvertToCamelCaseNull() {
        val returnValue = StringUtils.toCamelCase(null);

        assertNull(returnValue);
    }

    /**
     * Tests converting to camel case blank
     */
    @Test
    void testConvertToCamelCaseBlank() {
        val returnValue = StringUtils.toCamelCase("");

        assertEquals("", returnValue);
    }
}
