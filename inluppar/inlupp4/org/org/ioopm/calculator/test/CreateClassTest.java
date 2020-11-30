package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertNotNull;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class CreateClassTest {
    private Random rng = new Random();
    private double value;
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
        value = rng.nextDouble();
    }
    
    @Test
    public void createConstant() {
        Constant c = new Constant(value);
        assertNotNull(c);
    }
    
    @Test
    public void createVariable() {
        Variable v = new Variable("x");
        assertNotNull(v);
    }
    
    @Test
    public void createAddition() {
        Addition a = new Addition(new Constant(value), new Constant(rng.nextDouble()));
        assertNotNull(a);
    }
    
    @Test
    public void createSubtraction() {
        Subtraction s = new Subtraction(new Constant(value), new Constant(rng.nextDouble()));
        assertNotNull(s);
    }
    
    @Test
    public void createMultiplication() {
        Multiplication m = new Multiplication(new Constant(value), new Constant(rng.nextDouble()));
        assertNotNull(m);
    }
    
    @Test
    public void createDivision() {
        Division d = new Division(new Constant(value), new Constant(rng.nextDouble()));
        assertNotNull(d);
    }
    
    @Test
    public void createAssignment() {
        Assignment a = new Assignment(new Constant(value), new Variable("x"));
        assertNotNull(a);
    }
    
    @Test
    public void createSin() {
        Sin s = new Sin(new Constant(value));
        assertNotNull(s);
    }
    
    @Test
    public void createCos() {
        Cos c = new Cos(new Constant(value));
        assertNotNull(c);
    }
   
   @Test
    public void createNeg() {
        Negation n = new Negation(new Constant(value));
        assertNotNull(n);
    }
    
   @Test
    public void createExp() {
        Exp e = new Exp(new Constant(value));
        assertNotNull(e);
    }
    
   @Test
    public void createLog() {
        Log l = new Log(new Constant(value));
        assertNotNull(l);
    }
}