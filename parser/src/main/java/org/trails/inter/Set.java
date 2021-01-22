/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trails.inter;

import org.trails.symbol.Type;

public class Set extends Stmt {

    public Id id;
    public Expr expr;

    public Set(Id i, Expr x) {
        id = i;
        expr = x;
        if (check(id.type, expr.type) == null) {
            error("type error");
        }
    }

    public Type check(Type pi, Type p2) {
        if (Type.numeric(pi) && Type.numeric(p2)) {
            return p2;
        } else if (pi == Type.Bool && p2 == Type.Bool) {
            return p2;
        } else {
            return null;
        }
    }

    public void gen(int b, int a) {
        emit(id.toString() + " = " + expr.gen().toString());
    }
}
