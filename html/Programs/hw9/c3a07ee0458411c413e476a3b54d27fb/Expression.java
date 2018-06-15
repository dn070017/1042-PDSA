
import java.io.BufferedReader;
import java.io.FileReader;

public class Expression {

    private Node root;
    private double ans;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        
        Stack<String> operator = new Stack<String>();
        Stack<Double> value = new Stack<Double>();
        Stack<Node> node = new Stack<Node>();
        int flag = 0;  //when dealing with num, flag = 1
        int first_index = 0;
        
        for(int i=0;i<infix.length();i++){
            
            int charAscii = (int)infix.charAt(i);   
            if(flag == 1 && !((charAscii>=48 && charAscii<=57)||(charAscii==46)) ){  // back to symbol, need tp push num
                String s = infix.substring(first_index, i);
                value.push(Double.parseDouble(s));
                node.push(new Node(null,null,s));
                flag = 0;
            }
            if(infix.charAt(i) == '('); 
            else if(infix.charAt(i) == '+') { operator.push(infix.substring(i,i+1)); }
            else if(infix.charAt(i) == '-') { operator.push(infix.substring(i,i+1)); }
            else if(infix.charAt(i) == '*') { operator.push(infix.substring(i,i+1)); }
            else if(infix.charAt(i) == '/') { operator.push(infix.substring(i,i+1)); }
            else if(infix.charAt(i) == ')') {
                Node right = node.pop();
                Node left = node.pop();
                String op = operator.pop();
                Node new_node = new Node(left,right,op);
                node.push(new_node);
                
                if (op.equals(""+"")) {value.push(value.pop() + value.pop());} 
                else if (op.equals(""*"")) {value.push(value.pop() * value.pop());}
                else if (op.equals(""-"")) {
                    double temp_1 = value.pop();
                    double temp_2 = value.pop();
                    value.push(temp_2 - temp_1);
                } 
                else if (op.equals(""/"")) {
                    double temp_1 = value.pop();
                    double temp_2 = value.pop();
                    value.push(temp_2 / temp_1);
                }
            }
            else if((charAscii>=48 && charAscii<=57)||(charAscii==46)){ 
                if(flag == 0){
                    first_index = i;
                    flag = 1;
                }
            }
            else {System.out.printf(""Input wrong!""); }
        }
        this.root = node.pop();
        this.ans = value.pop();
        return root;
    }

    public Node[] PrintPrefix(){
        if(root == null){throw new NullPointerException();}
        Queue<Node> q = new Queue<Node>();
        Preorder(root,q);
        int size = q.size();
        Node[] prefix = new Node[size];
        for(int i=0;i<size;i++){
            prefix[i]=q.dequeue();
        }      
        return prefix;
    }

    public void Preorder (Node node, Queue<Node> q){
        if(node == null) { return; }
        q.enqueue(node);
        Preorder(node.getLeft(),q);
        Preorder(node.getRight(),q);
    }
    
    public Node[] PrintPostfix(){
        if(root == null){throw new NullPointerException();}
        Queue<Node> q = new Queue<Node>();
        Postorder(root,q);
        int size = q.size();
        Node[] postfix = new Node[size];
        for(int i=0;i<size;i++){
            postfix[i]=q.dequeue();
        }
        return postfix;
    }
    
    public void Postorder (Node node, Queue<Node> q){
        if(node == null) { return; }
        Postorder(node.getLeft(),q);
        Postorder(node.getRight(),q);
        q.enqueue(node);
    }
    
    public double Evaluation(){
        if(root == null){throw new NullPointerException();}
        double answer = this.ans;
        return answer;
    }
    
    public static void main(String[] args) throws Exception {
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            String line = br.readLine();
            Expression exp = new Expression();

            Node[] prefix = exp.PrintPrefix();
            Node[] postfix = exp.PrintPostfix();

            System.out.printf(""preorder Traversal = "");
            for(int i=0;i<prefix.length;i++){
                System.out.printf(prefix[i].getValue()+"" "");
            }
            System.out.printf(""\n"");
            System.out.printf(""postorder Traversal = "");
            for(int i=0;i<postfix.length;i++){
                System.out.printf(postfix[i].getValue()+"" "");
            }
            System.out.printf(""\n"");
            System.out.println(""ans = "" + exp.Evaluation());
        }
    }
    
}

