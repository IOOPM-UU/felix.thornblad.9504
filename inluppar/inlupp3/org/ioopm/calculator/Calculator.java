import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.util.Scanner;
import java.io.IOException;

/**
 * Taking input from the promt and evaluating it using CalculatorParser and the ast
 */ 

public class Calculator {
    public static void main(String [] args) {
        Environment vars = new Environment();
        final CalculatorParser parse = new CalculatorParser();
        int fullEval = 0;
        int enterd = 0;
        int evaluated = 0;
        double lastValue = 0;
        System.out.println("Welcome to our java claculator! Start by entering an expression or command.");
        
        
        while (true) {
            Environment save = vars;
            String input = System.console().readLine();
            try {
                SymbolicExpression parsed = parse.parse(input, vars);
                if(parsed.isCommand()) {
                    if (parsed instanceof Quit){
                        break;
                    }
                    else if(parsed instanceof Clear) {
                        vars = new Environment();
                    }
                    
                    else if (parsed instanceof Vars){
                        System.out.println(vars);
                    }
                    else if (parsed instanceof Ans){
                        System.out.println(lastValue);
                    }
                }
                else{
                    SymbolicExpression evaled = parsed.eval(vars);
                    if(evaled instanceof Warning) { //if an IllegalExpressionException has been encountered, go to next cycle
                        enterd ++;
                        continue;
                    }
                    System.out.println(evaled);
                    enterd ++;
                    evaluated ++;
                    if(evaled instanceof Constant){
                        lastValue = evaled.getValue();
                        vars.put("ans",new Constant (lastValue));
                        fullEval ++;
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Cannot parse ");
                vars = save;
            }
        }
    System.out.println("Thanks for using Java Calculator");
    System.out.println("You have enterd " + enterd + " expression,");
    System.out.println("of which " + evaluated + " were eveluated and " + fullEval + " fully evaluated.");
    }
}
