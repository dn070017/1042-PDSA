
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Calculator {

    public double ans(String d) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String[] cha = d.split("" "");
        int l = cha.length, cal = 0;
        int count = 0;
        while (count < l) {
            String s;
            s = cha[count];
            switch (s) {
                case ""("":
                    break;
                case ""+"":
                    ops.push(s);
                    break;
                case ""*"":
                    ops.push(s);
                    break;
                case ""-"":
                    ops.push(s);
                    break;
                case ""/"":
                    ops.push(s);
                    break;
                case "")"":
                    String op = ops.pop();
                    if (op.equals(""+"")) {
                        vals.push(vals.pop() + vals.pop());
                    } else if (op.equals(""*"")) {
                        vals.push(vals.pop() * vals.pop());
                    } else if (op.equals(""-"")) {
                        vals.push(-1 * (vals.pop() - vals.pop()));
                    } else if (op.equals(""/"")) {
                        vals.push((1 / vals.pop()) * vals.pop());
                    }
                    break;
                default:
                    vals.push(Double.parseDouble(s));
                    break;
            }
            count++;
        }
        return vals.pop();
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String fund = br.readLine();
            Calculator cct = new Calculator();
            double x;
            x = cct.ans(fund);
            System.out.println(x);
        }
        // TODO code application logic here
    }

}

