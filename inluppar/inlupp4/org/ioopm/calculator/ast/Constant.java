package org.ioopm.calculator.ast;

/**
 * Represents the terminal expression as a number
 */
public class Constant extends Atom {
    private double value;
    public Constant (final double value){
        super(String.valueOf(value));
        this.value = value;
       
    }
    @Override
    public double getValue (){
        return value;
    }
    
    @Override
    public Boolean isConstant (){
        return true;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);  
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority (){
        return 350;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Constant) {
            return this.equals((Constant) other);
        } else {
            return false;
        }
    }

    public boolean equals(Constant other) {
        /// access a private field of other!
        return this.value == other.getValue();
    }
    
    @Override
    public SymbolicExpression eval (Environment vars){
        return new Constant(value);
    }

}