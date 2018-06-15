import java.util.NoSuchElementException;

class Tree{
    Node root;
    Tree(){
        root = new Node(null,null,""0"");
    }

}

public class Expression {
  
    private Node root;
    private Stack<String> st;
    private Stack<String> op;
    private Stack<Tree> trees;
    private double ans;
    private int howmany = 0;
    
    private void Do(){
        String temp = new String();
        Tree newtree = new Tree();
        Tree R = new Tree();
        Tree L = new Tree();
        try{
            if(st.peek()!= ""tree""){
                temp = st.pop();
            R.root.setValue(temp);
            }else{
                st.pop();
                R = trees.pop();
            }
            

        if(st.peek()!= ""tree""){
                temp = st.pop();
            L.root.setValue(temp);
            }else{
                st.pop();
                L = trees.pop();
            }
        //System.out.println(right);
        //System.out.println(left);
        String s = op.pop();
        newtree.root.setValue(s);
        newtree.root.setRight(R.root);
        newtree.root.setLeft(L.root);
        trees.push(newtree);
        st.push(""tree"");
        }catch(NoSuchElementException ex){
        
        }
    } 

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        String data[] = infix.split("""");
        st = new Stack<String>();
        op = new Stack<String>();
        trees = new Stack<Tree>();
        String temp = new String();
        String s = ""speace"";
        for(int i = 0; i<data.length; i++){
            switch(data[i]){
                case ""/0"":
                    break;
                case ""+"":
                    howmany++;
                    op.push(data[i]);
                    break;
                case ""-"":
                    howmany++;
                    op.push(data[i]);
                    break;
                case ""*"":
                    howmany++;
                    op.push(data[i]);
                    break;
                case ""/"":
                    howmany++;
                    op.push(data[i]);
                    break;
                case ""("":
                    break;
                case "")"":
                    Do();
                    break;
                default:
                        temp = data[i];
                        howmany++;
                        while(data[i+1].equals(""1"")|| data[i+1].equals(""2"") ||data[i+1].equals(""3"")
                                || data[i+1].equals(""4"")|| data[i+1].equals(""5"")|| data[i+1].equals(""6"")
                                || data[i+1].equals(""7"")|| data[i+1].equals(""8"")|| data[i+1].equals(""9"")
                                || data[i+1].equals(""0"")|| data[i+1].equals("".""))
                        {
                            i++;
                            temp = temp + data[i];
                            //System.out.print(i);
                        }                        
                            st.push(temp);                        
            } 
        }
        root = trees.peek().root;
        return root;
    }

    public Node[] PrintPrefix()throws NullPointerException{
        Node[] prefix = new Node[howmany];
        int index = 0;
        Pre(prefix,root,index);
        return prefix;
    }
    private int Pre(Node[] nodes, Node n, int index)throws NullPointerException{
        nodes[index] = n;
        index ++;
        if(n.getLeft() != null)
            index = Pre(nodes, n.getLeft(), index);
        if(n.getRight() != null)
            index = Pre(nodes, n.getRight(), index);
        return index;
    }
  
    public Node[] PrintPostfix()throws NullPointerException{
        Node[] prefix = new Node[howmany];
        int index = 0;
        Pos(prefix,root,index);
        return prefix;
    }
    private int Pos(Node[] nodes, Node n, int index)throws NullPointerException{
        if(n.getLeft() != null)
            index = Pos(nodes, n.getLeft(), index);
        if(n.getRight() != null)
            index = Pos(nodes, n.getRight(), index);
        nodes[index] = n;
        index ++;
        return index;
    }

    public double Evaluation()throws NullPointerException{
        double answer = 0;
        answer = eva(root);
        return answer;
    }
    private double eva(Node n)throws NullPointerException{
        String s = n.getValue();
        double answer = 0;
        switch(s){
            case ""+"":
                    answer = eva(n.getLeft())+ eva(n.getRight());
                    break;
                case ""-"":
                    answer = eva(n.getLeft())- eva(n.getRight());
                    break;
                case ""*"":
                    answer = eva(n.getLeft())* eva(n.getRight());
                    break;
                case ""/"":
                    answer = eva(n.getLeft())/ eva(n.getRight());
                    break;
                default: 
                    answer = Double.parseDouble(n.getValue());
        }

        return answer;
    }
    
    public static void main(String[] args){
        String s = args[0];
        Expression e = new Expression();
        e.Infix2BT(s);
        Node[] n;
        n = e.PrintPrefix();
        for(int i = 0; i<n.length; i++){
            System.out.print(n[i].getValue());
        }
        System.out.println();
        n = e.PrintPostfix();
        for(int i = 0; i<n.length; i++){
            System.out.print(n[i].getValue());
        }
        System.out.println();
        System.out.print(e.Evaluation());
    }
}

