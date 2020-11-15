package org.ioopm.calculator.ast;

public class Addition extends Binary{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    
    public Addition (final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
        //int i=Integer.parseInt(s); 
         
        //evaluateExpression
           
        //toString
    }
    
    @Override
    public String getName (){
        return " + ";
    }
       
    @Override
    public int getPriority (){
        return 50;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Addition) {
            return this.equals((Addition) other);
        } else {
            return false;
        }
    }

    public boolean equals(Addition other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
    
    public SymbolicExpression eval (Environment vars) {
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);
        if (lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() + rhs.getValue());
        } else {
            return new Addition(lhs, rhs);
        }
    }
}