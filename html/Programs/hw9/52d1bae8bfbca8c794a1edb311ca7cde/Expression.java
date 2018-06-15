
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
                if(ops.isEmpty()){
                    break;
                }
                    
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

    public Node[] PrintPrefix(){
        if(root==null)
            throw new NullPointerException();
        List<Node> prefix;
        prefix = new ArrayList<>();
        get_prefix(prefix,root);

        return prefix.toArray(new Node[prefix.size()]);
    }
  
    public Node[] PrintPostfix(){
        if(root==null)
            throw new NullPointerException();
        List<Node> postfix;
        postfix = new ArrayList<>();
        get_postfix(postfix,root);

        return postfix.toArray(new Node[postfix.size()]);
    }

    public double Evaluation(){
        if(root==null)
            throw new NullPointerException();        
       // switch()       
        return evaluate(root);
    }

    private void get_prefix(List<Node> a,Node b) {
        if(b==null)
            return;
            a.add(b);
            get_prefix(a,b.getLeft());
            get_prefix(a,b.getRight());
    }
    private void get_postfix(List<Node> a,Node b) {
        if(b==null)
            return;
            get_prefix(a,b.getLeft());
            get_prefix(a,b.getRight());
            a.add(b);
    }

    private double evaluate(Node root) {
        switch(root.getValue()){
            case""+"":
                return evaluate(root.getLeft())+evaluate(root.getRight());
            case""-"":
                return evaluate(root.getLeft())-evaluate(root.getRight());
            case""*"":
                return evaluate(root.getLeft())*evaluate(root.getRight());
            case""/"":
                return evaluate(root.getLeft())/evaluate(root.getRight());
            default:
                return Double.parseDouble(root.getValue());
        }                        
        
       
    }
}

