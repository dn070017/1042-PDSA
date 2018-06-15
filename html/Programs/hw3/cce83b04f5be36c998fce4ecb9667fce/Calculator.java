
/**
 *
 * @author Daniel C
 */
public class Calculator {

    public Double ans (String e) {
        String [] inputnumb = e.split("" "");
.
    Stack<String> ops = new Stack<String>();
    Stack<Double> vals = new Stack<Double>();
    for(int i=0; i<e.length();i++) {
    String s = inputnumb[i];
//    System.out.println(s);
    if (s.contains(""(""))   ;
    else if (s.equals("" ""))     ;
    else if (s.equals(""+"")) ops.push(s);
    else if (s.equals(""-"")) ops.push(s);
    else if (s.equals(""*"")) ops.push(s);
    else if (s.equals(""/"")) ops.push(s);
    else if (s.equals("")""))
    {
     String op = ops.pop();
    if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
    else if (op.equals(""-"")) vals.push(vals.pop() - vals.pop());
    else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
    else if (op.equals(""/"")) vals.push(vals.pop() / vals.pop());
    }
    else vals.push(Double.parseDouble(s));
    }
//    System.out.println(vals.pop());
    while(!ops.isEmpty()){
        String op = ops.pop();
    if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
    else if (op.equals(""-"")) vals.push(vals.pop() - vals.pop());
    else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
    else if (op.equals(""/"")) vals.push(vals.pop() / vals.pop());
    }
        
        
      return vals.pop();
}
    public static void main(String[] args) {
      
        
    }
    
}

