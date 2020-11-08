package org.ioopm.calculator.ast;

public class Constant extends Atom {
    public Constant (final double value){
        super("Constant", String.valueOf(value));
       
    }
    
}