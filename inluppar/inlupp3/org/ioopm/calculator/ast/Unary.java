package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression leaf = null;
    public Unary(final SymbolicExpression lhs) {
        super(lhs);
    }
}