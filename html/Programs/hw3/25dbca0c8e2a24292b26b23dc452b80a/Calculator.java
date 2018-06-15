import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Dennis
 */
public class Calculator {

//    public Calculator()
//    {
//        
//    }
//    
public static Double ans(String e) {

        String[] equation = e.split("" "");
        Stack sta = new Stack();
        Stack num = new Stack();
        Stack opt = new Stack();
        int l = equation.length;
        int c=0,i=0;
        String op,a,b;
        double C=0,A=0,B=0;
                while( i < l )
                {
                    
                    if (equation[i].equals(""(""))
                    {
                       sta.push(equation[i]);
                       i++;
                    }
                    else if (equation[i].equals("")""))
                    {                     
                       sta.pop();
                       b=num.pop().toString();
                       B =  Double.parseDouble(b.trim());
                       c=1;
                       i++;
                    }
                    else if (equation[i].equals(""+""))
                    {
                       opt.push(equation[i]);
                       i++;
                    }
                    else if (equation[i].equals(""-""))
                    {
                       opt.push(equation[i]);
                       i++;
                    }
                    else if (equation[i].equals(""*""))
                    {
                       opt.push(equation[i]);                       
                       i++;
                    }
                    else if (equation[i].equals(""/""))
                    {
                       opt.push(equation[i]);                       
                       i++;
                    }
                    else
                    {                        
                       num.push(equation[i]);
                       i++;                    
                    }
                    if(c == 1)
                    {

                     a=num.pop().toString();
                     op=opt.pop().toString();
                     A = Double.parseDouble(a.trim());

                     c=0;
                     
                        if( op.equals(""+"")){
                            C= A+B ;
                            num.push(C);
                        }
                        else if(op.equals(""-"")){
                            C= A-B;
                            num.push(C); 
                        }
                        else if(op.equals(""*"")){
                            C= A*B;
                            num.push(C);
                        }
                        else if(op.equals(""/"")){
                        C= A/B;
                        num.push(C);
                        }
                    }
                }
      return C;
}

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args)throws Exception {

           String k =""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"";    
           double CC=0;
           CC=ans(k);
//           Calculator cct = new Calculator();
//           System.out.println(cct.ans(k));
           System.out.println(CC);

        // TODO code application logic here
    }
    
}
