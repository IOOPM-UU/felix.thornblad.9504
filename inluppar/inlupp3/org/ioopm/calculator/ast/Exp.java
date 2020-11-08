package org.ioopm.calculator.ast;

public class Exp extends Unary {
    public Exp (final SymbolicExpression lhs){
        super ("Exp", lhs);
    }
}