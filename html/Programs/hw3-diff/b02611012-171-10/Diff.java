

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class Calculator {
    public Double answer;
    public Double ans(String e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String ss[] = e.split("" "");
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            if (s.equals(""("")) { 
                continue;
            }
            else if (s.equals(""+"")) {
                ops.push(s);
            }
            else if (s.equals(""-"")) {
                ops.push(s);
            }
            else if (s.equals(""*"")) {
                ops.push(s);
            }
            else if (s.equals(""/"")) {
                ops.push(s);
            }
            else if (s.equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } 
                else if (op.equals(""-"")) {
                    Double a = vals.pop();
                    Double b = vals.pop();
                    vals.push(b - a);
                }
                else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                }
                else if (op.equals(""/"")) {
                    Double a = vals.pop();
                    Double b = vals.pop();
                    vals.push(b / a);
                }
            }
            else vals.push(Double.parseDouble(s));
        }
        answer = vals.pop();
        //StdOut.println(answer);
        return answer;
    }

    /**
     *
     * @param args
     */
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

