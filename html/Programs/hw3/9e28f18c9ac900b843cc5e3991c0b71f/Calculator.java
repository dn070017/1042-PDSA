/*
.
 * To change this template file, choose Tools | Templates
.
 */
package calculator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 *
 * @author Po-Lin
 */
public class Calculator {

    private Stack<String> stack = new Stack<String>();

    /**
     * @param args the command line arguments
     */
    public Double ans(String e) {
        double answer = 0.0;
        String[] charstore;
        String tempcharstore;
        String tempcalculator;
        double tempnum1 = 0.0, tempnum2 = 0.0, tempresult;
        charstore = e.split("" "");
        for (int i = 0; i < charstore.length; i++) {
            tempcharstore=charstore[i];
            if (tempcharstore.equals("")"")!=true) {
                stack.push(charstore[i]);
            } else{
                tempnum2 = Double.valueOf(stack.pop());
                tempcalculator = stack.pop();
                tempnum1 = Double.valueOf(stack.pop());
                stack.pop();  //remove the nearest '('
                switch (tempcalculator) {
                    case ""+"":
                        tempresult = tempnum1 + tempnum2;
                        stack.push(String.valueOf(tempresult));
                        break;
                    case ""-"":
                        tempresult = tempnum1 - tempnum2;
                        stack.push(String.valueOf(tempresult));
                        break;
                    case ""*"":
                        tempresult = tempnum1 * tempnum2;
                        stack.push(String.valueOf(tempresult));
                        break;
                    case ""/"":
                        tempresult = tempnum1 / tempnum2;
                        stack.push(String.valueOf(tempresult));
                        break;
                }
            }
        }
        System.out.println(stack.pop());
        //answer = Double.valueOf(stack.pop());
        return answer;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        String e = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";
        Calculator ccs = new Calculator();
        ccs.ans(e);
    }

}

