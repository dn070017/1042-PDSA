//package expression;

import java.util.ArrayList;
import java.util.Stack;

public class Expression{
  
    private Node root;
    public String Ans;
    public static ArrayList<Node> preList = new ArrayList();
    public static ArrayList<Node> postList = new ArrayList();

    // DO NOT MODIFY THIS
    public Expression(){}

    
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        int L=infix.length();
        int start = 0;
        int stop = 0;
        Stack<String> st = new Stack();
        Stack<Node> node_st = new Stack();
        
        for (int i=0 ; i<L ; i++){
            char c = infix.charAt(i);
            if (c=='(' ){
                
                start++;
                stop++;
            }
            else if ( c==')'){
                Node m ;
                if (stop==start){
                    Node r = node_st.pop();
                    m = node_st.pop();
                    m.setRight(r);
                }
                else{
                    m = node_st.pop();
                    String r_numstr = infix.substring(start, stop);
                    Node r = new Node(null,null,r_numstr);
                    st.push(r.getValue());
                    m.setRight(r);
                }
                    
                    Node l = node_st.pop();
                    m.setLeft(l);
                    node_st.push(m);
                    String ans = calculate(st.pop(),st.pop(),st.pop());
                    st.push(ans);
                    start = i+1;
                    stop = i+1;
                    //System.out.println(""m=""+m.getValue());
                    //System.out.println(""l=""+l.getValue());
                    //System.out.println(""r=""+m.getRight().getValue());
                    if (i==L-1){
                        root = m;
                        Ans = ans;
                    }
                
            }
            else if (Character.isDigit(c)|| c=='.'){
                
                stop++;
            }
            else{
                
                String numstr;
                if (start==stop){}
                    
                else{
                    numstr = infix.substring(start, stop);
                    //System.out.println(""numstr""+numstr);
                    
                    Node l = new Node(null,null,numstr);
                    node_st.push(l);
                    st.push(l.getValue());
                }
                
                Node m = new Node(null,null,Character.toString(c));    
                node_st.push(m);
                
                st.push(m.getValue());
                start = i+1;
                stop = i+1;
                
            }
            //System.out.println(st.size());
        }
        
        return root;
    }
    
    public String calculate(String r,String op,String l){
        //System.out.println(""l""+l);
        //System.out.println(""op""+op);
        //System.out.println(""r""+r);
        double ans = 0;
        double l_num = Double.parseDouble(l);
        double r_num = Double.parseDouble(r);
        if (op.equals(""+""))
            ans = l_num + r_num;
        else if (op.equals(""-""))
            ans = l_num - r_num;
        else if (op.equals(""*""))
            ans = l_num * r_num;
        else
            ans = l_num / r_num;
        return String.valueOf(ans);
    }

    public Node[] PrintPrefix(){
        
        if (root==null)
            throw new NullPointerException();
        else{
        preRecur(root);
        Node[] prefix = new Node[preList.size()];
        prefix = preList.toArray(prefix);
        
        return prefix; 
        }

    }
    public static void preRecur(Node node){
        preList.add(node);
        if (node.getLeft()==null){}
        else{
            Node left = node.getLeft();
            preRecur(left);
        }
        if (node.getRight()==null){}
        else{
            Node right = node.getRight();
            preRecur(right);
        }
    }
    
    public Node[] PrintPostfix(){
        
        if (root==null)
            throw new NullPointerException();
        else{
        postRecur(root);
        Node[] postfix = new Node[postList.size()];
        postfix = postList.toArray(postfix);
        return postfix;
        }
    }
    public static void postRecur(Node node){

        if (node.getLeft()==null){}
            //postList.add(node);
        else{
            Node left = node.getLeft();
            postRecur(left);
        }
        if (node.getRight()==null){}
            //postList.add(node);
        else{
            Node right = node.getRight();
            postRecur(right);
        }
        postList.add(node);
    }
    public double Evaluation(){
        if (root==null)
            throw new NullPointerException();
        double answer = Double.parseDouble(Ans);
        return answer;
    }
    
    public static void main(String[] args){
        Expression ex = new Expression();
        String input = ""(4+(((4*2)/2)/3))"";
        ex.Infix2BT(input);
        //System.out.println(ex.Evaluation());
        //System.out.println(ex.root.getRight().getValue());
        //for (Node node:ex.PrintPostfix())
            //System.out.print(node.getValue());
    }
}

