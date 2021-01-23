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
 * Defines the tags for Lexer.  Starts at 256 to allow single characters such as '[' to be matched.
 *
 * @author  Joel Schilling
 * @version 1.0, 01/12/2021
 * @since   1.0
 */
object Tag {
    const val AND = 256
    const val BASIC = 257
    const val BREAK = 258
    const val DO = 259
    const val ELSE = 260
    const val EQ = 261
    const val FALSE = 262
    const val GE = 263
    const val ID = 264
    const val IF = 265
    const val INDEX = 266
    const val LE = 267
    const val MINUS = 268
    const val NE = 269
    const val NUM = 270
    const val OR = 271
    const val REAL = 272
    const val TEMP = 273
    const val TRUE = 274
    const val WHILE = 275

    const val MODULE = 276;
    const val ENTITY = 277;
}
