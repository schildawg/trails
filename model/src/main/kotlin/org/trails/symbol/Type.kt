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
package org.trails.symbol

import org.trails.lexer.Tag
import org.trails.lexer.Word

/**
 * Base object for Types.
 */
public open class Type(string : String, tag : Int) : Word(string, tag) {
    companion object {
        @JvmField val Int   =  Type("Integer", Tag.BASIC)
        @JvmField val Float =  Type("float",   Tag.BASIC)
        @JvmField val Char  =  Type("char",    Tag.BASIC)
        @JvmField val Bool  =  Type("bool",    Tag.BASIC)
        @JvmField val String = Type("String",  Tag.BASIC)

        /**
         * Checks if type is numeric.
         *
         * @return true if numeric, false otherwise.
         */
        fun numeric(p: Type) : Boolean {
            return p === Char || p === Int || p === Float
        }

        /**
         * Returns the largest of two types.  Useful for casting.
         */
        fun max(type1 : Type, type2 : Type) : Type? {
            return if (!numeric(type1) || !numeric(type2)) {
                null
            }
            else if (type1 === Float || type2 === Float) {
                Float
            }
            else if (type1 === Int || type2 === Int) {
                Int
            }
            else {
                Char
            }
        }
    }
}