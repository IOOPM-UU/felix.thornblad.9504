package org.ioopm.calculator.ast;

public class Exp extends Unary {
    private SymbolicExpression lhs;
    public Exp (final SymbolicExpression lhs){
        super (lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "^ ";
    }
    
    @Override
    public int getPriority (){
        return 200;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Exp) {
            return this.equals((Exp) other);
        } else {
            return false;
        }
    }

    public boolean equals(Exp other) {
        /// access a private field of other!
        return this.lhs == other.lhs;
    }  
}