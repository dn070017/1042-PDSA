import edu.princeton.cs.algs4.Stack;
import java.math.*;

public class Calculator {

    public static void main(String[] args) throws Exception {
        Calculator cct = new Calculator();
        System.out.print(cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )""));
    }

    public Double ans(String e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String[] data = e.split("" "");

        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(""("")) {
            } else if (data[i].equals(""+"")) {
                ops.push(data[i]);
            } else if (data[i].equals(""-"")) {
                ops.push(data[i]);
            } else if (data[i].equals(""*"")) {
                ops.push(data[i]);
            } else if (data[i].equals(""/"")) {
                ops.push(data[i]);
            } else if (data[i].equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    double a = vals.pop();
                    double b = vals.pop();
                    vals.push(b + a);
                } else if (op.equals(""-"")) {
                    double a = vals.pop();
                    double b = vals.pop();
                    vals.push(b - a);
                } else if (op.equals(""*"")) {
                    double a = vals.pop();
                    double b = vals.pop();
                    vals.push(b * a);
                } else if (op.equals(""/"")) {
                    double a = vals.pop();
                    double b = vals.pop();
                    vals.push(b / a);
                }
            } else {
                vals.push(Double.parseDouble(data[i]));
            }

        }
        double output = new BigDecimal(vals.pop()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return output;
    }
}

