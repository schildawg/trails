package org.trails.inter;

import org.trails.lexer.Token;

public class Or extends Logical {
    public Or(Token tok, Expr xl, Expr x2) {
        super(tok, xl, x2);
    }

    @Override
    public void jumping(int t, int f) {
        int label = t != 0 ? t : newlabel();
        exprl.jumping(label, 0);
        expr2.jumping(t, f);
        if (t == 0) {
            emitlabel(label);
        }
    }
}
