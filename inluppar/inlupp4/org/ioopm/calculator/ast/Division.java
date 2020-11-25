package org.ioopm.calculator.ast;

/**
 * Represents the division operation that combines two subtrees into an SymbolicExpression
 */
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
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
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
    
    /**
    * evaluating division recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a constant if done or a new sivition
    */
    @Override
    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);
        if (lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() / rhs.getValue());
        } else {
            return new Division(lhs, rhs);
        }
    }
} 