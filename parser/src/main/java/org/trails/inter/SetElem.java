package org.trails.inter;

import org.trails.symbol.Array;
import org.trails.symbol.Type;

public class SetElem extends Stmt {

    public Id array;
    public Expr index;
    public Expr expr;

    public SetElem(Access x, Expr y) {
        array = x.array;
        index = x.index;
        expr = y;
        if (check(x.type, expr.type) == null) {
            error("type error");
        }
    }

    public Type check(Type pi, Type p2) {
        if (pi instanceof Array || p2 instanceof Array) {
            return null;
        } else if (pi == p2) {
            return p2;
        } else if (Type.numeric(pi) && Type.numeric(p2)) {
            return p2;
        } else {
            return null;
        }
    }

    @Override
    public void gen(int b, int a) {
        String si = index.reduce().toString();
        String s2 = expr.reduce().toString();
        emit(array.toString() + " [ " + si + " ] = " + s2);
    }
}
