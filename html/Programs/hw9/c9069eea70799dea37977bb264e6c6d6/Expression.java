
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
            root.setRight(new Node(null, null, Arrays.toString(out)));
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
            root.setLeft(new Node(null, null, Arrays.toString(out)));
        }
    }
    
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
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
        }
        else {
            int num=0;
            for (int i=2; i<in.length; i++) {
                if (in[i].equals("")"")) {
                    num = i;
                    break;
                }
            }
            String[] out = new String[num];
            System.arraycopy(in, 1, out, 0, num-1);
            root = new Node(null, null, Arrays.toString(out));
        }

        return root;
    }
    
//    public void traversal(Node p, Node[] prefix)
//        {
//            if (p == null) return;
//            prefix[precount] = p;
//            traversal(p.getLeft(), prefix); // 次輸出左子樹
//            traversal(p.getRight(), prefix);// 後輸出右子樹
//        }
//    
//    int precount;
//    public Node[] PrintPrefix(){
//        Node[] prefix = null;
//        precount = 0;
//        traversal(root, prefix);
//        return prefix;
//    }
  
    public Node[] PrintPostfix(){
        Node[] postfix = null;
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        return answer;
    }
    public static void main(String[] args) {
        Expression go = new Expression();
        Node root = go.Infix2BT(""(4+(((4*2)/2)/3))"");
//        Node[] prefix = go.PrintPrefix();
        System.out.println(root.getValue());
}
}

