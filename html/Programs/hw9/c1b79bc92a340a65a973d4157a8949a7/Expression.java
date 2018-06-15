
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Node> ops = new Stack<Node>();
        Stack<Node> vals = new Stack<Node>();
        String tot = """";
        for(int i = 0 ; i < infix.length() ; i++){
            char j = infix.charAt(i);
            switch (j){
                case '(':
                    tot = """";
                    break;
                case '-':
                case '+':
                case '*':
                case '/':
                    if(!tot.equals("""")){
                    vals.push(new Node(null , null , tot));
                    }
                    tot = """";
                    ops.push(new Node(null , null , j + """"));
                    break;
                case ')':
                    if(!tot.equals("""")){
                    vals.push(new Node(null , null , tot));
                    }
                    tot = """";
                    Node num01 = vals.pop();
                    Node num02 = vals.pop();
                    Node cal = ops.pop();
                    cal.setLeft(num02);
                    cal.setRight(num01);
                    root = cal;
                    vals.push(cal);
                    break;
                    
                default:
                    tot +=j;
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> prefix = new ArrayList<Node>();
        PrintPrefixRecusive(prefix, root);
        return prefix.toArray(new Node[prefix.size()]);
    }
    
    public void PrintPrefixRecusive(List<Node> p, Node root){
        if(root==null) return;
        p.add(root);
        PrintPrefixRecusive(p, root.getLeft());
        PrintPrefixRecusive(p, root.getRight());
    }
  
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> postfix = new ArrayList<Node>();
        PrintPostfixRecusive(postfix, root);
        return postfix.toArray(new Node[postfix.size()]);
    }
    public void PrintPostfixRecusive(List<Node> p, Node root){
        if(root==null) return;
        PrintPostfixRecusive(p, root.getLeft());
        PrintPostfixRecusive(p, root.getRight());
        p.add(root);
    }
    

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        Node[] eva = this.PrintPostfix();
        Stack<Double> evalue = new Stack<Double>();
        for(Node n:eva){
            String in = n.getValue();
            if(in.equals(""+"")){
                evalue.push(evalue.pop()+evalue.pop());
            } else if(in.equals(""-"")){
                evalue.push(-evalue.pop()+evalue.pop());
            } else if(in.equals(""*"")){
                evalue.push(evalue.pop()*evalue.pop());
            } else if(in.equals(""/"")){
                evalue.push(1/evalue.pop()*evalue.pop());
            } else {
                evalue.push(Double.parseDouble(in));
            }
            
        }
//        double answer = 0;
        return evalue.pop();
    }
}

