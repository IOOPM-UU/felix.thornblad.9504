package org.ioopm.calculator.ast;

public class Constant extends Atom {
    private double value;
    public Constant (final double value){
        super(String.valueOf(value));
        this.value = value;
       
    }
    @Override
    public double getValue (){
        return value;
    }
}