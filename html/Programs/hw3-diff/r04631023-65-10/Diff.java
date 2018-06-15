import java.io.BufferedReader;
import java.io.FileReader;

public class Calculator {

    public Double ans(String e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String[] data = e.split("" "");
        double first;
        double second;
        for (int i = 0; i < data.length; i++) {
            String s = data[i];
            if (s.equals(""("")) {
            } else if (s.equals(""+"")) {
                ops.push(s);
            } else if (s.equals(""-"")) {
                ops.push(s);
            } else if (s.equals(""*"")) {
                ops.push(s);
            } else if (s.equals(""/"")) {
                ops.push(s);
            } else if (s.equals("")"")) {
                String op = ops.pop();
                if (op.equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals(""-"")) {
                    first = vals.pop();
                    second = vals.pop();
                    vals.push(second - first);
                } else if (op.equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals(""/"")) {
                    first = vals.pop();
                    second = vals.pop();
                    vals.push(second / first);
                }
            } else {
                vals.push(Double.parseDouble(s));
            }
        }
        double value = vals.pop();
        return value;
    }
}
