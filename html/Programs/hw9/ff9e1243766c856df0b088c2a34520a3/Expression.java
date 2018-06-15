import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Expression{

    private Node root;
    private List<String> infixAarry = new ArrayList<>();

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
                infixAarry.add(c);
            } else if (c.equals("")"")) {
                if (!num.equals("""")) { value.push(new Node(null,null,num)); infixAarry.add(num); }
                infixAarry.add(c);
                num = """";
                Node n = operator.pop();
                n.setRight(value.pop());
                n.setLeft(value.pop());
                root = n;
                value.push(n);
            } else if (c.equals(""+"") || c.equals(""-"") || c.equals(""*"") || c.equals(""/"") ) {
                if (!num.equals("""")) { value.push(new Node(null,null,num)); infixAarry.add(num); }
                infixAarry.add(c);
                num = """";
                operator.push(new Node(null,null,c));
            } else {
                num = num + c;
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        list.add(root);
        boolean isPrefix = true;
        traversalTree(list,root.getLeft(),isPrefix);
        traversalTree(list,root.getRight(),isPrefix);
        return list.toArray(new Node[list.size()]);
    }

    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        boolean isPrefix = false;
        traversalTree(list,root.getLeft(),isPrefix);
        traversalTree(list,root.getRight(),isPrefix);
        list.add(root);
        return list.toArray(new Node[list.size()]);
    }

    private void traversalTree(List<Node> list, Node root,boolean isPrefix) {
        if (root == null) return;
        if (isPrefix) list.add(root);
        traversalTree(list,root.getLeft(),isPrefix);
        traversalTree(list,root.getRight(),isPrefix);
        if (!isPrefix) list.add(root);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        String[] e = infixAarry.toArray(new String[infixAarry.size()]);
        Stack<String> operator = new Stack<>();
        Stack<Double> value = new Stack<>();
        for (int i = 0; i < e.length; i++) {
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

//    public static void main(String[] args) {
//
//        String infix = ""(4+(((4*2)/2)/3))"";
//        String infix1 = ""((4.4-3)+((((4*2)/2)/3)+((2+1)*3)))"";
//        Expression e = new Expression();
//        Node n = e.Infix2BT(infix);
//        for (int i = 0; i < e.infixAarry.size(); i++) System.out.println(e.infixAarry.get(i));
//        Node[] nArray = e.PrintPostfix();
//        for (int i = 0; i < nArray.length; i++) System.out.println(nArray[i].getValue());
//        System.out.println(e.Evaluation());
//    }
}
