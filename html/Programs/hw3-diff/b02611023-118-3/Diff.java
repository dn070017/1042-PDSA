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

    /*public static void main(String[] args) {
     Calculator cal = new Calculator();
     double ans = cal.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
     System.out.println(ans);
     }*/
    public Double ans(String e) {
        String[] data = e.split("" "");
        String Str = ""\\d+\\.*\\d*"";
        String op = """";

        double ans = 0.0;

        LinkedStack<String> num = new LinkedStack<String>();

        for (int i = 0; i < data.length; i++) {
            if (data[i].matches(Str)) {
                num.push(data[i]);
            }
            if (data[i].matches(""[*-/+]"")) {
                num.push(data[i]);
            }
            if (data[i].equals("")"")) {
                double a = Double.parseDouble(num.pop());
                op = num.pop();
                double b = Double.parseDouble(num.pop());
                if (op.equals(""+"")) {
                    ans = b + a;
                }
                if (op.equals(""-"")) {
                    ans = b - a;
                }
                if (op.equals(""*"")) {
                    ans = b * a;
                }
                if (op.equals(""/"")) {
                    ans = b / a;
                }
                num.push(Double.toString(ans));
            }

        }
        return Double.parseDouble(num.pop());
    }

}

