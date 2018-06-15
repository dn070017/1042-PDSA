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

        double temp = 0.0;

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
                double ans = count(b, op, a);
                num.push(Double.toString(ans));
            }

        }
        return Double.parseDouble(num.pop());
    }

    public Double count(double a, String b, double c) {
        double count = 0.0;
        if (b.equals(""+"")) {
            count = a + c;
        }
        if (b.equals(""-"")) {
            count = a - c;
        }
        if (b.equals(""*"")) {
            count = a * c;
        }
        if (b.equals(""/"")) {
            count = a / c;
        }
        return count;
    }

}

