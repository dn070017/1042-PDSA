 import java.util.ArrayList;
public class Expression{
  
    private Node root;
    private int Size;
    private Node[] pre;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
      //  Node x=new Node(null,null,null);

        Stack<Node> stack = new Stack<Node>();
        //String[] splited = infix.split("""");
        for(int i=0;i<infix.length();i++){    
            String s = String.valueOf(infix.charAt(i));
            
           if (!s.equals("")"") && !s.equals(""("")) {
                stack.push(new Node(null,null,s));
                Size++;
  } 
           else if (s.equals("")"")) {
               Node rightNode=stack.pop();
               Node subRoot=stack.pop();
               Node leftNode=stack.pop();
               stack.push(new Node(leftNode,rightNode,subRoot.getValue()));                               
            }
        }
        root=stack.pop();
        return root;
    }
    ArrayList<Node> arrli = new ArrayList<Node>();
public  ArrayList<Node> preorder(Node root){
    
    if(root!=null){
        arrli.add(root);
        preorder(root.getLeft());
        preorder(root.getRight());
    }
    return arrli;
}
    public Node[] PrintPrefix(){
        Node[] prefix=new Node[Size];
        Node node = root;
      ArrayList<Node> arr= preorder(node);

       for(int i=0;i<Size;i++){
          prefix[i]= arr.get(i);
       }
        //prefix[0]=new Node(null,null,""A"");
        return prefix;
    }
      ArrayList<Node> arrlipo = new ArrayList<Node>();
public  ArrayList<Node> postorder(Node root){
    
    if(root!=null){
        
        postorder(root.getLeft());
        postorder(root.getRight());
        arrlipo.add(root);
    }
    
    return arrlipo;
}
    public Node[] PrintPostfix(){
        Node[] postfix = new Node[Size];
        Node node = root;
      ArrayList<Node> arr= postorder(node);

       for(int i=0;i<Size;i++){
          postfix[i]= arr.get(i);
       }        
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        Node[] postfix = PrintPostfix();
        Double[] Num = new Double[2];
        Stack<String> stack = new Stack<String>();
        for(int i=0;i<postfix.length;i++){
            if(!postfix[i].getValue().equals(""+"") && !postfix[i].getValue().equals(""-"") && !postfix[i].getValue().equals(""*"") && !postfix[i].getValue().equals(""/"")){
                stack.push(postfix[i].getValue());
            }
            else{
                 Num[0] =Double.parseDouble((String) stack.pop());
                    Num[1]= Double.parseDouble((String) stack.pop());
                if(postfix[i].getValue().equals(""+"")){
                  answer = Num[1] + Num[0];
                    stack.push(String.valueOf(answer));
                }
                else if(postfix[i].getValue().equals(""-"")){
                    answer = Num[1] - Num[0];
                    stack.push(String.valueOf(answer));
                }
                else if(postfix[i].getValue().equals(""*"")){
                    answer = Num[1] * Num[0];
                    stack.push(String.valueOf(answer));
                }
                else{
                    answer = Num[1] / Num[0];
                    stack.push(String.valueOf(answer));
                }
            }
                
        }
        
        return answer;
    }
    
    public static void main(String[] args) throws Exception {
        String input = ""(4+(((4*2)/2)/3))"";
Expression exp = new Expression();
Node root=exp.Infix2BT(input);
//System.out.print(root.getValue());
Node[] A =exp.PrintPrefix();
//for(int i=0;i<A.length;i++){
   System.out.print(exp.Evaluation());
//}
        


        
    }
}

