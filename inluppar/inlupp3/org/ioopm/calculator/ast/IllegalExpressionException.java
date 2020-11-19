package org.ioopm.calculator.ast;

public class IllegalExpressionException extends Exception {
    public IllegalExpressionException(final String error) {
        System.out.println(error);
    }
}