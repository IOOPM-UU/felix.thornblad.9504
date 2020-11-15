package org.ioopm.calculator.ast;

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
       
    @Override
    public int getPriority (){
        return 25;
    }
    
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
    
    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.cos(arg.getValue()));
        } else {
            return new Cos(arg);
        }
    }

}