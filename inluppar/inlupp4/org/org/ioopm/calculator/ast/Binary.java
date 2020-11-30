package org.ioopm.calculator.ast;

/**
 * Represents all operations that combines two subtrees into an SymbolicExpression
 */
public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Binary(final SymbolicExpression lhs, final SymbolicExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    public SymbolicExpression lhs() {
        return this.lhs;
    }
    
    public SymbolicExpression rhs() {
        return this.rhs;
    }
    
    /**
     * Converts a binary funktions subtrees to one sting
     * @return a String that represents the expresion 
     */ 
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