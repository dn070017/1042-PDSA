


public class Calculator {
   private  Stack<String> ops ;
     private    Stack<Double> val ;
      private   Stack<Double> buffer;
      private   Stack<String> opb;
    public  Double ans(String e){
        ops =  new Stack<>();
        val = new Stack<>();
        buffer = new Stack<>();
        opb = new Stack<>();
        
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
               
            int y = 0;       
            String op = """";
              
               while(true){  
                // buffer.push(val.pop());
                  
                 op = ops.pop();
                 if(op.equals(""("")) break;
                 if(op.equals(""+"")){
                     buffer.push(val.pop());
                     opb.push(op);
                 }
                 if(op.equals(""-"")){
                     buffer.push(val.pop());
                     opb.push(op);
                 }
                 if(op.equals(""*"")){
                  double b = val.pop();
                  double a = val.pop();
                  
                  val.push(a*b);
                 }
                 if(op.equals(""/"")){
                  double b = val.pop();
                  double a = val.pop();
                  
                  val.push(a/b);
                 }              
               } 
               buffer.push(val.pop());
               while(!opb.isEmpty()){
                   String op2 = """";
                   op2 = opb.pop();
                 double  a = buffer.pop();
                 double  b = buffer.pop();
                 if(op2.equals(""+""))   buffer.push(a+b);
                if(op2.equals(""-""))   buffer.push(a-b);
               }
               val.push(buffer.pop());
            }
            else {
               
                val.push(Double.parseDouble(z[k]));
            }
            
            k++;
            
            
        }
     
        double a = val.pop();
           while(!ops.isEmpty()){
            ops.pop();
        }
       while(!val.isEmpty()){
            val.pop();
        }
       while(!opb.isEmpty()){
            opb.pop();
        }
       while(!buffer.isEmpty()){
            buffer.pop();
        }
        
        
        return a;
    
    }  
      public static void main(String[] args){
         // System.out.print(10);
        //  String j = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        

      }
}


