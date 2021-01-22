/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trails.inter;

import org.trails.lexer.Token;
import org.trails.symbol.Type;

public class Op extends Expr {

    public Op(Token tok, Type p) {
        super(tok, p);
    }

    @Override
    public Expr reduce() {
        Expr x = gen();
        Temp t = new Temp(type);
        emit(t.toString() + " = " + x.toString());
        return t;
    }
}
