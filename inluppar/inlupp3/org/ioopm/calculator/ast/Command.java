package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {
    static SymbolicExpression x;
    public Command(SymbolicExpression x) {
        super("");
        this.x = x;
    }
    public static SymbolicExpression instance() {
       return x;
    }
    
    public SymbolicExpression eval(Environment vars){
        throw new RuntimeException("eval() called on expression with no operator");
    }
}