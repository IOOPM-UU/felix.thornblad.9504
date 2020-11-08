 package org.ioopm.calculator.ast;
 
public class Assignment extends Binary{
    public Assignment(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
    }
    
    @Override
    public String getName(){
        return "=";
    }
}