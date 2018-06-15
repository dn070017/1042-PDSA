public class Expression {

    private static boolean isOperator(String a) {
        if (a.equals(""+"") || a.equals(""-"") || a.equals(""*"") || a.equals(""/"")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNum(String a) {
        if (a.equals(""+"") || a.equals(""-"") || a.equals(""*"") || a.equals(""/"") || a.equals(""("") || a.equals("")"")) {
            return false;
        } else {
            return true;
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

        Stack<Node> ns = new Stack();
        //String opdR, opr, opdL;
        for (int i = 0; i < infix.length(); i++) {
            String s = infix.substring(i, i+1);
            if(s.equals(""(""))
                continue;
            else if(s.equals("")"")){
                if(ns.size() == 1){
                    break;
                } // ex: (123.456)
                
                Node nRight = ns.pop();
                Node nRoot = ns.pop();
                Node nLeft = ns.pop();
                nRoot.setRight(nRight);
                nRoot.setLeft(nLeft);
                ns.push(nRoot);
            }
            else{
                if(isOperator(s)){
                    Node opt = new Node(null,null,s);
                    ns.push(opt);   
                }else{
                    int j = i;
                    while(isNum(infix.substring(j+1,j+2)))
                        j++;
                    Node opr = new Node(null,null,infix.substring(i, j+1));
                    ns.push(opr);
                    i = j;
                }
            }
        }
        
        root = ns.pop();

        return (root);
    }

    public Node[] PrintPrefix() {
        if (root == null) {
            throw new NullPointerException();
        } else {
            Node[] prefix = null;

            Stack<Node> st = new Stack();
            prefixPut(root, st);
            int size = st.size();
            prefix = new Node[size];
            for (int i = st.size() - 1; i >= 0; i--) {
                prefix[i] = st.pop();
            }

            return (prefix);
        }
    }

    public Node[] PrintPostfix() {
        if (root == null) {
            throw new NullPointerException();
        } else {
            Node[] postfix = null;

            Stack<Node> st = new Stack();
            postfixPut(root, st);
            int size = st.size();
            postfix = new Node[size];
            for (int i = 0; i < size; i++) {
                postfix[i] = st.pop();
            }

            return (postfix);
        }
    }

    public double Evaluation() {
        if (root == null) {
            throw new NullPointerException();
        } else if(isNum(root.getValue())){
            return(Double.valueOf(root.getValue()));
        } else{
            double answer = compution(root);
            return (answer);
        }
    }

//    public static void main(String[] args) {
//        Expression test = new Expression();
//        test.Infix2BT(""(4.454+2.001)"");
//
//        for (int i = 0; i < test.PrintPrefix().length; i++) {
//            System.out.println(test.PrintPrefix()[i].getValue());
//        }
//
//        for (int i = 0; i < test.PrintPostfix().length; i++) {
//            System.out.println(test.PrintPostfix()[i].getValue());
//        }
//
//        System.out.println(test.Evaluation());
//    }
}
