import edu.princeton.cs.algs4.Stack;
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public double ans (String[] args){
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
        String s = StdIn.readString();
        if (s.equals(""("")) ;
        else if (s.equals(""+"")) ops.push(s);
        else if (s.equals(""*"")) ops.push(s);
        else if (s.equals("")""))
        {
        String op = ops.pop();
        if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
        else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
        }
        else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
        
        return 0;
        }
    
}

