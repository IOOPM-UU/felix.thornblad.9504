package org.ioopm.calculator.ast;

public class Sin extends Unary {
    public Sin (final SymbolicExpression lhs){
        super("Sin", lhs);
    }
}