package org.trails.symbol;

import org.trails.lexer.Tag;
import org.trails.lexer.Word;

public class Type extends Word {
    public int width = 0; // width is used for storage allocation

    public Type(String s, int tag, int w) {
        super(s, tag);
        width = w;
    }
    public static final Type Int = new Type("Integer", Tag.BASIC, 4),
            Float = new Type("float", Tag.BASIC, 8),
            Char = new Type("char", Tag.BASIC, 1),
            Bool = new Type("bool", Tag.BASIC, 1),
            String = new Type("String", Tag.BASIC, 1);

//Functions numeric (lines 11-14) and max (lines 15-20) are useful for type
//conversions.
    public static boolean numeric(Type p) {
        if (p == Type.Char || p == Type.Int || p == Type.Float) {
            return true;
        } else {
            return false;
        }
    }

    public static Type max(Type pi, Type p2) {
        if (!numeric(pi) || !numeric(p2)) {
            return null;
        } else if (pi == Type.Float || p2 == Type.Float) {
            return Type.Float;
        } else if (pi == Type.Int || p2 == Type.Int) {
            return Type.Int;
        } else {
            return Type.Char;
        }
    }
}
