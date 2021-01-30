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
package org.trails.inter

import org.trails.symbol.Type
import org.trails.lexer.Token

/**
 * Expression base class.
 *
 * @param token the token.
 * @param type the type.
 */
open class Expr(val token : Token, val type : Type) : Node {
    /**
     * Function to generate expression.
     */
    fun gen(): Expr {
        return this
    }

    /**
     * Function to reduce the expression.
     */
    fun reduce(): Expr {
        return this
    }

    override fun toString(): String = token.toString()
}