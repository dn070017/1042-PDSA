
import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
public class Expression{
  
    private Node root;
    //private Node Rroot;
    //private Node Newroot;
    //private Integer Rbracket = 0;
    //private Integer Lbracket = 0;
    
    private Queue<Node> PreNodeList = new Queue<Node>();
    //private ArrayList<Node> PreNodeList = new ArrayList<Node>();
    private Stack<Node> PostNodeList = new Stack<Node>();
    private Stack<Node> EVA =new Stack<Node>();
    
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        if (infix.equals("""")) {root=null; return root;}
        root = null;
        
        String[] I = infix.split(""((?<=\\+|\\-|\\*|\\/|\\(|\\))|(?=\\+|\\-|\\*|\\/|\\(|\\)))"");
        Integer len = I.length-1;
        String[] S1 = new String[len];
        for (int i=0;i<len;i++){ 
            S1[i]=I[i+1];
        }
        
        if (len==3) {
            root=new Node(null,null,S1[1]);
            return root;
        }
        
        String Index2 = S1[2];
        String IndexLast2 = S1[len-3];
        if (Index2.equals(""+"")||Index2.equals(""-"")||Index2.equals(""*"")||Index2.equals(""/"")){
            root = new Node(null,null,Index2);
            root = BuildTree(root,0,2,len,S1);
        }
        else if (IndexLast2.equals(""+"")||IndexLast2.equals(""-"")||IndexLast2.equals(""*"")||IndexLast2.equals(""/"")){
            root = new Node(null,null,IndexLast2);
            root = BuildTree(root,0,len-3,len,S1);
        }
        else{
            Integer Rbracket = 0;
            Integer Lbracket = 0;
            for (int i=0;i<len;i++){
                String O = S1[i];
                if ((O.equals(""+"") || O.equals(""-"") || O.equals(""*"") || O.equals(""/"")) && Lbracket-Rbracket==1){
                    root = new Node(null,null,O);
                    root = BuildTree(root,0,i,len,S1);
                    break;
                }
                if (O.equals(""("")) Lbracket++;
                else if (O.equals("")"")) Rbracket++;
            }
        }
        return root;
    }
        ////////////////////////////////////////////////////////////////////////
        
            
           
            
            
////////////////////////////////////////////////////////////////////////////////
    public Node[] PrintPrefix(){
        if (root==null) throw new NullPointerException();
        root = getPrefix(root);
        //Node[] prefix = PreNodeList.toArray(new Node[PreNodeList.size()]);
        Integer size1 = PreNodeList.size();
        Node[] prefix = new Node[size1];
        for (int i=0;i<size1;i++){
            prefix[i]=PreNodeList.dequeue();
        }
        
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        if (root==null) throw new NullPointerException();
        root = getPostfix(root);
        Integer size2 = PostNodeList.size();
        Node[] postfix = new Node[size2];
        for (int i=0;i<size2;i++){
            postfix[i]=PostNodeList.pop();
        }
        return postfix;
    }

    public double Evaluation(){
        if (root==null) throw new NullPointerException();
        Stack<Node> Temp = new Stack<Node>();
        root = getEVA(root);
        while (EVA.size() > 1){
            Node N1 = EVA.pop();
            Node N2 = EVA.pop();
            Node OP = EVA.pop();
            String OPV = OP.getValue();
            while (!OPV.equals(""+"") && !OPV.equals(""-"") && !OPV.equals(""*"") && !OPV.equals(""/"")){
                Temp.push(N1);
                N1=N2;
                N2=OP;
                OP=EVA.pop();
                OPV=OP.getValue();
            }
            Node TempNode = new Node(null,null,calValue(N1.getValue(),N2.getValue(),OP.getValue()));
            EVA.push(TempNode);
            while(Temp.size()!=0){
                EVA.push(Temp.pop());
            }
        }
        String a1 = EVA.pop().getValue();
        double answer = Double.parseDouble(a1);
        
        return answer;
    }
    ////////////////////////////////////////////////////////////////////////
    
    private Node getPrefix(Node n){
        if (n==null) return null;
        PreNodeList.enqueue(n);
        //PreNodeList.add(n);
        //if (n.getLeft()!=null) n.setLeft(getPrefix(n.getLeft()));
        //if (n.getRight()!=null) n.setRight(getPrefix(n.getRight()));
        n.setLeft(getPrefix(n.getLeft()));
        n.setRight(getPrefix(n.getRight()));
        return n;
    }
    
    
    private Node getPostfix(Node n){
        if (n==null) return null;
        PostNodeList.push(n);
        if (n.getRight()!=null) n.setRight(getPostfix(n.getRight()));
        if (n.getLeft()!=null) n.setLeft(getPostfix(n.getLeft()));
        return n;       
    }
    
    
    private Node getEVA(Node n){
        if (n==null) return null;
        EVA.push(n);
        if (n.getRight()!=null) n.setRight(getEVA(n.getRight()));
        if (n.getLeft()!=null) n.setLeft(getEVA(n.getLeft()));
        return n;
    }
    
    private String calValue(String n1,String n2, String op){
        double N1 = Double.parseDouble(n1);
        double N2 = Double.parseDouble(n2);
        if (op.equals(""+"")) return Double.toString(N1+N2);
        else if (op.equals(""-""))return Double.toString(N1-N2);
        else if (op.equals(""*""))return Double.toString(N1*N2);
        else return Double.toString(N1/N2);
    }
    
    private Node BuildTree(Node x,Integer LI,Integer Mid,Integer RI,String[] aList){
        String Temp = x.getValue();
        if (!Temp.equals(""+"") && !Temp.equals(""-"") && !Temp.equals(""*"") && !Temp.equals(""/"")) return x;
                
        if (!aList[Mid-1].equals("")"")){
            Node N = new Node(null,null,aList[Mid-1]);
            x.setLeft(BuildTree(N,LI,Mid-1,Mid,aList));
        }
        else if (aList[Mid-1].equals("")"")){
            Integer Rbracket = 0;
            Integer Lbracket = 0;
        
            for (int i=Mid-1;i>=LI;i--){
                String O =aList[i];
                if ((O.equals(""+"") || O.equals(""-"") || O.equals(""*"") || O.equals(""/"")) && Rbracket-Lbracket==1){
                    Node N =new Node(null,null,O);
                    x.setLeft(BuildTree(N,LI,i,Mid,aList));
                    break;
                }
                if (O.equals(""("")) Lbracket++;
                else if (O.equals("")"")) Rbracket++;
            }
        }
        
        if (!aList[Mid+1].equals(""("")){
            Node N = new Node(null,null,aList[Mid+1]);
            x.setRight(BuildTree(N,Mid,Mid+1,RI,aList));
        }
        else if (aList[Mid+1].equals(""("")){
            Integer Rbracket2 = 0;
            Integer Lbracket2 = 0;
        
            for (int i=Mid+1;i<=RI;i++){
                String O =aList[i];
                if ((O.equals(""+"") || O.equals(""-"") || O.equals(""*"") || O.equals(""/"")) && Lbracket2-Rbracket2==1){
                    Node N =new Node(null,null,O);
                    x.setRight(BuildTree(N,Mid,i,RI,aList));
                    break;
                }
                if (O.equals(""("")) Lbracket2++;
                else if (O.equals("")"")) Rbracket2++;
            }
        }
        return x;
    }
    /*
    public static void main(String[] args) {
        
    }
    */
}

    


