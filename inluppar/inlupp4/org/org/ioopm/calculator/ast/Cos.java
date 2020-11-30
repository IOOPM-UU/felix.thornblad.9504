package org.ioopm.calculator.ast;

/**
 * Represents the cos operation on a subtree
 */
public class Cos extends Unary {
    private SymbolicExpression arg;
    public Cos(final SymbolicExpression arg){
        super(arg);
        this.arg = arg;
    }
    
    @Override
    public String getName (){
        return "cos ";
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
        if (other instanceof Cos) {
            return this.equals((Cos) other);
        } else {
            return false;
        }
    }

    public boolean equals(Cos other) {
        /// access a private field of other!
        return this.arg == other.arg;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}