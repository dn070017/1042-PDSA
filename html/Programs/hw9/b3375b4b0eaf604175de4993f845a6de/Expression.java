
public class Expression {

    private Node root;
    Queue<Node> pre = new Queue<>();
    Queue<Node> post = new Queue<>();

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {

        String[] cut;
        cut = infix.split("""");
        int count = cut.length;

        boolean nint = false;
        boolean plot = false;

        java.util.Stack<String> ops = new java.util.Stack<>();
        java.util.Stack<Node> vals = new java.util.Stack<>();

        String op;
        for (int i = 0; i < count; i++) {
            switch (cut[i]) {
                case ""("":
                    nint = false;
                    plot = false;
                    break;
                case ""+"":
                    nint = false;
                    plot = false;
                    ops.push(cut[i]);
                    break;
                case ""-"":
                    nint = false;
                    plot = false;
                    ops.push(cut[i]);
                    break;
                case ""/"":
                    nint = false;
                    plot = false;
                    ops.push(cut[i]);
                    break;
                case ""*"":
                    nint = false;
                    plot = false;
                    ops.push(cut[i]);
                    break;
                case ""."":
                    plot = true;
                    break;
                case "")"":
                    nint = false;
                    plot = false;
                    op = ops.pop();
                    Node right = vals.pop();
                    Node left = vals.pop();
                    Node temp = new Node(left, right, op);
                    vals.push(temp);
                    break;
                default:
                    Node x;
                    if (plot) {
                        x = vals.pop();
                        String str = x.getValue() + ""."";
                        x.setValue(str);
                        vals.push(x);
                    }
                    if (nint) {
                        x = vals.pop();
                        String str = x.getValue() + cut[i];
                        x.setValue(str);
                    } else {
                        x = new Node(null, null, cut[i]);
                    }
                    vals.push(x);
                    nint = true;
                    plot = false;
                    break;
            }
        }
        root = vals.pop();
        return root;
    }

    public Node[] PrintPrefix() {
        pre.enqueue(root);
        getpre(root);
        int size = pre.size();
        Node[] prefix = new Node[size];
        for (int s = 0; s < size; s++) {
            prefix[s] = pre.dequeue();
        }
        return prefix;
    }

    private Node getpre(Node x) {
        if (x.getLeft() != null) {
            pre.enqueue(x.getLeft());
            getpre(x.getLeft());
        }
        if (x.getRight() != null) {
            pre.enqueue(x.getRight());
            getpre(x.getRight());
        }
        return x;
    }

    public Node[] PrintPostfix() {
        getpost(root);
        int size = post.size();
        Node[] postfix = new Node[size];
        for (int s = 0; s < size; s++) {
            postfix[s] = post.dequeue();
        }
        return postfix;
    }

    private Node getpost(Node x) {
        if (x.getLeft() != null) {
            getpost(x.getLeft());
        }
        if (x.getRight() != null) {
            getpost(x.getRight());
        }
        post.enqueue(x);
        return x;
    }

    public double Evaluation() {
        double answer = 0;
        Node[] kkk = PrintPostfix();
        String cal[] = new String[kkk.length];
        for (int i = 0; i < kkk.length; i++) {
            cal[i] = kkk[i].getValue();
        }
        java.util.Stack<String> ops = new java.util.Stack<>();
        java.util.Stack<Double> vals = new java.util.Stack<>();

        for (int i = 0; i < cal.length; i++) {
            switch (cal[i]) {
                case ""+"":
                    vals.push(vals.pop() + vals.pop());
                    break;
                case ""*"":
                    vals.push(vals.pop() * vals.pop());
                    break;
                case ""-"":
                    answer = -(vals.pop() - vals.pop());
                    vals.push(answer);
                    break;
                case ""/"":
                    answer = 1 / (vals.pop() / vals.pop());
                    vals.push(answer);
                    break;
                default:
                    vals.push(Double.parseDouble(cal[i]));
                    break;
            }
        }
        answer = vals.pop();
        return answer;
    }

//    public static void main(String[] args) {
//
//        String in = ""(4.1+(((4*2)/2)/3))"";
//        Expression ex = new Expression();
//        StdOut.println(ex.Infix2BT(in).getValue());
//
//        Node[] kkk = ex.PrintPostfix();
//        for (int i = 0; i < kkk.length; i++) {
//            StdOut.print(kkk[i].getValue());
//        }
//        StdOut.println();
//        StdOut.println(ex.Evaluation());
//
//    }

}

