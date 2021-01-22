/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trails.inter;

public class Break extends Stmt {

    Stmt stmt;

    public Break() {
        if (Stmt.Enclosing == null) {
            error("unenclosed break");
        }
        stmt = Stmt.Enclosing;
    }

    public void gen(int b, int a) {
        emit("goto L" + stmt.after);
    }
}
