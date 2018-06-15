
public class Calculator {
    
    
    public Double ans (String e) {        
        String[] input = e.split("" "");
        Stack<String> ops = new Stack<String>();        
        Stack<Double> vals = new Stack<Double>();
        
        for(int i =0;i < input.length;i++) {
        String s = input[i];
        if (s.equals(""("")) ;
        else if (s.equals(""+"") || s.equals(""-"")) ops.push(s);
        else if (s.equals(""*"") || s.equals(""/"")) ops.push(s);
        else if (s.equals("")""))
        {
         String op = ops.pop();
         if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
         else if (op.equals(""-"")) {
             double a = vals.pop();             
             vals.push(vals.pop() - a);
         }
         else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
         else if (op.equals(""/"")) {
             double a = vals.pop();             
             vals.push(vals.pop() / a);
         }
        }
        else vals.push(Double.parseDouble(s));
        }
        double result = vals.pop();        
        
        return result;
    }

    
    
}


