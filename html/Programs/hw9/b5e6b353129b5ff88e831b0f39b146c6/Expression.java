
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Expression{
  
    private Node root;
    public int eleSize = 0;
    public int size = 1;
    public int porder = 0;
    public Node[] p;
    
    
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        int count_r=0;
        int count =0;
        int sidelo = 0;
        int lengthB = infix.length();
        char[] ch = new char[lengthB]; // char array
        for (int i = 0;i<lengthB;i++){
            ch[i] = infix.charAt(i);
            if ( ch[i]==')' ) count_r++;
        }
        //System.out.println(ch.length);
        eleSize =  count_r*2+1; // not include ""("" and　"")""
        String[] str = new String[4*count_r+1];  // string array
        
           for(int i = 0;i<lengthB;i++){
               if (ch[i]=='('||ch[i]==')'||ch[i]=='+'||ch[i]=='-'||ch[i]=='*'||ch[i]=='/'){
                   str[count] = String.valueOf(ch[i]);
                   count++;}
               else if (ch[i-1]=='('||ch[i-1]==')'||ch[i-1]=='+'||ch[i-1]=='-'||ch[i-1]=='*'||ch[i-1]=='/'){ // maybe integer or float
                   sidelo = i; // float left bound
                   if (ch[i+1]=='('||ch[i+1]==')'||ch[i+1]=='+'||ch[i+1]=='-'||ch[i+1]=='*'||ch[i+1]=='/'){ // integer
                       str[count] = String.valueOf(ch[i]);
                       count++;
                   }
               }
               else if (ch[i+1]=='('||ch[i+1]==')'||ch[i+1]=='+'||ch[i+1]=='-'||ch[i+1]=='*'||ch[i+1]=='/'){
                   String t = """";//initialization
                   for (int j = sidelo;j<i+1;j++){
                       t = t + String.valueOf(ch[j]);
                   }
                   //System.out.println(count);
                   str[count] = t;
                   count++;
               }
             }
        
        
        //System.out.println(str.length);
        int lengthA = str.length;
        Stack<String> ops = new Stack<String>();
        Stack<Node> val = new Stack<Node>();
        
        // binary tree
        for (int i=0;i<lengthA;i++){
            String temp = str[i];
            if (temp.equals(""(""));
            else if (temp.equals(""+"")|| temp.equals(""-"")||temp.equals(""/"")||temp.equals(""*""))
                ops.push(temp);
            else if (temp.equals("")"")){
                size +=2; // element number: 1.最裡面括號以外 2.不含括號
                Node right = val.pop();
                Node left = val.pop();
                Node N = new Node(left,right,ops.pop());
                root = N;
                val.push(N);
            }
            else 
                val.push(new Node(null,null,temp));
         }
        //System.out.println(size);
        //System.out.println(eleSize);
        return root;
    }
    

    public Node[] PrintPrefix(){ //自己->左->右
//        Node[] prefix = null;
        if (root==null){
            throw new NullPointerException();
        }
        int order = 0;
        Node[] prefix = new Node[eleSize];
        Stack<Node> pre = new Stack<Node>();
        pre.push(root);
        while (!pre.isEmpty()){
            Node n = pre.pop();
            prefix[order] = n;
            order++;
            if (n.getRight()!=null)
                pre.push(n.getRight());
            if (n.getLeft()!=null)
                pre.push(n.getLeft());
        }
        return prefix;
    }
  
    public void postOrder(Node input){
        if (input!=null){
            postOrder(input.getLeft());
            postOrder(input.getRight());
            p[porder] = input;
            porder++;
        }
    }
    
    public Node[] PrintPostfix(){ // 左->右->自己
        //Node[] postfix = null;
        Node[] postfix = new Node[eleSize];
        if (root ==null){
            throw new NullPointerException();
        }
        //System.out.println(porder);
        porder = 0;
        p = new Node[eleSize];
        postOrder(root);
        //System.out.println(porder);
        for (int i=0;i<eleSize;i++){
            postfix[i]=p[i];
        }
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        if (root == null) {
            throw new NullPointerException();
        }
        Stack<String> stack = new Stack<String>(); // 只用來存數值，不存operaters
        Node[] E = PrintPostfix();
        String v1;
        String v2;
        String v;
        double an;
        for (int i=0;i<E.length;i++){
            String temp = E[i].getValue();
            if (temp.equals(""+"")){
                v2 = stack.pop();
                v1 = stack.pop();
                an = Double.parseDouble(v2)+Double.parseDouble(v1);
                v = String.valueOf(an);
                stack.push(v);
            }
            else if (temp.equals(""-"")){
                v2 = stack.pop();
                v1 = stack.pop();
                an = Double.parseDouble(v1)-Double.parseDouble(v2);
                v = String.valueOf(an);
                stack.push(v);
            }
            else if (temp.equals(""*"")){
                v2 = stack.pop();
                v1 = stack.pop();
                an = Double.parseDouble(v1)*Double.parseDouble(v2);
                v = String.valueOf(an);
                stack.push(v);
            }
            else if (temp.equals(""/"")){
                v2 = stack.pop();
                v1 = stack.pop();
                an = Double.parseDouble(v1)/Double.parseDouble(v2);
                v = String.valueOf(an);
                stack.push(v);
            }
            else stack.push(temp);
        }
        answer = Double.parseDouble(stack.peek()); // peek: would not move out and just observe ， pop : would move out
        return answer;
    }
    
       public static void main(String[] args) throws IOException {
        // TODO code application logic here
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           String input = br.readLine();
           
//           Calculator cct = new Calculator();
//           double y = cct.ans(data);
           //System.out.println(input.length());
           Expression e = new Expression();
           Node x = e.Infix2BT(input);
           //Node[] A = e.PrintPostfix();
           //System.out.println(A[6].getValue());
           double an = e.Evaluation();
           System.out.println(an);
    }
   }
}

