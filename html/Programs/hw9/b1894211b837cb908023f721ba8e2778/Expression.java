import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Expression {

    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        Stack<String> s = new Stack<String>();
        Stack<Node> node = new Stack<Node>();
        for (int i = 0; i < infix.split("""").length; i++) {
            String c = infix.split("""")[i];
            s.push(c);
            if (s.iterator().next().equals("")"")) {
                s.pop();
                ArrayList<String> ccc = new ArrayList<String>();
                int j = 0;
                while (true) {
                    String n = s.pop();
                    //System.out.print(n);
                    ccc.add(n);
                    if (ccc.get(j).equals(""+"") || ccc.get(j).equals(""-"") || ccc.get(j).equals(""*"") || ccc.get(j).equals(""/"")) {
                        //System.out.print(ccc.get(j));
                        break;
                    }
                    j = j + 1;
                }
                String ope = ccc.remove(j);

                String listString = """";
                Collections.reverse(ccc);

                StringBuilder sb = new StringBuilder();
                for (String buffer : ccc) {
                    sb.append(buffer);
                    //sb.append(""\t"");
                }
                listString=sb.toString();
                 
                //System.out.print(listString);
                Node right = new Node(null, null, listString);

                ArrayList<String> bbb = new ArrayList<String>();
                j = 0;
                while (true) {
                    String ssss = s.pop();

                    bbb.add(ssss);
                    if (bbb.get(j).equals(""("")) {

                        break;
                    }
                    j = j + 1;
                }
                bbb.remove(j);
                listString = """";
                Collections.reverse(bbb);
                
                StringBuilder ssb = new StringBuilder();
                for (String buffer : bbb) {
                    ssb.append(buffer);
                    //sb.append(""\t"");
                }
                listString=ssb.toString();
                
                

                Node left = new Node(null, null, listString);
                Node top = new Node(left, right, ope);

                s.push(""00"");
                node.push(top);
            }
        }
        Node n = node.pop();
        root = n;

        while (!node.isEmpty()) {
            Node nn = node.pop();
            if (n.getLeft().getValue().equals(""00"")) {

                n.setLeft(nn);

            } else if (n.getRight().getValue().equals(""00"")) {

                n.setRight(nn);
                //System.out.print(n.getRight().getValue());
                //System.out.print(nn.getValue());
            }

            n = nn;
            
        }

        return root;
    }

    public Node[] PrintPrefix() {
        Node n=root;
        Stack<Node> s=new Stack<Node>();
        LinkedQueue<Node> q = new LinkedQueue<Node>();
        
        while(true){
            if(n.getLeft()!=null){
                s.push(n);
                q.enqueue(n);
                Node nn=n.getLeft();
                n=nn;
            }else{
                q.enqueue(n);
                if(s.isEmpty())break;
                n=s.pop();
                n=n.getRight();
            }
        }
        
        Node[] prefix = new Node[q.size()];
        int len=q.size();
        for(int i=0;i<len;i++){
            prefix[i]=q.dequeue();
        }
        return prefix;
    }

    public Node[] PrintPostfix(){
        if(root==null) throw new NullPointerException();
        Node[] postfix = new Node[PrintPrefix().length];
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
    
    
    
    public double Evaluation() {
        Node[] n=PrintPostfix();
        ArrayList<String> calarray=new ArrayList<String>();
        for(int i=0;i<n.length;i++){
            calarray.add(n[i].getValue());
        }
        int i=0;
        while(calarray.size()!=1){
            if(calarray.get(i).equals(""+"")||calarray.get(i).equals(""-"")||calarray.get(i).equals(""*"")||calarray.get(i).equals(""/"")){
                Double d1= Double.parseDouble(calarray.get(i-1));
                Double d2= Double.parseDouble(calarray.get(i-2));
                Double ans=0.0;
                if(""+"".equals(calarray.get(i))){
                    ans=d2+d1;
                }
                if(""-"".equals(calarray.get(i))){
                    ans=d2-d1;
                }
                if(""*"".equals(calarray.get(i))){
                    ans=d2*d1;
                }
                if(""/"".equals(calarray.get(i))){
                    ans=d2/d1;
                }
                String aa=String.valueOf(ans);
                calarray.remove(i-2);
                calarray.remove(i-2);
                calarray.remove(i-2);
                calarray.add(i-2, aa);
                i=i-2;
            }
            i=i+1;
        }
        
        
        double answer = Double.parseDouble(calarray.get(0));
        return answer;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            Expression expression = new Expression();
            String infix = br.readLine();
            expression.Infix2BT(infix);
            Node n1[] = expression.PrintPostfix();
            Node n2[] = expression.PrintPrefix();
            
             for (int i = 0; i < n1.length; i++) {
             System.out.print(n1[i].getValue()+"" "");
             }
             System.out.println(""\n"");
             for (int i = 0; i < n2.length; i++) {
             System.out.print(n2[i].getValue()+"" "");
             }
             System.out.println(""\n"");
             System.out.println(expression.Evaluation());

//            expression.Infix2BT(infix);
//            Expression expression2= new Expression();
//            expression2.Infix2BT(""(4+(((4*2)/2)/3))"");
//            Node n3[]=expression2.PrintPostfix();
//            for (int i = 0; i < n3.length; i++) {
//                System.out.print(n3[i].getValue()+"" "");
//            }
        }
    }
}

