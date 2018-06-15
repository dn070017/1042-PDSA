
public class Expression {

    private Node root;
    private int size;    
    int index1 = 0;
    int index2 = 0;
    String[] array;
    Node[] prefix;
    Node[] postfix;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        Node r;
        Node l;
        size = 0;
        array = infix.split(""(?<=[\\(\\)\\+\\-*\\/])|(?=[\\(\\)\\+\\-*\\/])"");
        Stack<Node> tree = new Stack<Node>();
        
        for(String letters : array){
            if(letters.equals(""("") || letters.equals("""")){
                continue;
            }
            else if(letters.equals("")"")){
                r = tree.pop();
                root = tree.pop();
                l = tree.pop();
                
                root.setLeft(l);
                root.setRight(r);
                tree.push(root);
            }
            else{
                Node temp = new Node(null,null,letters);
                
                tree.push(temp);
                size ++;
            }
        }
        
        root = tree.pop();
        return root;
    }

    public Node[] PrintPrefix() {
        if(root == null) throw new NullPointerException();
        
        prefix = new Node[size];
        pre_traversal(root);        
        return prefix;
    }

    public Node[] PrintPostfix() {
        if(root == null) throw new NullPointerException();
        
        postfix = new Node[size];
        post_traversal(root);       
        return postfix;
    }

    public double Evaluation() {
        if(root == null) throw new NullPointerException();
        
        double answer = 0;
        double a;
        double b;
        String op;
        Stack<String> temp = new Stack<String>();
        
        for(int i = 1; i < array.length; i ++){
            if (array[i].equals(""("")) {
                continue;
            }
            else if(array[i].equals("")"")){
                a = Double.parseDouble(temp.pop());
                op = temp.pop();
                b = Double.parseDouble(temp.pop());

                if (op.equals(""+"")) {
                    answer = b + a;
                }
                if (op.equals(""-"")) {
                    answer = b - a;
                }
                if (op.equals(""*"")) {
                    answer = b * a;
                }
                if (op.equals(""/"")) {
                    answer = b / a;
                }
                temp.push("""" + answer);
            }
            else{
                temp.push(array[i]);
            }
        }
        
        answer = Double.parseDouble(temp.pop());
        return answer;
    }
    
    public void pre_traversal(Node pre){
        
        if(pre != null){
            prefix[index1 ++] = pre;
            pre_traversal(pre.getLeft());
            pre_traversal(pre.getRight());
        }
        
    }
    
    public void post_traversal(Node post){
        
        if(post != null){
            post_traversal(post.getLeft());
            post_traversal(post.getRight());
            postfix[index2 ++] = post;
        }
    }

//    public static void main(String[] args) {
//        Expression exp = new Expression();
//        String s = ""(4+(((4*2)/2)/3))"";
//        exp.Infix2BT(s);
//        System.out.println(exp.size);
//        System.out.println(exp.root.getValue());
//        System.out.println(exp.prefix.length);
//        for(Node a : exp.PrintPostfix()){
//            System.out.println(a.getValue());
//        }
//        System.out.println(exp.Evaluation());
//    }

}

