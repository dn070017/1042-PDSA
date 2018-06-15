
public class Expression {
    private class Node{
    private Node left;
    private Node right;
    private String value;

    public Node(Node left, Node right, String value){
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node getLeft(){
        return(this.left);
    }

    public Node getRight(){
        return(this.right);
    }

    public String getValue(){
        return(this.value);
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setValue(String value){
        this.value = value;
    } }
    
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}
    
    // Build a Binary and Return the Root
    private class littleNode{//設一個處理輸入字串的方向
        String item;
        littleNode pre;
    }
    private littleNode first = null;
    private Node[] array ;//要放每次產生的BTS
    public Node Infix2BT(String infix){
        int count = 0;//用來算array的數量
        int lenghtcount = 0;
        for(int i = 0;i < infix.length();i++){
            if(infix.substring(i, i+1).equals(""(""))lenghtcount++;}
        String[] Infix = new String[infix.length()-lenghtcount];
        Node[] array = new Node[infix.length()];
        int Infixcount = 0;
        for(int i = 0;i < infix.length();i++){//把輸入的改成字串陣列
            if(!infix.substring(i, i+1).equals(""("")){
            Infix[Infixcount] = infix.substring(i, i+1);Infixcount++;}}
        int flag = 0;
        for(int i = 0;i < Infix.length;i++){//主要code 
            if(flag == 0){
            littleNode oldfirst = first;
            first = new littleNode();
            first.item = Infix[i];
            first.pre = oldfirst;}
            flag = 0;
            if(Infix[i].equals("")"")){
                flag = 1;
                //數字連數字
                if(!first.pre.item.equals(""+"") && !first.pre.item.equals(""-"") && !first.pre.item.equals(""*"") && !first.pre.item.equals(""/"")
                        && !first.pre.pre.pre.item.equals(""+"") && !first.pre.pre.pre.item.equals(""-"") && !first.pre.pre.pre.item.equals(""*"")&& !first.pre.pre.pre.item.equals(""/"")){
                    Node a = new Node(null,null,first.pre.pre.pre.item);
                    Node c = new Node(null,null,first.pre.item);
                    Node b = new Node(a,c,first.pre.pre.item);   
                    //System.out.println(b.left.value+""  ""+b.value+""  ""+b.right.value);
                    array[count] = b;count++;
                }
                else if((first.pre.item.equals(""+"") || first.pre.item.equals(""-"") || first.pre.item.equals(""*"") || first.pre.item.equals(""/""))
                        && (!first.pre.pre.pre.item.equals(""+"") && !first.pre.pre.pre.item.equals(""-"") && !first.pre.pre.pre.item.equals(""*"")&& !first.pre.pre.pre.item.equals(""/""))){    
                    Node a = new Node(null,null,first.pre.pre.pre.item);
                    //c在array[count-1]
                    Node b = new Node(a,array[count-1],first.pre.pre.item);
                    //System.out.println(b.left.value+""  ""+b.value+""  ""+b.right.value);
                    array[count-1] = b;//count不用加
                    }
                else if((!first.pre.item.equals(""+"") && !first.pre.item.equals(""-"") && !first.pre.item.equals(""*"") && !first.pre.item.equals(""/""))
                        && (first.pre.pre.pre.item.equals(""+"") || first.pre.pre.pre.item.equals(""-"") || first.pre.pre.pre.item.equals(""*"")|| first.pre.pre.pre.item.equals(""/""))){
                    //a在array裡
                    Node c = new Node(null,null,first.pre.item);
                    Node b = new Node(array[count-1],c,first.pre.pre.item);
                    //System.out.println(b.left.value+""  ""+b.value+""  ""+b.right.value);
                    array[count-1] = b;//count不用加
                }
                else{
                    Node b = new Node(array[count-2],array[count-1],first.pre.pre.item);
                    //System.out.println(b.left.value+""  ""+b.value+""  ""+b.right.value);
                    array[count-2] = b; count--;
                }
                if(i+1!=Infix.length){
                littleNode A = first.pre.pre;
                littleNode B = first.pre.pre.pre.pre;
                //System.out.println("" 第一項   ""+B.item+"" 第二項  ""+A.item);
                
                first = new littleNode();
                
                first.item = Infix[++i];
                first.pre = A;
                A.pre = B;
                i--;}
                }}
        root = array[count-1];
        //System.out.println(root.right.left.left.right.value);
        return root;
    }
    int count = 0;
    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();        
        Node[] prefix1 = new Node[1000];
        preOrder(root,prefix1);
        int counta = 0;
        for(int i = 0;i < prefix1.length && prefix1[i]!=null;i++){
            //System.out.println(prefix1[i].value);
            counta++;
        } 
        Node[] prefix = new Node[counta];
        for(int i = 0;i < prefix.length;i++){
            prefix[i] = prefix1[i];
            //System.out.println(prefix[i].value);
        }         
        return prefix;
    }
    private void preOrder(Node x,Node[] y){
        y[count++] = x;
        if(x.left!=null)preOrder(x.left,y);
        if(x.right!=null)preOrder(x.right,y);
    }
    
    
    int countt = 0;
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        Node[] postfix1 = new Node[1000];
        postOrder(root,postfix1);
        int countb = 0;
        for(int i = 0;i < postfix1.length && postfix1[i]!=null;i++){
            countb++;
        }
        Node[] postfix = new Node[countb];
        for(int i = 0;i < postfix.length;i++){
            postfix[i] = postfix1[i];
            //System.out.println(postfix[i].value);
        }
        return postfix;
    }
    private void postOrder(Node x,Node[] y){
        if(x.left!=null) postOrder(x.left,y);
        if(x.right!=null)postOrder(x.right,y);
        y[countt++] = x;
    }
    private class EvaNode{
        Node item;
        EvaNode pre;
    }
    private EvaNode Evafirst = null;
    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        Node[] A = PrintPostfix();
        for(int i = 0;i < A.length;i++){
            EvaNode oldfirst = Evafirst;
            Evafirst = new EvaNode();
            Evafirst.item = A[i];
            Evafirst.pre = oldfirst;
            if(Evafirst.item.value.equals(""+"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.value);
                double b = Double.parseDouble(Evafirst.pre.item.value);
                double c = a+b;
                String newvalue=Double.toString(c);
                Evafirst.item.value = newvalue;
                Evafirst.pre = Evafirst.pre.pre.pre;
                //System.out.println(Evafirst.item.value);
            }
            else if(Evafirst.item.value.equals(""-"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.value);
                double b = Double.parseDouble(Evafirst.pre.item.value);
                double c = a-b;
                String newvalue=Double.toString(c);
                Evafirst.item.value = newvalue;
                Evafirst.pre = Evafirst.pre.pre.pre;
                //System.out.println(Evafirst.item.value);
            }
            else if(Evafirst.item.value.equals(""*"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.value);
                double b = Double.parseDouble(Evafirst.pre.item.value);
                double c = a*b;
                String newvalue=Double.toString(c);
                Evafirst.item.value = newvalue;
                Evafirst.pre = Evafirst.pre.pre.pre;
                //System.out.println(Evafirst.item.value);
            }
            else if(Evafirst.item.value.equals(""/"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.value);
                double b = Double.parseDouble(Evafirst.pre.item.value);
                double c = a/b;
                String newvalue=Double.toString(c);
                Evafirst.item.value = newvalue;
                Evafirst.pre = Evafirst.pre.pre.pre;
                System.out.println(Evafirst.item.value);
            }
        }
        double answer = Double.parseDouble(Evafirst.item.value);
        //System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Expression exp = new Expression();
        Node test = exp.Infix2BT(""(4+(((4*2)/2)/3))"");
        exp.Evaluation();
        
    }
    
}

