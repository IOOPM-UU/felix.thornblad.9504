package org.ioopm.calculator.ast;

public class Negation extends Unary {
    private SymbolicExpression lhs;
    public Negation (final SymbolicExpression lhs){
        super(lhs);
        this.lhs = lhs;
    }
    
    @Override
    public String getName (){
        return "-";
    }
    
    @Override
       public int getPriority (){
           return 300;
       }
       
    @Override
        public String toString() {
            return "(" + (getName() + lhs.toString() + ")");
            
        }
}