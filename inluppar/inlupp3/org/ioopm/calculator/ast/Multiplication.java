package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    public Multiplication (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
    }
    
    @Override
    public String getName (){
        return "*";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
}