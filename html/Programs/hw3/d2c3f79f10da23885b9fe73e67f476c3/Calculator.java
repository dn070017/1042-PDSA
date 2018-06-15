/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import algs4.Stack;
/**
 *
 * @author Lab304
 */
import java.io.FileReader;
import java.io.BufferedReader;
public class Calculator {

       public Double ans (String e){
           String[] sample=new String[100];
         Stack calculator=new Stack(); 
           String[] data = e.split("" "");
           int j=0;
           double count=0;
           //System.out.println( data[3]);
     for(int i=0;i<data.length;i++){
         if(data[i].equals("")"")){
             for( j=0;j<data.length;j++){
                 if(calculator.peek().equals(""("")){
                     calculator.pop();
                       break;
                     }
                 else{String ABC=String.valueOf(calculator.pop());
                  sample[j]=ABC;
                  //System.out.println( sample[j]);
                 // System.out.println(j);
                     }
                }
              for(int k=j-1;k>=0;k--){ //System.out.println(k);
                                     if(sample[k].equals(""*"")){
                                             Double x=Double.parseDouble(sample[k-1]);
                                              Double y=Double.parseDouble(sample[k+1]);
                                             count=y*x;
                                             //System.out.println(k);
                                           // System.out.println(count);
                                                   }
                                     else if(sample[k].equals(""/"")){
                                             Double x=Double.parseDouble(sample[k-1]);
                                              Double y=Double.parseDouble(sample[k+1]);
                                             count=y/x;
                                             //System.out.println(k);
                                            //System.out.println(count);
                                                   }
                                     else if(sample[k].equals(""+"")){
                                             Double x=Double.parseDouble(sample[k-1]);
                                             Double y=Double.parseDouble(sample[k+1]);
                                             count=y+x;
                                             //System.out.println(k);
                                           // System.out.println(count);
                                                   }
                                     else if(sample[k].equals(""-"")){
                                             Double x=Double.parseDouble(sample[k-1]);
                                              Double y=Double.parseDouble(sample[k+1]);
                                             count=y-x;
                                            // System.out.println(k);
                                           // System.out.println(count);
                                                   }
                                        
                                    }
              calculator.push(count);
             }
         else{
          calculator.push(data[i]);
          //System.out.println( calculator.peek());
         }
         }
   
           Double total=Double.parseDouble(String.valueOf(calculator.peek()));
        //   System.out.println(total);
      return   total;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
    //    Calculator cct = new Calculator() ;
       // cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )"");
        
     
        // TODO code application logic here
    }
    
}

