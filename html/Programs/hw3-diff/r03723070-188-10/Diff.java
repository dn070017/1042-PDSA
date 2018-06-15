/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class Calculator {
   ///////////////////////////////////////////
    /////////////////////////////////////////
    public Double ans (String e) {
        String [] e2 = e.split("" "");
        int size = e2.length;
        for (int i = size-1; i >= 0; i--) {
           if(e2[i].equals(""("")){
               Double innersum = Double.parseDouble(e2[i+1]);
               for(int j=i+1;j<size;j++){
                   if(e2[j].equals(""+"")||e2[j].equals(""-"")||e2[j].equals(""*"")||e2[j].equals(""/"")){
                       Double b = Double.parseDouble(e2[j+1]);
                       if(e2[j].equals(""+"")) innersum = innersum+b;
                       else if(e2[j].equals(""-"")) innersum = innersum-b;
                       else if(e2[j].equals(""/"")) innersum = innersum/b;
                       else if(e2[j].equals(""*"")) innersum = innersum*b;
                       e2[j] = "" "";
                       e2[j-1] = "" "";
                       e2[j+1] = "" "";
                   } 
                   else if(e2[j].equals("")"")){
                       e2[i] = Double.toString(innersum);
                       e2[j] = "" "";
                       break;
                   }
               }
           }         
        }
.
        Double ans = Double.parseDouble(e2[0]);
      return ans;
    }    
    /*public static void main(String[] args) throws Exception{
        Calculator cct = new Calculator();
        cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "");
    }*/
}

