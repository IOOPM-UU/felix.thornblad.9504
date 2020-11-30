package org.ioopm.calculator.ast;

/**
 * Represents the exp operation on a subtree
 */
public class Exp extends Unary {
    private SymbolicExpression arg;
    public Exp (final SymbolicExpression arg){
        super (arg);
        this.arg = arg;
    }
    
    @Override
    public String getName (){
        return "exp";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
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
        return this.arg == other.arg;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}