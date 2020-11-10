package org.ioopm.calculator.ast;

public abstract class Atom extends SymbolicExpression {
    public Atom(String x) {
        super(x);
    }
    //toString
    
    @Override
    public Boolean isConstant (){
        return true;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Atom) {
            return this.equals((Atom) other);
        } else {
            return false;
        }
    }
/*
    public boolean equals(Atom other) {
        /// access a private field of other!
        return this.value == other.value;
    }
    */
}