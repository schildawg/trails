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
package org.trails.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Verifies {@link StringExtensions}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/26/2021
 * @since   1.0
 */
internal class StringExtensionsTest {
    /**
     * Tests splitByCamelCase extension
     */
    @Test
    fun splitByCamelCase() {
        val returnValue = "NumberOfBooks".splitByCamelCase()

        assertEquals(3, returnValue.size)
        assertEquals("Number", returnValue[0])
        assertEquals("Of", returnValue[1])
        assertEquals("Books", returnValue[2])
    }

    /**
     * Tests toComment extension
     */
    @Test
    fun toCommentText() {
        val returnValue = "NumberOfBooks".toCommentText()

        assertEquals("number of books", returnValue)
    }

    /**
     * Tests toCamelCase extension
     */
    @Test
    fun toCamelCase() {
        val returnValue = "NumberOfBooks".toCamelCase()

        assertEquals("numberOfBooks", returnValue)
    }

    /**
     * Tests toPascalCase extension
     */
    @Test
    fun toPascalCase() {
        val returnValue = "numberOfBooks".toPascalCase()

        assertEquals("NumberOfBooks", returnValue)
    }
}