package org.ioopm.calculator.ast;

public class Sin extends Unary {
    private SymbolicExpression lhs;
    public Sin (final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "sin ";
    }
    
    @Override
       public int getPriority (){
           return 25;
       }
    
}