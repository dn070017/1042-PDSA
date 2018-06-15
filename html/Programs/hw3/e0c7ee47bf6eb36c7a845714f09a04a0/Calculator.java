
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Calculator {

    public Calculator() {

    }

    public Double ans(String e) {
        String[] cut;
        cut = e.split("" "");
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        double help;
        int count =cut.length;
        String op;
        for (int i = 0; i < count; i++) {
            switch (cut[i]) {
                case ""("":
                    break;
                case ""+"":
                    ops.push(cut[i]);
                    break;
                case ""-"":
                    ops.push(cut[i]);
                    break;
                case ""/"":
                    ops.push(cut[i]);
                    break;
                case ""*"":
                    ops.push(cut[i]);
                    break;
                case "")"":
                    op = ops.pop();
                    switch (op) {
                        case ""+"":
                            vals.push(vals.pop() + vals.pop());
                            break;
                        case ""*"":
                            vals.push(vals.pop() * vals.pop());
                            break;
                        case ""-"":
                            help = -(vals.pop() - vals.pop());
                            vals.push(help);
                            break;
                        case ""/"":
                            help = 1 / (vals.pop() / vals.pop());
                            vals.push(help);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    vals.push(Double.parseDouble(cut[i]));
                    break;
            }

        }

        return vals.pop();
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            String str = br.readLine();
            Calculator cct = new Calculator();
            StdOut.println(cct.ans(str));
        }
    }
}

