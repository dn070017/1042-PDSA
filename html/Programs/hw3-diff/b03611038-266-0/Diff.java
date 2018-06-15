/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Phoenix
 */

public class Calculator {
    public static void main(String args) throws Exception {
       
        Double i;
        i=0.0;
        System.out.println(i);
        
    }
    
    
    public Double ans (String e) {
    Stack<String> ops = new Stack<>();
    Stack<Double> vals = new Stack<>();
    while (!StdIn.isEmpty()) {
    String s = StdIn.readString();
    if (s.equals(""("")) ;
    else if (s.equals(""+"")) ops.push(s);
    else if (s.equals(""-"")) ops.push(s);
    else if (s.equals(""*"")) ops.push(s);
    else if (s.equals(""/"")) ops.push(s);
    else if (s.equals("")""))
    {
    String op = ops.pop();
    if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
    else if (op.equals(""-"")) vals.push(-(vals.pop() - vals.pop()));
    else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
    else if (op.equals(""/"")) vals.push(1/(vals.pop() / vals.pop()));
    }
    else vals.push(Double.parseDouble(s));
    }
.
      return vals.pop();
    }
}

