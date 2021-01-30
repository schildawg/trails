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
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies {@link Token}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/13/2021
 * @since   1.0
 */
public class TokenTest {
    /**
     * Tests constructor.
     */
    @Test
    void testConstructor()  {
        val token = new Token(42);

        assertEquals(42, token.getTag());
    }

    /**
     * Tests character constructor.
     */
    @Test
    void testConstructor2() {
        val token = new Token('%');

        assertEquals('%', token.getTag());
    }

    /**
     * Tests toString().
     */
    @Test
    void testToString()  {
        val token = new Token(42);

        assertEquals("*", token.toString());
    }

    /**
     * Tests the equals/hashcode contract has been satisfied.
     */
    @Test
    public void testEqualsHashCode() {
        EqualsVerifier.forClass(Token.class).withOnlyTheseFields("tag").usingGetClass().verify();
    }
}
