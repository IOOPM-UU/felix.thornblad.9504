 package org.ioopm.calculator.ast;
 
public class Assignment extends Binary{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Assignment(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName(){
        return "=";
    }
    
    @Override
    public int getPriority (){
        return 0;
    }
}