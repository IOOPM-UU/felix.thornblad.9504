package org.ioopm.calculator.ast;

/**
 * Represents the log operation on a subtree
 */
public class Log extends Unary {
    private SymbolicExpression arg;
    public Log (final SymbolicExpression arg){
        super(arg);
        this.arg = arg;
    }
    
    @Override
    public String getName (){
        return "log ";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority (){
        return 250;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Log) {
            return this.equals((Log) other);
        } else {
            return false;
        }
    }

    public boolean equals(Log other) {
        /// access a private field of other!
        return this.arg == other.arg;
    }
    /**
    * evaluating log recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a constant if done or a new log
    */
    @Override
    public SymbolicExpression eval (Environment vars) {
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.log(arg.getValue()));
        } else {
            return new Log(arg);
        }
    }

}