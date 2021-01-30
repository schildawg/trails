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
package org.trails.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Writer;

/**
 * Base class for Generators which emit with no consideration for existing content.
 *
 * @author  Joel Schilling
 * @version 1.0, 01/30/2021
 * @since   1.0
 */
public class SimpleGenerator extends Generator {
    private Writer writer;
    private int indent = 0;

    /**
     * Constructor.
     *
     * @param writer the writer.
     */
    protected SimpleGenerator(final Writer writer) {
        this.writer = writer;
    }

    /**
     * Writes a line at the current indent.
     *
     * @param string the line to write.
     */
    protected void writeLn(String string) {
        try {
            String indentString = StringUtils.repeat(' ', indent);

            writer.write(indentString + string + "\n");
            writer.flush();
        }
        catch (IOException e) {
            throw new RuntimeException("Error writing file!!!");
        }
    }

    /**
     * Writes a blank line.
     */
    protected void writeLn() {
        writeLn("");
    }

    /**
     * Increases the current indent.
     */
    protected void indent() {
        indent += 4;
    }

    /**
     * Decreases the current indent.
     */
    protected void unindent() {
        indent -= 4;
        if (indent < 0) {
            indent = 0;
        }
    }
}
