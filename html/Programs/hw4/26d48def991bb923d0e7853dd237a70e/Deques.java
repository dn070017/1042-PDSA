import java.util.Stack;

/**
.
 */
public class Calculator {
    private Stack<String> myStack = new Stack<String>();
    private Double answer = 0.0;
    private Stack<Double> valueStack = new Stack<Double>();
    private Stack<String> operatorStack = new Stack<String>();

    public Double ans(String e) {
        String s[] = e.split("" "");
        for (int i = s.length - 1; i >= 0; i--) {
            myStack.push(s[i]);
        }

        while (!myStack.isEmpty()) {
            String t=myStack.pop();
            if (t.equals(""("")) ;
            else if (t.equals(""+"") || t.equals(""-"") || t.equals(""*"") || t.equals(""/"")) {
                operatorStack.push(t);
            } else if (t.equals("")"")) {
                String op=operatorStack.pop();
                if (op.equals(""+"")) {
                    valueStack.push(valueStack.pop() + valueStack.pop());
                }
                else if (op.equals(""-"")) {
                    valueStack.push(-(valueStack.pop() - valueStack.pop()));
                }
                else if (op.equals(""*"")) {
                    valueStack.push(valueStack.pop() * valueStack.pop());
                }
                else if (op.equals(""/"")) {
                    valueStack.push(1/(valueStack.pop() / valueStack.pop()));

                }
            }else{
                valueStack.push(Double.parseDouble(t));
            }
        }
        answer=valueStack.pop();
        return answer;
    }
}

