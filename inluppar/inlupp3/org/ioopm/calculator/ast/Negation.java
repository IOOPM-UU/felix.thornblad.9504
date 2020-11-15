package org.ioopm.calculator.ast;

public class Negation extends Unary {
    private SymbolicExpression arg;
    public Negation (final SymbolicExpression arg){
        super(arg);
        this.arg = arg;
    }
    
    @Override
    public String getName (){
        return "-";
    }
    
    @Override
    public int getPriority (){
        return 300;
    }
       
    @Override
    public String toString() {
        return "(" + (getName() + arg.toString() + ")");
    }
        
    public boolean equals(Object other) {
        if (other instanceof Negation) {
            return this.equals((Negation) other);
        } else {
            return false;
        }
    }

    public boolean equals(Negation other) {
        /// access a private field of other!
        return this.arg == other.arg;
    }
    
    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.arg.eval(vars);
        if (arg.isConstant()) {
            return new Constant(-1*(arg.getValue()));
        } else {
            return new Negation(arg);
        }
    }

}