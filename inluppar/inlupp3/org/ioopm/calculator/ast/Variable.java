package org.ioopm.calculator.ast;

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
    
    @Override
    public int getPriority (){
        return 350;
    }
    
    @Override   
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