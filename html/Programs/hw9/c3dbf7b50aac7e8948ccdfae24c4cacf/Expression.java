
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Expression {

    private Stack<Node>nodes;
    static Node treeroot;
    static Stack<String> st;
    static int N = 0;
    
    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        String[] str = infix.split("""");
        st = new Stack<String>();
        nodes = new Stack<Node>();

        for (int i = 0; i < str.length; i++) {
            st.push(str[i]);
            if(i>0&&!(str[i].equals(""+"")||str[i].equals(""-"")||str[i].equals(""*"")||str[i].equals(""/"")||str[i].equals(""("")||str[i].equals("")""))){
                if(!(str[i-1].equals(""+"")||str[i-1].equals(""-"")||str[i-1].equals(""*"")||str[i-1].equals(""/"")||str[i-1].equals(""("")||str[i-1].equals("")""))){
                    st.pop();
                    st.push(st.pop()+str[i]);
                }
            }
                
            if (str[i].equals("")"")) {
                st.pop();
                String rightstr = st.pop();
                String oper = st.pop();
                String leftstr = st.pop();

                if (!leftstr.equals(""Tree"") && !rightstr.equals(""Tree"")) {
                    Node left = new Node(null, null, leftstr);
                    Node right = new Node(null, null, rightstr);
                    nodes.push(new Node(left, right, oper));
                    st.pop();
                    st.push(""Tree"");
                    N = N + 3;
//                    StdOut.print(root.getRight().getValue());
                } else if (leftstr.equals(""Tree"")&& !rightstr.equals(""Tree"")) {
                    Node root = nodes.pop();
                    Node oldroot = new Node(root.getLeft(), root.getRight(), root.getValue());
                    Node newroot = new Node(null,null,null);
                    newroot.setValue(oper);
                    newroot.setLeft(oldroot);
                    Node right = new Node(null, null, rightstr);
                    newroot.setRight(right);
                    nodes.push(newroot);
                    st.pop();
                    st.push(""Tree"");
                    N = N + 2;
                } else if (!leftstr.equals(""Tree"") &&rightstr.equals(""Tree"")) {
                    Node root = nodes.pop();
                    Node oldroot = new Node(root.getLeft(), root.getRight(), root.getValue());
                    Node newroot = new Node(null,null,null);
                    newroot.setValue(oper);
                    newroot.setRight(oldroot);
                    Node left = new Node(null, null, leftstr);
                    newroot.setLeft(left);
                    nodes.push(newroot);
                    st.pop();
                    st.push(""Tree"");
                    N = N + 2;
                }else if (leftstr.equals(""Tree"") &&rightstr.equals(""Tree"")) {
                    Node rightroot = nodes.pop();
                    Node leftroot = nodes.pop();
                    Node newroot = new Node(null,null,null);
                    newroot.setLeft(leftroot);
                    newroot.setRight(rightroot);
                    newroot.setValue(oper);
                    nodes.push(newroot);
                    st.pop();
                    st.push(""Tree"");
                    N = N + 1;
                }
            }

        }
        treeroot = nodes.pop();
        return (treeroot);
    }

    static ArrayList<Node> prefixal;

    public void TraversePre(Node node) {
        prefixal.add(node);
        if (node.getLeft() != null) {
            TraversePre(node.getLeft());
        }
        if (node.getRight() != null) {
            TraversePre(node.getRight());
        }

    }

    public Node[] PrintPrefix() {
        Node[] prefix = new Node[N];
        int curnum = 0;
        prefixal = new ArrayList<Node>();
        TraversePre(treeroot);
        for (int i = 0; i < N; i++) {
            prefix[i] = prefixal.get(i);
        }
        return (prefix);
    }

    static ArrayList<Node> postfixal;

    public void TraversePost(Node node) {

        if (node.getLeft() != null) {
            TraversePost(node.getLeft());
        }
        if (node.getRight() != null) {
            TraversePost(node.getRight());
        }
        postfixal.add(node);

    }

    public Node[] PrintPostfix() {
        Node[] postfix = new Node[N];
        int curnum = 0;
        postfixal = new ArrayList<Node>();
        TraversePost(treeroot);
        for (int i = 0; i < N; i++) {
            postfix[i] = postfixal.get(i);
        }
        return (postfix);
    }

    public static double Operate(String op, double a, double b) {
        if (op.equals(""+"")) {
            return (a + b);
        } else if (op.equals(""-"")) {
            return (a - b);
        } else if (op.equals(""*"")) {
            return (a * b);
        } else if (op.equals(""/"")) {
            return (a / b);
        } else {
            return 0;
        }
    }

    public double Evaluation(Node node) {

        if (node.getLeft() == null && node.getRight() == null) {
            return Double.parseDouble(node.getValue());
        } else {
            return Operate(node.getValue(), Evaluation(node.getLeft()), Evaluation(node.getRight()));
        }

    }

    public double Evaluation() {
        return Evaluation(treeroot);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String read = br.readLine();
        Expression ex = new Expression();
        ex.Infix2BT(read);

//        StdOut.print(ex.treeroot.getRight().getValue());
        for (Node node : ex.PrintPrefix()) {
            StdOut.print(node.getValue());
        }
        StdOut.print(ex.Evaluation());
    }
}

