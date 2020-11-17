package org.ioopm.calculator.ast;

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
    
    @Override
    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.exp(arg.getValue()));
        } else {
            return new Exp(arg);
        }
    }
}