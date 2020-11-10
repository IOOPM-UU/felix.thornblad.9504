package org.ioopm.calculator.ast;

public class Sin extends Unary {
    private SymbolicExpression lhs;
    public Sin (final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "sin ";
    }
    
    @Override
    public int getPriority (){
        return 25;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Sin) {
            return this.equals((Sin) other);
        } else {
            return false;
        }
    }

    public boolean equals(Sin other) {
        /// access a private field of other!
        return this.lhs == other.lhs;
    }
}