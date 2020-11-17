package org.ioopm.calculator.ast;

public class NamedConstant extends Constant{
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
    public SymbolicExpression eval (Environment vars){
        return new NamedConstant(name, value);
    }
}