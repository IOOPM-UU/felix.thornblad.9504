 package org.ioopm.calculator.ast;
 
 /**
  * Assign a SynbolicExpression to a varible 
  */
 
 
public class Assignment extends Binary{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    public Assignment(final SymbolicExpression lhs, final SymbolicExpression rhs){
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String getName(){
        return "=";
    }
    
    /**
    * the degree of priority of the statement 
    * @return a int representing the prioroty
    */
    @Override
    public int getPriority (){
        return 0;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            return this.equals((Assignment) other);
        } else {
            return false;
        }
    }

    public boolean equals(Assignment other) {
        /// access a private field of other!
        return (this.lhs == other.lhs && this.rhs == other.rhs);
    }
    
    /**
    * evaluating assignment recursive all the way to constant
    * @param vars a hachmap with all saved varibles
    * @return a SybolicExpression either a varible if done or a new assignment
    * @throw IllegalExpressionException if is a namedConstant
    */
    @Override
    public SymbolicExpression eval(Environment vars) {
        if (lhs instanceof Warning || rhs instanceof Warning) {
            return new Warning();
        }
        try {
            if(this.rhs.isConstant()) {
            throw new IllegalExpressionException("Error: cannot redefine " + rhs.getValue());
        }
        SymbolicExpression arg = this.lhs.eval(vars);
        if (arg.isConstant()) {
            vars.put(rhs.toString(), new Constant(arg.getValue()));
            return new Variable(this.rhs.toString()).eval(vars);
        } else {
            return new Assignment(arg, this.rhs);
        }
            
        } catch (Exception e) {
            return new Warning();
        }
    }
    
   
        
}