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

    const val PACKAGE = 276;
    const val ENTITY = 277;
}
