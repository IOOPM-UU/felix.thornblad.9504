package org.ioopm.calculator.ast;

public class Division extends Binary {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Division(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName (){
        return " / ";
    }
    
    @Override
    public int getPriority (){
        return 100;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Division) {
            return this.equals((Division) other);
        } else {
            return false;
        }
    }

    public boolean equals(Division other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
    
    @Override
    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);
        if (lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() / rhs.getValue());
        } else {
            return new Division(lhs, rhs);
        }
    }
} 