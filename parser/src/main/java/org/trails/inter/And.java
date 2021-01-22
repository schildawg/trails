package org.trails.inter;

import org.trails.lexer.Token;

public class And extends Logical {

    public And(Token tok, Expr xl, Expr x2) {
        super(tok, xl, x2);
    }

    @Override
    public void jumping(int t, int f) {
        int label = f != 0 ? f : newlabel();
        exprl.jumping(0, label);
        expr2.jumping(t, f);
        if (f == 0) {
            emitlabel(label);
        }
    }
}
