
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
    private Node Rroot;
    private Node Newroot;
    private Integer Rbracket = 0;
    private Integer Lbracket = 0;
    
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
        Rroot=null;
        Stack<String> S1 = new Stack<String>();
        Stack<String> S2 = new Stack<String>();
        
        String[] I = infix.split("""");
        for (int i=1;i<I.length;i++){
            //System.out.println(I[i]); 
            S1.push(I[i]);
        }
        
        
        while (S1.size()> 1){
            String O = S1.pop();
            
            if (O.equals("")"")) Rbracket++;
            else if (O.equals(""("")) {Lbracket++;}
            
            if ((O.equals(""+"") || O.equals(""-"") || O.equals(""*"") || O.equals(""/"")) && Rbracket-Lbracket==1){
                root=new Node(null,Rroot,O);
                Rroot=null;
                //System.out.println(S1.size());
                //System.out.println(S2.size());
                continue;
            }
            String temp = S1.pop();
            if ((O.equals(""+"") && O.equals(""-"") && O.equals(""*"") && O.equals(""/""))&& temp.equals("")"")){
                Newroot = new Node(null,Rroot,O);
                Rroot = Newroot;
                S1.push(temp);
                continue;
            }
            S1.push(temp);
            
            ////////////////////////////////////////////////////////////////////
            while (!O.equals(""("")){
                S2.push(O);
                O=S1.pop();
                if (O.equals("")"")) Rbracket++;
                else if (O.equals(""("")) {Lbracket++;}
            }
            
            String O1 = S2.pop();                
            String Number1 = """";
            while(!O1.equals(""+"") && !O1.equals(""-"") && !O1.equals(""*"") && !O1.equals(""/"")){
                
                if (S2.size()==0){
                    Node OO = new Node(null,null,Number1);
                    if (root==null){
                    root = OO;
                    return root;
                    }
                    else{
                        root.setLeft(OO);
                        return root;
                    }
                }
                
                Number1 = Number1+O1;
                O1=S2.pop();
            }
            
            String O2 = S2.pop();
            String Number2 = """";
            while(!O2.equals("")"")){
                Number2 = Number2+O2;
                O2=S2.pop();
            }
            
            if (!Number1.equals("""") && !Number2.equals("""")){
                Node L = new Node(null,null,Number1);
                Node R = new Node(null,null,Number2);
                Node OO = new Node(L,R,O1);
                if (Rroot == null) Rroot=OO;
                else {
                    Rroot.setLeft(OO);
                }
            }
            else if (Number1.equals("""")){
                Node R = new Node(null,null,Number2);
                Newroot = new Node(Rroot,R,O1);
                Rroot=Newroot;
            }
            else{
                Node L = new Node(null,null,Number1);
                Newroot = new Node(L,Rroot,O1);
                Rroot=Newroot;
            }
        }
        
        if(S2.size()>1){
            String O3 = S2.pop();
            String Number3 = """";
            while (S2.size()>1){
                Number3=Number3+S2.pop();
            }
            Node OO = new Node(null,null,Number3);
            root = new Node(null,OO,O3);
        } 
        
        root.setLeft(Rroot);
        return root;
    }

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
    
    public Node getPrefix(Node n){
        if (n==null) return null;
        PreNodeList.enqueue(n);
        //PreNodeList.add(n);
        //if (n.getLeft()!=null) n.setLeft(getPrefix(n.getLeft()));
        //if (n.getRight()!=null) n.setRight(getPrefix(n.getRight()));
        n.setLeft(getPrefix(n.getLeft()));
        n.setRight(getPrefix(n.getRight()));
        return n;
    }
    
    
    public Node getPostfix(Node n){
        if (n==null) return null;
        PostNodeList.push(n);
        if (n.getRight()!=null) n.setRight(getPostfix(n.getRight()));
        if (n.getLeft()!=null) n.setLeft(getPostfix(n.getLeft()));
        return n;       
    }
    
    
    public Node getEVA(Node n){
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
    
    /*
    public static void main(String[] args) {
        
    }
    */
}

    


