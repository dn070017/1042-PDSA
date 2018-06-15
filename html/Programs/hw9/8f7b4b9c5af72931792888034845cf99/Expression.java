import java.util.*;

public class Expression{


    boolean DEBUG = false;
    private Node root;
    //    Stack<Node> prefixStack = new Stack<>();
//    Stack<Node> postfixStack = new Stack<>();
    List<Node> prefixStack = new LinkedList<>();
    List<Node> postfixStack = new LinkedList<>();


    // DO NOT MODIFY THIS
    public Expression(){} //FIXME

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){


        if(infix.equals("""")) return null;

        String[] inputs = infix.split(""(?<=[)(+\\-*/=])|(?=[)(+\\-*/=])""); //如果前或後有+-*/就切開
        //(	4.0	+	(	(	(	4	*	2.0	)	/	2	)	/	3	)	)

//        Stack<Node> nodeS = new Stack<>();
        List<Node> nodeS = new LinkedList<>();

        if (DEBUG) {
            for (int i = 0; i < inputs.length; i++) {
                System.out.print(inputs[i]+""\t\t"");
            }
        }

        /* Generate Stack of Node from the String[] */
        for (int i = 0; i < inputs.length; i++) {nodeS.add(new Node(null,null,inputs[i]));}   //size = 17

        /* inspect */ //FIXME ignored cases like ""(10+2-59045)""
        for(int i=0;i<nodeS.size();i++){
            if(nodeS.get(i).getValue().equals("")"")){
                nodeS.get(i-2).setLeft(nodeS.get(i-3));
                nodeS.get(i-2).setRight(nodeS.get(i-1));
                nodeS.remove(i);
                nodeS.remove(i-1);
                nodeS.remove(i-3);
                nodeS.remove(i-4);
                i-=5;
                if(DEBUG) for(int j=0;j<nodeS.size();j++){System.out.print(nodeS.get(j).getValue());}
            }
        }
        assert nodeS.size()==1;
        root = nodeS.get(0);

        return root;
    }

    public Node[] PrintPrefix() throws NullPointerException {
//        prefixStack = new Stack<>();
        if(root.equals(null)) throw new NullPointerException();
        prefixStack = new LinkedList<>();
        TraversalPreorder(root);
        Node[] prefix = new Node[this.prefixStack.size()];
        for(int i=0;i<prefix.length;i++) prefix[i] = this.prefixStack.get(i);
        return prefix;
    }
    public Void TraversalPreorder(Node p){
        if (p==null) return null;
        prefixStack.add(p);
        if(DEBUG) System.out.print(p.getValue()+""\t"");
        TraversalPreorder(p.getLeft());
        TraversalPreorder(p.getRight());
        return null;
    }



    public Node[] PrintPostfix() throws NullPointerException {
//        postfixStack = new Stack<>();
        if(root.equals(null)) throw new NullPointerException();
        postfixStack = new LinkedList<>();
        TraversalPostorder(root);
        Node[] postfix = new Node[this.postfixStack.size()];
        for(int i=0;i<postfix.length;i++) postfix[i] = this.postfixStack.get(i);
        return postfix;
    }
    public Void TraversalPostorder(Node p){
        if (p==null) return null;
        TraversalPostorder(p.getLeft());
        TraversalPostorder(p.getRight());
        postfixStack.add(p);
        if(DEBUG) System.out.print(p.getValue()+""\t"");
        return null;
    }



    public double Evaluation() throws NullPointerException {
        if(root.equals(null)) throw new NullPointerException();
        double answer = 0;
        if(postfixStack.size()==0) return 0.0;
        for(int i=0;i<postfixStack.size();i++){
            if(postfixStack.get(i).getValue().equals(""+"")){
                double n1 = Double.parseDouble(postfixStack.get(i-2).getValue());
                double n2 = Double.parseDouble(postfixStack.get(i-1).getValue());
                Node newNode = new Node(null,null,String.valueOf(n1+n2));
                postfixStack.remove(i);
                postfixStack.remove(i-1);
                postfixStack.remove(i-2);
                postfixStack.add(i-2,newNode);
                i-=3;
            }else if(postfixStack.get(i).getValue().equals(""-"")){
                double n1 = Double.parseDouble(postfixStack.get(i-2).getValue());
                double n2 = Double.parseDouble(postfixStack.get(i-1).getValue());
                Node newNode = new Node(null,null,String.valueOf(n1-n2));
                postfixStack.remove(i);
                postfixStack.remove(i-1);
                postfixStack.remove(i-2);
                postfixStack.add(i-2,newNode);
                i-=3;
            }else if(postfixStack.get(i).getValue().equals(""*"")){
                double n1 = Double.parseDouble(postfixStack.get(i-2).getValue());
                double n2 = Double.parseDouble(postfixStack.get(i-1).getValue());
                Node newNode = new Node(null,null,String.valueOf(n1*n2));
                postfixStack.remove(i);
                postfixStack.remove(i-1);
                postfixStack.remove(i-2);
                postfixStack.add(i-2,newNode);
                i-=3;
            }else if(postfixStack.get(i).getValue().equals(""/"")){
                double n1 = Double.parseDouble(postfixStack.get(i-2).getValue());
                double n2 = Double.parseDouble(postfixStack.get(i-1).getValue());
                Node newNode = new Node(null,null,String.valueOf(n1/n2));
                postfixStack.remove(i);
                postfixStack.remove(i-1);
                postfixStack.remove(i-2);
                postfixStack.add(i-2,newNode);
                i-=3;
            }

        }

        answer = Double.parseDouble(postfixStack.get(0).getValue());
        return answer;
    }
}

