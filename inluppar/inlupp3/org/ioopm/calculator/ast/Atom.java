package org.ioopm.calculator.ast;

public abstract class Atom extends SymbolicExpression {
    private String leaf;
    public Atom(String leaf) {
        this.leaf = leaf;
    }
    //toString
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Atom && this.getClass() == other.getClass()) {
            return this.equals((Atom) other);
        } else {
            return false;
        }
    }
/*
    public boolean equals(Atom other) {
        /// access a private field of other!
        return this.leaf.equals(other.leaf);
    }
    */
}