/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author fc
 */
public class Calculator {
    
    
            
    public Double ans (String e) {
      double output = 0.00;
      String[] all = e.split("" "");
      int length = all.length;
      Stack<String> ops = new Stack<String>();
      Stack<Double> vals = new Stack<Double>();
      for(int i=0; i<length;++i){
          String s = all[i];
          if (s.equals(""(""));
          else if (s.equals(""+""))  ops.push(s);
          else if (s.equals(""-""))  ops.push(s);
          else if (s.equals(""*""))  ops.push(s);
          else if (s.equals(""/""))  ops.push(s);
          else if (s.equals("")"")){
              String op = ops.pop();
              if (op.equals(""+""))  vals.push(  vals.pop() + vals.pop()  );
              else if (op.equals(""-""))  vals.push(  vals.pop() - vals.pop()  );
              else if (op.equals(""*""))  vals.push(  vals.pop() * vals.pop()  );
              else if (op.equals(""/""))  vals.push(  vals.pop() / vals.pop()  );
          }
          else vals.push(Double.parseDouble(s));
      }
       
      output = vals.pop();
        
      return output;
}
}

