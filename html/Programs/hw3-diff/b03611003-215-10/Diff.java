/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author 士齊
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public Double ans(String e) {
.
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
 
        String[] le = e.split(""\\s+"");
        for (int i = 0; i < le.length; i++) {

            if (le[i].equals(""("")) {
                ;
            } else if (le[i].equals(""+"")) {
                ops.push(le[i]);
            } else if (le[i].equals(""-"")) {
                ops.push(le[i]);
            } else if (le[i].equals(""*"")) {
                ops.push(le[i]);
            } else if (le[i].equals(""/"")) {
                ops.push(le[i]);
            } else if (le[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""-"")) {
                    vals.push( -vals.pop() + vals.pop());
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""/"")) {
                    vals.push( 1/vals.pop() * vals.pop());
                }
            } else {
                vals.push(Double.parseDouble(le[i]));
            }
        }
      
//        System.out.print(vals.pop());

        return vals.pop();
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Calculator cct = new Calculator();
            String s = br.readLine();
            
            
    
        }
    }

}

