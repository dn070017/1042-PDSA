/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author daf
 */
//import edu.princeton.cs.algs4.Stack;

public class Calculator {
    
    public Double ans(String e){
        //e = ""( ( ( ( 1 + ( 12 * 7 ) ) - ( 3 * 12 ) ) + ( 4 / 5 ) ) - ( 9 - 5 ) )"";
        Stack<String> ops = new Stack<String>(); 
        Stack<Double> vals = new Stack<Double>();
        String[] r = e.split(""\\s+"");
        
        for(int i = 0; i<r.length; i++){
            if(r[i].equals(""("")) ;
            else if(r[i].equals(""+"")) ops.push(r[i]);
            else if(r[i].equals(""-"")) ops.push(r[i]);
            else if(r[i].equals(""*"")) ops.push(r[i]);
            else if(r[i].equals(""/"")) ops.push(r[i]);
            else if (r[i].equals("")"")) {
                String op = ops.pop();
                double b = vals.pop();
                double a = vals.pop();
                if(op.equals(""+"")) vals.push(a+b);
                else if(op.equals(""-"")) vals.push(a-b);
                else if(op.equals(""*"")) vals.push(a*b);
                else if(op.equals(""/"")) vals.push(a/b);
            }
            else {
                vals.push(Double.parseDouble(r[i]));
            } 
        }
            
        return vals.pop();
    }
    
    public static void main(String[] args){
        Calculator cal = new Calculator();
        StdOut.println(cal.ans(""""));
    }
}

