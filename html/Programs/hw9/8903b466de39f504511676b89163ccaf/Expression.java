import java.util.*;
import java.util.NoSuchElementException;

public class Expression{
    private Node root;
    private String[] input;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String e){
        List<String> p = new ArrayList<>();                        

        int counter = 0;
        int c = 0;
        while(c < e.length()){
            counter = c;
            String a = e.substring(c,c+1);
            if(a.equals(""("") || a.equals("")"") || a.equals(""+"") || a.equals(""-"") || a.equals(""*"") || a.equals(""/"")){    
            p.add(a);
            c++;}
            else{            
            int temp = c+1; 
            String b = e.substring(temp,temp+1);
            while(!(b.equals("")"") || b.equals(""+"") || b.equals(""-"") || b.equals(""*"") || b.equals(""/""))){            
            temp++;
            b = e.substring(temp,temp+1);
            }
            p.add(e.substring(c,temp));
            c = temp;
            }
        }
        
        
        input = new String[p.size()];
        input = p.toArray(input);
        
        Stack<Node> ops = new Stack<>();        
        Stack<Node> vals = new Stack<>();
        
        for (String s : input) {
            if (s.equals(""("")) ;
            else if (s.equals(""+"") || s.equals(""-"") || s.equals(""*"") || s.equals(""/"")) {ops.push(new Node(null,null,s));}
            else if (s.equals("")""))
            {
                if(ops.isEmpty());
                else{
                Node a = ops.pop();
                a.setRight(vals.pop());
                a.setLeft(vals.pop());
                vals.push(a);}
            }
            else vals.push(new Node(null,null,s));         
        }
        
        root = vals.pop();
        return(root);
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

