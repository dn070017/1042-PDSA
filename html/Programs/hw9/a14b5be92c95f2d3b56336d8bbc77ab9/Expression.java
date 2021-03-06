
public class Expression {
    
    
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
        int InfixCount = 0;
        String[] Infix1 = new String[1000];
        for(int i = 0;i < infix.length();i++){
            if(infix.substring(i, i+1).equals(""+"") || infix.substring(i, i+1).equals(""-"") || infix.substring(i, i+1).equals(""*"") || infix.substring(i, i+1).equals(""/"")){
                break;
                
            }
            if(i == infix.length()-1){
            Node a = new Node(null,null,infix.substring(1, i));
            root = a;
            return root;}
        }
        for(int i = 0;i < infix.length();i++){
            if(infix.substring(i, i+1).equals(""(""))continue;
            if(infix.substring(i, i+1).equals(""+"") || infix.substring(i, i+1).equals(""-"") || infix.substring(i, i+1).equals(""*"") || infix.substring(i, i+1).equals(""/"") || infix.substring(i, i+1).equals("")"")){
                Infix1[InfixCount++] = infix.substring(i, i+1);
                
            }
            else {
                int temp = i;
                while(!infix.substring(i+1, i+2).equals(""("") &&!infix.substring(i+1, i+2).equals(""+"") &&! infix.substring(i+1, i+2).equals(""-"") &&! infix.substring(i+1, i+2).equals(""*"") &&! infix.substring(i+1, i+2).equals(""/"") &&! infix.substring(i+1, i+2).equals("")"") ){
                    i++;
                }
                Infix1[InfixCount++] = infix.substring(temp, i+1);
            }
        }
        String[] Infix = new String[InfixCount];
        for(int i = 0;i < InfixCount;i++){
            Infix[i] = Infix1[i];
        }
                
        
        Node[] array = new Node[infix.length()];//不影響
        
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
        return root;
    }
    int count = 0;
    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();        
        Node[] prefix1 = new Node[1000];
        count = 0;
        preOrder(root,prefix1);
        int counta = 0;
        for(int i = 0;i < prefix1.length && prefix1[i]!=null;i++){
            //System.out.println(prefix1[i].getValue());
            counta++;
        } 
        Node[] prefix = new Node[counta];
        for(int i = 0;i < prefix.length;i++){
            prefix[i] = prefix1[i];
            //System.out.println(prefix[i].getValue());
        }         
        return prefix;
    }
    private void preOrder(Node x,Node[] y){
        y[count++] = x;
        if(x.getLeft()!=null)preOrder(x.getLeft(),y);
        if(x.getRight()!=null)preOrder(x.getRight(),y);
    }
    
    
    int countt = 0;
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        Node[] postfix1 = new Node[1000];
        countt = 0;
        postOrder(root,postfix1);
        int countb = 0;
        for(int i = 0;i < postfix1.length && postfix1[i]!=null;i++){
            countb++;
        }
        Node[] postfix = new Node[countb];
        for(int i = 0;i < postfix.length;i++){
            postfix[i] = postfix1[i];
            //System.out.println(postfix[i].getValue());
        }
        return postfix;
    }
    private void postOrder(Node x,Node[] y){
        if(x.getLeft()!=null) postOrder(x.getLeft(),y);
        if(x.getRight()!=null)postOrder(x.getRight(),y);
        y[countt++] = x;
    }
    private class EvaNode{
        Node item;
        EvaNode pre;
    }
    
    public double Evaluation(){
        EvaNode Evafirst = null;
        if(root == null) throw new NullPointerException();
        double[] valuearray = new double[100];
        Node[] A = PrintPostfix();
        for(int i = 0;i < A.length;i++){
            //System.out.println(A[i].getValue());
        }
        int countvalue = 0;
        for(int i = 0;i < A.length;i++){
            EvaNode oldfirst = Evafirst;
            Evafirst = new EvaNode();
            Evafirst.item = A[i];
            Evafirst.pre = oldfirst;
            if(Evafirst.item.getValue().equals(""+"")){
                if(!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/"")
                        && !Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a+b;
                valuearray[countvalue++] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = valuearray[countvalue-1];
                double c = a+b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (!Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a+b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = valuearray[countvalue-2];
                double c = a+b;
                valuearray[countvalue-2] = c;countvalue--;
                Evafirst.pre = Evafirst.pre.pre.pre;}
            }
            else if(Evafirst.item.getValue().equals(""-"")){
                if(!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/"")
                        && !Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a-b;
                valuearray[countvalue++] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = valuearray[countvalue-1];
                double c = a-b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (!Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a-b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = valuearray[countvalue-2];
                double c = a-b;
                valuearray[countvalue-2] = c;countvalue--;
                Evafirst.pre = Evafirst.pre.pre.pre;}
            }
            else if(Evafirst.item.getValue().equals(""*"")){
                if(!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/"")
                        && !Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a*b;
                valuearray[countvalue++] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = valuearray[countvalue-1];
                double c = a*b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (!Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a*b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = valuearray[countvalue-2];
                double c = a*b;
                valuearray[countvalue-2] = c;countvalue--;
                Evafirst.pre = Evafirst.pre.pre.pre;}
            }
            else if(Evafirst.item.getValue().equals(""/"")){
                if(!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/"")
                        && !Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/"")){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a/b;
                valuearray[countvalue++] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((!Evafirst.pre.pre.item.getValue().equals(""+"") && !Evafirst.pre.pre.item.getValue().equals(""-"")&& !Evafirst.pre.pre.item.getValue().equals(""*"")&& !Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = Double.parseDouble(Evafirst.pre.pre.item.getValue());
                double b = valuearray[countvalue-1];
                double c = a/b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (!Evafirst.pre.item.getValue().equals(""+"") && !Evafirst.pre.item.getValue().equals(""-"") && !Evafirst.pre.item.getValue().equals(""*"") && !Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = Double.parseDouble(Evafirst.pre.item.getValue());
                double c = a/b;
                valuearray[countvalue-1] = c;
                Evafirst.pre = Evafirst.pre.pre.pre;}
                else if((Evafirst.pre.pre.item.getValue().equals(""+"") || Evafirst.pre.pre.item.getValue().equals(""-"")|| Evafirst.pre.pre.item.getValue().equals(""*"") || Evafirst.pre.pre.item.getValue().equals(""/""))
                        && (Evafirst.pre.item.getValue().equals(""+"") || Evafirst.pre.item.getValue().equals(""-"") || Evafirst.pre.item.getValue().equals(""*"") || Evafirst.pre.item.getValue().equals(""/""))){
                double a = valuearray[countvalue-1];
                double b = valuearray[countvalue-2];
                double c = a/b;
                valuearray[countvalue-2] = c;countvalue--;
                Evafirst.pre = Evafirst.pre.pre.pre;}
            }
        }
        double answer = valuearray[countvalue-1];
        //System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Expression exp = new Expression();
        Node test = exp.Infix2BT(""(4-2)"");       
        Node[] pre = exp.PrintPrefix();
        Node[] post = exp.PrintPostfix();
       double answer = exp.Evaluation();
        for(int i = 0 ; i<pre.length;i++){
             System.out.println(pre[i].getValue()); }
        System.out.println(answer);
        
        
    }
    
}

