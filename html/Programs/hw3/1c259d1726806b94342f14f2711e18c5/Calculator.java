import java.io.FileReader;
import java.io.BufferedReader;



public class Calculator {

    public double ans (String e) 
    {
        double ans = 0.0;
        
        String[] data = e.split("" "");
        Stack<String> data_stack = new Stack<String>();
        int n = 0;
        do
        {
            if(data[n].equals("")""))
            {
                
                double p = 0.0,q = 0.0;
                
                q = Double.parseDouble(data_stack.pop());
                String ch = data_stack.pop();       
                p = Double.parseDouble(data_stack.pop());
                if(ch.equals(""+""))
                {
                    ans = p + q; 
                }
                else if(ch.equals(""-""))
                {
                    ans = p - q; 
                }
                else if(ch.equals(""*""))
                {
                    ans = p * q;
                }
                else if(ch.equals(""/""))
                {
                    ans = p / q;
                }
                data_stack.pop();
                data_stack.push(Double.toString(ans));
            }
            else
            {
                data_stack.push(data[n]);
            }
            n++;
        }while(n != data.length);
        
        return ans;
                        
    }
    
    public static void main(String[] args) throws Exception {
    
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        
            String datastream = br.readLine();
            if(datastream == null) return;
            Calculator cct = new Calculator();
            System.out.printf(Double.toString(cct.ans(datastream)));
        }
        
    }
}
   
    


