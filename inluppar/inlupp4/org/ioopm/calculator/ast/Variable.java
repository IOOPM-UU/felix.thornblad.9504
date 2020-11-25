package org.ioopm.calculator.ast;

/**
 * Represents the terminal expression as a string
 */
public class Variable extends Atom {
    private String id;
    public Variable (final String id){
        super(id);
        this.id = id;
    }
    
    @Override
    public String toString() {
        return id;  
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
        /// access a private field of other!
        return this.id == other.id;
    }
    
    /**
    * evaluating variable recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a constant if it is saved or a new varible
    */
    @Override
    public SymbolicExpression eval (Environment vars){
        if(vars.containsKey(this.id))
        {
            return vars.get(id);
        }
        else
        {
            return new Variable(id);
        }
    }
}