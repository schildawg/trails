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
 * Token base class.
 *
 * @param tag the tag.
 * @author  Joel Schilling
 * @version 1.0, 01/12/2021
 * @since   1.0
 */
public open class Token(val tag: Int) {
    /**
     * Constructor using characters.
     * @param tag the character tag.
     */
    constructor(tag : Char) : this(tag.toInt());


    override fun toString() : String  = "" + tag.toChar()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Token
        if (tag != other.tag) return false

        return true
    }

    override fun hashCode(): Int {
        return tag
    }
}