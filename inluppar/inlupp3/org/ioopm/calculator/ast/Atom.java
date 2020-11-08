package org.ioopm.calculator.ast;

public abstract class Atom extends SymbolicExpression {
    public Atom(String name, String x) {
        super(name, x);
    }
}