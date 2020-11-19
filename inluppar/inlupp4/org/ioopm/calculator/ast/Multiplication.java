package org.ioopm.calculator.ast;

/**
 * Represents the multiplication operation that combines two subtrees into an SymbolicExpression
 */
public class Multiplication extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Multiplication (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " * ";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority (){
        return 100;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Multiplication) {
            return this.equals((Multiplication) other);
        } else {
            return false;
        }
    }

    public boolean equals(Multiplication other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
    
    /**
    * evaluating muntiplication recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a constant if done or a new multiplication
    */
    @Override
    public SymbolicExpression eval(Environment vars) {
        if (lhs instanceof Warning || rhs instanceof Warning) {
            return new Warning();
        }
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);
        if (lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() * rhs.getValue());
        } else {
            return new Multiplication(lhs, rhs);
        }
    }

}