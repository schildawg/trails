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
package org.trails.lexer

import org.trails.symbol.Type
import java.io.IOException
import java.lang.StringBuilder

/**
 * Converts sequence of characters from Reader into a sequence of TRails tokens.
 *
 * @author  Joel Schilling
 * @version 1.0, 01/12/2021
 * @since   1.0
 */
class Lexer {
    private val words : MutableMap<String, Word> = HashMap()

    /**
     * Scans the reader for the next valid TRails token.
     *
     * @param reader the reader.
     * @throws IOException if problems reading from reader.
     */
    @Throws(IOException::class)
    fun scan(reader : LexerReader): Token {
        reader.skipWhitespace()

        when (reader.peek) {
            '&' -> return if (reader.read('&')) Word.and else Token('&')
            '|' -> return if (reader.read('|')) Word.or  else Token('|')
            '=' -> return if (reader.read('=')) Word.eq  else Token('=')
            '!' -> return if (reader.read('=')) Word.ne  else Token('!')
            '<' -> return if (reader.read('=')) Word.le  else Token('<')
            '>' -> return if (reader.read('=')) Word.ge  else Token('>')
        }

        if (Character.isDigit(reader.peek)) {
            var number = 0
            while (Character.isDigit(reader.peek)) {
                number = 10 * number + Character.digit(reader.peek, 10)
                reader.read()
            }
            if (reader.peek != '.') {
                return Number(number)
            }
            var floatingNumber = number.toFloat()
            var divideBy = 10f
            while (true) {
                reader.read()
                if (!Character.isDigit(reader.peek)) {
                    break
                }
                floatingNumber += Character.digit(reader.peek, 10) / divideBy
                divideBy *= 10
            }
            return Real(floatingNumber)
        }

        if (Character.isLetter(reader.peek)) {
            val sb = StringBuilder()
            do {
                sb.append(reader.peek)
                reader.read()
            }
            while (Character.isLetterOrDigit(reader.peek))

            val name = sb.toString()
            var word = words[sb.toString()]
            if (word != null) {
                return word
            }
            word = Word(name, Tag.ID)
            words[name] = word
            return word
        }
        val token = Token(reader.peek)
        reader.clearPeek()

        return token
    }

    private fun reserve(word: Word) {
        words[word.lexeme] = word
    }

    init {
        reserve(Word("if", Tag.IF))
        reserve(Word("else", Tag.ELSE))
        reserve(Word("while", Tag.WHILE))
        reserve(Word("do", Tag.DO))
        reserve(Word("break", Tag.BREAK))

        reserve(Word("module", Tag.MODULE))
        reserve(Word("entity", Tag.ENTITY))

        reserve(Word.True)
        reserve(Word.False)
        reserve(Type.Int)
        reserve(Type.Char)
        reserve(Type.Bool)
        reserve(Type.Float)
        reserve(Type.String)
    }
}
