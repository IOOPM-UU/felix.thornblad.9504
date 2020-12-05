package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class EqualsTest {
    private Random rng = new Random(); 
    private final EvaluationVisitor evaluator = new EvaluationVisitor();
    private Environment env = null; 
    private double value;
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
        value = rng.nextDouble();
    }
    
    @Test
    public void evalAddMulti() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(value*2);
        Addition a = new Addition(c1, c2); 
        Constant three = new Constant(3);
        Multiplication m = new Multiplication(c1, three);
        SymbolicExpression q = evaluator.evaluate(m, env, env);
        SymbolicExpression r = evaluator.evaluate(a, env, env);
        assertTrue((r.equals(q)));
        assertEquals(m.toString(), "" + value + " * " + three); 
    }
    
    @Test
    public void evalSubNeg() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(value*2);
        Negation neg = new Negation(c2);
        Addition a = new Addition(c1, c2); 
        Subtraction s = new Subtraction(c1, neg);
        SymbolicExpression q = evaluator.evaluate(a, env, env);
        SymbolicExpression r = evaluator.evaluate(s, env, env);
        assertTrue((r.equals(q)));
        assertEquals(s.toString(), "" + value + " - " + "(-" + c2.toString() + ")"); 
    }
    
    @Test 
    public void evalDivExp() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(value);
        Division d = new Division(c1, (new Exp(c2)));
        
        SymbolicExpression q = evaluator.evaluate(d, env, env);
        assertEquals(d.toString(), "" + c1 + d.getName() + "exp(" + c2 + ")");
    }
    
}
    
 