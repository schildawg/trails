package org.trails.symbol;

import org.trails.inter.Id;
import org.trails.lexer.Token;

import java.util.HashMap;
import java.util.Map;

public class Env {
    private Map<Token, Id> table;
    protected Env prev;

    public Env(Env n) {
        table = new HashMap<>();
        prev = n;
    }

    public void put(Token token, Id id) {
        table.put(token, id);
    }

    public Id get(Token w) {
        for (Env e = this; e != null; e = e.prev) {
            Id found = e.table.get(w);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
