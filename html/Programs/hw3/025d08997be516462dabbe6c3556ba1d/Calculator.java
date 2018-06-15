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
    // for first method
    private Stack<Double> stackVal = new Stack<Double>();
    private Stack<String> stackOpr = new Stack<String>();
    
    public void displayOpr() {
        /* print out stack of operator */
        System.out.println(""Print operand in Stack"");
        for (String opr : stackOpr) { System.out.print(opr + "" ""); }
        System.out.println();
    } // end func displayOpr
    
    public void displayVal() {
        /* print out stack of value */
        System.out.println(""Print value in Stack"");
        for (Double val : stackVal) { System.out.print(val + "" ""); }
        System.out.println();
    } // end func displayVal
    
    public Double ans (String e) {
        /* iterate through the input string and perform calculation */
        // http://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
        
        String  val = """";
        for (int i = 0; i < e.length(); i++){
            // foreach character in input string
            char c = e.charAt(i);
            
            // deal with space and bracket
            if (c == ' '){ continue; } // 如果不加上這一條，1, 6, 7 test 會當掉
            if (c == '('){ continue; }
            if (c == ')'){ 
                // two cases when encounter ')'
                // case 1: '(1+5)'      
                // => push 5 into value stack and perform calculation
                //                      
                // case 2: '(...(...))' sequential right bracket
                // => perform calculation only
                
                // push value if necessary
                if (!val.equals("""")) { 
                    //System.out.println("")"" + val);
                    stackVal.push(Double.parseDouble(val));
                } // end inner if
                
                // perform calculation
                if (stackOpr.size() != 0){
                    Double res   = 0.0;
                    Double val02 = stackVal.pop();
                    Double val01 = stackVal.pop();
                    String opr   = stackOpr.pop();
                    if (opr.equals(""+"")){ res = val01 + val02; }
                    if (opr.equals(""-"")){ res = val01 - val02; }
                    if (opr.equals(""*"")){ res = val01 * val02; }
                    if (opr.equals(""/"")){ res = val01 / val02; }
                    stackVal.push(res);
                } // end if
                
                // initialize value
                val = """";
                continue;
            } // end outer if
            
            // deal with operators
            if (c == '+' || c == '-' || c == '*' || c == '/'){
                // two cases when encounter operation
                // case 1: '5+...'      
                // => push 5 into value stack; push operator into oprator stack
                //                      
                // case 2: '...)+' right after right bracket
                // => push operator into oprator stack
                
                // case 1
                if (!val.equals("""")) {
                    //stackVal.push(val);
                    stackVal.push(Double.parseDouble(val));
                    //System.out.println(c + val);
                } // end if
                
                // case 1&2
                stackOpr.push(String.valueOf(c));
                
                // initialize value
                val = """";
                continue;
            } // end if
            
            // if nothing
            val += c;
            // http://stackoverflow.com/questions/8172420/how-to-convert-a-char-to-a-string-in-java   
        } // end for loop
        
        return stackVal.pop();
    } // end func ans
    //*
    public static void main(String[] args) {
        Calculator cct = new Calculator();
        System.out.println(cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) ""));
        System.out.println(cct.ans(""( ( ( ( 2 / 3 ) + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) ""));
        System.out.println(cct.ans(""(0-3)""));
        System.out.println(cct.ans(""(((1+(12*5))-(3*4))+(4/5))""));
        System.out.println(cct.ans(""(1-3)""));
        System.out.println(cct.ans(""(1/3)""));
        System.out.println(cct.ans(""(((1+(12*5))-(3*4))+((4/5)))""));
        System.out.println(cct.ans(""((((1+(12*5))-(3*4)))+((4/5)))""));
    } // end main
    //*/
} // 

