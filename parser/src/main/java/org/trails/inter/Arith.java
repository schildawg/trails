package org.trails.inter;

import org.trails.lexer.Token;
import org.trails.symbol.Type;

public class Arith extends Op {
    public Expr exprl, expr2;

    public Arith(Token tok, Expr xl, Expr x2) {
        super(tok, null);
        exprl = xl;
        expr2 = x2;
        type = Type.max(exprl.type, expr2.type);
        if (type == null) {
            error("type e r r o r ");
        }
    }

    @Override
    public Expr gen() {
        return new Arith(op, exprl.reduce(), expr2.reduce());
    }

    @Override
    public String toString() {
        return exprl.toString() + " " + op.toString() + " " + expr2.toString();
    }
}
