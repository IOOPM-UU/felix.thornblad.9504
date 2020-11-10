package org.ioopm.calculator.ast;

public class Log extends Unary {
    private SymbolicExpression lhs;
    public Log (final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "log ";
    }
    
    @Override
    public int getPriority (){
        return 250;
    }
        
}