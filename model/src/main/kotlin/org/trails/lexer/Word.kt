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

/**
 * Word token.  Contains simple Word constants.
 *
 * @param lexeme the lexeme.
 * param tag the tag.
 * @author  Joel Schilling
 * @version 1.0, 01/12/2021
 * @since   1.0
 */
open class Word(val lexeme : String, tag : Int) : Token(tag) {
    override fun toString(): String = lexeme

    companion object {
        @JvmField val and = Word("&&", Tag.AND)
        @JvmField val or  = Word("||", Tag.OR)
        @JvmField val eq  = Word("==", Tag.EQ)
        @JvmField val ne  = Word("!=", Tag.NE)
        @JvmField val le  = Word("<=", Tag.LE)
        @JvmField val ge =  Word(">=", Tag.GE)
        @JvmField val minus = Word("minus", Tag.MINUS)
        @JvmField val True =  Word("true",  Tag.TRUE)
        @JvmField val False = Word("false", Tag.FALSE)
        @JvmField val temp =  Word("t",     Tag.TEMP)
    }
}