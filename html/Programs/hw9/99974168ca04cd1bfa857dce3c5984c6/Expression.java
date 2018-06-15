public class Expression{
  
    private Node root;
    private int size;
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        
        int len = infix.length();
        Stack<String> ops = new Stack<>();
        Stack<String> val = new Stack<>();
        root = null;
        for(int i = 0;i<len;i++){
            String temp = infix.substring(i, i+1);
            if(temp.equals(""("")) ;
            else if(temp.equals(""+"")) ops.push(temp);
            else if(temp.equals(""-"")) ops.push(temp);
            else if(temp.equals(""*"")) ops.push(temp);
            else if(temp.equals(""/"")) ops.push(temp);
           
            else if(temp.equals("")"")) {
                String op = ops.pop();
                if(root==null){
                    Node c = new Node(null,null,val.pop()); size++;
                    Node a = new Node(null,null,val.pop()); size++;
                    Node b = new Node(a,c,op);  
                    size++;
                    root = b;
                    val.push(root.getValue());
                }
                else{
                    String cc = val.pop();
                    String aa = val.pop();
                    if(this.find(cc)){
                        Node a = new Node(null,null,aa); size++;
                        Node b = new Node(a,root,op); size++;
                         root = b;
                    val.push(root.getValue());
                    }
                    else{
                        Node c = new Node(null,null,cc); size++;
                        Node b = new Node(root,c,op); size++;
                         root = b;
                    val.push(root.getValue());
                    }
                }               
            }                
            else{
                  int j = i+1;
                  String wa = infix.substring(j, j+1);
                  while(this.find(wa)!=true){
                      j++;
                      wa = infix.substring(j, j+1);
                  }
                  wa = infix.substring(i, j);
                  i = j-1;
                  val.push(wa);
                    }
        }
        return root;
    }

    public Node[] PrintPrefix(){
         if(root ==null) throw new NullPointerException();
         
        Node[] prefix = new Node[size];
        this.pre(root, prefix, 0);
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        if(root ==null) throw new NullPointerException();
        
        Node[] postfix = new Node[size];
        this.post(root, postfix, 0);       
        return postfix;
    }
    
    public double Evaluation(){
        double answer ;
        Node[] po = this.PrintPostfix();
        Stack<Double> ans = new Stack<>();
        for(int i = 0;i<size;i++){
            if(po[i].getValue().equals(""+"")){
                double a,b;
                b = ans.pop();
                a = ans.pop();
                ans.push(a+b);
            }
            else  if(po[i].getValue().equals(""-"")){
                double a,b;
                b = ans.pop();
                a = ans.pop();
                ans.push(a-b);
            }
            else  if(po[i].getValue().equals(""*"")){
                double a,b;
                b = ans.pop();
                a = ans.pop();
                ans.push(a*b);
            }
            else  if(po[i].getValue().equals(""/"")){
                double a,b;
                b = ans.pop();
                a = ans.pop();
                ans.push(a/b);
            }
            else{
                double a = Double.parseDouble(po[i].getValue());
                ans.push(a);
            }
        }
        answer = ans.pop();
        return answer;
    }
    
    public boolean find(String a){
        if(a.equals(""+"") || a.equals(""-"") || a.equals(""*"")
                || a.equals(""/"") || a.equals("")""))
        return true;
        else return false;
    }
    
   public int getsize(){
       return size;
   }
   public int pre(Node a, Node[] b, int i){
       b[i] = a;
       i++;
       if(a.getLeft()!=null)
           i = this.pre(a.getLeft(), b, i);
       if(a.getRight()!=null)
           i = this.pre(a.getRight(), b, i);
       return i;
   }
   public int post(Node a, Node[] b, int i){
        if(a.getLeft()!=null)
          i =this.post(a.getLeft(), b, i);          
        if(a.getRight()!=null)
          i =this.post(a.getRight(), b, i);           
       b[i] = a;
       i++;          
      return i;
   }
}

