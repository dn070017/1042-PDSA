
import edu.princeton.cs.algs4.Stack;

public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Node> vals = new Stack<Node>();
        Stack<String> ops = new Stack<String>();
        String[] ee = infix.split("""");
        for (int i = 0; i < ee.length; i++) {
            if (ee[i].equals(""("")) {             
            } else if (ee[i].equals(""+"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""-"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""*"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals(""/"")) {
                ops.push(ee[i]);
            } else if (ee[i].equals("")"")) {
                root.setRight(vals.pop());
                root.setLeft(vals.pop());
                root.setValue(ops.pop());
            } else {
                Node temp_node = new Node(null, null, ee[i]);
                vals.push(temp_node); 
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        Node[] prefix = null;
        
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        Node[] postfix = null;
        
        return postfix;
    }

    public double Evaluation(String infix){
        double answer = 0;
        return answer;
    }
}

