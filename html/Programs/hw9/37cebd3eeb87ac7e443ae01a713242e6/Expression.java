import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.awt.Color;
import java.util.Arrays;

public class Expression {
    
    private Queue<Node> PRE = new Queue<Node>();
    private Queue<Node> PRO = new Queue<Node>();
    private Node root;


    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){        
        
        Queue<String> temp = insrt(infix); //doing this will save any operator & value in chronologic
        Stack<Node> st1 = new Stack<Node>();//save value
        Stack<String> st2 = new Stack<String>();//save operator
        
        Node right = new Node(null,null,null);
        Node left  = new Node(null,null,null);
        
        int N = temp.size();
        for(int i=0; i<N; i++){
            String A = temp.dequeue();
            
            
            if(A.equals(""("")){        
            }
            else if(A.equals(""+"")){   st2.push(A); 
            }
            else if(A.equals(""-"")){   st2.push(A); 
            }
            else if(A.equals(""*"")){   st2.push(A);  
            }
            else if(A.equals(""/"")){   st2.push(A); 
            }
            else if(A.equals("")"")){
                String op = st2.pop();
                
                Node R = st1.pop();
                Node L = st1.pop();
                root = new Node(L,R,op);
                st1.push(root);
                
                }//end of else if (
            
            else {  
                Node Duncan = new Node(null,null,A);  
                st1.push(Duncan);
            }
        }//end of for
        
        return root;
    }
    public void Prefix(Node ro) {
            this.PRE.enqueue(ro);
            if (ro.getLeft() == null&&ro.getRight() == null) return;
            else{
                Prefix(ro.getLeft());
                Prefix(ro.getRight());
            }
            return;
    } 
    public Node[] PrintPrefix(){
        Node roo = this.root;
        Prefix(roo);
        int prefixsize = this.PRE.size();
        Node[] prefix= new Node[prefixsize];

        return prefix;
    }
    
  
    
    public void Postfix(Node ro) {
            
            if (ro.getLeft() == null&&ro.getRight() == null){ this.PRO.enqueue(ro);return;}
            else{
                Postfix(ro.getLeft());
                Postfix(ro.getRight());
                this.PRO.enqueue(ro);
            }
            return;
    }   
    public Node[] PrintPostfix(){
        Node roo = this.root;
        Postfix(roo);
        int postfixsize = this.PRO.size();
        Node[] postfix= new Node[postfixsize];
 
        return postfix;
    }


    public double Evaluation(){
        double answer = 0;
        Stack<Double> val = new Stack<Double>();
        Node[] pos = this.PrintPostfix();
//        System.out.println(count);
        int ic = 1;
        val.push(Double.parseDouble(pos[0].getValue()));
        while(ic != 9){
            //System.out.printf(pos[i].getValue()+"" "");
            if(pos[ic].getValue().equals(""+"")) {
                double c = val.pop();
                double b = val.pop();
                val.push(b+c);
                answer = (b+c);
//                System.out.println(b+c);
            }
            else if(pos[ic].getValue().equals(""-"")) {
                double c = val.pop();
                double b = val.pop();
                val.push(b-c);
                answer = (b-c);
//                System.out.println(b-c);
            }
            else if(pos[ic].getValue().equals(""*"")) {
                double c = val.pop();
                double b = val.pop();
                val.push(b*c);
                answer = (b*c);
//                System.out.println(b*c);
            }
            else if(pos[ic].getValue().equals(""/"")) {
                double c = val.pop();
                double b = val.pop();
                val.push(b/c);
                answer = (b/c);
//                System.out.println(b/c);
            }
            else{
                val.push(Double.parseDouble(pos[ic].getValue()));
            }
            ic++;
        }
        return answer;
    }
    
    private Queue<String> insrt(String A){
        String store ="""";
        String[] temp = A.split("""");
        Queue<String> PQ = new Queue<String>();
        Queue<String> deal = new Queue<String>();
        
        for(int i=0; i<temp.length; i++){
            if(temp[i].equals(""("")){       
            PQ.enqueue(temp[i]);
            PQ.enqueue("" "");
            }
            else if(temp[i].equals("")"")){   
            PQ.enqueue("" "");        
            PQ.enqueue(temp[i]);             
            }
            else if(temp[i].equals(""+"")){   
            PQ.enqueue("" "");        
            PQ.enqueue(temp[i]);    
            PQ.enqueue("" "");             
            }
            else if(temp[i].equals(""-"")){   
            PQ.enqueue("" "");        
            PQ.enqueue(temp[i]);    
            PQ.enqueue("" "");               
            }
            else if(temp[i].equals(""*"")){   
            PQ.enqueue("" "");        
            PQ.enqueue(temp[i]);    
            PQ.enqueue("" "");               
            }
            else if(temp[i].equals(""/"")){  
            PQ.enqueue("" "");        
            PQ.enqueue(temp[i]);    
            PQ.enqueue("" "");               
            }
            else  PQ.enqueue(temp[i]);
        }
        int N = PQ.size();
        for(int i=0; i<N;i++){
            store = store + PQ.dequeue();
        }
        String[] AA = store.split("" "");
        int AKK = AA.length;  //count exist
        
        for(int i=0; i<AKK;i++){
            deal.enqueue(AA[i]);
        }
        return deal;
    }
    
   
}

