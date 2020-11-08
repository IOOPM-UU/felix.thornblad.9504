package org.ioopm.calculator.ast;

public class Division extends Binary {
    public Division(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
    }
    
    @Override
    public String getName (){
        return "/";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
} 