/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
 */
class Calculator {
    
    public double ans (String [] buf) {
.
      double a = 0;
      double b = 0;
      double c = 0;
      
      int counter = 0;
      
      Stack<String> ops = new Stack<String>();
      Stack<Double> vals = new Stack<Double>(); 
      
      while(counter != buf.length){
          
            if (buf[counter].equals(""("")) ;
            else if (buf[counter].equals(""+"")) ops.push(buf[counter]);
            else if (buf[counter].equals(""-"")) ops.push(buf[counter]);
            else if (buf[counter].equals(""*"")) ops.push(buf[counter]);
            else if (buf[counter].equals(""/"")) ops.push(buf[counter]);
            else if (buf[counter].equals("")""))
            {
            String op = ops.pop();
            if (op.equals(""+"")){ b = vals.pop(); c = vals.pop();vals.push(c+b);}
            else if (op.equals(""-"")){ b = vals.pop(); c = vals.pop(); vals.push(c-b);}
            else if (op.equals(""*"")){ b = vals.pop(); c = vals.pop(); vals.push(c*b);}
            else if (op.equals(""/"")){ b = vals.pop(); c = vals.pop(); vals.push(c/b);}
            }
            else vals.push(Double.parseDouble(buf[counter]));
            
            counter++;
      }     
      a = vals.pop();
      
      return a;
    }
}

