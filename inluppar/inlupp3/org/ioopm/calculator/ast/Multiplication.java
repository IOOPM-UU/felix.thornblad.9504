package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Multiplication (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " * ";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Multiplication) {
            return this.equals((Multiplication) other);
        } else {
            return false;
        }
    }

    public boolean equals(Multiplication other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
}