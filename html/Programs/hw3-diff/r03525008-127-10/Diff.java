import java.util.Stack;

/**
.
 */
public class Calculator {
//    public Double ans(String e){
//        Stack<Character> ops = new Stack<>();
//        Stack<Double> vals = new Stack<>();
//        String num = """";
//        for(int i = 0 ; i < e.length() ; i++){
//            char c = e.charAt(i);
//            switch (c){
//                case ' ':
//                    if(!num.equals(""""))
//                        vals.push(Double.parseDouble(num));
//                    num = """";
//                    break;
//                case '(':
//                    break;
//                case '+':
//                case '-':
//                case '*':
//                case '/':
//                    ops.push(c);
//                    break;
//                case ')':
//                    char op = ops.pop();
//                    switch (op){
//                        case '+':
//                            vals.push(vals.pop() + vals.pop());
//                            break;
//                        case '-':
//                            vals.push(-vals.pop() + vals.pop());
//                            break;
//                        case '*':
//                            vals.push(vals.pop() * vals.pop());
//                            break;
//                        case '/':
//                            vals.push(1/vals.pop()*vals.pop());
//                            break;
//                    }
//                    break;
//                default:
//                    num += c;
//            }
//        }
//        return vals.pop();
//    }

    public Double ans(String e){
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        String[] input = e.split(""\\s"");
        for(String s:input){
            if (s.equals(""("")) ;
            else if (s.equals(""+"")) ops.push(s);
            else if (s.equals(""-"")) ops.push(s);
            else if (s.equals(""*"")) ops.push(s);
            else if (s.equals(""/"")) ops.push(s);
            else if (s.equals("")""))
            {
                String op = ops.pop();
                if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
                else if (op.equals(""-"")) vals.push(-vals.pop() + vals.pop());
                else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
                else if (op.equals(""/"")) vals.push(1/vals.pop()*vals.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        return vals.pop();

    }




}

