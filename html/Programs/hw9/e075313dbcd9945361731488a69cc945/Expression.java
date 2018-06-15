public class Expression{
  
    private Node root;
    Stack<String> calculator = new Stack<String>();
    Stack<Node> stack = new Stack<Node>();
    Queue<Node> queue = new Queue<Node>();
    public double ans;
   
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        //store the string in que
         String ans = new String();
        
        for (String i : infix.split("""")){
           // StdOut.println(""QQQQ"");
            Node a = new Node(null,null,i);
            queue.enqueue(a); 
            //StdOut.println(i);
        }
        
        queue.dequeue();
        Node [] arr = new Node[2];
        //int count = 0;
        Node operator = new Node(null,null,null);
        //calculator.push(""1"");
        while(!queue.isEmpty()){
            Node b = queue.dequeue();
            if(!b.getValue().equals(""("")){
                if(!b.getValue().equals("")"")){
                calculator.push(b.getValue());
                stack.push(b);  
                }

                if(b.getValue().equals("")"")){
                    arr[1] = stack.pop();
                    operator = stack.pop();
                    arr[0] = stack.pop();
                    
                    operator.setLeft(arr[0]);
                    operator.setRight(arr[1]);
                    stack.push(operator);
                    //2.calculate
                    double innersum = 0 ;
                    double two = Double.parseDouble(calculator.pop());
                    //StdOut.println(two);
                    String op = calculator.pop();
                    //StdOut.println(op);
                    double one = Double.parseDouble(calculator.pop());
                    //StdOut.println(one);
                    if(op.equals(""+"")) innersum = one+two;
                    else if(op.equals(""-"")) innersum = one-two;
                    else if(op.equals(""/"")) innersum = one/two;
                    else if(op.equals(""*"")) innersum = one*two;
                    String cal = Double.toString(innersum);
                    calculator.push(cal);
                    ans = cal;
                    //StdOut.println(ans);
                }
            }
        }
        //ans = cal;
        root = operator;
        return root;
    }

public Node[] PrintPrefix(){
        
        Queue<Node> print_prefix = PrintPrefix(root);
        Node[] prefix = new Node [print_prefix.size()] ;
        //StdOut.println(print_prefix.isEmpty());
        int i = 0;
        //StdOut.println(prefix[i]);
        while(!print_prefix.isEmpty()){
            prefix[i] = print_prefix.dequeue();
            i++;
        }
        return prefix;
        //List<Integer> myList = new ArrayList<Integer>()
    }
    public Queue<Node> PrintPrefix(Node x){
        Queue<Node> print_prefix = new Queue<Node>();
        
        if(x==null) {
            throw new NullPointerException();
        }
        else{
            print_prefix.enqueue(x);
            if(x.getLeft()!=null){
                Queue<Node> temp1=PrintPrefix(x.getLeft());
                while(!temp1.isEmpty()){
                    print_prefix.enqueue(temp1.dequeue());
                }
            }
            if(x.getRight()!=null){
                Queue<Node> temp2=PrintPrefix(x.getRight());
                while(!temp2.isEmpty()){
                    print_prefix.enqueue(temp2.dequeue());
                            }
            }
            return print_prefix;
        }
            //if(x.getRight()!=null && x.getLeft()!=null){
            //}   
    }
  
    
    
    
    
    public Node[] PrintPostfix(){
        Node[] postfix = null;
        return postfix;
    }
    public double Evaluation(){
        ans = Double.parseDouble(Evaluation(root));
        return ans;
    }

    public String Evaluation(Node x){
        

        double inner = 0;
        //Stack<Double> innersum = new Stack<>();
        Stack<String> innersum = new Stack<>();
        String ans_end = x.getValue();
        //inner = x.getLeft();
        //double innersum ;
        //innersum = Double.parseDouble(x.getLeft().getValue());
        //double two = Double.parseDouble(calculator.pop());
        //if(null==x.getValue()) return null;
        if(x.getLeft()!=null) {
            //StdOut.println(""QQQ"");
            ans_end = Evaluation(x.getLeft());
            innersum.push(ans_end);
            //StdOut.printf(""ans_end is %s\n"",innersum.pop());
            //StdOut.printf(""ans_end is %s\n"",ans_end);
        }
        if(x.getRight()!=null){ 
            //StdOut.println(""RRR"");
            //x = x.getRight();
            ans_end = Evaluation(x.getRight());
            innersum.push(ans_end);
        } //x = Evaluation(x.getRight());
        
        //
        if(x.getRight()!=null && x.getLeft()!=null){
            //StdOut.println(""PPPP"");
            ans_end =  x.getValue();
            double two = Double.parseDouble(innersum.pop());
            double one = Double.parseDouble(innersum.pop());
                
                if(ans_end.equals(""+"")) inner = one+two;
                else if(ans_end.equals(""-"")) inner = one-two;
                else if(ans_end.equals(""/"")) inner = one/two;
                else if(ans_end.equals(""*"")) inner = one*two;
                
                ans_end = Double.toString(inner);
                //innersum.push(cal);
        }
        //innersum.push(ans_end);
        return ans_end;
    }
    
    
}

