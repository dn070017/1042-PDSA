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
    private Stack<String>    stackVal = new Stack<String>();
    private Stack<Character> stackOpr = new Stack<Character>();
    
    public void displayOpr() {
        /* print out stack of operator */
        System.out.println(""Print operand in Stack"");
        for (Character opr : stackOpr) { System.out.print(opr + "" ""); }
        System.out.println();
    } // end func displayOpr
    
    public void displayVal() {
        /* print out stack of value */
        System.out.println(""Print value in Stack"");
        for (String val : stackVal) { System.out.print(val + "" ""); }
        System.out.println();
    } // end func displayVal
    
    private void cal(){
        /* perform calculation */
        // pop out two value and one operator from the stacks
        Character opr;
        String val01, val02;
        Double res;
        if (stackVal.size() != 0){
            val02 = stackVal.pop();
        } else {
            val02 = null;
        } // end if-else
        
        if (stackVal.size() != 0){
            val01 = stackVal.pop();
        } else {
            val01 = null;
        } // end if-else
        
        if (val01 == null || val02 == null) return;
        opr = stackOpr.pop();
        
        // calculate the result and push into stack of values
        switch(opr) {
            case '+':
                //System.out.println('+');
                res = Double.valueOf(val01) + Double.valueOf(val02);
                stackVal.push(String.valueOf(res));
                break;
            case '-':
                //System.out.println('-');
                res = Double.valueOf(val01) - Double.valueOf(val02);
                stackVal.push(String.valueOf(res));
                break;
            case '*':
                //System.out.println('*');
                res = Double.valueOf(val01) * Double.valueOf(val02);
                stackVal.push(String.valueOf(res));
                break;
            case '/':
                //System.out.println('/');
                res = Double.valueOf(val01) / Double.valueOf(val02);
                stackVal.push(String.valueOf(res));
                break;
        } // end switch
    } // end func cal
    
    public Double ans (String e) {
        /* iterate through the input string and perform calculation */
        // http://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
        
        String  val = """";
        for (int i = 0; i < e.length(); i++){
            // foreach character in input string
            char c = e.charAt(i);
            
            // deal with space and bracket
            if (c == ' '){ continue; }
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
                    stackVal.push(val);
                } // end inner if
                
                // perform calculation
                if (stackOpr.size() != 0){
                    cal();
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
                    stackVal.push(val);
                    //System.out.println(c + val);
                } // end if
                
                // case 1&2
                stackOpr.push(c);
                
                // initialize value
                val = """";
                continue;
            } // end if
            
            // if nothing
            val += c;
            // http://stackoverflow.com/questions/8172420/how-to-convert-a-char-to-a-string-in-java
            
        } // end for loop
        //displayVal();
        //displayOpr();
        if (stackVal.size() != 0){
            return Double.valueOf(stackVal.pop());
        } else {
            return 0.0;
        } // end if-else
    } // end func ans
    
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal.ans(""(((1+(12*5))-(3*4))+(4/5))""));
        System.out.println(cal.ans(""(((1+(12*5))-(3*4))+((4/5)))""));
        System.out.println(cal.ans(""((((1+(12*5))-(3*4)))+((4/5)))""));
        System.out.println(cal.ans(""(-9)+8""));
        System.out.println(cal.ans(""()""));
        //System.out.println(Double.valueOf(""1"") == -1.0);
        //System.out.println(Double.valueOf(""-1"") == -1.0);
    } // end main
} // 

