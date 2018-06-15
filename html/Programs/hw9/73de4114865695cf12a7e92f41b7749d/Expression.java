
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Expression{
  
    private Node root=null;
    private List<String> infixAryList= new ArrayList<>();
    private Stack<Node> valueStack = new Stack<>();
    private Stack<String> operatorStack = new Stack<>();
    private int num=0;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        char tmpChar[]=infix.toCharArray();
        Node nodeL;
        Node nodeR;

        char charTmp=' ';
        int indexForDupNum=0;
        for (int i = 0; i < tmpChar.length; i++) {
            char charNow = tmpChar[i];
            if ((charTmp <= 57 && charTmp >= 48) && (charNow <= 57 && charNow >= 48)) {
                indexForDupNum--;
                infixAryList.add(indexForDupNum, infixAryList.get(indexForDupNum).concat(String.valueOf(charNow)));
                infixAryList.remove(++indexForDupNum);
            } else {
                infixAryList.add(String.valueOf(tmpChar[i]));
                indexForDupNum++;
            }
            charTmp =tmpChar[i];
        }

        for (int i = 0; i < infixAryList.size(); i++) {
            String t=infixAryList.get(i);
            if (t.equals(""(""));
            else if (t.equals(""+"") || t.equals(""-"") || t.equals(""*"") || t.equals(""/"")) {
                operatorStack.push(t);
                num++;
            }else if(t.equals("")"")) {
                if(root==null) {
                    nodeR = new Node(null, null, valueStack.pop().getValue());
                    nodeL = new Node(null, null, valueStack.pop().getValue());
                }else{
                    nodeR = valueStack.pop();
                    nodeL = valueStack.pop();
                }
                root=new Node(nodeL,nodeR,operatorStack.pop());
                valueStack.push(root);
            }else {
                Node tmpNode=new Node(null,null,t);
                valueStack.push(tmpNode);
                num++;
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
       if(root==null) throw new NullPointerException();
        Node[] prefix = new Node[num];
        LinkedQueue<Node> q=new LinkedQueue<>();
//        Queue<Node> q = new LinkedList<>();
        pre(root,q);
        int n=q.size();
        for (int i = 0; i < n; i++) {
            prefix[i] = q.dequeue();
        }
        return prefix;
    }

    private void pre(Node node, LinkedQueue<Node> q){
        if(node.getLeft()!=null && node.getRight()!=null)q.enqueue(node);
        if(node.getLeft()==null && node.getRight()==null){
            q.enqueue(node);
            return;
        }
        pre(node.getLeft(), q);
        pre(node.getRight(), q);
        return;
    }

    public Node[] PrintPostfix(){
        if(root==null) throw new NullPointerException();
        Node[] postfix = new Node[num];
//        Queue<Node> q = new LinkedList<>();
        LinkedQueue<Node> q=new LinkedQueue<>();
        post(root,q);
        int n=q.size();
        for (int i = 0; i < n; i++) {
            postfix[i]=q.dequeue();
        }
        return postfix;
    }

    private void post(Node node, LinkedQueue<Node> q){
        if(node.getLeft()==null && node.getRight()==null){
            q.enqueue(node);
            return;
        }
        post(node.getLeft(), q);
        post(node.getRight(), q);
        q.enqueue(node);
        return;
    }

    public double Evaluation(){
        if(root==null) throw new NullPointerException();
        double answer;
        Stack<Double> vStack = new Stack<>();
        Stack<String> oStack = new Stack<>();
        for (int i = 0; i < infixAryList.size(); i++) {
            String t = infixAryList.get(i);
            if (t.equals(""("")) ;
            else if (t.equals(""+"") || t.equals(""-"") || t.equals(""*"") || t.equals(""/"")) {
                oStack.push(t);
            } else if (t.equals("")"")) {
                String op = oStack.pop();
                if (op.equals(""+"")) {
                    vStack.push(vStack.pop() + vStack.pop());
                } else if (op.equals(""-"")) {
                    vStack.push(-(vStack.pop() - vStack.pop()));
                } else if (op.equals(""*"")) {
                    vStack.push(vStack.pop() * vStack.pop());
                } else if (op.equals(""/"")) {
                    vStack.push(1 / (vStack.pop() / vStack.pop()));
                }
            } else {
                vStack.push(Double.parseDouble(t));
            }
        }
        answer = vStack.pop();
        return answer;
    }
    public static void main(String[] args)throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Expression expression= new Expression();
            String infix=br.readLine();
            expression.Infix2BT(infix);
            Node n1[]=expression.PrintPostfix();
            Node n2[]=expression.PrintPrefix();

            for (int i = 0; i < n1.length; i++) {
                System.out.println(n1[i].getValue());
            }
            System.out.println(""\n"");
            for (int i = 0; i < n2.length; i++) {
                System.out.println(n2[i].getValue());
            }

            System.out.println(expression.Evaluation());
        }
    }
}

