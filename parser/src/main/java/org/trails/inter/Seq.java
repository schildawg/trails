package org.trails.inter;

public class Seq extends Stmt {

    Stmt stmti;
    Stmt stmt2;

    public Seq(Stmt si, Stmt s2) {
        stmti = si;
        stmt2 = s2;
    }

    public void gen(int b, int a) {
        if (stmti == Stmt.Null) {
            stmt2.gen(b, a);
        } else if (stmt2 == Stmt.Null) {
            stmti.gen(b, a);
        } else {
            int label = newlabel();
            stmti.gen(b, label);
            emitlabel(label);
            stmt2.gen(label, a);
        }
    }
}
