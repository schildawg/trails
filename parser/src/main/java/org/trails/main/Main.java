package org.trails.main;

import lombok.val;
import org.trails.lexer.Lexer;
import org.trails.lexer.LexerReader;
import org.trails.parser.Parser;

import java.io.FileReader;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws Exception {
        val lexer = new Lexer();
        val reader = new LexerReader(new FileReader( Paths.get("parser/src/main/resources/org/trails/main/test").toAbsolutePath().toString()));

        val parse = new Parser(lexer, reader);
        parse.program();
        System.out.write('\n');
    }
}
