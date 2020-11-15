package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression arg;
    public Unary(final SymbolicExpression arg) {
        super(arg);
        this.arg = arg;
    }
    public String toString() {
        return this.getName() + "(" + this.arg.toString() + ")";
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
        return this.arg == other.arg;
    }
}
