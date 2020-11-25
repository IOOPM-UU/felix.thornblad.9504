package org.ioopm.calculator.test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class GetValueTest {
    private Random rng = new Random();
    
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
    }
    
    @Test
    public void getValueConstant() {
        double value = rng.nextDouble();
        Constant c = new Constant(value);
        assertEquals(value, c.getValue(), 0f);
    }
}