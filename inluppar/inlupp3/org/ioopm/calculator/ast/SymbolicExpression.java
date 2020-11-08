package org.ioopm.calculator.ast;

    
    public abstract class SymbolicExpression {
        private String[] subExpressions;
        /// The second argument allows us to pass in 0 or more arguments
        public SymbolicExpression(Object... subExpressions) {
            this.subExpressions = new String[subExpressions.length];
            for (int i = 0; i < subExpressions.length; ++i) {
                this.subExpressions[i] = subExpressions[i].toString();
            }
        }
        
      
        /// Returns e.g., "Constant(42)" if name is "Constant" and subExpressions is ["42"]
        public String toString(String msg) {
        StringBuilder sb = new StringBuilder();
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
        
        public String[] getSubExpressions()
        {
            return subExpressions;
        }
        
        public Boolean isConstant(){
            return false; 
        }
        
        //ska ge error
        
        public String getName(){
            throw new RuntimeException("getName() called on expression with no operator");
        }
        
        
        public int getPriority() {
            return 0;
        }
        
        public double getValue() {
            throw new RuntimeException("getValue() called on expression that is not an constant");
        }
    }

