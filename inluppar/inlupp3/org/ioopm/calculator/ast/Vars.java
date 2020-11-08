package org.ioopm.calculator.ast;


public class Vars extends Command {
    private SymbolicExpression x;
    public Vars (SymbolicExpression x){
        super(x);
        this.x = x;
    }
    /*
    public SymbolicExpression instance() {
        return x;
    }
    */
}