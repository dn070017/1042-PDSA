
import java.io.BufferedReader;
import java.io.FileReader;

public class Expression {

    private Node root;
    private int count;
    private boolean isexisted = true;
    private Stack<String> ops = new Stack<String>();
    private Stack<String> val = new Stack<String>();
    private Node[] postfix = null;
    private int countpost = 0;
    private Node[] prefix = null;
    private int countpre = 0;
    private int nodenum = 0;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
//    public Node Infix2BTre(String infix) {
//        if(root == null){
//            throw new NullPointerException();
//        }
//        if (isexisted == false) {
//            String[] a = infix.split("""");
//            if (a[count].equals(""("")) {
//                if (count == 1) {
//                    count++;
//                    root = new Node(Infix2BT(infix), Infix2BT(infix), Infix2BT(infix).getValue());
//                    return root;
//                } else {
//                    count++;
//                    Node A = new Node(Infix2BT(infix), Infix2BT(infix), Infix2BT(infix).getValue());
//                    return A;
//                }
//            } else if (a[count].equals(""+"")) {
//                ops.push(a[count]);
//                count++;
//                return Infix2BT(infix);
//            } else if (a[count].equals(""-"")) {
//                ops.push(a[count]);
//                count++;
//                return Infix2BT(infix);
//            } else if (a[count].equals(""*"")) {
//                ops.push(a[count]);
//                count++;
//                return Infix2BT(infix);
//            } else if (a[count].equals(""/"")) {
//                ops.push(a[count]);
//                count++;
//                return Infix2BT(infix);
//            } else if (a[count].equals("")"")) {
//                count++;
//                Node A = new Node(null, null, ops.pop());
//                return A;
//            } else {
//                if (!((a[count + 1].equals(""+"") || a[count + 1].equals(""-"")) || (a[count + 1].equals(""*"") || a[count + 1].equals(""/"")) || a[count + 1].equals("")""))) {
//                    val.push(a[count]);
//                    count++;
//                    return Infix2BT(infix);
//                } else {
//                    String value = null;
//                    val.push(a[count]);
//                    value = val.pop();
//                    while (!val.isEmpty()) {
//                        value = val.pop() + value;
//                    }
//                    Node A = new Node(null, null, value);
//                    count++;
//                    return A;
//                }
//            }
//
//        } else {
//            count = 1;
//            isexisted = false;
//            nodenum = 2 * (infix.length() - infix.replace(""("", """").length()) + 1;
//            return Infix2BT(infix);
//        }
//    }
//    
    public Node Infix2BT(String infix){
        String[] a = infix.split("""");
        String n = """";
        Stack<Node> node = new Stack<Node>();
         nodenum = 2 * (infix.length() - infix.replace(""("", """").length()) + 1;
        for(int i = 1; i < a.length;i++){
            switch(a[i]){
                case ""("":
                    break;
                case ""+"":
                    if(n != """"){
                       node.push(new Node(null,null, n));
                    }
                    n = """";
                    ops.push(a[i]);
                    break;
                case ""-"":
                    if(n != """"){
                       node.push(new Node(null,null, n));
                    }
                    n = """";
                    ops.push(a[i]);
                    break;
                case ""/"":
                    if(n != """"){
                       node.push(new Node(null,null, n));
                    }
                    n = """";
                    ops.push(a[i]);
                    break;
                case ""*"":
                    if(n != """"){
                       node.push(new Node(null,null, n));
                    }
                    n = """";
                    ops.push(a[i]);
                    break;
                case "")"":
                    if(n != """"){
                       node.push(new Node(null,null, n));
                    }
                    n = """";
                    Node A = node.pop();
                    Node B = node.pop();
                    node.push(new Node(B, A, ops.pop()));
                    break;
                default :
                    n = n +a[i];
                    break;
            }
        }
        root = node.pop();
        return root;
    }

    public Node[] PrintPrefix() {
        prefix = new Node[nodenum];
        countpre = 0;
        if(root == null){
            throw new NullPointerException();
        }
        Prere(root);
        return prefix;
    }

    public Node[] PrintPostfix() {
        postfix = new Node[nodenum];
        countpost = 0;
        if(root == null){
            throw new NullPointerException();
        }
        Postre(root);
        return postfix;
    }

    public double Evaluation() {
        double answer = 0;
        Node[] k = PrintPostfix();
        for (int i = 0; i < nodenum; i++) {
            if (k[i].getValue().equals(""+"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(a + b));
            } else if (k[i].getValue().equals(""-"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(b - a));
            } else if (k[i].getValue().equals(""*"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(a * b));
            } else if (k[i].getValue().equals(""/"")) {
                double a = Double.parseDouble(val.pop());
                double b = Double.parseDouble(val.pop());
                val.push(String.valueOf(b / a));
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
//        Node B = new Node(null, null, A.getValue());
        postfix[countpost] = A;
        countpost++;
        return A;
    }

    public Node Prere(Node A) {
//        Node B = new Node(null, null, A.getValue());
        prefix[countpre] = A;
        countpre++;

        if (A.getLeft() != null) {
            Prere(A.getLeft());
            Prere(A.getRight());
        }
        return A;

    }

    public static void main(String[] args) throws Exception {

        String infix = ""((((((41+5)/33)-0.05)*(8-9))/4)+177.8)"";
        Expression expression = new Expression();
        expression.Infix2BT(infix);
        Node[] prefixNodes = expression.PrintPrefix();
        for (Node n : prefixNodes) {
            System.out.print(n.getValue());
        }
        System.out.println("""");

        Node[] postfixNodes = expression.PrintPostfix();
        for (Node n : postfixNodes) {
            System.out.print(n.getValue());
        }
        System.out.println("""");

        System.out.println(expression.Evaluation());
     
    }

}
