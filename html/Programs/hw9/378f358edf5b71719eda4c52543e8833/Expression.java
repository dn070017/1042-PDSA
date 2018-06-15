import java.util.ArrayList;
import java.util.List;

public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Node> ops = new Stack<Node>();        
        Stack<Node> vals = new Stack<Node>();
        boolean flag =false;
        String[] input = infix.split("""");
         for(int i=0;i<input.length;i++){
            String s = input[i];
            //System.out.println(s);
            switch(s){
                case(""(""):
                    flag=false;
                    break;
                   
            case(""+""):
                //else if (s.equals(""+"") || s.equals(""-"") || s.equals(""*"") || s.equals(""/""))
                //Node ops =(null,null,s);          
            case(""-""):
            case(""*""):
            case(""/""):
                ops.push(new Node(null,null,s));
                flag=false;
                break;
                
            case("")""):               
                //vals.pop();
                //vals.pop();              
                //ops.pop();
                String val =ops.pop().getValue();
                
                //new Node(vals.pop(),vals.pop(),ss);
                Node r = vals.pop();
                Node l = vals.pop();
                vals.push(new Node(l,r,val));
                flag=false;
                //System.out.println(val);
                break;
            default:
                if(flag==false){
                vals.push(new Node(null,null,s));
                flag=true;
                }
                else if(flag==true){
                    String f1=vals.pop().getValue();
                    //System.out.println(f1);
                 vals.push(new Node(null,null,f1+s));
                 //System.out.println(f1+s);                
                }                
                break;

            }//end of switch              
         }//end of for
        root = vals.pop();
        return root;
    }

    public Node[] PrintInfix(){
        if(root == null) throw new NullPointerException();
        List<Node> n = new ArrayList<>();
        for (String input1 : input) {    
                n.add(new Node(null, null, input1));
        }

        Node[] infix = new Node[n.size()];
        infix = n.toArray(infix);
        
        return(infix);        
                
    }    
    
    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> n = new ArrayList<>();
        Stack<Node> stack = new Stack<>(); 
                
        stack.push(root);  
    
         while(stack.isEmpty()==false){  
             Node node = stack.pop();
             n.add(node);
 
             if((node.getRight() == null)==false){  
                 stack.push(node.getRight());  
             }  
             if((node.getLeft() == null)==false){  
                 stack.push(node.getLeft());  
             }  
    
         }  


        Node[] prefix = new Node[n.size()];
        prefix = n.toArray(prefix);
        
        return(prefix);
    }
  
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> n = new ArrayList<>();
        Stack<Node> s = new Stack<>(); 
        
        Node current = root;
        
    while( true ) {  
    
        if(( current == null )==false) {  
             if(( current.getRight() == null )==false)   
              s.push( current.getRight() );  
             s.push( current );  
             current = current.getLeft();  
             continue;  
        }  

        if(s.isEmpty())
            break;
        current = s.pop( );
        
        if( (current.getRight() == null)==false && s.isEmpty( )==false && current.getRight() == s.peek() ) {  
            s.pop( );  
            s.push( current );  
            current = current.getRight();} 
        else {  
            n.add(current);
            current = null;  
        }  
    } 
        Node[] postfix = new Node[n.size()];
        postfix = n.toArray(postfix);
        
        return(postfix);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        
        Stack<String> ops = new Stack<>();        
        Stack<Double> vals = new Stack<>();
        
        for (String s : input) {
            if (s.equals(""("")) ;
            else if (s.equals(""+"") || s.equals(""-"")) ops.push(s);
            else if (s.equals(""*"") || s.equals(""/"")) ops.push(s);
            else if (s.equals("")""))
            {
                if(ops.isEmpty());
                else{
                String op = ops.pop();
                if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
                else if (op.equals(""-"")) {
                    double a = vals.pop();
                    vals.push(vals.pop() - a);
                }
                else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
                else if (op.equals(""/"")) {
                    double a = vals.pop();
                    vals.push(vals.pop() / a);
                }}
            }
            else vals.push(Double.parseDouble(s));
        }
        return(vals.pop());
    }
    
}
