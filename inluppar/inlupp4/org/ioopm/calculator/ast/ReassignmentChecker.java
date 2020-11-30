package org.ioopm.calculator.ast; 
import java.util.ArrayList;

public class ReassignmentChecker implements Visitor {
    private ArrayList<String> seenVars; 
    private boolean varAssignedOnce;
    
    public boolean check(SymbolicExpression a) {
        this.varAssignedOnce = true;
        this.seenVars = new ArrayList<String>(); 
        a.accept(this);
        return this.varAssignedOnce; 
    }
    
    public boolean check(Assignment a) {
        String s = a.rhs().toString();
        try {
            if(seenVars.contains(s)) {
                throw new IllegalExpressionException(); 
            }
            else {
                seenVars.add(s);
            }
        } catch (IllegalExpressionException e) {
            System.out.println("Error, the variable " + s + " is reassigned." );
            this.varAssignedOnce = false;
        } finally {
            return this.varAssignedOnce;
        }
    }
  
    // When we hit an assignment, make sure to check!
    public SymbolicExpression visit(Assignment a) {
        a.lhs().accept(this);
        a.rhs().accept(this);
        if (a.rhs() instanceof Variable) { 
            check(a); 
        } 
        return a;
    }
 
    public SymbolicExpression visit(Addition a) {
        a.lhs().accept(this);
        a.rhs().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Subtraction a) {
        a.lhs().accept(this);
        a.rhs().accept(this);
        return a; 
    }

    public SymbolicExpression visit(Multiplication a) {
        a.lhs().accept(this);
        a.rhs().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Division a) {
        a.lhs().accept(this);
        a.rhs().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Sin a) {
        a.arg().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Cos a) {
        a.arg().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Log a) {
        a.arg().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Exp a) {
        a.arg().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Negation a) {
        a.arg().accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Constant a) {
        return a; 
    }
    
   public SymbolicExpression visit(Variable a) {
        return a; 
    }
    
    public SymbolicExpression visit(NamedConstant a) {
        a.accept(this);
        return a; 
    }
    
    public SymbolicExpression visit(Quit a) {
        return a; 
    }
    
    public SymbolicExpression visit(Clear a) {
        return a; 
    }
    
   public SymbolicExpression visit(Vars a) {
        return a; 
    }
    
   public SymbolicExpression visit(Ans a) {
        return a; 
    }
}


