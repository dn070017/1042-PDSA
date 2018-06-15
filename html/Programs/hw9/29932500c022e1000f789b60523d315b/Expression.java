
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
                case '+':
                case '-':
                case '*':
                case '/':
                    if(!tot.equals("""")){
                    vals.push(new Node(null , null , tot));
                    tot = """";
                    ops.push(new Node(null , null , j + """"));
                    break;
                    }
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
        List<Node> prefix = new ArrayList<Node>();
        PrintPostfixRecusive(prefix, root);
        return prefix.toArray(new Node[prefix.size()]);
    }
    public void PrintPostfixRecusive(List<Node> p, Node root){
        if(root==null) return;
        p.add(root);
        PrintPostfixRecusive(p, root.getLeft());
        PrintPostfixRecusive(p, root.getRight());
        p.add(root);
    }
    

    public double Evaluation(){
        double answer = 0;
        return answer;
    }
}

