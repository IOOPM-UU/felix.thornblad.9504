package org.ioopm.calculator.ast;

    
public abstract class SymbolicExpression {
    
        
    public Boolean isConstant(){
        return false; 
    }
    
    public Boolean isCommand(){
        return false;
    }
        
    //ska ge error
        
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
      
    @Override  
    public boolean equals(Object other) {
        if (other instanceof SymbolicExpression && this.getClass() == other.getClass()) {
            return this.equals((SymbolicExpression) other);
        } else {
            return false;
        }
    }
/* override does not work as in type is matched only here
    public boolean equals(SymbolicExpression other) {
         System.out.println("symb");
        /// access a private field of other!
        return this.subExpressions == other.subExpressions;
    }
    */
        
}

