 package org.ioopm.calculator.ast;
 
public class Assignment extends Binary{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Assignment(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName(){
        return "=";
    }
    
    @Override
    public int getPriority (){
        return 0;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            return this.equals((Assignment) other);
        } else {
            return false;
        }
    }

    public boolean equals(Assignment other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
}