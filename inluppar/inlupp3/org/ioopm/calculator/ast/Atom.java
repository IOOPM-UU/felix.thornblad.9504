package org.ioopm.calculator.ast;

public abstract class Atom extends SymbolicExpression {
    public Atom(String x) {
        super(x);
    }
    //toString
    
    @Override
    public Boolean isConstant (){
        return true;
    }
}