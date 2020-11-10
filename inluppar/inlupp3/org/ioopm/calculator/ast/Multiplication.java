package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Multiplication (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " * ";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
}