public class Expression{
  
    private Node root;
    final static Queue<Node> prefix = new Queue<Node>();
    final static Queue<Node> postfix = new Queue<Node>();
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        final Stack<Node> nodes = new Stack<Node>();
        String[] str = mod(infix);
        for (int i = 0; i < str.length; i++) {
            String temp  = str[i];
            if(temp.equals(""("")){
            }
            else if(temp.equals("")"")){
                Node rightNode = nodes.pop();
                String op = nodes.pop().getValue();
                Node leftNode = nodes.pop();
                nodes.push(new Node(leftNode, rightNode, op));
            }
            else{
                nodes.push(new Node(null, null, temp));
            }
        }
        root = nodes.pop();
        return root;
    }

    public String[] mod(String str){
        String[] output = new String[str.length()];
        String value = """";
        int k = 0;
        int size = 0;
        for (int i = 0; i < str.length(); i++) {
            char temp  = str.charAt(i);
            if(!notOperand(temp)){
                value += temp;
            }
            else{
                if(value != """"){
                    output[k++] = value;
                    value = """";
                }
                output[k++] = String.valueOf(temp);
            }
        }
        for(int i = 0; i < output.length; i++)
            if(output[i] != null)
                size++;
        String[] finaloutput = new String[size];
        k = 0;
        for(int i = 0; i < output.length; i++)
            if(output[i] != null)
                finaloutput[k++] = output[i];
        return finaloutput;
    }
    
    private static void PrefixTree(Node root) {
        if (isLeaf(root))
            prefix.enqueue(root);
        else {
            prefix.enqueue(root);
            PrefixTree(root.getLeft());
            PrefixTree(root.getRight());
        }
    }

    public Node[] PrintPrefix(){
        int i = 0;
        PrefixTree(root);
        Node[] output = new Node[prefix.size()];
        while(!prefix.isEmpty()){
            output[i++] = prefix.dequeue();
        }
        return output;
    }
    
    private static void PostfixTree(Node root) {
        if (isLeaf(root))
            postfix.enqueue(root);
        else{
            PostfixTree(root.getLeft());
            PostfixTree(root.getRight());
            postfix.enqueue(root);
        }
    }
  
    public Node[] PrintPostfix(){
        int i = 0;
        PostfixTree(root);
        Node[] output = new Node[postfix.size()];
        while(!postfix.isEmpty()){
            output[i++] = postfix.dequeue();
        }
        return output;
    }
    private double eval(Node root){
        if (isLeaf(root)){
            return Double.parseDouble(root.getValue());
        } else if(root.getValue().equals(""*"")){
            return eval(root.getLeft()) * eval(root.getRight());
        } else if (root.getValue().equals( ""+"" )) {
            return eval(root.getLeft()) + eval(root.getRight());
        } else if (root.getValue().equals( ""-"" )) {
            return eval(root.getLeft()) - eval(root.getRight());
        } else if (root.getValue().equals( ""/"" )) {
            return eval(root.getLeft()) / eval(root.getRight());
        }
        return Double.parseDouble(root.getValue());
  }
    

    public double Evaluation(){
        double answer = eval(root);
        return answer;
    }

    private boolean notOperand(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }
    
    private static boolean isLeaf(Node n) {
        return (n.getLeft() == null && n.getRight() == null);
    }
    
    public static void main(String[] args) {
         Expression s = new Expression();
         String test = ""(4+(((4*2)/2)/3))"";
         String[] output = s.mod(test);
         Node a = s.Infix2BT(test);
         Node[] b = s.PrintPostfix();
         System.out.println(a.getValue());
         System.out.println(s.Evaluation());
//         for(int i = 0; i < output.length; i++)
//         System.out.print(output[i].toString() + ""\n"");
    }
}

