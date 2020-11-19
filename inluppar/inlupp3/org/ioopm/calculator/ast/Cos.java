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
    
    @Override
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
    
    /**
    * evaluating cos recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a constant if done or a new cos
    */
    @Override
    public SymbolicExpression eval(Environment vars) {
        if (arg instanceof Warning) {
            return new Warning();
        }
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.cos(arg.getValue()));
        } else {
            return new Cos(arg);
        }
    }

}