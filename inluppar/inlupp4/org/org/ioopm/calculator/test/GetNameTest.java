package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class GetNameTest {
    private Random rng = new Random();
    private Environment env = null; 
    private double value;
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
        value = rng.nextDouble();
    }
    
    @Test
    public void getNameAddition() {
        String s = " + ";
        Constant c = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Addition add = new Addition(c, c2);
        assertEquals(s, add.getName());
    }
    
    @Test
    public void getNameSubtraction() {
        String s = " - ";
        Constant c = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Subtraction sub = new Subtraction(c, c2);
        assertEquals(s, sub.getName());
    }
    
    @Test
    public void getNameMultiplication() {
        String s = " * ";
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Multiplication m = new Multiplication(c1, c2);
        assertEquals(s, m.getName());
    }
    
    @Test
    public void getNameDivision() {
        String s = " / "; 
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Division d = new Division(c1, c2); 
        assertEquals(s, d.getName());
    }
    
   @Test
    public void getNameAssignment() {
        String s = " = "; 
        Constant c1 = new Constant(value);
        Variable v = new Variable("x");
        Assignment a = new Assignment(c1, v);
        assertEquals(s, a.getName());
    }
    
   @Test
    public void getNameCos() {
        String s = "cos ";
        Constant c = new Constant(value);
        Cos cos = new Cos(c);
        assertEquals(s, cos.getName());
    }
    
    @Test
    public void getNameSin() {
        String s = "sin ";
        Constant c = new Constant(value);
        Sin sin = new Sin(c);
        assertEquals(s, sin.getName());
    }
   
   @Test
    public void getNameExp() {
        String s = "exp";
        Constant c = new Constant(value);
        Exp exp = new Exp(c);
        assertEquals(s, exp.getName());
    }
    
    @Test
    public void getNameLog() {
        String s = "log ";
        Constant c1 = new Constant(value);
        Log l = new Log(c1);
        assertEquals(s, l.getName());
    }
    
    @Test
    public void getNameNegation() {
        String s = "-";
        Constant c = new Constant(value);
        Negation neg = new Negation(c);
        assertEquals(s, neg.getName());
    }
}
    
 