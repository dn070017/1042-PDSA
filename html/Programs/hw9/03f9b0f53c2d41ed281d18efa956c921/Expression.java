import java.util.*;
import java.util.NoSuchElementException;

public class Expression{

    public static void main(String[] args) {
        String input = ""(2.71828/12)"";
        
        Expression test = new Expression();
        test.Infix2BT(input);
        
        //print(test.PrintInfix());
        print(test.PrintPrefix());
        print(test.PrintPostfix());        
        System.out.println(test.Evaluation());
               
    }    
  
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
    
    public static void print(Node[] pf){
        for(int i = 0; i < pf.length;i++){
            System.out.printf(pf[i].getValue()+"" "");
        }
        System.out.println();        
    }
    
}
