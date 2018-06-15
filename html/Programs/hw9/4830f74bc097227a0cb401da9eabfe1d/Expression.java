public class Expression{
public Node root;
    public Expression(){}

    public Node Infix2BT(String infix){
    root.setValue(infix);
    return root;
    }
 
    public Node[] PrintPrefix(){
    Node[] p =new Node[1];
    return p;
    }
  
    public Node[] PrintPostfix(){
    Node[] p =new Node[1];
    return p;
    }

    public double Evaluation(){
    return Double.parseDouble(root.getValue());

    }
}

