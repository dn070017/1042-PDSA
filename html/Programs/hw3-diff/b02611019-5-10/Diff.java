
import java.io.BufferedReader;
import java.io.FileReader;

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

    static public double Operate(String op, double a, double b) {
        if (op.equals(""+"")) {
            return (a + b);
        } else if (op.equals(""-"")) {
            return  (a - b);
        } else if (op.equals( ""*"")) {
            return (a * b);
        } else if (op.equals( ""/"")) {
            return  (a / b);
        }
        else
            return 0;
    }

    
    static public Double ans(String e) {
        String[] str=e.split("" "");
        Stack<String> st = new Stack<String>();
        double a;
        String push;
        for(int i=0;i<str.length;i++){
            st.push(str[i]);
            if (str[i].equals("")"")){
                st.pop();
                a=Double.parseDouble(st.pop());
                push = (String.valueOf(Calculator.Operate(st.pop(), Double.parseDouble(st.pop()),a)));
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
        /*BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String numstr = br.readLine();
        int num = Integer.parseInt(numstr);

        for (int i = 0; i < 1; i++) {
            String oper[] = br.readLine().split("" "");
            Stack<String> st = new Stack<String>();
            for (int k = 0; k < oper.length; k++) {
//                StdOut.print(oper[k]+""\n"");
                st.push(oper[k]);
                if (oper[k].equals("")"")) {
                    st.pop();
                    double a = Double.parseDouble(st.pop());
                    StdOut.print(a);
//                    StdOut.print(st.pop());
                    String push = (String.valueOf(Operate(st.pop(), a, Double.parseDouble(st.pop()))));

                    StdOut.print(push);
                    st.pop();
                    st.push(push);

                }
            }
        }*/

    }
}

