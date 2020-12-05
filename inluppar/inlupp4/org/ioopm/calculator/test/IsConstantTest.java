package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class IsConstantTest {
    private Random rng = new Random();
    private final EvaluationVisitor evaluator = new EvaluationVisitor();
    private Environment env; 
    private double value;
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
        env = new Environment(); 
        value = rng.nextDouble();
    }
    
    @Test
    public void isConstant() {
        Constant c = new Constant(value);
        assertTrue(c.isConstant());
    }
    
    @Test
    public void isConstantAddition() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Addition a = new Addition(c1, c2); 
        assertTrue(!a.isConstant());
        SymbolicExpression r = evaluator.evaluate(a, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantSubtraction() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Subtraction s = new Subtraction(c1, c2); 
        assertTrue(!s.isConstant());
        SymbolicExpression r = evaluator.evaluate(s, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantMultiplication() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Multiplication m = new Multiplication(c1, c2); 
        assertTrue(!m.isConstant());
        SymbolicExpression r = evaluator.evaluate(m, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantDivision() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Division d = new Division(c1, c2); 
        assertTrue(!d.isConstant());
        SymbolicExpression r = evaluator.evaluate(d, env, env);
        assertTrue(r.isConstant());
    }
    
   @Test
    public void isConstantAssignment() {
        Constant c1 = new Constant(value);
        Variable v = new Variable("x");
        Assignment a = new Assignment(c1, v);
        assertTrue(!a.isConstant());
        SymbolicExpression r = evaluator.evaluate(a, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantCos() {
        Constant c1 = new Constant(value);
        Cos c = new Cos(c1);
        assertTrue(!c.isConstant());
        SymbolicExpression r = evaluator.evaluate(c, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantSin() {
        Constant c1 = new Constant(value);
        Sin s = new Sin(c1);
        assertTrue(!s.isConstant());
        SymbolicExpression r = evaluator.evaluate(s, env, env);
        assertTrue(r.isConstant());
    }
   
   @Test
    public void isConstantExp() {
        Constant c1 = new Constant(value);
        Exp e = new Exp(c1);
        assertTrue(!e.isConstant());
        SymbolicExpression r = evaluator.evaluate(e, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantLog() {
        Constant c1 = new Constant(value);
        Log l = new Log(c1);
        assertTrue(!l.isConstant());
        SymbolicExpression r = evaluator.evaluate(l, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantNegation() {
        Constant c1 = new Constant(value);
        Negation n = new Negation(c1);
        assertTrue(!n.isConstant());
        SymbolicExpression r = evaluator.evaluate(n, env, env);
        assertTrue(r.isConstant());
    }
    
    @Test
    public void isConstantVariable() {
        Variable x = new Variable("x");
        assertTrue(!x.isConstant());
    }
}