package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class IsCommandTest {
    private Random rng = new Random();
    private Environment env = null; 
    private double value;
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
        value = rng.nextDouble();
    }
    
    @Test
    public void isCommandQuit() {
        Quit q = Quit.instance();
        assertTrue(q.isCommand());
    }
    
   @Test
    public void isCommandVars() {
        Vars v = Vars.instance();
        assertTrue(v.isCommand());
    }
    
   @Test
    public void isCommandAns() {
        Ans a = Ans.instance();
        assertTrue(a.isCommand());
    }
   @Test
    public void isCommandClear() {
        Clear c = Clear.instance();
        assertTrue(c.isCommand());
    }
    
    @Test
    public void isCommandConstant() {
        Constant c = new Constant(value);
        assertTrue(!c.isCommand()); 
    }
    
    @Test
    public void isCommandAddition() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Addition a = new Addition(c1, c2); 
        assertTrue(!a.isCommand());
    }
    
    @Test
    public void isCommandSubtraction() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Subtraction s = new Subtraction(c1, c2); 
        assertTrue(!s.isCommand());
    }
    
    @Test
    public void isCommandMultiplication() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Multiplication m = new Multiplication(c1, c2); 
        assertTrue(!m.isCommand());
    }
    
    @Test
    public void isCommandDivision() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Division d = new Division(c1, c2); 
        assertTrue(!d.isCommand());
    }
    
    @Test
    public void isCommandAssignment() {
        Constant c = new Constant(value);
        Variable v = new Variable("x");
        Assignment a = new Assignment(c, v); 
        assertTrue(!a.isCommand());
    }
    
    @Test
    public void isCommandSin() {
        Constant c = new Constant(value);
        Sin s = new Sin(c); 
        assertTrue(!s.isCommand());
    }
    
    @Test
    public void isCommandCos() {
        Constant c = new Constant(value);
        Cos cos = new Cos(c); 
        assertTrue(!cos.isCommand());
    }
    
    @Test
    public void isCommandLog() {
        Constant c = new Constant(value);
        Log l = new Log(c); 
        assertTrue(!l.isCommand());
    }

    @Test
    public void isCommandExp() {
        Constant c = new Constant(value);
        Exp e = new Exp(c); 
        assertTrue(!e.isCommand());
    }
    
    @Test
    public void isCommandNegation() {
        Constant c = new Constant(value);
        Negation n = new Negation(c); 
        assertTrue(!n.isCommand());
    }
}