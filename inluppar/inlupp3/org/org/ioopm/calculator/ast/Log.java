package org.ioopm.calculator.ast;

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
    
    @Override
    public int getPriority (){
        return 250;
    }
    
    @Override
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