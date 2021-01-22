package org.trails.inter;

import org.trails.lexer.Word;
import org.trails.symbol.Type;

/**
 *
 * @author jschilling
 */
public class Temp extends Expr {
    static int count = 0;
    int number = 0;

    public Temp(Type p) {
        super(Word.temp, p);
        number = ++count;
    }

    @Override
    public String toString() {
        return "t" + number;
    }
}
