package org.ioopm.calculator.ast;

public class Cos extends Unary {
    private SymbolicExpression lhs;
    public Cos(final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "cos ";
    }
       
    @Override
    public int getPriority (){
        return 25;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Cos) {
            return this.equals((Cos) other);
        } else {
            return false;
        }
    }

    public boolean equals(Cos other) {
        /// access a private field of other!
        return this.lhs == other.lhs;
    }
}