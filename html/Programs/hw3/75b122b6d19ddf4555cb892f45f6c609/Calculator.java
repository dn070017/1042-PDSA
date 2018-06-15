import java.io.FileReader;
import java.io.BufferedReader;

public class Calculator {
    public static  Double ans(String e){
        Stack<String> ops = new Stack<>();
        Stack<Double> val = new Stack<>();
        int k = 0;
        int i = 0;
        while(k!=-1){
       k = e.indexOf("" "", ++k);
       
        i++;
    }
        i--;
        k = 0;
       String[] z = e.split("" "");
        
        
        while(k<i){
            
           
            if(z[k].equals(""("")){
               // System.out.println(z);
            }
            else if(z[k].equals("" "")){
               // System.out.println(z[k]);
            }
            else if(z[k].equals(""+"")){
               // System.out.println(z);
                ops.push(z[k]);
            }
            else if(z[k].equals(""-"")){
               // System.out.println(z);
                ops.push(z[k]);
            }
            else if(z[k].equals(""*"")){
               // System.out.println(z);
                ops.push(z[k]);
            }
            else if(z[k].equals(""/"")){
               // System.out.println(z);
                ops.push(z[k]);
            }
            else if(z[k].equals("")"")){
                // System.out.println(z);
                double a,b ;
                b = val.pop();
                a = val.pop();
                String op = ops.pop();
                if(op.equals(""+""))   val.push(a+b);
                if(op.equals(""-""))   val.push(a-b);
                if(op.equals(""*""))   val.push(a*b);
                if(op.equals(""/""))   val.push(a/b);
                
            }
            else {
                // System.out.println(z);
                val.push(Double.parseDouble(z[k]));
            }
            
            k++;
            
            
        }
        
        
        
        
        
        
        return val.pop();
    
    }

public static void main(String[] args){
    String j = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
    
    int k = 0;
    int i = 0;
    int d = j.length();
    
    i--;
    k = j.length()-i;
    //System.out.print(i);
    //String k = j.substring(0, 1);
    //System.out.println(k);
  double x =  ans(j);
    System.out.println(x);
    //System.out.print(ans(j));
   // System.out.println(j.substring(1, 3));
}    
    
}

