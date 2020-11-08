package org.ioopm.calculator.ast;

public class Sin extends Unary {
    public Sin (final SymbolicExpression lhs){
        super(lhs);
    }
    
    @Override
    public String getName (){
        return "sin";
    }
    
    @Override
       public int getPriority (){
           return 25;
       }
    
}