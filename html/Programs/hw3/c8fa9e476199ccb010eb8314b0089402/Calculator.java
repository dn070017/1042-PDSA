
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 余軒
 */
public class Calculator {
        
    public Double ans(String e){
        
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String[] a = e.split("" "");
        
        for (int i=0;i< a.length;i++){
            if (a[i].equals(""("")) ;
            else if ((a[i].equals(""+""))||(a[i].equals(""-""))||(a[i].equals(""*""))||(a[i].equals(""/"")))
                    {
                       ops.push(a[i]);
                    }
            else if (a[i].equals("")"")){
               String op = ops.pop();
               if (op.equals(""+"")) vals.push(vals.pop()+vals.pop());
               else if (op.equals(""-"")){
                   double temp1 = vals.pop();
                   double temp2 = vals.pop();
                   vals.push(temp2-temp1);}
               else if (op.equals(""*"")) vals.push(vals.pop()*vals.pop());
               else if (op.equals(""/"")){ 
                   double temp1 = vals.pop();
                   double temp2 = vals.pop();
                   vals.push(temp2/temp1);}
               //else break;
            }
            else vals.push(Double.parseDouble(a[i]));
            
        }
        double x = vals.pop();
            return x; //null為你算出的答案  Double 為 class
    }
    
    
    
    
    
   public static void main(String[] args) throws IOException {
        // TODO code application logic here
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           String data = br.readLine();
           Calculator cct = new Calculator();
           double y = cct.ans(data);
           
           System.out.println(y);
           //System.out.println(data);
           //System.out.println(data[2]);
    }
   }
}
