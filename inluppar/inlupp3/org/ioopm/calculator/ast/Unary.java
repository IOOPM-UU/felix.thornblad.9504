package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression leaf = null;
    public Unary(String name, final SymbolicExpression lhs) {
        super(name, lhs);
    }
}