 package org.ioopm.calculator.ast;
 
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
    
    @Override
    public SymbolicExpression eval(Environment vars) {
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
    }
    
    
}