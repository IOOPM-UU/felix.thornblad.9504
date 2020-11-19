package org.ioopm.calculator.ast;

/**
 * Shows all the current variables values
 */
public class Vars extends Command {
    private static final Vars theInstance = new Vars();
    private Vars() {}
    public static Vars instance() {
        return theInstance;
    }
    
}