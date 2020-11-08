package org.ioopm.calculator.ast;

public class Negation extends Unary {
    public Negation (final SymbolicExpression lhs){
        super(lhs);
    }
    
    @Override
    public String getName (){
        return "-";
    }
    
    @Override
       public int getPriority (){
           return 300;
       }
}