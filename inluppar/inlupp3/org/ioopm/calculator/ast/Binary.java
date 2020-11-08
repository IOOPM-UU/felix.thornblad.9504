package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null;
    public Binary(String name, final SymbolicExpression lhs, final SymbolicExpression rhs) {
        super(name, lhs, rhs);
        
    }
    
        
}