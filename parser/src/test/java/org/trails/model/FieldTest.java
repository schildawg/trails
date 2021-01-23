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
package org.trails.model;

import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.trails.symbol.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies {@link Field}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/23/2021
 * @since   1.0
 */
class FieldTest {
    /**
     * Tests the constructor.
     */
    @Test
    void testConstructor() {
        val field = new Field("numberOfBooks", Type.Int);

        assertEquals("numberOfBooks", field.getName());
        assertEquals(Type.Int, field.getType());
    }

    /**
     * Tests toString()
     */
    @Test
    void testToString() {
        val field = new Field("numberOfBooks", Type.Int);

        assertEquals("Field(name=numberOfBooks, type=Integer)", field.toString());
    }


    /**
     * Tests the equals/hashcode contract has been satisfied.
     */
    @Test
    public void testEqualsHashCode() {
        EqualsVerifier.forClass(Field.class).usingGetClass().verify();
    }
}