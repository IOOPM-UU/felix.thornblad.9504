package org.ioopm.calculator.ast;

public class Division extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Division(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " / ";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Division) {
            return this.equals((Division) other);
        } else {
            return false;
        }
    }

    public boolean equals(Division other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
} 