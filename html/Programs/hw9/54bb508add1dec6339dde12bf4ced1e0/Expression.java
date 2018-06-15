import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;


public class Expression{
	private static String exp;
	private String pattern = ""\\((\\(.*\\))([+-/*])(\\(.*\\))\\)"";
	private String pattern_l = ""\\((\\(.*\\))([+-/*])([0-9.]*)\\)"";
	private String pattern_r = ""\\(([0-9.]*)([+-/*])(\\(.*\\))\\)"";
	private String pattern_i = ""\\(([0-9.]*)([+-/*])([0-9.]*)\\)"";
	private Pattern m = Pattern.compile(pattern);
	private Pattern l = Pattern.compile(pattern_l);
	private Pattern r = Pattern.compile(pattern_r);
	private Pattern i = Pattern.compile(pattern_i);
	private int N;
	private Node BT_root;
	
	Expression(){
		N = 1;
		BT_root = new Node(null, null, null);
	}
	
	
	public static void main(String[] args){
	String exp = args[0];
    Expression reg = new Expression();
	reg.Infix2BT(exp);
	System.out.println(""Prefix:"");
	Node[] Pre  = reg.PrintPrefix();
	System.out.println(""\nPostfix:"");
	Node[] Post = reg.PrintPostfix();
	System.out.println();
	System.out.println(reg.size());
	System.out.println(reg.Evaluation());
	}
	
	public Node Infix2BT(String infix){
        Node root = BT_root;
		splt(root, infix);	
		if(BT_root == null){throw new java.lang.NullPointerException();}
		return BT_root;
    }
	
	public void splt(Node root, String s){	    
	
	Matcher M = m.matcher(s);
	Matcher L = l.matcher(s);
	Matcher R = r.matcher(s);
    Matcher I = i.matcher(s);
	
    if(R.matches()){
		
        /*System.out.println(""R"");
		System.out.println(""Found value: "" + R.group(1) );
		System.out.println(""Found value: "" + R.group(2) );
		System.out.println(""Found value: "" + R.group(3) );*/
		
		
		root.setLeft(new Node(null, null, R.group(1)));
		N++;
		root.setValue(R.group(2));
		root.setRight(new Node(null, null, null));
		N++;
		splt(root.getRight(), R.group(3));
    
	} else if(L.matches()){
		
        /*System.out.println(""L"");
		System.out.println(""Found value: "" + L.group(1) );
		System.out.println(""Found value: "" + L.group(2) );
		System.out.println(""Found value: "" + L.group(3) );*/
		
		
		root.setLeft(new Node(null, null, null));
        N++;
		root.setValue(L.group(2));
		root.setRight(new Node(null, null, L.group(3)));
		N++;
		splt(root.getLeft(), L.group(1));
		
	}else if (M.matches()) {
		
		/*System.out.println(""M"");
        System.out.println(""Found value: "" + M.group(1) );
		System.out.println(""Found value: "" + M.group(2) );
		System.out.println(""Found value: "" + M.group(3) );*/
		
		
		root.setLeft(new Node(null, null, null));
		N++;
		splt(root.getLeft(), M.group(1));
		root.setValue(M.group(2));
		root.setRight(new Node(null, null, null));
		N++;
		splt(root.getRight(), M.group(3)); 
		
	}else if(I.matches()){
        
		/*System.out.println(""I"");
		System.out.println(""Found value: "" + I.group(1) );
		System.out.println(""Found value: "" + I.group(2) );
		System.out.println(""Found value: "" + I.group(3) );*/
		
		
		root.setLeft(new Node(null, null, I.group(1)));
		N++;
		root.setValue(I.group(2));
		root.setRight(new Node(null, null, I.group(3)));
		N++;
    } else {
		System.out.println(""Error experssion"");
	}
	
	}
	
	public Node[] PrintPrefix(){
        Queue<Node> q = new Queue<Node>();
        pre(BT_root, q);
		Node[] prefix = new Node[N];
		for(int i = 0; i < N; i++){
			prefix[i] = q.dequeue();
			//System.out.print(prefix[i].getValue()+"" "");
		}
		return prefix;
    }
	
	private void pre(Node root, Queue<Node> q){
		if(root == null) return;
		//System.out.print(root.getValue());
		q.enqueue(root);
		pre(root.getLeft(),q);
		pre(root.getRight(),q);
	}
	
	public Node[] PrintPostfix(){
		Queue<Node> q = new Queue<Node>();
		post(BT_root, q);
		Node[] postfix = new Node[N];
		for(int i = 0; i < N; i++){
			postfix[i] = q.dequeue();
			//System.out.print(postfix[i].getValue()+"" "");
		}
		return postfix;
	}
	
	private void post(Node root, Queue<Node> q){
		if(root == null) return;
		post(root.getLeft(),q);
		post(root.getRight(),q);
		//System.out.print(root.getValue());
		q.enqueue(root);
	}
	
	public int size(){
		return N;
	}

	public double Evaluation(){
		Stack<Double> num = new Stack<Double>();
		Node[] NodeArray =  PrintPrefix();
		
		//System.out.println();
		for(int i = N-1; i > -1; i--){
			String s = NodeArray[i].getValue();
			//System.out.print(s+"" "");
			if(s.matches(""[0-9.]*"")){
				num.push(Double.parseDouble(s));
			}else{
				num.push(eva(num.pop(),num.pop(),s));
			}
		}
		
		double answer = num.pop();
		
		return answer;
    }
	
	private double eva(double d1, double d2, String operator){
		//System.out.println(d1 + "" "" + operator + "" "" + d2);
		if(operator.equals(""+"")){
			return d1 + d2;
		}else if(operator.equals(""-"")){
			return d1 - d2;
		}else if(operator.equals(""*"")){
			return d1 * d2;
		}else if(operator.equals(""/"")){
			return d1 / d2;
		}else{
			throw new NoSuchElementException(""Error operator"");
		}
	}
}


