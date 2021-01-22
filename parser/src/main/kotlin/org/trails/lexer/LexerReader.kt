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

import java.io.IOException
import java.io.Reader

/**
 * Wrapper for Reader to keep track of the "peek" value, and the current line.
 *
 * @param reader the reader.
 * @author  Joel Schilling
 * @version 1.0, 01/12/2021
 * @since   1.0
 */
class LexerReader(private val reader : Reader) {
    var line = 1
        private set
    var peek = ' '

    /**
     * Reads a character, and sets the peek.  If character is carriage return, increments line.
     *
     * @throws IOException if problems reading from reader.
     */
    @Throws(IOException::class)
    fun read() {
        peek = reader.read().toChar()
        if (peek == '\n') {
            line += 1
        }
    }

    /**
     * Reads a character, with expected result.  If not the expected result, returns false, and sets the peek to
     * a blank.
     *
     * @param c expected character.\
     * @return true if the character matches;  false otherwise.
     * @throws IOException if problems reading from reader.
     */
    @Throws(IOException::class)
    fun read(c: Char): Boolean {
        read()
        if (peek != c) {
            return false
        }
        peek = ' '
        return true
    }

    /**
     * Skips blanks, tabs, and carriage returns, reading next available character.
     */
    fun skipWhitespace() {
        while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
            read()
        }
    }

    /**
     * Sets the peek to blank.
     */
    fun clearPeek() {
        peek = ' '
    }
}