package org.ioopm.calculator.ast;

public class Log extends Unary {
    public Log (final SymbolicExpression lhs){
        super(lhs);
    }
    
    @Override
    public String getName (){
        return "log";
    }
    
    @Override
    public int getPriority (){
        return 250;
    }
}