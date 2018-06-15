/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
import java.io.FileReader;
import java.io.BufferedReader;

public class Calculator {

    public Double ans(String e) {
        double a = 0.0;
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < e.split("" "").length; i++) {
            String c = e.split("" "")[i];
            s.push(c);
            //System.out.print(c+"" "");

            if (s.iterator().next().equals("")"")) {
                s.pop();
                double x=Double.parseDouble(s.pop());
                String cal=s.pop();
                double y=Double.parseDouble(s.pop());
                s.pop();
                if(cal.equals(""+""))x=y+x;
                if(cal.equals(""-""))x=y-x;
                if(cal.equals(""*""))x=y*x;
                if(cal.equals(""/""))x=y/x;
                s.push(String.valueOf(x));
                //System.out.print(x);
            }
                //System.out.print(1);

        }
        a=Double.parseDouble(s.pop());
        return a;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            Calculator cct = new Calculator();
            String b = br.readLine();
            System.out.print(cct.ans(b));
            /*String aaa="")"";
             System.out.print(aaa.equals("")""));*/
            //System.out.print(b.split("" "").length);

        }
    }
}

