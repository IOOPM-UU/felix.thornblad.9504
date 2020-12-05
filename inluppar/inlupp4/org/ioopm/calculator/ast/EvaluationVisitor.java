package org.ioopm.calculator.ast; 
import java.util.Stack;
import java.util.ArrayList;

public class EvaluationVisitor implements Visitor {
    private Environment env; 
    private Environment functions;
    private Stack<Environment> stack;

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env, Environment functions) {
        this.env = env;
        this.functions = functions;
        this.stack = new Stack<Environment>(); 
        this.stack.push(env);
        
        return topLevel.accept(this);
    }
    
    public SymbolicExpression visit(Sequence n) {
        SymbolicExpression result = null;
        for (SymbolicExpression e : n.body()) {
            result = e.accept(this);
        }
        
        return result;
    }
    
    public SymbolicExpression visit(FunctionCall n) {
        FunctionDeclaration fun = (FunctionDeclaration) functions.get(n.identifier());
        Sequence seq = fun.sequence();
        if(!(seq.parameters().size() == n.arguments().size())) {
            throw new IllegalExpressionException("Wrong amount of arguments!"); 
        }
        int i = 0;
        for(SymbolicExpression arg : n.arguments()) {
            if (arg instanceof Atom) {
                (new Assignment(arg, new Variable(seq.parameters().get(i)))).accept(this);
                i++;
            } else {
                throw new IllegalExpressionException("expected constant or variable");
            }
        }
        SymbolicExpression result = seq.accept(this);
        return result;
    }
   
    
    public SymbolicExpression visit(FunctionDeclaration n) {
        SymbolicExpression sequence = n.sequence().accept(this);
        return sequence;
    }
    
    public SymbolicExpression visit(Conditional n) {
        SymbolicExpression left = n.left().accept(this);
        SymbolicExpression right = n.right().accept(this);
        
        if(!(left.isConstant() && right.isConstant())) {
            throw new IllegalExpressionException("variables not defined"); 
        }
        
        double differance = left.getValue() - right.getValue();
        if (differance == 0 && n.operation().equals("==") ||
           differance <= 0 && n.operation().equals("<=") ||
           differance >= 0 && n.operation().equals(">=") || 
           differance < 0 && n.operation().equals("<")   ||
           differance > 0 && n.operation().equals(">")) 
        {
            return n.ifTrue().accept(this);     
        }
        return n.ifFalse().accept(this); 
    }

    public SymbolicExpression visit(Scope n) {
        this.stack.push(new Environment());
        SymbolicExpression arg = n.arg().accept(this);
        this.stack.pop();
        return arg;
    }
    
    public SymbolicExpression visit(Addition n) {
        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);
        
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
        
        Environment currentEnv = this.stack.peek();
        currentEnv.put((Variable) right, left); 
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
        return new Constant(n.getValue());
    }
    
    public SymbolicExpression visit(Variable n) {
        for(int i = this.stack.size(); i > 0; i--) {
            Environment currentEnv = this.stack.get(i - 1);
            if(currentEnv.containsKey(n)) {
                return currentEnv.get(n);
            }
        }
        return new Variable(n.toString());
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
