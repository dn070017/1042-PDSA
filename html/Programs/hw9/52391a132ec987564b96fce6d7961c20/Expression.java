
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Expression {

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        String[] x = infix.split("""");
        Stack<Node> val = new Stack<>();
        Stack<Node> ops = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            String c = x[i];
            if (c.equals(""("")); 
            else if (c.equals(""+"")) {
                ops.push(new Node(null, null, c));
            } else if (c.equals(""-"")) {
                ops.push(new Node(null, null, c));
            } else if (c.equals(""*"")) {
                ops.push(new Node(null, null, c));
            } else if (c.equals(""/"")) {
                ops.push(new Node(null, null, c));
            } else if (c.equals("")"")) {
                Node op = ops.pop();
                Node v2 = val.pop();
                Node v1 = val.pop();
                op.setLeft(v1);
                op.setRight(v2);
                root = op;
                val.push(op);
            } else {
                val.push(new Node(null, null, c));
            }

        }

        return root;

    }
    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        PrintPrefixHelper(list, root);
        return list.toArray(new Node[list.size()]);
    }

   private void PrintPrefixHelper(List<Node> list, Node root){
        if (root == null) return;
        list.add(root);
        PrintPrefixHelper(list, root.getLeft());
        PrintPrefixHelper(list , root.getRight());
    }

    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        PrintPostfixHelper(list, root);
        return list.toArray(new Node[list.size()]);
    }

    private void PrintPostfixHelper(List<Node> list, Node root){
        if (root == null) return;
        PrintPostfixHelper(list, root.getLeft());
        PrintPostfixHelper(list, root.getRight());
        list.add(root);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        Node[] nodes =  this.PrintPostfix();
        Stack<Double> values = new Stack<Double>();
        for (Node node:nodes) {
            String str = node.getValue();
            switch (str) {
                case ""+"":
                    values.push(values.pop() + values.pop());
                    break;
                case ""-"":
                    values.push(-values.pop() + values.pop());
                    break;
                case ""*"":
                    values.push(values.pop() * values.pop());
                    break;
                case ""/"":
                    values.push(1 / values.pop() * values.pop());
                    break;
                default:
                    values.push(Double.parseDouble(str));
                    break;
            }
        }
        return values.pop();
    }

 public static void main(String[] args) throws Exception {
        String infix = ""(4+(((4*2)/2)/3))"";
        Expression expression = new Expression();
        expression.Infix2BT(infix);
        Node[] prefixNodes = expression.PrintPrefix();
        for(Node n:prefixNodes){
            System.out.print(n.getValue());
        }
        System.out.println("""");

        Node[] postfixNodes = expression.PrintPostfix();
        for(Node n:postfixNodes){
            System.out.print(n.getValue());
        }
        System.out.println("""");

        System.out.println(expression.Evaluation());

       
       
            
    }
}

