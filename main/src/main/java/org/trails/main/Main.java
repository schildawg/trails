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
package org.trails.main;

import lombok.val;
import lombok.var;
import org.trails.generator.Generator;
import org.trails.generator.JavaBeanGenerator;
import org.trails.lexer.Lexer;
import org.trails.lexer.LexerReader;
import org.trails.model.Entity;
import org.trails.parser.TRailsParser;

import java.io.FileReader;
import java.io.IOException;

/**
 * Main class.
 *
 * @author  Joel Schilling
 * @version 1.0, 01/24/2021
 * @since   1.0
 */
public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_GRAY = "\u001B[37m";
    private static final String ANSI_WHITE = "\u001B[97m";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: trails Entity[.trails] generator");
            System.exit(0);
        }

        var fileName = args[0];
        if (!fileName.contains(".")) {
            fileName += ".trails";
        }

        val generator = args[1];
        System.out.println();
        System.out.println(ANSI_WHITE + "TRails (c) 2021" + ANSI_RESET);
        System.out.println();
        try {
            val lexer = new Lexer();
            val reader = new LexerReader(new FileReader(fileName));
            val parser = new TRailsParser(lexer, reader);

            try {
                val entity = parser.program();
                val lookup = lookup(generator, entity);
                
                System.out.print(ANSI_GRAY + generator + " (" + fileName.substring(0, fileName.indexOf(".")) + ") ............................................... ");
                System.out.println(ANSI_GREEN + "SUCCESS" + ANSI_RESET);
                entity.accept(lookup);
            }
            catch (Error e) {
                System.out.println(ANSI_RED + "FAIL");
                displayError("Error " + e.getMessage());
            }

        }
        catch (IOException e) {
            displayError("Cannot read file: " + fileName);
        }
    }

    private static void displayError(String message) {
        System.out.println(ANSI_GRAY + "[" + ANSI_RED + "ERROR" + ANSI_GRAY + "] " + message);
    }

    private static Generator lookup(String name, Entity entity) throws IOException {
        if ("java-bean".equals(name)) {
            return JavaBeanGenerator.create(entity);
        }
        displayError("Invalid generator: " + name);
        System.exit(0);

        return null;
    }
}
