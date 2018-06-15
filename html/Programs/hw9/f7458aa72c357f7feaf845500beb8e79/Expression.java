/**
 * Class Expression 
.
.
.
.
.
.
 * @author Clintko, r02b48003
 */
public class Expression{
  
    private Node root;
    private int index;
    private int size;
    
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        index = 1;
        size = 0;
        root = null;
        root = Infix2BT(root, infix);
        return root;
    }
    
    private Node Infix2BT(Node x, String infix) {
        // initialization
        if (x == null) {
            x = new Node(null, null, """");
            this.size += 1;
        } // end if
        String val = """";
        boolean isLeft = true;
        
        //
        while (true) {
            char c = infix.charAt(this.index++);
            if (c == '('){ 
                if (isLeft) x.setLeft (Infix2BT(x.getLeft(),  infix));
                else        x.setRight(Infix2BT(x.getRight(), infix));
                continue;
            } // end if
            
            if (c == ')'){ 
                if (!val.equals("""")) {
                    //System.out.println(val);
                    x.setRight(new Node(null, null, val));
                    // update size
                    this.size += 1;
                } // end if
                break;
            } // end if
            
            // deal with operators
            if (c == '+' || c == '-' || c == '*' || c == '/'){
                if (!val.equals("""")) {
                    //System.out.print(val);
                    x.setLeft(new Node(null, null, val));
                    // update size & reset value
                    this.size += 1;
                    val = """";
                } // end if
                
                // node value = operator
                x.setValue(c+"""");
                
                //System.out.print(x.getValue());
                isLeft = false;
                continue;
            } // end if
            
            // collect char if not special char
            val += c;
        } // end while
        
        return x;
    } // end private class infix2BT 
    
    
    public int size() {
        return size;
    } // end public method size
    
    public Node[] PrintPrefix(){
        Node[] prefix = new Node[size];
        index = 0;
        PrintPrefix(root, prefix);
        return prefix;
    }
    
    private void PrintPrefix(Node x, Node[] nodes){
        nodes[index++] = x;
        
        if (x.getLeft() != null)
            PrintPrefix(x.getLeft(), nodes);
        
        if (x.getRight() != null)
            PrintPrefix(x.getRight(), nodes);
    } // end private method PrintPrefix
    
    public Node[] PrintPostfix(){
        Node[] postfix = new Node[size];
        index = 0;
        PrintPostfix(root, postfix);
        return postfix;
    }
    
    private void PrintPostfix(Node x, Node[] nodes){
        if (x.getLeft() != null)
            PrintPostfix(x.getLeft(), nodes);
        
        if (x.getRight() != null)
            PrintPostfix(x.getRight(), nodes);
        
        nodes[index++] = x;
    } // end private method PrintPrefix

    public double Evaluation(){
        double answer = Evaluation(root);
        return answer;
    }
    
    private Double Evaluation(Node x){
        if (x.getLeft() == null) return Double.parseDouble(x.getValue());
        
        Double val1 = Evaluation(x.getLeft());
        Double val2 = Evaluation(x.getRight());
        
        if (x.getValue().equals(""+"")) return val1 + val2;
        if (x.getValue().equals(""-"")) return val1 - val2;
        if (x.getValue().equals(""*"")) return val1 * val2;
        if (x.getValue().equals(""/"")) return val1 / val2; 
        
        return null;
    } // end private method Evaluation
    
    public static void main(String[] args) {
        Expression exp = new Expression();
        String expStr0 = ""(4+(((4*2)/2)/3))"";
        String expStr1 = ""(10+25)"";
        String expStr2 = ""(1.5+2.6)"";
        String expStr3 = ""((1*2)+(1/2))"";
        
        Node root;
        Node[] nodes;
        
        System.out.println(""---------Expression 00---------"");
        root = exp.Infix2BT(expStr0);
        System.out.println(""Expression: "" + expStr0);
        System.out.println(""Size: "" + exp.size());
        System.out.println(root.getValue());
        System.out.println(root.getLeft().getValue());
        System.out.println(root.getRight().getValue());
        
        System.out.print(""PrintPrefix: "");
        nodes = exp.PrintPrefix();
        for (Node x : nodes) System.out.print(x.getValue() + "" "");
        System.out.println();
        
        System.out.print(""PrintPostfix: "");
        nodes = exp.PrintPostfix();
        for (Node x : nodes) System.out.print(x.getValue() + "" "");
        System.out.println();
        
        System.out.print(""Evaluation: "");
        System.out.print(exp.Evaluation());
        System.out.println();
        
        System.out.println(""---------Expression 02---------"");
        root = exp.Infix2BT(expStr2);
        System.out.println(""Expression: "" + expStr2);
        System.out.println(""Size: "" + exp.size());
        System.out.println(root.getValue());
        System.out.println(root.getLeft().getValue());
        System.out.println(root.getRight().getValue());
        
        System.out.print(""PrintPrefix: "");
        nodes = exp.PrintPrefix();
        for (Node x : nodes) System.out.print(x.getValue() + "" "");
        System.out.println();
        
        System.out.print(""PrintPostfix: "");
        nodes = exp.PrintPostfix();
        for (Node x : nodes) System.out.print(x.getValue() + "" "");
        System.out.println();
        
        System.out.print(""Evaluation: "");
        System.out.print(exp.Evaluation());
        System.out.println();
        
        System.out.println(""---------Expression 03---------"");
        root = exp.Infix2BT(expStr3);
        System.out.println(""Expression: "" + expStr3);
        System.out.println(""Size: "" + exp.size());
        System.out.println(root.getValue());
        System.out.println(root.getLeft().getValue());
        System.out.println(root.getRight().getValue());
        
        System.out.print(""PrintPrefix: "");
        nodes = exp.PrintPrefix();
        for (Node x : nodes) System.out.print(x.getValue() + "" "");
        System.out.println();
        
        System.out.print(""PrintPostfix: "");
        nodes = exp.PrintPostfix();
        for (Node x : nodes) System.out.print(x.getValue() + "" "");
        System.out.println();
        
        System.out.print(""Evaluation: "");
        System.out.print(exp.Evaluation());
        System.out.println();
        
    } // end main
} // end class Expression
