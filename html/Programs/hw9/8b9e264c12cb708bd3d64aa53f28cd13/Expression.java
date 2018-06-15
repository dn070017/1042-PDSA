public class Expression{
public Node root;
    public Expression(){}

    public Node Infix2BT(String infix){
        root= new Node(null,null,""1"");
        return root;
    }
 
    public Node[] PrintPrefix(){
        Node[] prefix = new Node[2];
        prefix[0]= root;
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        Node[] postfix1 = new Node[2];
        postfix1[0]= root;
        return postfix1;
    }

    public double Evaluation(){
        double answer = 1.1;
        return answer;  
    }
}

