package org.ioopm.calculator.ast;

/**
 * Represents the sin operation on a subtree
 */
public class Sin extends Unary {
    private SymbolicExpression arg;
    public Sin (final SymbolicExpression arg){
        super(arg);
        this.arg = arg;
    }
    
    @Override
    public String getName (){
        return "sin ";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
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
        return this.arg == other.arg;
    }
    

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}