package org.ioopm.calculator.ast;

public class Clear extends Command {
    private static SymbolicExpression x;
    public Clear (SymbolicExpression x){
        super("Clear", x);
        this.x = x;
    }
    /*
    public static SymbolicExpression instance() {
        return x;
    }
    */
}