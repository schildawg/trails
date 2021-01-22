package org.trails.inter;

import org.trails.symbol.Type;

public class If extends Stmt {

    Expr expr;
    Stmt stmt;

    public If(Expr x, Stmt s) {
        expr = x;
        stmt = s;
        //if (expr.type != Type.Bool) {
        //    expr.error("boolean required in i f ");
        //}
    }

    public void gen(int b, int a) {
        int label = newlabel(); // label for the code for stmt
        expr.jumping(0, a); // f a l l through on t r u e , goto a on false
        emitlabel(label);
        stmt.gen(label, a);
    }
}
