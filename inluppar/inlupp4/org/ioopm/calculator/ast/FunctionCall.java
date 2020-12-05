package org.ioopm.calculator.ast;
import java.util.ArrayList; 

public class FunctionCall extends SymbolicExpression {
    private SymbolicExpression identifier;
    private ArrayList<SymbolicExpression> arguments;
    
    public FunctionCall(SymbolicExpression identifier, ArrayList<SymbolicExpression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
    }
    
    public ArrayList<SymbolicExpression> arguments() {
        return this.arguments;
    }
    
    public SymbolicExpression identifier() {
        return this.identifier;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v){
        return v.visit(this); 
    }
}