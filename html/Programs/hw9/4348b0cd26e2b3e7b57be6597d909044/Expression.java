import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Vector;

public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        char[] data = infix.toCharArray();
        Stack<Node> data_stack = new Stack<Node>();
        int n = 0;
        do
        {
            if(data[n] == ')')
            {
                Node RN = data_stack.pop();
                String op = data_stack.pop().getValue();       
                Node LN = data_stack.pop();
                Node N = new Node(LN,RN,op);
                data_stack.pop();
                data_stack.push(N);
            }
            else
            {
                data_stack.push(new Node(null,null,Character.toString(data[n])));
            }
            n++;
        }while(n != data.length);
        
        root = data_stack.pop();
        return root;
    }

    //recursively do prefix stack
    void GoPreOrder(Node N,Stack node_stack)
    {
        node_stack.push(N);
        Node LN = N.getLeft();
        Node RN = N.getRight();
        if(LN != null) GoPreOrder(LN,node_stack);
        if(RN != null) GoPreOrder(RN,node_stack);
    }
    
    //recursively do prefix stack
    void GoPostOrder(Node N,Stack node_stack)
    {
        Node LN = N.getLeft();
        Node RN = N.getRight();
        if(LN != null) GoPostOrder(LN,node_stack);
        if(RN != null) GoPostOrder(RN,node_stack);
        node_stack.push(N);
        
    }
    
    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        
        Node[] prefix = null;
        Stack<Node> prefix_stack = new Stack<Node>();
        GoPreOrder(root,prefix_stack);
        prefix = new Node[prefix_stack.size()];
        int i = prefix.length;
        while(!prefix_stack.isEmpty())
        {
            prefix[--i] = prefix_stack.pop();
        }
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        
        Node[] postfix = null;
        Stack<Node> prostfix_stack = new Stack<Node>();
        GoPostOrder(root,prostfix_stack);
        postfix = new Node[prostfix_stack.size()];
        int i = postfix.length;
        while(!prostfix_stack.isEmpty())
        {
            postfix[--i] = prostfix_stack.pop();
        }
        return postfix;
    }
    
    boolean IsOperator(String value)
    {
        if(value.equals(""+"") ||value.equals(""-"") ||value.equals(""*"") ||value.equals(""/""))
        {
            return true;
        }
        else return false;
    }
    
    public double Evaluation(Node N){
         double answer = 0;
         double a = 0, b = 0;
         Node LN = N.getLeft();
         Node RN = N.getRight();
         if(LN != null && IsOperator(LN.getValue()))
         {
             a = Evaluation(LN);
         }
         else
         {
             a = Double.valueOf(LN.getValue());
         }
         if(RN != null && IsOperator(RN.getValue()))
         {
             b = Evaluation(RN);
         }
         else
         {
             b = Double.valueOf(RN.getValue());
         }
         
         switch(N.getValue())
         {
             case ""+"":
                 answer = a + b;
                 break;
             case ""-"":
                 answer = a - b;
                 break;
             case ""*"":
                 answer = a * b;
                 break;
             case ""/"":
                 answer = a / b;
                 break;
         }
         return answer;
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        
        double answer = 0;
        answer = Evaluation(root);
        return answer;
    }
    /*public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Expression e = new Expression();
            
            System.out.println(e.Infix2BT(br.readLine()).getValue());
            e.PrintPrefix();
            e.PrintPostfix();
            System.out.println(e.Evaluation());
        }
    }*/
}

