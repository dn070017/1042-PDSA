
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

        boolean plot = false;
        java.util.Stack<Double> dou = new java.util.Stack<>();
//
//        for (int i = 0; i < count; i++) {
//            if (cut[i].equals(""+"") || cut[i].equals(""-"") || cut[i].equals(""/"") || cut[i].equals(""*"") || cut[i].equals("")"")) {
//
//                double num = 0;
//                int n = dou.size();
//                int m;
//                if (plot) {
//                    m = n;
//                    while (m > 0) {
//                        m--;
//                        num += dou.pop() * Math.pow(10, -m);
//                    }
//                    plot = false;
//                } else {
//                    m = 0;
//                    while (m < n) {
//                        num += dou.pop() * Math.pow(10, m);
//                        m++;
//                    }
//                }
//                cut[i - 1] = Double.toString(num);
//
//            } else if (cut[i].equals(""("")) {
//            } else if (cut[i].equals(""."")) {
//                double num = 0;
//                int n = dou.size();
//                while (n != 0) {
//                    n--;
//                    num += dou.pop() * Math.pow(10, n);
//                }
//                dou.push(num);
//                plot = true;
//
//                cut[i] = """";
//            } else {
//                dou.push(Double.parseDouble(cut[i]));
//                cut[i] = """";
//            }
//        }

        java.util.Stack<String> ops = new java.util.Stack<>();
        java.util.Stack<Node> vals = new java.util.Stack<>();

        String op;
        for (int i = 0; i < count; i++) {
            switch (cut[i]) {
                case """":
                    break;
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
                    Node right = vals.pop();
                    Node left = vals.pop();
                    Node temp = new Node(left, right, op);
                    vals.push(temp);
                    break;
                default:
                    Node x = new Node(null, null, cut[i]);
                    vals.push(x);
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
        answer=vals.pop();
        return answer;
    }

    public static void main(String[] args) {

        String in = ""(4+(((4*2)/2)/3))"";
        Expression ex = new Expression();
        StdOut.println(ex.Infix2BT(in).getValue());

        Node[] kkk = ex.PrintPostfix();
        for (int i = 0; i < kkk.length; i++) {
            StdOut.print(kkk[i].getValue());
        }
        StdOut.println();
        StdOut.println(ex.Evaluation());

    }

}

