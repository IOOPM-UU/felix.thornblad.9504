package org.ioopm.calculator.ast;

/**
 * Command for  the program
 */
public class Ans extends Command {
    private static final Ans theInstance = new Ans();
    private Ans() {}
    public static Ans instance() {
        return theInstance;
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}