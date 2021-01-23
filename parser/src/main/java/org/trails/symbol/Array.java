package org.trails.symbol;

import org.trails.lexer.Tag;

public class Array extends Type {
    public Type of; // array *of* type
    public int size = 1; // number of elements

    public Array(int sz, Type p) {
        super("[]", Tag.INDEX);
        size = sz;
        of = p;
    }

    @Override
    public String toString() {
        return " [" + size + "] " + of.toString();
    }
}

