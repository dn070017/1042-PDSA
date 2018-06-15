import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Expression{

    private Node root;
    private List<String> infixArry = new ArrayList<>();

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Node> operator = new Stack<Node>();
        Stack<Node> value = new Stack<Node>();
        Character a;
        String num = """", c;
        for (int i = 0; i < infix.length(); i++) {
            a = infix.charAt(i);
            c = a.toString();

            if (c.equals(""("")) {
                num = """";
                infixArry.add(c);
            } else if (c.equals("")"")) {
                if (!num.equals("""")) value.push(new Node(null,null,num));
                infixArry.add(c);
                num = """";
                Node n = operator.pop();
                n.setRight(value.pop());
                n.setLeft(value.pop());
                root = n;
                value.push(n);
            } else if (c.equals(""+"") || c.equals(""-"") || c.equals(""*"") || c.equals(""/"") ) {
                if (!num.equals("""")) value.push(new Node(null,null,num));
                infixArry.add(c);
                num = """";
                operator.push(new Node(null,null,c));
            } else {
                num = num + c;
                infixArry.add(num);
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        list.add(root);
        traversalTree(list,root.getLeft());
        traversalTree(list,root.getRight());
        return list.toArray(new Node[list.size()]);
    }

    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        traversalTree(list,root.getLeft());
        traversalTree(list,root.getRight());
        list.add(root);
        return list.toArray(new Node[list.size()]);
    }

    private void traversalTree(List<Node> list, Node root) {
        if (root == null) return;
        list.add(root);
        traversalTree(list,root.getLeft());
        traversalTree(list,root.getRight());
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        String[] e = infixArry.toArray(new String[infixArry.size()]);
        Stack<String> operator = new Stack<>();
        Stack<Double> value = new Stack<>();
        for (int i = 0; i <e.length; i++) {
            String s = e[i];
            if (s.equals(""("")) ;
            else if (s.equals(""+"")) operator.push(s);
            else if (s.equals(""-"")) operator.push(s);
            else if (s.equals(""*"")) operator.push(s);
            else if (s.equals(""/"")) operator.push(s);
            else if (s.equals("")""))
            {
                String op = operator.pop();
                if (op.equals(""+"")) {
                    value.push(value.pop() + value.pop());
                } else if (op.equals(""-"")) {
                    value.push(- value.pop() + value.pop());
                } else if (op.equals(""*"")) {
                    value.push(value.pop() * value.pop());
                } else if (op.equals(""/"")) {
                    Double a = value.pop();
                    Double b = value.pop();
                    value.push(b/a);
                }
            }
            else value.push(Double.parseDouble(s));
        }
        return value.pop();
    }
//
//    public static void main(String[] args) {
//
//        String infix = ""(4+(((4*2)/2)/3))"";
//        String infix1 = ""((4.4-3)+((((4*2)/2)/3)+((2+1)*3)))"";
//        Expression e = new Expression();
//        Node n = e.Infix2BT(infix1);
//        Node[] nArray = e.PrintPostfix();
//        for (int i = 0; i < nArray.length; i++) System.out.println(nArray[i].getValue());
//        System.out.println(e.Evaluation());
//    }
}
