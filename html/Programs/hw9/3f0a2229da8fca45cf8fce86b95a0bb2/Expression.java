
import java.util.Arrays;

public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}
    
    public void rightin(Node root, String[] in) {
        int count = 0;      
        if (in[0].equals(""("")) {
            for (int i=1; i<in.length; i++) {
                if      (in[i].equals(""("")) count++;
                else if (in[i].equals("")"")) count--;
                else if (in[i].equals(""+"") || in[i].equals(""-"") || in[i].equals(""*"") || in[i].equals(""/""))
                    if (count == 0) {
                        root.setRight(new Node(null, null, in[i]));
                        String[] rightout = new String[in.length-i-1];
                        System.arraycopy(in, i+1, rightout, 0, in.length-i-1);
                        rightin(root.getRight(), rightout);
                        String[] leftout = new String[i];
                        System.arraycopy(in, 0, leftout, 0, i);
                        leftin(root.getRight(), leftout);  
                    }
            }
        }
        else {
            int num=0;
            for (int i=1; i<in.length; i++) {
                if (in[i].equals("")"")) {
                    num = i;
                    break;
                }
            }
            String[] out = new String[num];
            System.arraycopy(in, 0, out, 0, num);

            
            
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < out.length; i++) {
            result.append( out[i] );
            //result.append( optional separator );
            }
            String str1 = result.toString();
            root.setRight(new Node(null, null, str1));
        }
    }
    
    public void leftin(Node root, String[] in) {
        int count = 0;      
        if (in[in.length-1].equals("")"")) {
            for (int i=in.length-2; i>-1; i--) {
                if      (in[i].equals("")"")) count++;
                else if (in[i].equals(""("")) count--;
                else if (in[i].equals(""+"") || in[i].equals(""-"") || in[i].equals(""*"") || in[i].equals(""/""))
                    if (count == 0) {
                        root.setLeft(new Node(null, null, in[i]));
                        String[] rightout = new String[in.length-i-1];
                        System.arraycopy(in, i+1, rightout, 0, in.length-i-1);
                        rightin(root.getLeft(), rightout);
                        String[] leftout = new String[i];
                        System.arraycopy(in, 0, leftout, 0, i);
                        leftin(root.getLeft(), leftout);  
                    }
            }
        }        
        else {
            int num=0;
            for (int i=in.length-2; i>-1; i--) {
                if (in[i].equals(""("")) {
                    num = in.length-2-i+1;
                    break;
                }
            }
            String[] out = new String[num];
            System.arraycopy(in, in.length-num, out, 0, num);
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < out.length; i++) {
            result.append( out[i] );
            //result.append( optional separator );
            }
            String str1 = result.toString();
            root.setLeft(new Node(null, null, str1));
        }
    }
    
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        root = null;
        String[] in = infix.split("""");
        
        int count = 0;      
        if (in[1].equals(""("")) {
            for (int i=2; i<in.length; i++) {
                if      (in[i].equals(""("")) count++;
                else if (in[i].equals("")"")) count--;
                else if (in[i].equals(""+"") || in[i].equals(""-"") || in[i].equals(""*"") || in[i].equals(""/""))
                    if (count == 0) {
                        root = new Node(null, null, in[i]);
                        String[] rightout = new String[in.length-i-1];
                        System.arraycopy(in, i+1, rightout, 0, in.length-i-1);
                        rightin(root, rightout);
                        String[] leftout = new String[i-1];
                        System.arraycopy(in, 1, leftout, 0, i-1);
                        leftin(root, leftout);  
                    }
            }
//            if (count<0) {
//                int num=0;
//                for (int i=2; i<in.length; i++) {
//                    if (in[i].equals("")"")) {
//                        num = i-1;
//                        break;
//                    }
//                }
//                String[] out = new String[num-1];
//                System.arraycopy(in, 2, out, 0, num-1);
//                String str1 = Arrays.toString(out);
//                str1 = str1.substring(1, str1.length()-1);
//                root = new Node(null, null, str1);
//                
//            }
        }
        

        return root;
    }
    
    public void pretraversal(Node p, Node[] pre) {
        if (p == null) return;
        pre[precount] = p;
        precount++;
        pretraversal(p.getLeft(), pre); // 次輸出左子樹
        pretraversal(p.getRight(), pre);// 後輸出右子樹
    }
    
    int precount;
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
    
    int postcount;
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
        Node root = go.Infix2BT(""(((12+3)+(6.4+5))+((2+3)+(4+5.5)))"");
        Node[] prefix = go.PrintPrefix();
        Node[] postfix = go.PrintPostfix();
        double answer = go.Evaluation();
        System.out.println(root.getValue());
}
}

