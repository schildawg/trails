package org.trails.generator;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.trails.model.Entity;
import org.trails.model.Field;
import org.trails.symbol.Type;

/**
 * Verifies {@link Generator}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/30/2021
 * @since   1.0
 */
class GeneratorTest {
    @Test
    void visitEntity() {
        val entity =
            new Entity.Builder().name("Book").module("book").type(Entity.Type.Entity).build();

        val uut = new Generator();

        // exercise!
        uut.visitEntity(entity);
    }

    @Test
    void visitField() {
        val field = new Field("field1", Type.Int);

        val uut = new Generator();

        // exercise!
        uut.visitField(field);
    }
}