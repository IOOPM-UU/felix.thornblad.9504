package org.ioopm.calculator.ast;

public class Cos extends Unary {
    private SymbolicExpression lhs;
    public Cos(final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
       public String getName (){
           return "cos ";
       }
       
    @Override
       public int getPriority (){
           return 25;
       }
    
}