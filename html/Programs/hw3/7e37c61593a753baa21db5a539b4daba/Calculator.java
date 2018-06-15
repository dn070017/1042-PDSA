import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Calculator {
    public static void main(String args[]) throws Exception {
        
    }
    public  Double ans (String e) {
        String[]  q = e.split("" "");
        int i =0;
        int x=0;
        for(String w: q)
        {
            i++;
        }
    Stack<String> ops = new Stack<>();
    Stack<Double> vals = new Stack<>();
    while (x<i) {
    String s = q[x];
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
    x++;
    }
.
      return vals.pop();
    }
}


