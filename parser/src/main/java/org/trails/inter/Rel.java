package org.trails.inter;

import org.trails.lexer.Token;
import org.trails.symbol.Array;
import org.trails.symbol.Type;

public class Rel extends Logical {
    public Rel(Token tok, Expr xl, Expr x2) {
        super(tok, xl, x2);
    }

    @Override
    public Type check(Type pi, Type p2) {
        if (pi instanceof Array || p2 instanceof Array) {
            return null;
        } else if (pi == p2) {
            return Type.Bool;
        } else {
            return null;
        }
    }

    @Override
    public void jumping(int t, int f) {
        Expr a = exprl.reduce();
        Expr b = expr2.reduce();

        String test = a.toString() + " " + op.toString() + " " + b.toString();
        emitjumps(test, t, f);
    }
}
