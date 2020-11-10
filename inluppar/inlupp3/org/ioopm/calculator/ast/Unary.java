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
    
    public boolean equals(Object other) {
        if (other instanceof Unary && this.getClass() == other.getClass()) {
            return this.equals((Unary) other);
        } else {
            return false;
        }
    }

    public boolean equals(Unary other) {
        /// access a private field of other!
        return this.lhs == other.lhs;
    }
}
