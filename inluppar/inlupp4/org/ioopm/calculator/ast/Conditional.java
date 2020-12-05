package org.ioopm.calculator.ast; 
 

public class Conditional extends SymbolicExpression {
    private SymbolicExpression left;
    private SymbolicExpression right;
    String operation; 
    SymbolicExpression ifTrue;
    SymbolicExpression ifFalse;
    
    public Conditional(SymbolicExpression left, String operation, SymbolicExpression right, SymbolicExpression ifTrue, SymbolicExpression ifFalse) {
        this.left = left;
        this.operation = operation;
        this.right = right;
        this.left = left;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
    @Override
    public String toString() {
        return String.format("if %s %s %s %s else %s", 
            left.toString(), operation, right.toString(), ifTrue.toString(), ifFalse.toString());
    }
    
    public SymbolicExpression left() {
        return this.left;
    }
    
    public SymbolicExpression right() {
        return this.right;
    }
    
    public String operation() {
        return this.operation;
    }
    
    public SymbolicExpression ifTrue() {
        return this.ifTrue;
    }
    
    public SymbolicExpression ifFalse() {
        return this.ifFalse;
    }
}
