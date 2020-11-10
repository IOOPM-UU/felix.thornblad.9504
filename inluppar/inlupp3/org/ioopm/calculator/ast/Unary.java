package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression lhs;
    public Unary(final SymbolicExpression lhs) {
        super(lhs);
        this.lhs = lhs;
    }
    public String toString() {
        return this.getName() + "(" + this.lhs.toString() + ")";
    }
}