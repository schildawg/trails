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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.trails.symbol.Type;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies {@link Entity}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/23/2021
 * @since   1.0
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EntityTest {
    /**
     * Tests the constructor.
     */
    @Test
    void testConstructor() {
        val entity = new Entity("Book", Entity.Type.Entity, "book", new HashSet<>());

        assertEquals("Book", entity.getName());
        assertEquals("book", entity.getModule());
        assertEquals(Entity.Type.Entity, entity.getType());
    }

    /**
     * Tests the builder.
     */
    @Test
    void testBuilder() {
        val fields = new HashSet<Field>();
        fields.add(new Field("isbn", Type.String));

        val entity = new Entity.Builder().
            name("Book").
            module("book").
            type(Entity.Type.Entity).
            fields(fields).
            build();

        assertEquals("Book", entity.getName());
        assertEquals("book", entity.getModule());
        assertEquals(Entity.Type.Entity, entity.getType());
        assertTrue(entity.getFields().contains(new Field("isbn", Type.String)));
    }

    /**
     * Tests the builder with a null value throws IllegalArgumentException.
     */
    @Test
    void testBuilderNullValue() {
         Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Entity.Builder().
                name(null).
                module("book").
                type(Entity.Type.Entity).
                build();
        });
    }

    /**
     * Tests toString()
     */
    @Test
    void testToString() {
        val entity = new Entity("Book", Entity.Type.Entity, "book", new HashSet<>());

        assertEquals("Entity(name=Book, type=Entity, module=book, fields=[])", entity.toString());
    }

    /**
     * Tests the equals/hashcode contract has been satisfied.
     */
    @Test
    void testEqualsHashCode() {
        EqualsVerifier.forClass(Entity.class).usingGetClass().verify();
    }

    /**
     * Tests entity visitor.
     */
    @Test
    void testVisitor() {
        EntityVisitor visitor = Mockito.mock(EntityVisitor.class);

        val field1 = new Field("field1", Type.Int);
        val field2 = new Field("field2", Type.String);

        val entity = new Entity("Book", Entity.Type.Entity, "book",
            new HashSet<>(Arrays.asList(field1, field2)));

        entity.accept(visitor);

        Mockito.verify(visitor).visitEntity(entity);
        Mockito.verify(visitor).visitField(field1);
        Mockito.verify(visitor).visitField(field2);
    }
}