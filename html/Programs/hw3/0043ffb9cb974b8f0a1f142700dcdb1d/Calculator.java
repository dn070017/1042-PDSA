/*
.
 * To change this template file, choose Tools | Templates
.
 */
package whateverprj;
//I will fuck you up men!
/**
 *
 * @author LAB228
 */
public class Calculator {

public Double ans (String e) {
.
      java.util.Stack<String> inf = new java.util.Stack<String>();
      java.util.Stack<String> st1 = new java.util.Stack<String>();//save value
      java.util.Stack<Double> st2 = new java.util.Stack<Double>();//save operator
      String temp;
      String[] cal = e.split("" "");
     
      for(int i=0; i<cal.length ; i++){
              inf.push(cal[cal.length-1-i]);
      }
      
      
      while(!inf.empty()){        
            temp = inf.pop();
         
            if (temp.equals(""("")) ;
            else if (temp.equals(""+"")) st1.push(temp);
            
            else if (temp.equals(""*"")) st1.push(temp);
            else if (temp.equals(""-"")) st1.push(temp);
            else if (temp.equals(""/"")) st1.push(temp);
            else if (temp.equals("")"")){
                 String op = st1.pop();
                if (op.equals(""+"")) st2.push(st2.pop() + st2.pop());
                else if (op.equals(""*"")) st2.push(st2.pop() * st2.pop());
                else if (op.equals(""-"")){ double s = st2.pop();
                    st2.push(st2.pop() - s);}
                else if (op.equals(""/"")){ double s = st2.pop();
                    st2.push(st2.pop() / s);}
            }
             
                else st2.push(Double.parseDouble(temp));
                
        }
          
         //  System.out.print(st2.pop()); 
  
      
      return st2.pop();
}    
}

