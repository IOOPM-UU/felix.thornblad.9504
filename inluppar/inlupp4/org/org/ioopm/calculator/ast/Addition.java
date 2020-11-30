package org.ioopm.calculator.ast;

/**
 * Represents the addition operation that combines two subtrees into an SymbolicExpression
 */
public class Addition extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    
   
    public Addition (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " + ";
    }
       
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority(){
        return 50;
    }
    

    public boolean equals(Object other) {
        if (other instanceof Addition) {
            return this.equals((Addition) other);
        } else {
            return false;
        }
    }

    public boolean equals(Addition other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
        // return (this.lhs.equals(other.lhs) && 
        //       this.rhs.equals(other.rhs));
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}