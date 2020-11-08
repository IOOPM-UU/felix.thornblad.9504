package org.ioopm.calculator.ast;

public class Exp extends Unary {
    public Exp (final SymbolicExpression lhs){
        super (lhs);
    }
    
    @Override
    public String getName (){
        return "exp";
    }
    
    @Override
    public int getPriority (){
        return 200;
    }
}