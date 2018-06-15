

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Expression {

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        String[] input = new String[infix.length()];
        int count = 0;

        Stack<Node> left = new Stack<Node>();
        Stack<Node> roots = new Stack<Node>();
        Stack<Node> right = new Stack<Node>();

        for (int i = 0; i < infix.length() + 1; i++) {
            input = infix.split(""(?<=[\\(\\)\\+\\-*\\/])|(?=[\\(\\)\\+\\-*\\/])"");
            if (input[i].equals(""("") || input[i].equals("")"")) {
                count++;
            }
        }
//        for (int i = 0; i < infix.length() + 1; i++) {
//            System.out.print(input[i] + ""\n"");
//        }

//        System.out.print(""-------------------------\n"");
        //stem.out.print(infix.length());
//        Node[] inputarray = new Node[infix.length() - count];
        Node ing;
        Node[] ans = new Node[count / 2];
        Node temp = new Node(null, null, ""null"");;
//        Node temp_root1 = new Node(null, null, null);
//        Node temp_root2 = new Node(null, null, null);

        int root_flag = 0;
        int left_flag = 0;
//        int left_ff = 0;
        int right_flag = 0;
//        int right_ff = 0;
        int j = 1;
        int k = 0;
        
        left.push(temp);
        roots.push(temp);
        right.push(temp);
        

        for (int i = 0; i < (infix.length() ); i++) {
            while (j < (infix.length())) {
                j++;
                if (input[j].equals(""("")) {
//                    if (left_flag != 1) {
//                        left_ff = 1;
//                    }
//                    if (right_flag != 1) {
//                        right_ff = 1;
//                    }
                    root_flag = 0;
                    left_flag = 0;
                    right_flag = 0;
                    //System.out.print(j);
                    continue;
                } else if (input[j].equals("")"")) {
                    if ((!(left.peek().getValue().equals(""null""))) && (!(roots.peek().getValue().equals(""null""))) && (!(right.peek().getValue().equals(""null"")))) {//都有      
                        //temp_root1 = roots.pop();
                        ans[k] = new Node(left.pop(), right.pop(), roots.pop().getValue());
                        right_flag = right_flag - 1;
                        root_flag = root_flag - 1;
                        left_flag = left_flag - 1;
//                        System.out.print(""all\n"");
//                        System.out.print(ans[k].getValue() + ""\n"");
//                        System.out.print(ans[k].getLeft().getValue() + ""\n"");
//                        System.out.print(ans[k].getRight().getValue() + ""\n"");
                    } else if (((left.peek().getValue().equals(""null""))) && (!(roots.peek().getValue().equals(""null""))) && (!(right.peek().getValue().equals(""null"")))) {  //沒有left
                        //temp_root2 = roots.pop();
                        if (((k - 1) >= 0)) {
                            ans[k] = new Node(ans[k - 1], right.pop(), roots.pop().getValue());
                            right_flag = right_flag - 1;
                            root_flag = root_flag - 1;
                        }
                        left.pop();
//                        System.out.print(""no left\n"");
//                        System.out.print(ans[k].getValue() + ""\n"");
//                        System.out.print(ans[k].getLeft().getValue() + ""\n"");
//                        System.out.print(ans[k].getRight().getValue() + ""\n"");
                    } else if ((!(left.peek().getValue().equals(""null""))) && (!(roots.peek().getValue().equals(""null""))) && ((right.peek().getValue().equals(""null"")))) {   //沒有right
                        if (((k - 1) >= 0)) {
                            ans[k] = new Node(left.pop(), ans[k - 1], roots.pop().getValue());
                            left_flag = left_flag - 1;
                            root_flag = root_flag - 1;
                        }
                        right.pop();
//                        System.out.print(""no right\n"");
//                        System.out.print(ans[k].getValue() + ""\n"");
//                        System.out.print(ans[k].getLeft().getValue() + ""\n"");
//                        System.out.print(ans[k].getRight().getValue() + ""\n"");
                    } else if (((left.peek().getValue().equals(""null""))) && (!(roots.peek().getValue().equals(""null""))) && ((right.peek().getValue().equals(""null"")))) { //只有root
                        if (((k - 2) >= 0) && ((k - 1) >= 0)) {
                            ans[k] = new Node(ans[k - 2], ans[k - 1], roots.pop().getValue());
                            root_flag = root_flag - 1;
                        }
                        left.pop();
                        right.pop();
//                        System.out.print(""only root\n"");
//                        System.out.print(ans[k].getValue() + ""\n"");
//                        System.out.print(ans[k].getLeft().getValue() + ""\n"");
//                        System.out.print(ans[k].getRight().getValue() + ""\n"");
                    }
                    k++;
                    break;

                } else if ((input[j].equals(""+"")) || (input[j].equals(""-"")) || (input[j].equals(""*"")) || (input[j].equals(""/""))) {
                    root_flag++;
                    ing = new Node(null, null, input[j]);
                    roots.push(ing);
                    //System.out.print(j);
                    break;
                } else {
                    temp = new Node(null, null, ""null"");
                    ing = new Node(null, null, input[j]);
                    if (root_flag != 0) {
                        if (left_flag != 0) {                            
                            right.push(ing);
                            right_flag++;
                        } else {
                            left.push(temp);                            
                            right.push(ing);
                            right_flag++;
                        }
                    } //System.out.print(j);
                    else {
                        left.push(ing);
                        right.push(temp);
                        left_flag++;
                        //System.out.print(j);
                    }
                    break;
                }

            }
        }
        root = ans[k-1];
        return root;
    }
    
    Stack<Node> a = new Stack<Node>();
    Stack<Node> b = new Stack<Node>();
    Stack<Node> c = new Stack<Node>();
    
    public Node[] PrintPrefix() throws Exception{
        if(root != null){
        pre(root);
        int size = a.size();
        Node[] ans = new Node[size];
        //System.out.printf(""size"" + size + ""\n"");
        //System.out.printf(test.getValuetest = a.pop();() + ""\n"");
        for(int i = 0 ; i < size ; i++){
            //ans[0] = prefix.pop();
            ans[i] = a.pop();
            //System.out.printf(ans[i].getValue()+ ""\n"");
            //System.out.print(i+ ""\n"");
        }
        return ans;
        }else
            throw new NullPointerException();
        
    }
    
    private void pre(Node n){        
        if(n != null){            
            //System.out.printf(n.getValue() + ""\n"");
            pre(n.getRight());
            pre(n.getLeft());
            a.push(n);
        }
    }   
    
    public Node[] PrintPostfix() throws Exception{
        if(root != null){
        post(root);       
        int size = b.size();
        Node[] ans = new Node[size];
        //System.out.printf(""size"" + size + ""\n"");
        //System.out.printf(test.getValuetest = a.pop();() + ""\n"");
        for(int i = 0 ; i < size ; i++){
            //ans[0] = prefix.pop();
            ans[i] = b.pop();
            //System.out.printf(ans[i].getValue()+ ""\n"");
            //System.out.print(i+ ""\n"");
        }
        return ans;}
        else
            throw new NullPointerException();
    }
    
    private void post(Node n){        
        if(n != null){            
            //System.out.printf(n.getValue() + ""\n"");
            b.push(n);
            post(n.getRight());
            post(n.getLeft());
            
        }
    } 

    public double Evaluation() {
        if(root != null){
        eva(root);       
        int size = c.size();
        Node[] ans = new Node[size];
        //System.out.printf(""size"" + size + ""\n"");
        //System.out.printf(test.getValuetest = a.pop();() + ""\n"");
        for(int i = 0 ; i < size ; i++){
            //ans[0] = prefix.pop();
            ans[i] = c.pop();
            //System.out.printf(ans[i].getValue()+ ""\n"");
            //System.out.print(i+ ""\n"");
        }        
        
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        for(int i = 0 ; i < size ; i++){
            if (ans[i].getValue().equals(""+"")) {
                vals.push(vals.pop() + vals.pop());
            } else if (ans[i].getValue().equals(""*"")) {
                vals.push(vals.pop() * vals.pop());
            } else if (ans[i].getValue().equals(""/"")) {
                double one = vals.pop();
                double two = vals.pop();
                vals.push(two / one);
            } else if (ans[i].getValue().equals(""-"")) {
                double one = vals.pop();
                double two = vals.pop();
                vals.push(two - one);
            } else {
                vals.push(Double.parseDouble(ans[i].getValue()));
            }
        }
        double answer = vals.pop();
        
        return answer;}
        else
            throw new NullPointerException();
    }
    
    private void eva(Node n){        
        if(n != null){            
            //System.out.printf(n.getValue() + ""\n"");
            c.push(n);
            eva(n.getRight());
            eva(n.getLeft());
            
        }
    }

//    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//            String input = ""(4+(((4*2)/2)/3))"";
//
//            Expression test = new Expression();
//            test.Infix2BT(input);
//            test.PrintPrefix();
//            test.PrintPostfix();
//            
//            //Node[] a = test.PrintPrefix();
//            //System.out.printf(test.PrintPrefix()[0].getValue());
//            //System.out.print();
//            test.Evaluation();
//            
//
//        }
//    }

}

