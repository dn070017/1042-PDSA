
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author 林康維
 */
public class Calculator {

    Calculator(){
        
    }
    
    public Double ans(String e){
            
            double ans ;
            ans = 0 ;
            Stack<Double> value = new Stack<Double>() ;
            Stack<String> operator = new Stack<String>() ;
            String[] num = e . split ("" "") ;
            int count = 0 ;
            //System.out.println(num.length) ;
            //System.out.println(count);
        while (true){
            /*if (num[count].equalsIgnoreCase(""("") ) {
            System.out.println(""yes"") ;
            
            System.out.println(count);
            }
            
            /*else if (num[count].equalsIgnoreCase(null))*/
            switch (num[count]){
                case ""("":
                    //System.out.println(count);
                    //System.out.println(""yes"") ;
                    break ;
                case ""+"":
                    operator.push(num[count]) ;
                    break ;
                case ""-"":
                    operator.push(num[count]) ;
                    break ;
                case ""*"":
                    operator.push(num[count]) ;
                    break ;
                case ""/"":
                    operator.push(num[count]) ;
                    break ;
                case "")"":
                    double b = value.pop() ;
                    double a = value.pop() ;
                    String op = operator.pop() ;
                    if (op.equalsIgnoreCase(""+""))
                        ans = a + b ;
                    
                    else if (op.equalsIgnoreCase(""-""))
                    ans  = a-b ;
                    else if (op.equalsIgnoreCase(""*""))
                        ans = a * b ;
                    else if (op.equalsIgnoreCase(""/""))
                        ans = a / b ;
                
                    value.push(ans);
        //System.out.println(ans) ;
                    break ;
                    
                default:
                    value.push(Double.parseDouble(num[count])) ;
                    break ;
            }
            count += 1 ;
            if(count == num.length){
                break ;
            }
        }
            return ans ;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        File file  ;
        file = new File(args[0]) ;
        //double ans ;
        //ans = 0 ;
        try{
        Scanner File_in = new Scanner(file) ;
        String num = File_in . nextLine() ;
        Calculator cct = new Calculator();
        double b = cct.ans(num);
        System.out.printf(""%f"", b);

        /*Stack<Double> value = new Stack<Double>() ;
        Stack<String> operator = new Stack<String>() ;
            int count = 0 ;
            System.out.println(num.length) ;
            System.out.println(count);
        while (true){
            /*if (num[count].equalsIgnoreCase(""("") ) {
            System.out.println(""yes"") ;
            
            System.out.println(count);
            }
            
            //else if (num[count].equalsIgnoreCase(null))
            switch (num[count]){
                case ""("":
                    //System.out.println(count);
                    //System.out.println(""yes"") ;
                    break ;
                case ""+"":
                    operator.push(num[count]) ;
                    break ;
                case ""-"":
                    operator.push(num[count]) ;
                    break ;
                case ""*"":
                    operator.push(num[count]) ;
                    break ;
                case ""/"":
                    operator.push(num[count]) ;
                    break ;
                case "")"":
                    double b = value.pop() ;
                    double a = value.pop() ;
                    String op = operator.pop() ;
                    if (op.equalsIgnoreCase(""+""))
                        ans = a + b ;
                    
                    else if (op.equalsIgnoreCase(""-""))
                    ans  = a-b ;
                    else if (op.equalsIgnoreCase(""*""))
                        ans = a * b ;
                    else if (op.equalsIgnoreCase(""/""))
                        ans = a / b ;
                
                    value.push(ans);
        System.out.println(ans) ;
                    break ;
                    
                default:
                    value.push(Double.parseDouble(num[count])) ;
                    break ;
            }
            count += 1 ;
            if(count == num.length){
                break ;
            }
        }*/
        }
        catch(IOException e){
            System.out.println(""error!""); 
        }
    }
    
}

