package org.ioopm.calculator.ast;

public class Quit extends Command {
    private SymbolicExpression x;
    public Quit (SymbolicExpression x){
        super("Quit", x);
        this.x = x;
    }
    /*
    public SymbolicExpression instance() {
        return x;
    }
    */
}