import java.io.FileReader;
import java.util.Arrays;

public class Expression {
    private Queue<Node> PRE = new Queue<Node>();
    private Stack<Node> POS = new Stack<Node>();
    private Node root;
    private int count=0;//count exist

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){        
        
        Queue<String> temp = insrt(infix); //doing this will save any operator & value in chronologic
        Stack<Node> value = new Stack<Node>();//save value
        Stack<String> operator = new Stack<String>();//save operator
        
        Node right = new Node(null,null,null);
        Node left  = new Node(null,null,null);
        
        int N = temp.size();
        for(int i=0; i<N; i++){
            String A = temp.dequeue();
            
            if(A.equals(""("")){        
            }
            else if(A.equals(""+"")){   
                operator.push(A);
            }
            else if(A.equals(""-"")){   
                operator.push(A);
            }
            else if(A.equals(""*"")){   
                operator.push(A);
            }
            else if(A.equals(""/"")){   
                operator.push(A);
            }
            else if(A.equals("")"")){
                String op = operator.pop();
                
                Node R = value.pop();
                Node L = value.pop();
                root = new Node(L,R,op);
                this.count++;
                value.push(root);
                
                }//end of else if (
            
            else {  
                this.count++;
                Node Duncan = new Node(null,null,A);
                value.push(Duncan);
            }
        }//end of for   
        return root;
    }

    public Node[] PrintPrefix(){
        Node roo = this.root;
        Prefix(roo);
        int prefixsize = this.PRE.size();
        Node[] prefix= new Node[prefixsize];
        
        
        for(int i=0; i<prefixsize; i++){
            prefix[i] =  this.PRE.dequeue();
//            System.out.print(prefix[i].getValue()+"" "");
        }           
//        System.out.println();
        return prefix;
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
  
    public Node[] PrintPostfix(){
        Node roo = this.root;
        Postfix(roo);
        int postfixsize = this.POS.size();
        Node[] postfix= new Node[postfixsize];
        
        
        for(int i=0; i<postfixsize; i++){
            postfix[i] =  this.POS.pop();
//            System.out.print(postfix[i].getValue()+"" "");
        }        
//        System.out.println();
        return postfix;
    }
    public void Postfix(Node ro) {
            this.POS.push(ro);
            if (ro.getLeft() == null&&ro.getRight() == null){  return;}
            else{
                Postfix(ro.getRight());
                Postfix(ro.getLeft());
            }
            return;
    }   

    @SuppressWarnings(""empty-statement"")
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
            if(temp[i].equals(""("")){PQ.enqueue(temp[i]);PQ.enqueue(""_"");
            }
            else if(temp[i].equals("")"")){PQ.enqueue(""_"");PQ.enqueue(temp[i]);             
            }
            else if(temp[i].equals(""+"")){PQ.enqueue(""_"");PQ.enqueue(temp[i]);PQ.enqueue(""_"");             
            }
            else if(temp[i].equals(""-"")){PQ.enqueue(""_"");PQ.enqueue(temp[i]);PQ.enqueue(""_"");               
            }
            else if(temp[i].equals(""*"")){PQ.enqueue(""_"");PQ.enqueue(temp[i]);PQ.enqueue(""_"");               
            }
            else if(temp[i].equals(""/"")){PQ.enqueue(""_"");PQ.enqueue(temp[i]);PQ.enqueue(""_"");               
            }
            else  PQ.enqueue(temp[i]);
        }
        int N = PQ.size();
        for(int i=0; i<N;i++){
            store = store + PQ.dequeue();
        }
        String[] AA = store.split(""_"");
//        this.count = AA.length;  //count exist
        
        for(int i=0; i<AA.length;i++){
            deal.enqueue(AA[i]);
        }
        return deal;
    }  
}
