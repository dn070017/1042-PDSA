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
        Stack<Node>temp=new Stack();
        Prefix003(temp,root);
        for(int i=0;i<N;i++)prefix[i]=temp.pop();
        return prefix;
    }    
    private void Postfix003(Stack<Node>temp,Node root){
        if(root==null)return;
        temp.push(root);
        Postfix003(temp,root.getRight());
        Postfix003(temp,root.getLeft());
    }
    
    public Node[] PrintPostfix(){
        if(root==null) throw new NullPointerException();
        Node[] postfix = new Node[N];
        Stack<Node>temp=new Stack();
        Postfix003(temp,root);
        for(int i=0;i<N;i++)postfix[i]=temp.pop();
        return postfix;
    }    
    private void Prefix003(Stack<Node>temp, Node root){
        if(root==null)return;
        Prefix003(temp,root.getRight());
        Prefix003(temp,root.getLeft());
        temp.push(root);
    }
    
    public double Evaluation(){
        if(root==null) throw new NullPointerException();
        double answer = 0;
        String[] calc=new String[N];
        Node[] fuck=PrintPostfix();
        Stack<Double> vals=new Stack();
        
        for(int i=0;i<N;i++){
            calc[i]=fuck[i].getValue();
            if(""+"".equals(calc[i])){
                double baba;
                baba=vals.pop()+vals.pop();
                vals.push(baba);
            }
            else if(""-"".equals(calc[i])){
                double baba;
                baba=-vals.pop()+vals.pop();
                vals.push(baba);
            }
            else if(""*"".equals(calc[i])){
                double baba;
                baba=vals.pop()*vals.pop();
                vals.push(baba);
            }
            else if(""/"".equals(calc[i])){
                double baba;
                baba=(1/vals.pop())*vals.pop();
                vals.push(baba);
            }
            else vals.push(Double.parseDouble(calc[i]));
        }
        answer=vals.pop();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        String input=""(4+(((4*2)/2)/3))"";
        Expression gg=new Expression();
        
        System.out.println(gg.Infix2BT(input).getValue());
        
        Node[]haha=gg.PrintPrefix();
        for(int i=0;i<haha.length;i++) 
            System.out.print(haha[i].getValue());
        
        System.out.println("""");
        
        haha=gg.PrintPostfix();
        for(int i=0;i<haha.length;i++) 
             System.out.print(haha[i].getValue());
        System.out.println("""");
        System.out.println(gg.Evaluation());
    }
}

