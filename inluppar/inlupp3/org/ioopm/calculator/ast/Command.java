package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {
    static SymbolicExpression x;
    public Command(String name, SymbolicExpression x) {
        super(name, "");
        this.x = x;
    }
    public static SymbolicExpression instance() {
       return x;
    }
}