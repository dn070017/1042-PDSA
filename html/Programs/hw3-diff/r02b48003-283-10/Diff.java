//import edu.princeton.cs.algs4.Stack;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author clint
 */
public class Calculator {
    private Stack<String> stackOpr = new Stack<String>();
    private Stack<Double> stackVal = new Stack<Double>();
    
    public Double ans(String e){
        // split the Arithmetic expression
        String[] exprs = e.split("" "");        
        for (String s : exprs) {
            //System.out.println(s);
            
            // ignore the left bracket
            if (s.equals(""("")) { continue; }
            
            // push each operator into the stack of operators
            if (s.equals(""+"") || s.equals(""-"") || s.equals(""*"") || s.equals(""/"")) {
                stackOpr.push(s);
                continue;
            } // end if
            
            // calculate if right charactor
            if (s.equals("")"")) {
                Double res   = 0.0;
                Double val02 = stackVal.pop();
                Double val01 = stackVal.pop();
                String opr   = stackOpr.pop();
                if (opr.equals(""+"")){ res = val01 + val02; }
                if (opr.equals(""-"")){ res = val01 - val02; }
                if (opr.equals(""*"")){ res = val01 * val02; }
                if (opr.equals(""/"")){ res = val01 / val02; }
                stackVal.push(res);
                continue;
            } // end if
            
            // non of above, the string contains the value
            // push the value into the stack of value
            stackVal.push(Double.valueOf(s));
            
        } // end for loop
        
        // return the value remains in the stack of value
        // ideally, the stack should contain only
        return stackVal.pop();
    } // end func ans
    
    public static void main(String[] args){
        Calculator cct = new Calculator();
        //cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "");
        System.out.println(cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) ""));
    } // end func main
}

