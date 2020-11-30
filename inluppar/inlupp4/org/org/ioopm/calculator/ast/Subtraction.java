package org.ioopm.calculator.ast;

/**
 * Represents the subtraction operation that combines two subtrees into an SymbolicExpression
 */
public class Subtraction extends Binary{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Subtraction (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " - ";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority (){
        return 50;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Subtraction) {
            return this.equals((Subtraction) other);
        } else {
            return false;
        }
    }

    public boolean equals(Subtraction other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}