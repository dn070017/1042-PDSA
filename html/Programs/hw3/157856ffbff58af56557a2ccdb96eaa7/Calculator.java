
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.*;

public class Calculator {

    //StackOfStrings stack = new StackOfStrings();
    public Double ans(String e) {

        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        //StdOut.println(e.length());
        int i = 0;
        String[] ch = e.split("" "");
        while(i < ch.length-1){
            //StdOut.println(""bug"");
            
//            if ((ch = e.split("" ""))==null) {
//                StdOut.println(""break!!!"");
//                break;
//            }
           i++;
            //e = StdIn.readString();
            //ch = e.split("" "");
            
            //StdOut.println(ch.length);
            //StdOut.println(ch[i]);
            if (ch[i].equals(""("")) {
                //StdOut.println(""1"");
            } else if (ch[i].equals(""+"")) {
                ops.push(ch[i]);
                //StdOut.println(""2"");
            } else if (ch[i].equals(""*"")) {
                ops.push(ch[i]);
                //StdOut.println(""3"");
            } else if (ch[i].equals(""/"")) {
                ops.push(ch[i]);
                //StdOut.println(""3"");
            } else if (ch[i].equals(""-"")) {
                ops.push(ch[i]);
                //StdOut.println(""3"");
            } else if (ch[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {                    
                    vals.push(vals.pop() + vals.pop());
                    //StdOut.println(""4"");
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                    //StdOut.println(""5"");
                } else if (op.equals(""/"")) {
                    double one = vals.pop();
                    double two = vals.pop();
                    vals.push(two / one);
                    //StdOut.println(""5"");
                } else if (op.equals(""-"")) {
                    double one = vals.pop();
                    double two = vals.pop();
                    vals.push(two - one);
                    //StdOut.println(""5"");
                }

            } else {
                vals.push(Double.parseDouble(ch[i]));
                //StdOut.println(""6"");
            }

            
            
        }

        double ans = vals.pop();
        //StdOut.println(ans);
//StdOut.println(""aaa"");
        //Calculator ccl = new Calculator();
        //LinkedStackOfStrings linkedstack = ccl.new LinkedStackOfStrings();

        return ans;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Calculator cct = new Calculator();
            String input;
            input = br.readLine();
            //System.out.printf(input);
            cct.ans(input);

            //System.out.printf(""\n\n\n"");
            StdOut.println(cct.ans(input));
        } catch (IOException ex) {
            System.out.printf(""Failed to open the file"");

        }
    }
}

