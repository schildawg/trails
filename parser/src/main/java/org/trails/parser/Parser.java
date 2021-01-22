package org.trails.parser;

import org.trails.inter.Access;
import org.trails.inter.And;
import org.trails.inter.Arith;
import org.trails.inter.Break;
import org.trails.inter.Constant;
import org.trails.inter.Do;
import org.trails.inter.Else;
import org.trails.inter.Expr;
import org.trails.inter.Id;
import org.trails.inter.If;
import org.trails.inter.Not;
import org.trails.inter.Or;
import org.trails.inter.Rel;
import org.trails.inter.Seq;
import org.trails.inter.Set;
import org.trails.inter.SetElem;
import org.trails.inter.Stmt;
import org.trails.inter.Unary;
import org.trails.inter.While;
import org.trails.lexer.*;

import org.trails.lexer.Number;
import org.trails.symbol.Array;
import org.trails.symbol.Env;
import org.trails.symbol.Type;

import java.io.IOException;
import java.util.HashSet;

public class Parser {
    private LexerReader reader;
    private Lexer lexer;

    private Token look; // lookahead tagen
    Env top = null;     // current or top symbol table
    int used = 0;       // storage used for declarations



    public Parser(Lexer lexer, LexerReader reader) throws IOException {
        this.lexer = lexer;
        this.reader = reader;

        move();
    }

    void move() throws IOException {
        look = lexer.scan(reader);
    }

    void error(String s) {
        throw new Error("near line " + reader.getLine() + ": " + s);
    }

    void match(int t) throws IOException {
        if (look.getTag() == t) {
            move();
        } else {
            error("syntax error");
        }
        while (look.getTag() == '.') {
            move();
        }
    }

    public void program() throws IOException { // program -> block
        Stmt s = block();
        int begin = s.newlabel();
        int after = s.newlabel();
        s.emitlabel(begin);
        s.gen(begin, after);
        s.emitlabel(after);


    }

    Stmt block() throws IOException { // block -> { decls stmts }
        match('{');
        Env savedEnv = top;
        top = new Env(top);
        decls();
        Stmt s = stmts();
        match('}');
        top = savedEnv;
        return s;
    }

    void decls() throws IOException {
        while (look.getTag() == Tag.BASIC) { // D -> type ID ;
            Type p = type();
            Token tok = look;
            match(Tag.ID);
            match(';');

            Id id = new Id((Word) tok, p, used);
            top.put(tok, id);
            used = used + p.width;
        }
    }

    Type type() throws IOException {
        Type p = (Type) look; // expect look.tag == Tag.BASIC
        match(Tag.BASIC);
        if (look.getTag() != '[') {
            return p; // T -> basic
        } else {
            return dims(p); // return array type
        }
    }

    Type dims(Type p) throws IOException {
        match('[');
        Token tok = look;
        match(Tag.NUM);
        match(']');
        if (look.getTag() == '[') {
            p = dims(p);
        }
        return new Array(((Number) tok).getValue(), p);
    }

    Stmt stmts() throws IOException {
        if (look.getTag() == '}') {
            return Stmt.Null;
        } else {
            return new Seq(stmt(), stmts());
        }

    }

    Stmt stmt() throws IOException {
        Expr x;
        Stmt s, si, s2;
        Stmt savedStmt; // save enclosing loop for breaks
        switch (look.getTag()) {
            case ';':
                move();
                return Stmt.Null;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                x = bool();
                match(')');

                si = stmt();
                if (look.getTag() != Tag.ELSE) {
                    return new If(x, si);
                }
                match(Tag.ELSE);
                s2 = stmt();
                return new Else(x, si, s2);
            case Tag.WHILE:
                While whilenode = new While();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = whilenode;
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');

                si = stmt();
                whilenode.init(x, si);
                Stmt.Enclosing = savedStmt; // reset Stmt.Enclosing
                return whilenode;
            case Tag.DO:
                Do donode = new Do();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = donode;
                match(Tag.DO);
                si = stmt();
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');
                match(';');
                donode.init(si, x);
                Stmt.Enclosing = savedStmt; // reset Stmt.Enclosing
                return donode;

            case Tag.BREAK:
                match(Tag.BREAK);
                match(';');
                return new Break();
            case '{':
                return block();
            default:
                return assign();
        }
    }

    Stmt assign() throws IOException {
        Stmt stmt;
        Token t = look;
        match(Tag.ID);
        Id id = top.get(t);
        if (id == null) {
            error(t.toString() + " undeclared");
        }
        if (look.getTag() == '=') { // S -> id = E ;
            move();
            stmt = new Set(id, bool());
        } else { // S -> L = E ;
            Access x = offset(id);
            match('=');
            stmt = new SetElem(x, bool());
        }
        match(';');

        return stmt;
    }

    Expr bool() throws IOException {
        Expr x = join();
        while (look.getTag() == Tag.OR) {
            Token tok = look;
            move();
            x = new Or(tok, x, join());
        }
        return x;
    }

    Expr join() throws IOException {
        Expr x = equality();
        while (look.getTag() == Tag.AND) {
            Token tok = look;
            move();
            x = new And(tok, x, equality());
        }
        return x;
    }

    Expr equality() throws IOException {
        Expr x = rel();
        while (look.getTag() == Tag.EQ || look.getTag() == Tag.NE) {
            Token tok = look;
            move();
            x = new Rel(tok, x, rel());
        }
        return x;
    }

    Expr rel() throws IOException {
        Expr x = expr();

        switch (look.getTag()) {
            case '<':
            case Tag.LE:
            case Tag.GE:
            case '>':
                Token tok = look;
                move();
                return new Rel(tok, x, expr());
            default:
                return x;
        }
    }

    Expr expr() throws IOException {
        Expr x = term();
        while (look.getTag() == '+' || look.getTag() == '-') {
            Token tok = look;
            move();
            x = new Arith(tok, x, term());
        }
        return x;
    }

    Expr term() throws IOException {
        Expr x = unary();
        while (look.getTag() == '*' || look.getTag() == '/') {
            Token tok = look;
            move();
            x = new Arith(tok, x, unary());
        }
        return x;
    }

    Expr unary() throws IOException {
        if (look.getTag() == '-') {
            move();
            return new Unary(Word.minus, unary());
        } else if (look.getTag() == '!') {
            Token tok = look;
            move();
            return new Not(tok, unary());
        } else {
            return factor();
        }
    }

    Expr factor() throws IOException {
        Expr x = null;
        switch (look.getTag()) {
            case '(':
                move();
                x = bool();
                match(')');
                return x;
            case Tag.NUM:
                x = new Constant(look, Type.Int);
                move();
                return x;
            case Tag.REAL:
                x = new Constant(look, Type.Float);
                move();
                return x;
            case Tag.TRUE:
                x = Constant.True;
                move();
                return x;
            case Tag.FALSE:
                x = Constant.False;
                move();
                return x;
            default:
                error("syntax error");
                return x;
            case Tag.ID:
                String s = look.toString();
                Id id = top.get(look);
                if (id == null) {
                    error(look.toString() + " undeclared");
                }
                move();
                if (look.getTag() != '[') {
                    return id;
                } else {
                    return offset(id);
                }
        }
    }

    Access offset(Id a) throws IOException { // I -> [E] I [E] I
        Expr i;
        Expr w;
        Expr tl, t2;
        Expr loc; // inherit id
        Type type = a.type;
        match('[');
        i = bool();
        match(']'); // first index, I -> [ E ]
        type = ((Array) type).of;
        w = new Constant(type.width);
        tl = new Arith(new Token('*'), i, w);

        loc = tl;
        while (look.getTag() == '[') { // multi-dimensional I -> [ E ] I
            match('[');
            i = bool();
            match(']');
            type = ((Array) type).of;
            w = new Constant(type.width);
            tl = new Arith(new Token('*'), i, w);

            t2 = new Arith(new Token('+'), loc, tl);
            loc = t2;
        }
        return new Access(a, loc, type);
    }
}
