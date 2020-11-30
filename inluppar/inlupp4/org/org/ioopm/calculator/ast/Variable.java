package org.ioopm.calculator.ast;

/**
 * Represents the terminal expression as a string
 */
public class Variable extends Atom implements Comparable<Variable> {
    private String id;
    public Variable (final String id){
        super(id);
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.id;  
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
        if (other instanceof Variable) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }
    
    public boolean equals(Variable other) {
       return this.hashCode() == other.hashCode();
    }
    
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    
    public int compareTo(Variable other) {
        int sum = 0;
        // Gets the smallest length of both strings 
        int limit = this.id.length() < other.id.length() ? 
            this.id.length() : other.id.length();
        // Checks at every position in both string up to the limit
        for (int i = 0; i < limit; i++) {
            sum = this.id.charAt(i) - other.id.charAt(i);
            // if sum is 0 then it was the same char and we continue checking
            if (sum == 0) { continue; }
            // else we return the difference
            else { return sum; }
        } 
        // If there are no difference between the checked part,
        // return the difference in length 
        return this.id.length() - other.id.length();
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
}