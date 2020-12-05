package org.ioopm.calculator.ast;
import java.util.ArrayList; 

public class Sequence extends SymbolicExpression {
    private final ArrayList<String> parameters;
    private final ArrayList<SymbolicExpression> body;
    
    public Sequence(ArrayList<String> parameters, ArrayList<SymbolicExpression> body) {
        this.parameters = parameters;
        this.body = body; 
    }
    
    public ArrayList<String> parameters() {
        return this.parameters;
    }
    
    public ArrayList<SymbolicExpression> body() {
        return this.body;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this); 
    }
}