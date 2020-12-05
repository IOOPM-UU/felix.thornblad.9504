package org.ioopm.calculator.ast;

/**
 * Represents the terminal expression as one of the allowed combinations in the namedConstants HashMap
 */
public class NamedConstant extends Constant {
    private String name;
    private double value;
    
    public NamedConstant(final String name, final double value){
        super(value);
        this.name = name;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return name; 
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}