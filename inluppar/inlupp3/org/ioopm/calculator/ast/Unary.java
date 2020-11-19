package org.ioopm.calculator.ast;

/**
 * Represents all operations that operates on their own subtree
 */
public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression arg;
    public Unary(final SymbolicExpression arg) {
        this.arg = arg;
    }
    @Override
    public String toString() {
        return this.getName() + "(" + this.arg.toString() + ")";
    }
    
}
