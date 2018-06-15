package calculator;

//import edu.princeton.cs.algs4.Stack;
import java.io.BufferedReader;
import java.io.FileReader;

public class Calculator {
    
    public Double ans(String e) {
        Stack<Double> vals = new Stack<Double>();
        Stack<String> ops = new Stack<String>();
        String[] ee = e.split("" "");
        for (int i = 0; i < ee.length; i++) {
            if (ee[i].equals(""("")) {             
            } else if (ee[i].equals(""+"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""-"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""*"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""/"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""-"")) {
                    vals.push((-1 * vals.pop()) + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""/"")) {
                    vals.push((1 / vals.pop()) * vals.pop());
                }
            } else {
                vals.push(Double.parseDouble(ee[i])); 
            }
        }
        return (vals.pop());
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Calculator cct = new Calculator();
            String b = br.readLine();
            System.out.print(cct.ans(b));
        }
    }
}

