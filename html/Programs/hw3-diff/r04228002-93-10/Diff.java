
import java.io.BufferedReader;
import java.io.FileReader;
//import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;
import java.util.ArrayList;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */

public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String[] row1;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            row1 = br.readLine().split("","");
            //System.out.println(Arrays.toString(row1));            
        }
        // TODO code application logic here
        String str="""";
        for (int i=0;i<row1.length;i++){
            str=str+row1[i];
        }
        /*
        String[] a=str.split("" "");
        String str2="""";
        for (int i=0;i<a.length;i++){
            str2=str2+a[i];
        }
        */
  
        
        
        Calculator cct = new Calculator();
        double result =cct.ans(str);
        System.out.println(result);
        
    }
    public Double ans (String e) {
.
        String LeftB = ""("";
        String RightB = "")"";
        String[] a=e.split("" "");
        Stack<String> cal=new Stack<String>();
        for (int i=0;i<a.length;i++){
            if (!a[i].equals(RightB)){
                cal.push(a[i]);
            }
            else{
                Stack<String> Temp = new Stack<String>();
                String b =cal.pop();
                Temp.push(b);
                while(!b.equals(LeftB)){
                    b=cal.pop();
                    Temp.push(b);
                }
                String Bracket=Temp.pop();
                double N1=Double.parseDouble(Temp.pop());
                String OP=Temp.pop();
                double N2=Double.parseDouble(Temp.pop());
                double TempR=0;
                if (OP.equals(""+"")){
                    TempR=N1+N2;
                }
                else if(OP.equals(""-"")){
                    TempR=N1-N2;
                }
                else if(OP.equals(""*"")){
                    TempR=N1*N2;
                }
                else{
                    TempR=N1/N2;
                }
                String TempR2 = String.valueOf(TempR);
                cal.push(TempR2);
                
            }
        }
    String FinalR=cal.pop();
    double FinalR2=Double.parseDouble(FinalR);
    //Stack<char> cal= new Stack<char>();
      
    return FinalR2;
}
    
}


