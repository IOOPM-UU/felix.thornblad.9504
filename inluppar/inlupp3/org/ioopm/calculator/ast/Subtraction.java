package org.ioopm.calculator.ast;

public class Subtraction extends Binary{
    public Subtraction (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
    }
    
    @Override
    public String getName (){
        return "-";
    }
    
    @Override
    public int getPriority (){
        return 50;
    }
}