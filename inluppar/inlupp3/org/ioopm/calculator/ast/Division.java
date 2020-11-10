package org.ioopm.calculator.ast;

public class Division extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Division(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " / ";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
   
} 