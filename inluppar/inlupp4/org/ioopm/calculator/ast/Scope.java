package org.ioopm.calculator.ast; 
import java.util.Stack; 

public class Scope extends SymbolicExpression {
    private SymbolicExpression arg;
    
    public Scope(SymbolicExpression arg) {
        this.arg = arg; 
    }
    
    public SymbolicExpression arg() {
        return this.arg;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
    @Override
    public String toString() {
        return String.format("{%s}", arg.toString()); 
    }
}