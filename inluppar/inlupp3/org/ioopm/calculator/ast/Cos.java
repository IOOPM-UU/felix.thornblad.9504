package org.ioopm.calculator.ast;

public class Cos extends Unary {
    public Cos(final SymbolicExpression lhs){
        super(lhs);
    }
    
    @Override
       public String getName (){
           return "cos";
       }
       
    @Override
       public int getPriority (){
           return 25;
       }
}