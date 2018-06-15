import java.lang.NullPointerException;

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
    
    /* Recursive call for Method Infix2BT */
    private Node Infix2BT(Node x, String infix) {
        // initialization
        if (x == null) {
            // since '(' is a recursive call, 
            // for such case ""(((1+2)....."", we need to prevent null Node
            x = new Node(null, null, """");
            this.size += 1; // update size
        } // end if
        String val = """";
        boolean isLeft = true;
        
        // iterate through the expression
        while (true) {
            // read an character
            char c = infix.charAt(this.index++);
            
            // case01: '(': recursive call to the next level
            if (c == '('){ 
                if (isLeft) x.setLeft (Infix2BT(x.getLeft(),  infix));
                else        x.setRight(Infix2BT(x.getRight(), infix));
                continue;
            } // end if
            
            // case02: ')': finish a subtree and break,return
            if (c == ')'){ 
                if (!val.equals("""")) {
                    // if value exist, add to the right link 
                    x.setRight(new Node(null, null, val));
                    // update size
                    this.size += 1;
                } // end if
                break;
            } // end if
            
            // case03: deal with operators
            if (c == '+' || c == '-' || c == '*' || c == '/'){
                if (!val.equals("""")) {
                    // if value exist, add to the left link
                    x.setLeft(new Node(null, null, val));
                    // update size & reset value
                    this.size += 1;
                    val = """";
                } // end if
                
                // node value = operator
                x.setValue(c+"""");
                
                // set isLeft to false and continue
                isLeft = false;
                continue;
            } // end if
            
            // case04: collect char if not special char
            val += c;
        } // end while
        
        return x;
    } // end private class infix2BT 
    
    /**
     * Method: size
     * return the number of nodes in the tree
     * @return integer
     */
    public int size() {
        return size;
    } // end public method size
    
    /**
     * Method: PrintPrefix
     * return Node array in Prefix order by calling a recursive function
     * @return Node[]
     */
    public Node[] PrintPrefix(){
        if (root == null) throw new NullPointerException();
        // initialization
        Node[] prefix = new Node[size];
        index = 0;
        // get Prefix of the expression and return
        PrintPrefix(root, prefix);
        return prefix;
    } // end public method PrintPrefix
    
    /* Recursive call for Method PrintPrefix */
    /* Postfix: (1) print itself (2) print left (3) print right */
    private void PrintPrefix(Node x, Node[] nodes){
        nodes[index++] = x;
        
        if (x.getLeft() != null)
            PrintPrefix(x.getLeft(), nodes);
        
        if (x.getRight() != null)
            PrintPrefix(x.getRight(), nodes);
    } // end private method PrintPrefix
    
    /**
     * Method: PrintPostfix
     * return Node array in Postfix order by calling a recursive function
     * @return Node[]
     */
    public Node[] PrintPostfix(){
        if (root == null) throw new NullPointerException();
        // initialization
        Node[] postfix = new Node[size];
        index = 0;
        // get Postfix of the expression and return
        PrintPostfix(root, postfix);
        return postfix;
    } // end public method PrintPostfix
    
    /* Recursive call for Method PrintPostfix */
    /* Postfix: (1) print left (2) print right (3) print itself */
    private void PrintPostfix(Node x, Node[] nodes){
        if (x.getLeft() != null)
            PrintPostfix(x.getLeft(), nodes);
        
        if (x.getRight() != null)
            PrintPostfix(x.getRight(), nodes);
        
        nodes[index++] = x;
    } // end private method PrintPrefix

    /**
     * Method: Evaluation
     * calculate the result of expression by calling a recursive function
     * @return Node[]
     */
    public double Evaluation(){
        double answer = Evaluation(root);
        return answer;
    } // end public method Evaluation
    
    /* Recursive call for Method Evaluation */
    private Double Evaluation(Node x){
        if (x.getLeft() == null) return Double.parseDouble(x.getValue());
        
        // first evaluation left and right subtree
        Double val1 = Evaluation(x.getLeft());
        Double val2 = Evaluation(x.getRight());
        
        // return the result
        if (x.getValue().equals(""+"")) return val1 + val2;
        if (x.getValue().equals(""-"")) return val1 - val2;
        if (x.getValue().equals(""*"")) return val1 * val2;
        if (x.getValue().equals(""/"")) return val1 / val2; 
        
        // impossible to be executed, unless other operators exist
        return null; 
    } // end private method Evaluation
    
    public static void main(String[] args) {
        Expression exp = new Expression();
        String expStr0 = ""(4+(((4*2)/2)/3))"";
        String expStr1 = ""(10+25)"";
        String expStr2 = ""(1.5+2.6)"";
        String expStr3 = ""((1*2)+(1/2))"";
        String expStr4 = ""(((2/1)+(3+4))+(10/5))"";
        String expStr5 = ""((1.1122+10.3344)+100.5511)"";
        
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
        
        System.out.println(""---------Expression 04---------"");
        root = exp.Infix2BT(expStr4);
        System.out.println(""Expression: "" + expStr4);
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
        
        System.out.println(""---------Expression 05---------"");
        root = exp.Infix2BT(expStr5);
        System.out.println(""Expression: "" + expStr5);
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

