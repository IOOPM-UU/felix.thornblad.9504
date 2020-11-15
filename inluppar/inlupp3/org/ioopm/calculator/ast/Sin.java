package org.ioopm.calculator.ast;

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
    
    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }
}