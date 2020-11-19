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
    
    @Override
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
    
    /**
    * evaluating exp recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a constant if done or a new exp
    */
    @Override
    public SymbolicExpression eval(Environment vars) {
        if (arg instanceof Warning) {
            return new Warning();
        }
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.exp(arg.getValue()));
        } else {
            return new Exp(arg);
        }
    }
}