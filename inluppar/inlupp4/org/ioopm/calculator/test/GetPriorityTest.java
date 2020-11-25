package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class GetPriorityTest {
    private Random rng = new Random();
    private Environment env = null; 
    private double value;
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
        value = rng.nextDouble();
    }
    
    @Test
    public void getPriorityAddDiv() {
        Constant c1 = new Constant(value);
        Constant c2 = new Constant(rng.nextDouble());
        Addition a = new Addition(c1, c2); 
        Division d = new Division(c1, c2);
        Variable x = new Variable("x");
        Assignment ass = new Assignment(c1, x);
        Cos cos = new Cos(c1);
        Exp exp = new Exp(c1);
        Log log = new Log(c1);
        Negation neg = new Negation(c1);
        assertTrue(ass.getPriority() < cos.getPriority());  //0<25
        assertTrue(cos.getPriority() < a.getPriority());  //25<50
        assertTrue(a.getPriority() < d.getPriority());   //50<100
        assertTrue(d.getPriority() < exp.getPriority());   //100<200
        assertTrue(exp.getPriority() < log.getPriority());  //200<250
        assertTrue(log.getPriority() < neg.getPriority());  //250<300
        assertTrue(neg.getPriority() < x.getPriority());  //300<350
    }
    
    
}