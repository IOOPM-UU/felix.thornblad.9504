package org.ioopm.calculator.ast; 

public class EvaluationVisitor implements Visitor {
    private Environment env = null;

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env) {
        this.env = env;
        return topLevel.accept(this);
    }

    // This method gets called from Addition.accept(Visitor v) -- you should
    // be able to see from the eval() methods how these should behave (i.e., 
    // compare this method with your Addition::eval() and Symbolic.addition) 
    public SymbolicExpression visit(Addition n) {
        // Visit the left hand side and right hand side subexpressions
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);
        // When we come back here, the visitor has visited all subexpressions, 
        // meaning left and right point to newly created trees reduced to 
        // the extent possible (best case -- both are constants)

        // If subexpressions are fully evaluated, replace them in
        // the tree with a constant whose value is the sub of the
        // subexpressions, if not, simply construct a new addition
        // node from the new subexpressions
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() + right.getValue());
        } else {
            return new Addition(left, right);
        }
    }
    
    public SymbolicExpression visit(Subtraction n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);
        
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() - right.getValue());
        } else {
            return new Subtraction(left, right);
        }
    }
    
    public SymbolicExpression visit(Division n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);
        
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() / right.getValue());
        } else {
            return new Division(left, right);
        }
    }
    
    public SymbolicExpression visit(Multiplication n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);
        
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() * right.getValue());
        } else {
            return new Multiplication(left, right);
        }
    }
    
    public SymbolicExpression visit(Assignment n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs();
        
        if(right.isConstant()) {
            throw new IllegalExpressionException("Error: cannot redefine " + right.getValue());
        } else {
            env.put((Variable) right, left);
        }
        return left;
    }
    
    public SymbolicExpression visit(Sin n) {
        SymbolicExpression arg = n.arg().accept(this);
        
        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }
    
    public SymbolicExpression visit(Cos n) {
        SymbolicExpression arg = n.arg().accept(this);
        
        if (arg.isConstant()) {
            return new Constant(Math.cos(arg.getValue()));
        } else {
            return new Cos(arg);
        }
    }
    
    public SymbolicExpression visit(Log n) {
        SymbolicExpression arg = n.arg().accept(this);
        
        if (arg.isConstant()) {
            return new Constant(Math.log(arg.getValue()));
        } else {
            return new Log(arg);
        }
    }
    
    public SymbolicExpression visit(Exp n) {
        SymbolicExpression arg = n.arg().accept(this);
        
        if (arg.isConstant()) {
            return new Constant(Math.exp(arg.getValue()));
        } else {
            return new Exp(arg);
        }
    }
    
    public SymbolicExpression visit(Negation n) {
        SymbolicExpression arg = n.arg().accept(this);
        
        if (arg.isConstant()) {
            return new Constant(-(arg.getValue()));
        } else {
            return new Negation(arg);
        }
    }
    
    public SymbolicExpression visit(Constant n) {
       // SymbolicExpression arg = n.accept(this);
        
        return new Constant(n.getValue());
    }
    
    public SymbolicExpression visit(Variable n) {
        
        if(env.containsKey(n)) {
            return env.get(n);
        }
        else {
            return new Variable(n.toString());
        }
    }
    
    public SymbolicExpression visit(NamedConstant n) {
        return new NamedConstant(n.toString(), n.getValue());
    }
    
    public SymbolicExpression visit(Quit n) {
        return Quit.instance(); 
    }
    
    public SymbolicExpression visit(Clear n) {
        return Clear.instance(); 
    }
    
    public SymbolicExpression visit(Vars n) {
        return Vars.instance(); 
    }
    
    public SymbolicExpression visit(Ans n) {
        return Ans.instance(); 
    }
}
