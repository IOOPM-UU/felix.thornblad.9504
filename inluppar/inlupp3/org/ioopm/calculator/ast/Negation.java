package org.ioopm.calculator.ast;

public class Negation extends Unary {
    public Negation (final SymbolicExpression lhs){
        super("Negation", lhs);
    }
}