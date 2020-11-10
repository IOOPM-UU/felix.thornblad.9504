package org.ioopm.calculator.ast;

public class CalculatorAst {
    
    public abstract class SymbolicExpression {
        private String name; 
        private String[] subExpressions;
        /// The second argument allows us to pass in 0 or more arguments
        public SymbolicExpression(String name, Object... subExpressions) {
            this.name = name;
            this.subExpressions = new String[subExpressions.length];
            for (int i = 0; i < subExpressions.length; ++i) {
                this.subExpressions[i] = subExpressions[i].toString();
            }
        }

        /// Returns e.g., "Constant(42)" if name is "Constant" and subExpressions is ["42"]
        public String toString(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(");
        for (int i = 1; i < this.subExpressions.length; ++i) {
            sb.append(this.subExpressions[i]);
            if (i + 1 < subExpressions.length) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString(); 
        }
        
    
       
    
      

        public abstract class Binary extends SymbolicExpression {
            private SymbolicExpression lhs = null;
            private SymbolicExpression rhs = null;
        
             public class Addition extends Binary{
       
            }
            
            public class Subtraction extends Binary{
       
            }
            
            public class Multiplication extends Binary {
       
            }
            
            public class Division extends Binary {
                
            } 
            
            public class Assignment extends Binary{
                
            }
        }
        
        
        public abstract class Unary extends SymbolicExpression {
    
            public class Sin extends Unary {
                
            }
            
            public class Cos extends Unary {
                
            }
            
            public class Exp extends Unary {
                
            }
            
            public class Log extends Unary {
                
            }
            
            public class Negation extends Unary {
                
            }
        }
        
        public abstract class Command extends SymbolicExpression {
        
            public class Vars extends Command {
            
            }
        
            public class Quit extends Command {
            
            }
        }
        
        public abstract class Atom extends SymbolicExpression {
            
           private class Constant extends Atom {
                private double value; 
           }
           
            private class Variable extends Atom {
                private String identifier;
            }
        }
    
    }

}