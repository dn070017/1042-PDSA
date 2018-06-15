import java.io.FileReader;
import java.io.BufferedReader;


/**
 *
 * @author stonebreaker
 */
public class Calculator {
    
    private double ans;
    private double a , b;
    private char op;
    
    public Calculator() {
        ans = 0.0;
        a = 0.0;
        b = 0.0;
    }
    
    public Double ans (String e)
    {
       String [] data = e.split("" "");
       Stack<String> equation = new Stack<String>();
       Stack<Double> number = new Stack<Double>(); 
       Stack<String> operator = new Stack<String>();
       
       for (int i = 0; i < data.length; i++)
       {
          if ( data[i].equals(""(""))
              equation.push(data[i]);
          else if( data[i].equals(""+"") || data[i].equals(""-"") || data[i].equals(""*"") || data[i].equals(""/""))
              operator.push(data[i]);
          else if( data[i].equals("")""))
          {
              b = number.pop();
              op = operator.pop().charAt(0);
              a = number.pop();
              equation.pop();
              switch(op){
                  case '+':
                      ans = a + b;
                      number.push(ans);
                      break;
                  case '-':
                      ans = a - b;
                       number.push(ans);
                      break;
                  case '*':
                      ans = a * b;
                       number.push(ans);
                      break;
                  case '/':
                      ans = a / b;
                       number.push(ans);
                      break;
              }
          }
          else
              number.push(Double.parseDouble(data[i]));
       }
        return ans;
    }
    
    
    
    public static void main(String[] args) {
        String e = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
//        String e ="" 1 + 2 "";
        Calculator cct = new Calculator();
        System.out.print(cct.ans(e));
//       
    }
    
}

