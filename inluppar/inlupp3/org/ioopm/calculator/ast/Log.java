package org.ioopm.calculator.ast;

public class Log extends Unary {
    public Log (final SymbolicExpression lhs){
        super("Log", lhs);
    }
}