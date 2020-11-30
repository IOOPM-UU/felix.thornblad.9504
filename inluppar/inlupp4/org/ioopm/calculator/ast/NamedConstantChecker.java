package org.ioopm.calculator.ast; 

public class NamedConstantChecker implements Visitor {
    private boolean noError;
    public boolean check(SymbolicExpression a) {
        this.noError = true; 
        a.accept(this);
        return noError; 
    }
    
    public boolean check(Assignment topLevel) {
        try {
            if (topLevel.rhs() instanceof NamedConstant) {
                throw new IllegalExpressionException(); 
            } else {
                return true; 
            }
        } catch (IllegalExpressionException e) {
            System.out.println("Error, assignments to named constants:" );
            System.out.println("\t" + topLevel.lhs().getValue() + " = " + topLevel.rhs().toString());
            noError = false;
            return noError;
        } 
    }
    
    // When we hit an assignment, make sure to check!
    public SymbolicExpression visit(Assignment a) {
        a.lhs().accept(this);
        a.rhs().accept(this);
        if (a.rhs().isConstant()) { // or maybe you used just isConstant
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


