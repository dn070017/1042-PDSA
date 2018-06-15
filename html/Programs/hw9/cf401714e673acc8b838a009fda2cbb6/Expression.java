/*
.
 * To change this template file, choose Tools | Templates
.
*/
/**
 *
 * @author jerry
 */
public class Expression{
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){ 
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        this.root = null;//nullify the root
        String[] infixchar = infix.split(""(?<=[-+*/()])|(?=[-+*/()])"");
        String[] postfixchar = postfixer(infixchar);
        Stack<Node> stack = new Stack<Node>();
		for (int i = 0; i < postfixchar.length; i++) {
			Node node = new Node(null,null,postfixchar[i]);
                        if(postfixchar[i].equals(""+"") || postfixchar[i].equals(""-"") || postfixchar[i].equals(""*"") || postfixchar[i].equals(""/"")){
        		node.setRight(stack.pop());
			node.setLeft(stack.pop());
			stack.push(node);                
                        }
                        else{
                        stack.push(node);
                        }
		}
        this.root = stack.pop();
        return root;
    } 


    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        Queue<Node> q = new Queue<Node>();
        PrintPrefix(root,q);
        int N = q.size();
        Node[] result = new Node[N];
        int i = 0;
        for(Node x:q){
            result[i++] = x;
        }
        return result;
    }
    
    private void  PrintPrefix(Node x,Queue<Node> q){
        if(x == null) return;
        q.enqueue(x);
        PrintPrefix(x.getLeft(),q);
        PrintPrefix(x.getRight(),q);
    }
    
  
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        Queue<Node> q = new Queue<Node>();
        PrintPostfix(root,q);
        int N = q.size();
        Node[] result = new Node[N];
        int i = 0;
        for(Node x:q){
            result[i++] = x;
        }
        return result;        
    }
    
    private void PrintPostfix(Node x,Queue<Node> q){
        if(x == null) return;
        PrintPostfix(x.getLeft(),q);
        PrintPostfix(x.getRight(),q);
        q.enqueue(x);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        Node[] nodes = PrintPostfix();
        Stack<Double> s = new Stack<Double>();
        for(int i = 0; i < nodes.length;i++){
            String data = nodes[i].getValue();
            if(data.equals(""+"")){s.push(s.pop()+s.pop());}
            else if(data.equals(""*"")){s.push(s.pop()*s.pop());}
            else if(data.equals(""-"")){
                double first = s.pop();
                double second = s.pop();
                s.push(second-first);
            }
            else if(data.equals(""/"")){
                double first = s.pop();
                double second = s.pop();
                s.push(second/first);
            }            
            else{s.push(Double.parseDouble(data));}
        }
        Double answer = s.pop();
        return(answer);
    }
    
    public static void main(String[] args) {
        String str1 = ""((4+2)*(4-2))"";
        String[] str2 = str1.split(""(?<=[-+*/()])|(?=[-+*/()])"");
        for(int i = 0; i < str2.length; i++){
        System.out.print(str2[i]+ "" "");
        }
        System.out.println();
        String[] str3 = postfixer(str2);
        for(int i = 0; i < str3.length; i++){
        System.out.print(str3[i]+ "" "");
        }
        System.out.println();        
        
        
        Expression test = new Expression();
        String teststr = ""((4+2)*(4-2))"";
        test.Infix2BT(teststr);
        Node[] result = test.PrintPrefix();
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i].getValue()+"" "");
        }
        System.out.println();
        Node[] result2 = test.PrintPostfix();
        for(int i = 0; i < result2.length; i++){
            System.out.print(result2[i].getValue()+"" "");
        }        
        System.out.println();
        System.out.print(test.Evaluation());
        System.out.println();    
    }
    
    
    // function to convert infix to postix, convert first than create the tree
	public static String[] postfixer(String[] infix) {
		// create a new stack of character type
		Stack<String> stack = new Stack<String>();
		// create an array 'result' which is the same length as of infix and
		// type char
		String[] result = new String[infix.length];
		// set count as the counter for the operations on result
		// increment after every use
		int count = 0;
		for (int i = 0; i < infix.length; i++) {
			// System.out.println(result);
                        if(infix[i].equals(""("")){
                            stack.push(infix[i]);
                        }
                        else if(infix[i].equals("")"")){
                        	while ( (!stack.isEmpty()) && (stack.peek().equals(""(""))) {
					stack.pop();
				}
				if ((!stack.isEmpty())) {
					result[count] = stack.pop();
					count++;
                                        if(stack.peek().equals(""("")) stack.pop(); 
				}
                        }
                        else if(infix[i].equals(""+"") || infix[i].equals(""-"") || infix[i].equals(""*"") || infix[i].equals(""/"")){
                        stack.push(infix[i]);
                        }
                        else{
                        result[count] = infix[i];
			count++;                        
                        }
		}
		while ((!stack.isEmpty())) {
                        result[count] = stack.pop();
			count++;
		}
                String[] result2 = new String[count];
                for(int i = 0; i < count; i++) result2[i] = result[i];
		return result2;
	}
}

