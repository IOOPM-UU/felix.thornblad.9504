package org.ioopm.calculator.ast;

/**
 * Represents all inputs that results in an action rather than a value
 */
public abstract class Command extends SymbolicExpression {
    
    public Command() {
        
    }
    public static SymbolicExpression instance() {
        return (new Constant(5));
    }
    
    @Override
    public Boolean isCommand(){
        return true;
    }
    
    @Override
    public SymbolicExpression eval(Environment vars){
        throw new RuntimeException("eval() called on expression with no operator");
    }
}