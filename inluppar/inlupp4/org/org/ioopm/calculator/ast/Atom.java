package org.ioopm.calculator.ast;

/**
 * Represents all terminal expressions
 */
public abstract class Atom extends SymbolicExpression {
    private String leaf;
    public Atom(String leaf) {
        this.leaf = leaf;
    }
}