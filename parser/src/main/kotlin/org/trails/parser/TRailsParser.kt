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
package org.trails.parser

import java.io.IOException

import org.trails.lexer.Lexer
import org.trails.lexer.LexerReader
import org.trails.model.Entity
import org.trails.model.Field
import org.trails.symbol.Type
import org.trails.lexer.Token
import org.trails.lexer.Word
import org.trails.lexer.Tag

/**
 * Parser for TRails DSL.
 *
 * @param lexer the lexer.
 * @param reader the reader.
 * @author  Joel Schilling
 * @version 1.0, 01/14/2021
 * @since   1.0
 */
class TRailsParser(private val lexer: Lexer, private val reader: LexerReader) {
    private var look : Token? = null        // lookahead tag.
    private var matched : Token? = null     // tag that was matched.

    private val builder = Entity.Builder()

    val fields : MutableSet<Field> = HashSet()

    /**
     * Parses program.
     */
    @Throws(IOException::class)
    fun program() : Entity {
        module()
        entity()

        return builder.build()
    }

    private fun module() {
        match(Tag.MODULE)
        match(Tag.ID)

        val sb = StringBuffer()
        var word = matched as Word
        sb.append(word.lexeme)

        while (look!!.tag.toChar() == '.') {
            move()
            match(Tag.ID)
            word = matched as Word
            sb.append(".").append(word.lexeme)
        }
        builder.module(sb.toString())
    }

    private fun entity() {
        match(Tag.ENTITY)
        match(Tag.ID)
        val word = matched as Word
        builder.type(Entity.Type.Entity).name(word.lexeme)
        match('{')
        fields()
        match('}')
    }

    private fun fields()  {
        val fields = HashSet<Field>()
        while (look!!.tag == Tag.ID) {
            match(Tag.ID)
            val word = matched as Word

            match(':')
            match(Tag.BASIC, "invalid type: $look")
            val type = matched as Type

            fields.add(Field(word.lexeme, type))
        }
        builder.fields(fields);
    }

    @Throws(IOException::class)
    private fun move() {
        look = lexer.scan(reader)
    }

    private fun error(error : String) {
        throw Error("near line ${reader.line} : $error")
    }

    @Throws(IOException::class)
    private fun match(tag : Char) = match(tag.toInt())

    @Throws(IOException::class)
    private fun match(tag : Int, message : String = "syntax error") {
        if (look!!.tag == tag) {
            matched = look
            move()
        }
        else {
            error(message)
        }
    }

    init {
        move()
    }
}