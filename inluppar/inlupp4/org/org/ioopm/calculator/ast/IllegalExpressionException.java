package org.ioopm.calculator.ast;

public class IllegalExpressionException extends RuntimeException {
    public IllegalExpressionException() {
        super(); 
    }
    
    public IllegalExpressionException(final String error) {
        super(error);
    }
}