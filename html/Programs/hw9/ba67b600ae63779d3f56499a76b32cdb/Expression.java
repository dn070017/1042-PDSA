
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Expression {

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }
    private Stack<Node> z = new Stack<Node>();

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        String[] input;
        int count = 0;
        int input_length = 0;
        Stack<Node> ops = new Stack<Node>();
        Stack<Node> vals = new Stack<Node>();
//        Stack<Node> roots = new Stack<Node>();
//        Stack<Node> right = new Stack<Node>();
        input = infix.split(""(?<=[\\(\\)\\+\\-*\\/])|(?=[\\(\\)\\+\\-*\\/])"");

        for (int i = 1; i < input.length; i++) {
            input_length++;
            //System.out.printf(input[i]);
            if (input[i].equals(""("") || input[i].equals("")"")) {
                count++;
            }
        }
        //System.out.printf(""\n"");
        
        Node[] inputarray = new Node[input.length - 1];
        for (int i = 1; i < input.length; i++) {
            inputarray[i - 1] = new Node(null, null, input[i]);
            //System.out.printf(inputarray[i - 1].getValue());
        }
        
        //System.out.printf(""\n"");
        
        ArrayList<Node> tree = new ArrayList<Node>();
        
        for (int i = 0; i < inputarray.length; i++) {
            if (inputarray[i].getValue().equals(""("")) {
                continue;
            } else if (inputarray[i].getValue().equals(""+"")) {
                ops.push(inputarray[i]);
                //StdOut.println(""1"");
            } else if (inputarray[i].getValue().equals(""*"")) {
                ops.push(inputarray[i]);
                //StdOut.println(""2"");
            } else if (inputarray[i].getValue().equals(""/"")) {
                ops.push(inputarray[i]);
                //StdOut.println(""3"");
            } else if (inputarray[i].getValue().equals(""-"")) {
                ops.push(inputarray[i]);
                //StdOut.println(""4"");
            } else if (inputarray[i].getValue().equals("")"")) {                
                //StdOut.println(""6"");
                Node right = vals.pop();
                //StdOut.println(""7"");
                Node left = vals.pop();
                //StdOut.println(""8"");
                Node ing = new Node(left, right,ops.pop().getValue());
                //StdOut.println(""9"");
                vals.push(ing);
                tree.add(ing);
                
            } else {
                vals.push((inputarray[i]));
                //StdOut.println(""5"");
            }
        }
        //StdOut.println(tree.get(0).getValue());
//        StdOut.println(op[0].getLeft().getValue());
//        StdOut.println(op[0].getRight().getValue());
//        StdOut.println(op[1].getValue());
//        StdOut.println(op[1].getLeft().getValue());
//        StdOut.println(op[1].getRight().getValue());
//        StdOut.println(op[2].getValue());
//        StdOut.println(op[2].getLeft().getValue());
//        StdOut.println(op[2].getRight().getValue());
        
//        if (infix.isEmpty()) {
//            return null;
//        }
        //StdOut.println(tree.get(tree.size()-1).getValue());
        root = tree.get(tree.size()-1);
        return root;
    }

    

    private Stack<Node> a = new Stack<Node>();
    private Stack<Node> b = new Stack<Node>();
    private Stack<Node> c = new Stack<Node>();

    public Node[] PrintPrefix() {
        //System.out.print(root.getValue());
        if (root != null) {
            //System.out.print(""1"");
            pre(root);
            int size = a.size();
            Node[] prefix = new Node[size];
            //System.out.printf(""size"" + size + ""\n"");
            //System.out.printf(test.getValuetest = a.pop();() + ""\n"");
            for (int i = 0; i < size; i++) {
                //ans[0] = prefix.pop();
                prefix[i] = a.pop();
                //System.out.printf(ans[i].getValue()+ ""\n"");
                //System.out.print(i+ ""\n"");
            }
            return prefix;
        } else {
            throw new NullPointerException();
        }

    }

    private void pre(Node n) {
        if (n != null) {
            //System.out.printf(n.getValue() + ""\n"");
            pre(n.getRight());
            pre(n.getLeft());
            a.push(n);
        }
    }

    public Node[] PrintPostfix() {
        if (root != null) {
            post(root);
            int size = b.size();
            Node[] postfix = new Node[size];
            //System.out.printf(""size"" + size + ""\n"");
            //System.out.printf(test.getValuetest = a.pop();() + ""\n"");
            for (int i = 0; i < size; i++) {
                //ans[0] = prefix.pop();
                postfix[i] = b.pop();
                //System.out.printf(ans[i].getValue()+ ""\n"");
                //System.out.print(i+ ""\n"");
            }
            return postfix;
        } else {
            throw new NullPointerException();
        }
    }

    private void post(Node n) {
        if (n != null) {
            //System.out.printf(n.getValue() + ""\n"");
            b.push(n);
            post(n.getRight());
            post(n.getLeft());

        }
    }

    public double Evaluation() {
        if (root != null) {
            eva(root);
            int size = c.size();
            Node[] ans = new Node[size];
            //System.out.printf(""size"" + size + ""\n"");
            //System.out.printf(test.getValuetest = a.pop();() + ""\n"");
            for (int i = 0; i < size; i++) {
                //ans[0] = prefix.pop();
                ans[i] = c.pop();
                //System.out.printf(ans[i].getValue()+ ""\n"");
                //System.out.print(i+ ""\n"");
            }

            //Stack<String> ops = new Stack<String>();
            Stack<Double> vals = new Stack<Double>();
            for (int i = 0; i < size; i++) {
                if (ans[i].getValue().equals(""+"")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (ans[i].getValue().equals(""*"")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (ans[i].getValue().equals(""/"")) {
                    double one = vals.pop();
                    double two = vals.pop();
                    vals.push(two / one);
                } else if (ans[i].getValue().equals(""-"")) {
                    double one = vals.pop();
                    double two = vals.pop();
                    vals.push(two - one);
                } else {
                    vals.push(Double.parseDouble(ans[i].getValue()));
                }
            }
            double answer = vals.pop();

            return answer;
        } else {
            throw new NullPointerException();
        }
    }

    private void eva(Node n) {
        if (n != null) {
            //System.out.printf(n.getValue() + ""\n"");
            c.push(n);
            eva(n.getRight());
            eva(n.getLeft());

        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String input = ""(((2+3)+(4+5))+((2+3)+(4+5)))"";

            Expression test = new Expression();
            test.Infix2BT(input);
            
            
            //System.out.print(test.Infix2BT(input).getValue() + ""\n"");
            Node[] t1 = test.PrintPrefix();
            Node[] t2 = test.PrintPostfix();

//            for (int i = 0; i < t1.length; i++) {
//                System.out.printf(t1[i].getValue() + "" "");
//            }
//            System.out.printf(""\n"");
//            for (int i = 0; i < t2.length; i++) {
//                System.out.printf(t2[i].getValue() + "" "");
//            }

            //Node[] a = test.PrintPrefix();
            //System.out.printf(test.PrintPrefix()[0].getValue());
            //System.out.print();
//            System.out.print(test.Evaluation());

        }
    }
}

