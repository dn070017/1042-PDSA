

import java.io.BufferedReader;
import java.io.FileReader;

public class Expression {

    private Node root;
    private int count;
    private static boolean isexisted = true;
    private Stack<String> ops = new Stack<String>();
    private Stack<String> val = new Stack<String>();
    private Node[] postfix = null;
    private int countpost = 0;
    private Node[] prefix = null;
    private int countpre = 0;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        if (isexisted == false) {
            String[] a = infix.split("""");
            if (a[count].equals(""("")) {
                if (count == 1) {
                    count++;
                    root = new Node(Infix2BT(infix), Infix2BT(infix), Infix2BT(infix).getValue());
                    return root;
                } else {
                    count++;
                    Node A = new Node(Infix2BT(infix), Infix2BT(infix), Infix2BT(infix).getValue());
                    return A;
                }
            } else if (a[count].equals(""+"")) {
                ops.push(a[count]);
                count++;
                return Infix2BT(infix);
            } else if (a[count].equals(""-"")) {
                ops.push(a[count]);
                count++;
                return Infix2BT(infix);
            } else if (a[count].equals(""*"")) {
                ops.push(a[count]);
                count++;
                return Infix2BT(infix);
            } else if (a[count].equals(""/"")) {
                ops.push(a[count]);
                count++;
                return Infix2BT(infix);
            } else if (a[count].equals("")"")) {
                count++;
                Node A = new Node(null, null, ops.pop());
                return A;
            } else {
                if (((a[count + 1].equals(""+"") && a[count + 1].equals(""-"")) && (a[count + 1].equals(""*"") && a[count + 1].equals(""/"")) && a[count + 1].equals("")""))) {
                    val.push(a[count]);
                    count++;
                    return Infix2BT(infix);
                } else {
                    String value = null;
                    while (!val.isEmpty()) {
                        value += val.pop();
                    }
                    Node A = new Node(null, null, value);
                    count++;
                    return A;
                }
            }

        } else {
            count = 1;
            isexisted = false;
            return Infix2BT(infix);
        }
    }

    public Node[] PrintPrefix() {
        prefix = null;
        countpre = 0;
        Prere(root);
        return prefix;
    }

    public Node[] PrintPostfix() {
        postfix = null;
        countpost = 0;
        Postre(root);
        return postfix;
    }

    public double Evaluation() {
        double answer = 0;
        Node[] k = PrintPostfix();
        for (int i = 0; i < k.length; i++) {
            if (k[i].equals(""+"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(a + b));
            } else if (k[i].equals(""-"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(a - b));
            } else if (k[i].equals(""*"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(a * b));
            } else if (k[i].equals(""/"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(a / b));
            } else {
                val.push(k[i].getValue());
            }
        }
        answer = Double.parseDouble(val.pop());
        return answer;
    }

    public Node Postre(Node A) {
        if (A.getLeft() != null) {
            Postre(A.getLeft());
            Postre(A.getRight());
        }
        postfix[countpost] = A;
        countpost++;
        return A;
    }

    public Node Prere(Node A) {
        prefix[countpre] = A;
        countpre++;

        if (A.getLeft() != null) {
            Prere(A.getLeft());
            Prere(A.getRight());
        }
        return A;

    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Expression exp = new Expression();
            String s = br.readLine();

            exp.Infix2BT(s);

        }
    }
}

