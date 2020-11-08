package org.ioopm.calculator.ast;

public class Subtraction extends Binary{
    public Subtraction (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super("Subtraction", lhs, rhs);
    }
}