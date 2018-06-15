
//import edu.princeton.cs.algs4.Stack;
import java.io.FileReader;
import java.io.BufferedReader;


public class Calculator {

    public Double ans (String e) {
.
      String[] data = e.split("" "");
      Stack<String> stk=new Stack<String>();
      double answer = 0.0;
      int i=0;
      do
      {
          if(data[i].equals("")""))
          {
              String num2 = stk.pop();
              double numTwo=Double.parseDouble(num2);
              
              String label = stk.pop();             
              
              String num1 = stk.pop();
              double numOne=Double.parseDouble(num1);
              if(label.equals(""+""))
              {
                  answer = numOne+numTwo;
              }
              if(label.equals(""-""))
              {
                  answer = numOne-numTwo;
              }
              if(label.equals(""*""))
              {
                  answer = numOne*numTwo;
              }
              if(label.equals(""/""))
              {
                  answer = numOne/numTwo;
              }
              stk.pop();
              stk.push(String.valueOf(answer));
          }
          else
          {
              stk.push(data[i]);
          }
          i++;
      }
      while(i!=data.length);
     
      //System.out.print(stk.pop());
      return answer;
    }
    
    
    
    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           Calculator cct = new Calculator();
           cct.ans(br.readLine());
            
        }
    }
}

