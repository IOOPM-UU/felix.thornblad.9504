package org.ioopm.calculator.ast;
import java.util.ArrayList; 

public class FunctionDeclaration extends SymbolicExpression {
    private final Variable name;
    private final Sequence sequence; 
    private FunctionCall args;
    
    public FunctionDeclaration(Variable name, Sequence sequence) {
        this.name = name;
        this.sequence = sequence;
    }
    
    public Sequence sequence() {
        return this.sequence;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}