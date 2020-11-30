package org.ioopm.calculator.parser;

public class SyntaxErrorException extends RuntimeException {
    public SyntaxErrorException (final String error){
        super(error);
    }
}