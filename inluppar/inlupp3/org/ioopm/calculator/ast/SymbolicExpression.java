package org.ioopm.calculator.ast;

/**
 * Represents the abstraction of different inputs
 */
public abstract class SymbolicExpression {
    
        
    public Boolean isConstant(){
        return false; 
    }
    
    public Boolean isCommand(){
        return false;
    }

    public String getName(){
        throw new RuntimeException("getName() called on expression with no operator");
    }
    
    public int getPriority() {
        throw new RuntimeException("getPriority() called on expression that is not an operator");
    }
        
    public double getValue() {
        throw new RuntimeException("getValue() called on expression that is not an constant");
    }
     
    public abstract SymbolicExpression eval(Environment vars);
    
    /**
     * checks if an objekt is eqal to this objekt
     * @param other the objekt that this objekt is kompared to 
     * @return a boolean true if equal, false if not
     */  
    @Override  
    public boolean equals(Object other) {
        if (other instanceof SymbolicExpression && this.getClass() == other.getClass()) {
            return this.equals((SymbolicExpression) other);
        } else {
            return false;
        }
    }
        
}

