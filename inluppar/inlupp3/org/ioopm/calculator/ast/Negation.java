package org.ioopm.calculator.ast;

public class Negation extends Unary {
    private SymbolicExpression lhs;
    public Negation (final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "-";
    }
    
    @Override
    public int getPriority (){
        return 300;
    }
       
    @Override
    public String toString() {
        return "(" + (getName() + lhs.toString() + ")");
    }
        
    public boolean equals(Object other) {
        if (other instanceof Negation) {
            return this.equals((Negation) other);
        } else {
            return false;
        }
    }

    public boolean equals(Negation other) {
        /// access a private field of other!
        return this.lhs == other.lhs;
    }
}