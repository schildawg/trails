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
import org.trails.lexer.Tag
import org.trails.lexer.Token
import org.trails.lexer.Word
import org.trails.symbol.Env

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
    private var look : Token? = null // lookahead tagen
    private var matched : Token? = null

    private val top: Env? = null // current or top symbol table

    var module : String? = null
    var entity : String? = null
    val fields : MutableSet<String> = HashSet()

    /**
     * Parses program.
     */
    @Throws(IOException::class)
    fun program() {
        module()
        entity()
    }

    private fun module() {
        match(Tag.PACKAGE)
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
        module = sb.toString()
    }

    private fun entity() {
        match(Tag.ENTITY)
        match(Tag.ID)
        val word = matched as Word
        entity = word.lexeme
        match('{')
        fields()
        match('}')
    }

    private fun fields()  {
        while (look!!.tag == Tag.ID) {
            match(Tag.ID)
            val word = matched as Word
            fields.add(word.lexeme)

            match(':')
            match(Tag.BASIC, "invalid type: $look")
        }
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