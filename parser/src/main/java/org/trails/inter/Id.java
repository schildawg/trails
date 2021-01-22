package org.trails.inter;

import org.trails.lexer.Word;
import org.trails.symbol.Type;

public class Id extends Expr {

    public int offset; // relative address

    public Id(Word id, Type p, int b) {
        super(id, p);
        offset = b;
    }
}
