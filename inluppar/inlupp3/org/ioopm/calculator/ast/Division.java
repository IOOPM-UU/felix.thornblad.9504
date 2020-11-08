package org.ioopm.calculator.ast;

public class Division extends Binary {
    public Division(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super("Division", lhs, rhs);
    }  
} 