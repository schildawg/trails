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
package org.trails.utils

/**
 * Adds extension for String to split by camel case.
 */
fun String.splitByCamelCase() = StringUtils.splitByCamelCase(this)

/**
 * Adds extension for String to convert to comment text
 */
fun String.toCommentText() = StringUtils.toCommentText(this)

/**
 * Adds extension for String to convert to camel case
 */
fun String.toCamelCase() = StringUtils.toCamelCase(this)

/**
 * Adds extension for String to convert to pascal case
 */
fun String.toPascalCase() = StringUtils.toPascalCase(this)