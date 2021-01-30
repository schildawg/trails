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
import org.trails.model.Entity;
import org.trails.model.Field;
import org.trails.symbol.Type;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Verifies {@link JavaBeanGenerator}
 *
 * @author  Joel Schilling
 * @version 1.0, 01/30/2021
 * @since   1.0
 */
class JavaBeanGeneratorTest {
    /**
     * Tests generating using a PrintWriter
     */
    @Test
    void testGenerate() throws Exception {
        // Uncomment for first run, and visually scan!
        // generateGoldMaster();

        val entity = makeEntity();
        val writer = new StringWriter();

        val uut = JavaBeanGenerator.create(writer);
        entity.accept(uut);

        val goldMaster = readGoldMaster();

        assertEquals(goldMaster, writer.toString());
    }

    /**
     * Exercises static factory method using entity, that creates a FileWriter.
     */
    @Test
    void testCreate() throws Exception {
        JavaBeanGenerator.create(makeEntity());
    }

    /**
     * Generates a Gold Master File.
     */
    private void generateGoldMaster()  {
        try {
            val goldMaster = new FileWriter("src/test/resources/org/trails/generator/java-bean-goldmaster.txt");

            val entity = makeEntity();
            entity.accept(JavaBeanGenerator.create(goldMaster));

            goldMaster.close();
            fail("Writing Gold Master file.");
        }
        catch (IOException e) {
            fail("Error generating Gold Master file.");
        }
    }

    /**
     * Reads Gold Master File.
     */
    private String readGoldMaster() throws Exception  {
        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/org/trails/generator/java-bean-goldmaster.txt"));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    private Entity makeEntity() {
        val field1 = new Field("author", Type.String);
        val field2 = new Field("numberOfPages", Type.Int);
        val field3 = new Field("active", Type.Bool);

        return new Entity.Builder().
            name("Book").
            module("book").
            fields(new HashSet<>(Arrays.asList(field1, field2, field3))).
            type(Entity.Type.Entity).
            build();
    }
}