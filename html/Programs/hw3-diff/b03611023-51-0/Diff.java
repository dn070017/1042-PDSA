


public class Calculator {
    public  Double ans(String e){
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
               ops.push(z[k]);
            }
            else if(z[k].equals("" "")){
               
            }
            else if(z[k].equals(""+"")){
             
                ops.push(z[k]);
            }
            else if(z[k].equals(""-"")){
              
                ops.push(z[k]);
            }
            else if(z[k].equals(""*"")){
              
                ops.push(z[k]);
            }
            else if(z[k].equals(""/"")){
              
                ops.push(z[k]);
            }
            else if(z[k].equals("")"")){
               String op = """";
              int p = 0;
               while(true){
                double a,b ;
                 op = ops.pop();
                 if(op.equals(""("")) break;
                b = val.pop();
                a = val.pop();
                
                if(op.equals(""+""))   val.push(a+b);
                if(op.equals(""-""))   val.push(a-b);
                if(op.equals(""*""))   val.push(a*b);
                if(op.equals(""/""))   val.push(a/b);
               }  
            }
            else {
               
                val.push(Double.parseDouble(z[k]));
            }
            
            k++;
            
            
        }
        
       
        double a = val.pop();
        
        
        return a;
    
    }  
      public static void main(String[] args){
         // System.out.print(10);
        //  String j = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        

      }
}


