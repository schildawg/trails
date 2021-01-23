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
package org.trails.model

/**
 * Entity model.
 *
 * @param name name of the entity.
 * @param type the type of the entity; i.e. Entity, Value, or Service.
 * @param module the module entity belongs to.
 * @author  Joel Schilling
 * @version 1.0, 01/23/2021
 * @since   1.0
 */
data class Entity(val name : String, val type : Type, val module : String, val fields : Set<Field>) {
    /**
     * Entity types.
     */
    enum class Type { Entity, Value, Service }

    /**
     * Entity model builder.
     */
    class Builder {
        private var name : String? = null
        private var type : Type? = null
        private var module : String? = null
        private var fields: Set<Field> = HashSet()

        /**
         * Sets the name.
         */
        fun name(name : String) = apply { this.name = name }

        /**
         * Sets the type.
         */
        fun type(type : Type) = apply { this.type = type }

        /**
         * Sets the module.
         */
        fun module(module : String) = apply { this.module = module }

        /**
         * Sets the fields.
         */
        fun fields(fields : Set<Field>) = apply { this.fields = fields }

        /**
         * Builds the Entity.
         */
        fun build() = Entity(name!!, type!!, module!!, fields)
    }
}