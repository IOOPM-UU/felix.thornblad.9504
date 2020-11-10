package org.ioopm.calculator.ast;

public class Log extends Unary {
    private SymbolicExpression lhs;
    public Log (final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "log ";
    }
    
    @Override
    public int getPriority (){
        return 250;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Log) {
            return this.equals((Log) other);
        } else {
            return false;
        }
    }

    public boolean equals(Log other) {
        /// access a private field of other!
        return this.lhs == other.lhs;
    }    
}