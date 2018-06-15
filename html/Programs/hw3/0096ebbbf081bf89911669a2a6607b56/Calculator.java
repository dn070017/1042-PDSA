import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Dennis
 */
public class Calculator {

public static Double ans(String e) {

        String[] equation = e.split("" "");
        Stack sta = new Stack();
        Stack num = new Stack();
        Stack opt = new Stack();
        int l = equation.length;
        int c=0,i=0;
        String op;
        double C=0,A=0,B=0;
                while( i < l )
                {

                    if (equation[i].indexOf(""("") != -1)
                    {
                       sta.push(equation[i]);

                       i++;
                    }
                    else if (equation[i].indexOf("")"") != -1)
                    {                     
                       String b;
                       sta.pop();
                       b=num.pop().toString();
                       B =  Float.valueOf(b.trim()).floatValue();

                       c=1;
                       i++;
                    }
                    else if (equation[i].indexOf(""+"") != -1)
                    {

                       opt.push(equation[i]);
                       i++;
                    }
                    else if (equation[i].indexOf(""-"") != -1)
                    {

                       opt.push(equation[i]);
                       i++;
                    }
                    else if (equation[i].indexOf(""*"") != -1)
                    {
                    
                       opt.push(equation[i]);                       
                       i++;
                    }
                    else if (equation[i].indexOf(""/"") != -1)
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
                     String a;
                     a=num.pop().toString();
                     op=opt.pop().toString();
                     A = Float.valueOf(a.trim()).floatValue();

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
