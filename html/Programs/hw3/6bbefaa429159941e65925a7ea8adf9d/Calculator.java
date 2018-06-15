import java.util.DoubleSummaryStatistics;
import java.util.Stack;

/**
.
 */
public class Calculator {
    public Double ans(String e){
        Stack<Character> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String num = """";
        for(int i = 0 ; i < e.length() ; i++){
            char c = e.charAt(i);
            switch (c){
                case ' ':
                    if(!num.equals("""")){
                        vals.push(Double.parseDouble(num));
                    }
                    num = """";
                    break;
                case '(':
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    ops.push(c);
                    break;
                case ')':
                    char op = ops.pop();
                    switch (op){
                        case '+':
                            vals.push(vals.pop() + vals.pop());
                            break;
                        case '-':
                            vals.push(-vals.pop() + vals.pop());
                            break;
                        case '*':
                            vals.push(vals.pop() * vals.pop());
                            break;
                        case '/':
                            Double d2 = vals.pop();
                            Double d1 = vals.pop();
                            vals.push(d1/d2);
                            break;
                    }


                    Double d = vals.pop();
                    System.out.println(d);
                    vals.push(d);
                    break;
                default:
                    num += c;
            }
        }
        return vals.pop();
    }
}

