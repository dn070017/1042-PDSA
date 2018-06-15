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
        double ans = cal.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 0.5 / 5 ) )"");
         System.out.println(ans);
    }*/

    public Double ans(String e) {
        String[] data = e.split("" "");
        String Str = ""\\d+\\.*\\d*"";
        String op = """";

        double temp = 0.0;

        LinkedStack<Double> num = new LinkedStack<Double>();

        for (int i = 0; i < data.length; i++) {
            if(data[i].matches(Str)){
                num.push(Double.parseDouble(data[i]));
            }
            for (int j = i-1; j >= 0; j--) {
                if(data[i].equals("")"")){
                    if(data[j].matches(""[*-/+]"")){
                        double a = num.pop();
                        double b = num.pop();
                        double ans = count(b,data[j],a);
                        num.push(ans);
                        data[j] = ""c"";
                        break;
                    }
                }
            }
        }
        return num.pop();
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

