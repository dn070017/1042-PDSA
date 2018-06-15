/*
.
 * To change this template file, choose Tools | Templates
.


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
/**
 *
 * @author Steven
 */
public class Calculator {

    public static Double ans (String e) {
          String[] hello = e.split("" "");
          
            Stack<String> ops = new Stack<String>();
            Stack<Double> vals = new Stack<Double>();
           for(int i=0;i<hello.length;i++){
            String s = hello[i];
            if (s.equals(""("")) ;
             else if (s.equals(""+"")) ops.push(s);
            else if (s.equals(""*"")) ops.push(s);
            else if (s.equals("")""))
            {
            String op = ops.pop();
            if (op.equals(""+"")) vals.push(vals.pop() + vals.pop());
            else if (op.equals(""*"")) vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(s));
            }
                return vals.pop();
            }   
 
    public static void main(String[] args) throws Exception {
      
       
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
          Calculator cct = new Calculator();
            // read a line and split by ','
            String hihi = br.readLine();
            System.out.println(ans(hihi));
            
    }
    
}
}

