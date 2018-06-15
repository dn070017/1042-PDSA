public class Expression{
  
    private Node root;
    private String infix;
    private int count;

    // DO NOT MODIFY THIS
    public Expression(){}
    
    private Node readTree() {
        Node n = new Node(null, null, null);
        // get next non-whitespace char
        char ch = infix.charAt(count); count++;
        int chArrayCount = 0;
        String ans ;
        if ((ch >= '0') && (ch <='9')) {
            // leaf node
            ans = String.valueOf(ch);
            while((infix.charAt(count) == '.') || (infix.charAt(count) >= '0' && infix.charAt(count) <= '9')) {
                ans = ans.concat(String.valueOf(infix.charAt(count)));
                count++;
            }
            n.setValue(ans);
        } 
        else if (ch == '(') {
            // an expression
            n.setLeft(readTree());
            n.setValue(String.valueOf(infix.charAt(count))); count++; 
            n.setRight(readTree());
            ch = infix.charAt(count); count++;
            if (ch != ')')
                System.out.print(""EXPECTED ) - } ASSUMED..."");
        } 
        else {
            System.out.print(""EXPECTED ( - CAN'T PARSE"");
            System.exit(1);
        }
        return n;
    }
    
    
    
    
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        root = null;
        this.infix = infix;
        count = 0;
        root =  readTree();
        return root;
    }

    public void pretraversal(Node p, Node[] pre) {
        if (p == null) return;
        pre[precount] = p;
        precount++;
        pretraversal(p.getLeft(), pre); // 次輸出左子樹
        pretraversal(p.getRight(), pre);// 後輸出右子樹
    }
    
    private int precount;
    public Node[] PrintPrefix(){
        if (root == null) { throw new NullPointerException(); }
        Node[] pre = new Node[20];
        precount = 0;
        pretraversal(root, pre);
        Node[] prefix = new Node[precount];
        System.arraycopy(pre, 0, prefix, 0, precount);
        return prefix;
    }
    
    public void posttraversal(Node p, Node[] post) {
        if (p == null) return;
        posttraversal(p.getLeft(), post); // 次輸出左子樹
        posttraversal(p.getRight(), post);// 後輸出右子樹
        post[postcount] = p;
        postcount++;
    }
    
    private int postcount;
    public Node[] PrintPostfix(){
        if (root == null) { throw new NullPointerException(); }
        Node[] post = new Node[20];
        postcount = 0;
        posttraversal(root, post);
        Node[] postfix = new Node[postcount];
        System.arraycopy(post, 0, postfix, 0, postcount);
        return postfix;
    }

    public double Evaluation(){
        if (root == null) { throw new NullPointerException(); }
        double answer = 0;
        Node[] postfix = PrintPostfix();
        Stack<String> C = new Stack();
        for (int i=0; i<postfix.length; i++){
            if (postfix[i].getValue().equals(""+"")) {
                double num2 = Double.parseDouble(C.pop());
                double num1 = Double.parseDouble(C.pop());
                C.push( String.valueOf(num1+num2) );
            }
            else if (postfix[i].getValue().equals(""-"")) {
                double num2 = Double.parseDouble(C.pop());
                double num1 = Double.parseDouble(C.pop());
                C.push( String.valueOf(num1-num2) );
            }
            else if (postfix[i].getValue().equals(""*"")) {
                double num2 = Double.parseDouble(C.pop());
                double num1 = Double.parseDouble(C.pop());
                C.push( String.valueOf(num1*num2) );
            }
            else if (postfix[i].getValue().equals(""/"")) {
                double num2 = Double.parseDouble(C.pop());
                double num1 = Double.parseDouble(C.pop());
                C.push( String.valueOf(num1/num2) );
            }
            else C.push(postfix[i].getValue());
        }
        answer = Double.parseDouble(C.pop());
        return answer;
    }
    
    
    public static void main(String[] args) {
        Expression go = new Expression();
        Node root = go.Infix2BT(""((20120224791*(121259+797912))+(211544771212/2))"");
        Node[] prefix = go.PrintPrefix();
        Node[] postfix = go.PrintPostfix();
        double answer = go.Evaluation();
        System.out.println(root.getValue());
        }
    
    
    
}

