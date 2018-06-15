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
    private Stack<Double>    stackVal = new Stack<Double>();
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
        for (Double val : stackVal) { System.out.print(val + "" ""); }
        System.out.println();
    } // end func displayVal
    
    
    //private Double cal(String str01, String str02, Character opr) {
    private Double cal(Double val01, Double val02, Character opr) {
        //Double val01 = Double.valueOf(str01);
        //Double val02 = Double.valueOf(str02);
        Double res = 0.0;
        
        switch(opr) {
            case '+':
                //System.out.println('+');
                res = val01 + val02;
                break;
            case '-':
                //System.out.println('-');
                res = val01 - val02;
                break;
            case '*':
                //System.out.println('*');
                res = val01 * val02;
                break;
            case '/':
                //System.out.println('/');
                //if (Double.valueOf(val02) == 0.0) return;
                res = val01 / val02;
                break;
        } // end switch
        return res;
    } // end function cal
            
    
    private void calculate(){
        /* perform calculation */
        // pop out two value and one operator from the stacks
        Character opr;
        //String val01, val02;
        Double val01, val02;
        Double res;
        //*
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
        
        // in case something happen
        if (val01 == null || val02 == null) return;
        //*/
        opr = stackOpr.pop();
        //val01 = 10.0;
        //val02 = 20.0;
        // calculate the result and push into stack of values
        //stackVal.push(String.valueOf(cal(val01, val02, opr)));
        stackVal.push(cal(val01, val02, opr));
        //*/
        
    } // end func cal
    
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
                    calculate();
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
            return stackVal.pop();
        } else {
            return 0.0;
        } // end if-else
    } // end func ans
    /*
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
        System.out.println(cct.ans(""(5/0)""));
        System.out.println(cct.ans(""(-9)+8""));
        System.out.println(cct.ans(""()""));
        //cct.ans02(""(((1+(12*5))-(3*4))+(4/5))"");
        //System.out.println(Double.valueOf(""1"") == -1.0);
        //System.out.println(Double.valueOf(""-1"") == -1.0);
    } // end main
    */
} // 

