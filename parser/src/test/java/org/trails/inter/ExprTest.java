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
package org.trails.inter;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.trails.lexer.Tag;
import org.trails.lexer.Word;
import org.trails.symbol.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies {@link Expr}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/23/2021
 * @since   1.0
 */
public class ExprTest {
    /**
     * Tests constructor.
     */
    @Test
    void testConstructor() {
        val id = new Expr(new Word("abc", Tag.ID), Type.Int);

        assertEquals(new Word("abc", Tag.ID), id.getToken());
        assertEquals(Type.Int, id.getType());
    }

    /**
     * Exercise the reduce method.
     */
    @Test

    void testReduce() {
        val id = new Expr(new Word("abc", Tag.ID), Type.Int);

        assertEquals(id, id.reduce());
    }

    /**
     * Exercise the gen method.
     */
    @Test
    void testGen() {
        val id = new Expr(new Word("abc", Tag.ID), Type.Int);

        assertEquals(id, id.gen());
    }
}
