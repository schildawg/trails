package org.trails.inter;

import org.trails.symbol.Type;

public class Else extends Stmt {

    Expr expr;
    Stmt stmti, stmt2;

    public Else(Expr x, Stmt si, Stmt s2) {
        expr = x;
        stmti = si;
        stmt2 = s2;
        if (expr.type != Type.Bool) {
            expr.error("boolean required in if");
        }
    }

    @Override
    public void gen(int b, int a) {
        int labell = newlabel(); // labell for stmti
        int label2 = newlabel(); // label2 for stmt2
        expr.jumping(0, label2); // fall through to stmti on true
        emitlabel(labell);
        stmti.gen(labell, a);
        emit("goto L" + a);
        emitlabel(label2);
        stmt2.gen(label2, a);
    }
}
