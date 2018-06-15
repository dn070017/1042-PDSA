import java.io.FileReader;
import java.io.BufferedReader;
//import edu.princeton.cs.algs4.Stack;
public class Calculator {

    public static void main(String[] args) throws Exception {        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            Calculator bigfatgg=new Calculator();       
            String e=br.readLine();
            System.out.println(bigfatgg.ans(e));
        }
     }
    
    public double ans (String e){
            double result;
            Stack<String> ops = new Stack<>();
            Stack<Double> vals = new Stack<>();
            String gg[]=new String[e.length()];
            gg=e.split("" "");
            int i=0;
            while(!gg[i].isEmpty()){
                
                if(i==24){
                    String oper=ops.pop();
                    vals.push(inside(vals.pop() ,oper, vals.pop()));
                    return vals.pop();
                }
                else{                
                    if(gg[i].equals(""(""))   i++;
                    else if((gg[i].equals(""+""))||(gg[i].equals(""-""))||(gg[i].equals(""*""))||(gg[i].equals(""/"")))  {
                        ops.push(gg[i]);
                        i++;
                    }
                    //碰到)就開算
                    else if(gg[i].equals("")"")){
                        String oper=ops.pop();
                        vals.push(inside(vals.pop() ,oper, vals.pop()));
                        i++;
                    }
                    else {
                        vals.push(Double.parseDouble(gg[i]));
                        i++;
                    }
                }
            }
            return vals.pop();
        }
    
    
    private double inside(double b,String oper, double a){
        if(oper.equals(""+""))return a+b;
        else if(oper.equals(""-""))return a-b;
        else if(oper.equals(""*""))return a*b;
        else return a/b;
    }
    
}
