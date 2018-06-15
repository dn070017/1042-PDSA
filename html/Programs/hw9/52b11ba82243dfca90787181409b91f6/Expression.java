public class Expression{
  
    private Node root;
    int N=0;
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        //讀檔，會有小數及二位數Up
        String e[]=infix.split("""");
        
        Stack<Node> vals=new Stack();
        Stack<Node> ops=new Stack();
        int i=0;
        
        while(i<e.length){
            String s=e[i++];
            if(s.equals(""("")) ;
            else if (s.equals(""+"")) {
                Node op=new Node(null,null,s);
                ops.push(op);
                if(root==null) root=op;
                N++;
            }
            else if (s.equals(""*"")) {
                Node op=new Node(null,null,s);
                ops.push(op);
                if(root==null) root=op;
                N++;
            }
            else if (s.equals(""-"")) {
                Node op=new Node(null,null,s);
                ops.push(op);
                if(root==null) root=op;
                N++;
            }
            else if (s.equals(""/"")) {
                Node op=new Node(null,null,s);
                ops.push(op);
                if(root==null) root=op;
                N++;
            }
            else if (s.equals("")""))
            {  
                if(ops.isEmpty()||vals.isEmpty())break;
                Node op=ops.pop();
                op.setRight(vals.pop());
                op.setLeft(vals.pop());
                vals.push(op);
            }
            else{//讀到數字時創一個嶄新的node，RL都是null，值存入
                N++;
                Node val=new Node(null,null,s);
                vals.push(val);
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        if(root==null) throw new NullPointerException();
        Node[] prefix = new Node[N];
        Prefix003(prefix, root,0);
        return prefix;
    }
    
    private void Prefix003(Node[] prefix,Node root, int i){
        if(root==null)return;
        prefix[i]=root;
        i++;        
        Prefix003(prefix, root.getRight(), i);
        Prefix003(prefix, root.getLeft(), i);
    }
    
    public Node[] PrintPostfix(){
        if(root==null) throw new NullPointerException();
        Node[] postfix = new Node[N];
        Postfix003(postfix,root,0);
        return postfix;
    }
    private void Postfix003(Node[] prefix,Node root, int i){
        if(root==null)return;
        Postfix003(prefix, root.getRight(), i);
        Postfix003(prefix, root.getLeft(), i);
        i++;
        prefix[i]=root;
    }
    
    public double Evaluation(){
        if(root==null) throw new NullPointerException();
        double answer = 0;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        String input=""(4+(((4*2)/2)/3))"";
        Expression gg=new Expression();
        System.out.println(gg.Infix2BT(input).getValue());
        Node[]haha=gg.PrintPrefix();
      //  for(int i=0;i<haha.length;i++) System.out.print(haha[i].getValue());
        haha=gg.PrintPostfix();
        for(int i=0;i<haha.length;i++) System.out.print(haha[i].getValue());
        System.out.println(gg.Evaluation());
    }
}

