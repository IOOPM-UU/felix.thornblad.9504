package org.ioopm.calculator.ast;

public class Subtraction extends Binary{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Subtraction (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " - ";
    }
    
    @Override
    public int getPriority (){
        return 50;
    }
  
}