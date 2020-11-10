package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Binary(final SymbolicExpression lhs, final SymbolicExpression rhs) {
        super(lhs, rhs); 
        this.lhs = lhs;
        this.rhs = rhs;
    }
    

    @Override
    public String toString() {
        if(this.lhs.getPriority() < this.getPriority() && this.rhs.getPriority() < this.getPriority()){
            return ("(" + this.lhs.toString() + ")" + this.getName() + "(" + this.rhs.toString() + ")");
        }
        else if(this.rhs.getPriority() < this.getPriority()) {
            return (this.lhs.toString() + this.getName() + "(" + this.rhs.toString() + ")");
        }
        else if(this.lhs.getPriority() < this.getPriority()) {
            return ("(" + this.lhs.toString() + ")" + this.getName()  + this.rhs.toString());
        }
        else{
            return (this.lhs.toString() + this.getName() + this.rhs.toString());
        }
            
    }

}