
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Expression {

    private Node root;
     int i = 0;
    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        String[] x = infix.split("""");
        Stack<Node> val = new Stack<>();
        Stack<Node> ops = new Stack<>();
        String n = """";
        for (int i = 0; i < infix.length(); i++) {
            String c = x[i];
            if (c.equals(""("")); 
            else if (c.equals(""+"")) {
                if(!n.equals(""""))
                val.push(new Node(null,null,n));
                n="""";
                ops.push(new Node(null, null, c));
            } else if (c.equals(""-"")) {
                  if(!n.equals(""""))
                val.push(new Node(null,null,n));
                n="""";
                ops.push(new Node(null, null, c));
            } else if (c.equals(""*"")) {
                  if(!n.equals(""""))
                val.push(new Node(null,null,n));
                n="""";
                ops.push(new Node(null, null, c));
            } else if (c.equals(""/"")) {
                  if(!n.equals(""""))
                val.push(new Node(null,null,n));
                n="""";
                ops.push(new Node(null, null, c));
            } else if (c.equals("")"")) {
                  if(!n.equals(""""))
               val.push(new Node(null,null,n));
                n="""";
                Node op = ops.pop();
                Node v2 = val.pop();
                Node v1 = val.pop();
                op.setLeft(v1);
                op.setRight(v2);
                root = op;
                val.push(op);
            }
            else {
               n =n+c;
            }

        }

        return root;

    }
    public Node[] PrintPrefix(){
        
        Stack<Node> prefix = new Stack<>();
        prefix(prefix, root);
        Node prefix1[] = new Node[prefix.size()];
        for(int i=prefix.size()-1;i>=0;i--){
            prefix1[i] = prefix.pop();
        }
        return prefix1;
    }
    public void  prefix(Stack<Node> a, Node root){
          if(root==null) return;
          a.push(root);
          prefix( a, root.getLeft());
          prefix( a, root.getRight());
    }
  
    public Node[] PrintPostfix(){
         
        Stack<Node> postfix = new Stack<>();
        postfix(postfix, root);
        Node postfix1[] = new Node[postfix.size()];
        for(int i=postfix.size()-1;i>=0;i--){
            postfix1[i] = postfix.pop();
        }
        return postfix1;
    }
     public void  postfix(Stack<Node> a, Node root){
          if(root==null) return;
          postfix( a, root.getLeft());
          postfix( a, root.getRight());
          a.push(root);
    }


public double Evaluation(){
        if(root == null) throw new NullPointerException();
        return EvaluationHelper(root);
    }

    private double EvaluationHelper(Node root){
        switch (root.getValue()) {
            case ""+"":
                return EvaluationHelper(root.getLeft()) + EvaluationHelper(root.getRight());
            case ""-"":
                return EvaluationHelper(root.getLeft()) - EvaluationHelper(root.getRight());
            case ""*"":
                return EvaluationHelper(root.getLeft()) * EvaluationHelper(root.getRight());
            case ""/"":
                return EvaluationHelper(root.getLeft()) / EvaluationHelper(root.getRight());
            default:
                return Double.parseDouble(root.getValue());
        }
    }
   
