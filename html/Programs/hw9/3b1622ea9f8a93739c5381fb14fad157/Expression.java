
import java.util.ArrayList;
import java.util.Stack;

public class Expression {

    private static Node root;
    private static Stack<Node> prefix = new Stack<Node>();
    private static Stack<Node> postfix = new Stack<Node>();
    private static Double answer;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public static Node Infix2BT(String infix) {
        Stack<Node> ops = new Stack<Node>();
        Stack<Node> vals = new Stack<Node>();
        Stack<String> aops = new Stack<String>();
        Stack<Double> avals = new Stack<Double>();
        String[] in = infix.split(""(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)"");
        for (int i = 0; i < in.length; i++) {
            String s = in[i];
            if (s.equals(""("")) {
                continue;
            } else if (s.equals(""+"")) {
                ops.push(new Node(null, null, s));
                aops.push(s);
            } else if (s.equals(""-"")) {
                ops.push(new Node(null, null, s));
                aops.push(s);
            } else if (s.equals(""*"")) {
                ops.push(new Node(null, null, s));
                aops.push(s);
            } else if (s.equals(""/"")) {
                ops.push(new Node(null, null, s));
                aops.push(s);
            } else if (s.equals("")"")) {
                Node op = ops.pop();
                op.setRight(vals.pop());
                op.setLeft(vals.pop());
                if (i == in.length - 1) {
                    root = op;
                } else {
                    vals.push(op);
                }
                String aop = aops.pop();
                if (aop.equals(""+"")) {
                    avals.push(avals.pop() + avals.pop());
                } 
                else if (aop.equals(""-"")) {
                    Double a = avals.pop();
                    Double b = avals.pop();
                    avals.push(b - a);
                }
                else if (aop.equals(""*"")) {
                    avals.push(avals.pop() * avals.pop());
                }
                else if (aop.equals(""/"")) {
                    Double a = avals.pop();
                    Double b = avals.pop();
                    avals.push(b / a);
                }
            } else {
                vals.push(new Node(null, null, s));
                avals.push(Double.parseDouble(s));
            }
        }
        answer = avals.pop();
        return root;
    }

    private void prefix(Node a) {
        this.prefix.push(a);
        if (a.getLeft() != null) {
            prefix(a.getLeft());
        }
        if (a.getRight() != null) {
            prefix(a.getRight());
        }
    }

    public Node[] PrintPrefix() {
        if (root == null) {
            throw new NullPointerException(""Stack underflow"");
        }
        prefix(root);
        int count = this.prefix.size();
        Node[] p = new Node [count];
        for (int i = 0; i < count; i++) {
            p[i] = prefix.pop();
        }
        return p;
    }

    private static void postfix(Node a) {
        if (a.getLeft() != null) {
            postfix(a.getLeft());
        }
        if (a.getRight() != null) {
            postfix(a.getRight());
        }
        postfix.push(a);
    }

    public static Node[] PrintPostfix() {
        if (root == null) {
            throw new NullPointerException(""Stack underflow"");
        }
        postfix(root);
        int count = Expression.postfix.size();
        Node[] p = new Node [count];
        for (int i = 0; i < count; i++) {
            p[i] = postfix.pop();
        }
        return p;
    }

    public static double Evaluation() {
        return answer;
    }
    
public static void main(String[] args) {
        String input = ""(12/12)"";
        
        Expression test = new Expression();
        test.Infix2BT(input);
        print(test.PrintPrefix());
        print(test.PrintPostfix());        
        System.out.println(test.Evaluation());        
    } 
        
    public static void print(Node[] pf){
        for(int i = 0; i < pf.length;i++){
            System.out.printf(pf[i].getValue()+"" "");
        }
        System.out.println();        
    }        
}

