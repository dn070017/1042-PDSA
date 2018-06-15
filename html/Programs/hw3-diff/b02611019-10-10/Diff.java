
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author S410
 */
public class Calculator {

//    static double a;
//    static String push;
    static Stack<String> st;
    public Calculator(){
    }
    
    public static double Operate(String op, double a, double b) {
        if (op.equals(""+"")) {
            return (a + b);
        } else if (op.equals(""-"")) {
            return (a - b);
        } else if (op.equals(""*"")) {
            return (a * b);
        } else if (op.equals(""/"")) {
            return (a / b);
        } else {
            return 0;
        }
    }

    public static Double ans(String e) {
        String[] str = e.split("" "");
        st = new Stack<String>();
        double a;
        String push;
        for (int i = 0; i < str.length; i++) {
            st.push(str[i]);
            if (str[i].equals("")"")) {
                st.pop();
                a = Double.parseDouble(st.pop());
                push = (String.valueOf(Calculator.Operate(st.pop(), Double.parseDouble(st.pop()), a)));
//                StdOut.print(push);
                st.pop();
                st.push(push);
            }
        }
        return Double.parseDouble(st.pop());
    }

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(args[0]));
//        String read= br.readLine();
//        StdOut.print(ans(read));
        

    }
}

