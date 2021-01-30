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
package org.trails.generator

import org.trails.model.Entity
import org.trails.symbol.Type
import org.trails.utils.toCommentText
import org.trails.utils.toPascalCase
import java.io.FileWriter
import java.io.IOException
import java.io.Writer

/**
 * JavaBean TRails Generator.
 *
 * @author  Joel Schilling
 * @version 1.0, 01/30/2021
 * @since   1.0
 */
class JavaBeanGenerator private constructor(writer : Writer) : SimpleGenerator(writer) {
    override fun visitEntity(entity: Entity) {
        // package
        writeLn("package org.schildawg.${entity.module};")
        writeLn()

        // imports
        writeLn("import java.io.Serializable;")
        writeLn()

        // class
        writeLn("/**")
        writeLn(" * ${entity.name} entity.")
        writeLn(" */")
        writeLn("public class ${entity.name} implements Serializable {")
        indent()

        // fields
        writeLn("private static final long serialVersionUID = 1L;")
        writeLn()
        for ((name, type) in entity.fields) {
            writeLn("private " + makeType(type) + " $name;")
        }
        writeLn()

        // constructor
        writeLn("/**")
        writeLn("* No-arg constructor.")
        writeLn("*/")
        writeLn("public ${entity.name}() {")
        writeLn("}")

        // setters and getters
        for ((name, type) in entity.fields) {
            writeLn()
            writeLn("/**")
            writeLn("* Sets ${name.toCommentText()}.")
            writeLn("*/")
            writeLn("public void set${name.toPascalCase()}(final " + makeType(type) + " $name) {")
            indent()
            writeLn("this.$name = $name;")
            unindent()
            writeLn("}")
            writeLn()
            writeLn("/**")
            writeLn("* Gets ${name.toCommentText()}.")
            writeLn("*/")
            writeLn("public " + makeType(type) + " " + makeGet(type) + "${name.toPascalCase()}() {")
            indent()
            writeLn("return $name;")
            unindent()
            writeLn("}")
        }
        unindent()
        writeLn("}")
    }

    private fun makeType(type: Type): String {
        return if (type === Type.Bool) "boolean" else type.toString()
    }

    private fun makeGet(type: Type): String {
        return if (type === Type.Bool) "is" else "get"
    }

    companion object {
        /**
         * Creates a new JavaBeanGenerator with a FileWriter named after entity.
         *
         * @param entity the entity.
         * @throws IOException if fails to create FileWriter
         */
        @JvmStatic
        @Throws(IOException::class)
        fun create(entity : Entity): JavaBeanGenerator {
            return JavaBeanGenerator(FileWriter(entity.name + ".java"))
        }

        /**
         * Creates a new JavaBeanGenerator with an override Writer.
         *
         * @param writer the override writer.
         */
        @JvmStatic
        fun create(writer: Writer): JavaBeanGenerator {
            return JavaBeanGenerator(writer)
        }
    }
}