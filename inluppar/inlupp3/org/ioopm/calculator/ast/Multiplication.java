package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    public Multiplication (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super("Multiplication", lhs, rhs);
    }
}