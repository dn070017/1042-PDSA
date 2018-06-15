import java.util.*;

public class Expression{


    boolean DEBUG = false;
    private Node root;
    Stack<Node> s = new Stack<>();


    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){



        String[] inputs = infix.split(""(?<=[)(+\\-*/=])|(?=[)(+\\-*/=])""); //如果前或後有+-*/就切開
        //(	4.0	+	(	(	(	4	*	2.0	)	/	2	)	/	3	)	)

        Stack<Node> nodeS = new Stack<>();

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

    public Node[] PrintPrefix(){



        TraversalPreorder(root);

        Node[] prefix = new Node[s.size()];
        for(int i=0;i<prefix.length;i++) prefix[i] = s.get(i);
        assert s.size()>1;

        return prefix;
    }

    public Void TraversalPreorder(Node p){
        if (p==null) return null;
        s.add(p);
        if(DEBUG) System.out.print(p.getValue()+""\t"");
        TraversalPreorder(p.getLeft());
        TraversalPreorder(p.getRight());
        return null;
    }

    public Node[] PrintPostfix(){
        Node[] postfix = null;
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        return answer;
    }
}

