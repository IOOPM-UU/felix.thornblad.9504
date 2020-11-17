package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Binary(final SymbolicExpression lhs, final SymbolicExpression rhs) {
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
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Binary && this.getClass() == other.getClass()) {
            return this.equals((Binary) other);
        } else {
            return false;
        }
    }

    public boolean equals(Binary other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }

}