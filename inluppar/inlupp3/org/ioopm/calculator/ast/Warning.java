package org.ioopm.calculator.ast;

/**
 * Represents a warning of an IllegalExpressionError
 */
public class Warning extends SymbolicExpression{

    @Override
    public SymbolicExpression eval(Environment vars)
    {
        return new Warning();
    }
}
