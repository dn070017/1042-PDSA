//import edu.princeton.cs.algs4.*;
import java.util.ArrayList;

public class Expression{
  
    private Node root;
    private int length = 0;
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Node> operandStack = new Stack<Node> ();
        Stack<Node> operateStack = new Stack<Node> ();
        ArrayList<String> stringReg = new ArrayList<String> ();
        String[] infixes;
        String[] stringSplit = infix.split("""");;
        String reg = new String();

        length = 0;

        for(int i = 0; i < stringSplit.length; i++){
            if(stringSplit[i].equals(""+"") || stringSplit[i].equals(""-"") ||
               stringSplit[i].equals(""*"") || stringSplit[i].equals(""/"") ||
               stringSplit[i].equals(""("") || stringSplit[i].equals("")"")){
                if(!reg.equals("""")){
                    stringReg.add(reg);
                    reg = new String ();
                }
                stringReg.add(stringSplit[i]);
            }
            else{
                reg = reg.concat(stringSplit[i]);
            }
        }

        infixes = new String[stringReg.size()];
        infixes = stringReg.toArray(infixes);

        for(int i = 1; i < infixes.length; i++){
            if(infixes[i].equals("")"") && !operateStack.isEmpty()){
                Node operate = operateStack.pop();
                Node right = operandStack.pop();
                Node left = operandStack.pop();

                operate.setRight(right);
                operate.setLeft(left);
                operandStack.push(operate);
            }
            else if(infixes[i].equals(""+"") || infixes[i].equals(""-"") ||
                    infixes[i].equals(""*"") || infixes[i].equals(""/"")){
                operateStack.push(new Node(null, null, infixes[i]));
                ++length;
            }
            else if(!infixes[i].equals(""("") && !infixes[i].equals("")"")){
                operandStack.push(new Node(null, null, infixes[i]));
                ++length;
            }
        }
        if(!operandStack.isEmpty()){
            root = operandStack.pop();
        }else{
            root = null;
        }
        return root;
    }

    public Node[] PrintPrefix(){
        if(root != null){
            Node[] prefix = new Node[length];
            prefixBuilder(prefix, 0, root);
            return prefix;
        }
        else{
            return new Node[0];
        }
    }
  
    public Node[] PrintPostfix(){
        if(root != null){
            Node[] postfix = new Node[length];
            postfixBuilder(postfix, length - 1, root);
            return postfix;
        }
        else{
            return new Node[0];
        }
    }

    public double Evaluation(){
        double answer = 0;
        Node calcuate = new Node(null, null, root.getValue());
        deepCopy(root, calcuate);
        answer = recursicive(calcuate);
        return answer;
    }

    private int prefixBuilder(Node[] prefix, int index, Node now){
        prefix[index++] = now;
        if(now.getLeft() != null){
            index = prefixBuilder(prefix, index, now.getLeft());
            index = prefixBuilder(prefix, index, now.getRight());
        }
        return index;
    }

    private int postfixBuilder(Node[] postfix, int index, Node now){
        postfix[index--] = now;
        if(now.getLeft() != null){
            index = postfixBuilder(postfix, index, now.getRight());
            index = postfixBuilder(postfix, index, now.getLeft());
        }
        return index;
    }

    private void deepCopy(Node now, Node copy){
        if(now.getLeft() != null){
            copy.setLeft(new Node(null, null, now.getLeft().getValue()));
            deepCopy(now.getLeft(), copy.getLeft());
        }
        if(now.getRight() != null){
            copy.setRight(new Node(null, null, now.getRight().getValue()));
            deepCopy(now.getRight(), copy.getRight());
        }
    }

    private double recursicive (Node now){
        String value = now.getValue();
        if(now.getRight() == null && now.getLeft() == null){
            return Double.parseDouble(value);
        }
        else{
            if(value.equals(""+"")){
                double left = recursicive(now.getLeft());
                double right = recursicive(now.getRight());
                return left + right;
            }
            else if(value.equals(""-"")){
                double left = recursicive(now.getLeft());
                double right = recursicive(now.getRight());
                return left - right;
            }
            else if(value.equals(""*"")){
                double left = recursicive(now.getLeft());
                double right = recursicive(now.getRight());
                return left * right;
            }
            else if(value.equals(""/"")){
                double left = recursicive(now.getLeft());
                double right = recursicive(now.getRight());
                return left / right;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Expression e = new Expression();
        Node[] prefix;
        Node[] postfix;
        In in = new In(args[0]);

        e.Infix2BT(in.readLine());

        prefix = e.PrintPrefix();
        for(int i = 0; i < prefix.length; i++){
            System.out.print(prefix[i].getValue() + "" "");
        }
        System.out.println();
        postfix = e.PrintPostfix();
        for(int i = 0; i < postfix.length; i++){
            System.out.print(postfix[i].getValue() + "" "");
        }
        System.out.println();
        System.out.println(e.Evaluation());
    }
}
