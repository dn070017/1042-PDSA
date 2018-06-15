public class Expression {

    private static boolean isOperator(String a) {
        if (a.equals(""+"") || a.equals(""-"") || a.equals(""*"") || a.equals(""/"")) {
            return true;
        } else {
            return false;
        }
    }

    private static void prefixPut(Node n, Stack<Node> st) {
        st.push(n);
        if (n.getLeft() != null) {
            prefixPut(n.getLeft(), st);
        }
        if (n.getRight() != null) {
            prefixPut(n.getRight(), st);
        }
    }

    private static void postfixPut(Node n, Stack<Node> st) {
        st.push(n);
        if (n.getRight() != null) {
            postfixPut(n.getRight(), st);
        }
        if (n.getLeft() != null) {
            postfixPut(n.getLeft(), st);
        }
    }

    private static double compution(Node n) {
        double a, b;
        String opr = n.getValue();

        if (isOperator(n.getLeft().getValue())) {
            a = compution(n.getLeft());
        } else {
            a = Double.valueOf(n.getLeft().getValue());
        }

        if (isOperator(n.getRight().getValue())) {
            b = compution(n.getRight());
        } else {
            b = Double.valueOf(n.getRight().getValue());
        }

        if (opr.equals(""+"")) {
            return a + b;
        } else if (opr.equals(""-"")) {
            return a - b;
        } else if (opr.equals(""*"")) {
            return a * b;
        } else if (opr.equals(""/"")) {
            return a / b;
        } else {
            return 0;
        }
    }

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {

        Stack<String> query = new Stack();
        String opdR, opr, opdL;
        for (int i = 0; i < infix.length(); i++) {
            String s = infix.substring(i, i + 1);
            if (!s.equals("")"")) {
                query.push(s);
            } else { // s.equals("")"")
                String c = query.pop();
                if (!isOperator(c)) {
                    opr = query.pop();
                    opdR = c;
                    opdL = null;
                    if (!query.peek().equals(""("")) {
                        opdL = query.pop();
                    }
                } else {
                    opr = c;
                    opdR = null;
                    opdL = query.pop();
                }
                query.pop(); //pop '('

                if (opdL == null) {
                    Node nRight = new Node(null, null, opdR);
                    Node nRoot = new Node(root, nRight, opr);
                    root = nRoot;
                } else if (opdR == null) {
                    Node nLeft = new Node(null, null, opdL);
                    Node nRoot = new Node(nLeft, root, opr);
                    root = nRoot;
                } else { // opdL != null && opdR != null
                    Node nLeft = new Node(null, null, opdL);
                    Node nRight = new Node(null, null, opdR);
                    Node nRoot = new Node(nLeft, nRight, opr);
                    root = nRoot;
                }
            }
        }

        return (root);
    }

    public Node[] PrintPrefix() {
        if (root == null) {
            throw new NullPointerException();
        }else{
        Node[] prefix = null;

        Stack<Node> st = new Stack();
        prefixPut(root, st);
        int size = st.size();
        prefix = new Node[size];
        for (int i = st.size() - 1; i >= 0; i--) {
            prefix[i] = st.pop();
        }

        return (prefix);}
    }

    public Node[] PrintPostfix() {
        if (root == null) {
            throw new NullPointerException();
        }else{
        Node[] postfix = null;

        Stack<Node> st = new Stack();
        postfixPut(root, st);
        int size = st.size();
        postfix = new Node[size];
        for (int i = 0; i < size; i++) {
            postfix[i] = st.pop();
        }

        return (postfix);}
    }

    public double Evaluation() {
        if (root == null) {
            throw new NullPointerException();
        }else{

        double answer = compution(root);
        return (answer);}
    }

//    public static void main(String[] args) {
//        Expression test = new Expression();
//        test.Infix2BT("""");
//
//        for(int i =0;i<test.PrintPrefix().length;i++){
//            System.out.println(test.PrintPrefix()[i].getValue());
//        }
//        
//        for(int i =0;i<test.PrintPostfix().length;i++){
//            System.out.println(test.PrintPostfix()[i].getValue());
//        }
//        
//        System.out.println(test.Evaluation());
//    }
}
