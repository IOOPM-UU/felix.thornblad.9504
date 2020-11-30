package org.ioopm.calculator.ast;

/**
 * Represents the nagation operation that operates on their own subtree
 */
public class Negation extends Unary {
    private SymbolicExpression arg;
    public Negation (final SymbolicExpression arg){
        super(arg);
        this.arg = arg;
    }
    
    @Override
    public String getName (){
        return "-";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority (){
        return 300;
    }
       
    @Override
    public String toString() {
        return "(" + (getName() + arg.toString() + ")");
    }
    
    public boolean equals(Object other) {
        if (other instanceof Negation) {
            return this.equals((Negation) other);
        } else {
            return false;
        }
    }

    public boolean equals(Negation other) {
        /// access a private field of other!
        return this.arg == other.arg;
    }
 
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}