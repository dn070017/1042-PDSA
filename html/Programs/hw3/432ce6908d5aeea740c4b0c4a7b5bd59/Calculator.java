/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LinWeiKuan
 */
import java.util.Stack;
import java.util.List;
import java.util.Vector;
import java.lang.Character;
public class Calculator 
{
    public static boolean isNumeric(String str)  
   {  
     try  
     {  
       double d = Double.parseDouble(str);  
     }  
     catch(NumberFormatException nfe)  
     {  
       return false;  
     }  
     return true;  
   }
    
    private Vector <String>postfix = new Vector<String>();
    private Stack<String> temp= new Stack<String>();
    
    public void toPostfix(String inputString)
    {
        String[] inputs = inputString.split("" "");        

        
        for (int i=0; i<inputs.length; i++)
        {
            if(inputs[i].equals(""(""))
             {
                temp.push(""("");
                while(!inputs[i+1].equals("")""))
                {
                    if(isNumeric(inputs[i+1]))
                    {
                        postfix.add(inputs[i+1]);
                    }
                    else
                        temp.push(inputs[i+1]);
                    i++;  
                }
                continue;
             }
            else if(inputs[i].equals("")""))
            {
                while(true)
                {
                    if(temp.peek()==""("")
                    {
                        temp.pop();
                        break;
                    }
                    else
                    {
                        postfix.add(temp.pop());
                    }
                }
                continue;
            }
            
//                    
            else if(inputs[i].equals(""+""))  
            {

                if (! temp.isEmpty())
                {
                    postfix.add(temp.pop());
                     temp.push(""+"");
                }
                else 
                    temp.push(""+"");
                   continue;
            }
        
    
            
            
            else if(inputs[i].equals(""-""))  
            {
                if (! temp.isEmpty())
                {
                    postfix.add(temp.pop());
                     temp.push(""-"");
                }
                else 
                    temp.push(""-"");
                   continue;
            }
            else if(inputs[i].equals(""*""))  
            {
                if(!temp.isEmpty())
                {
                    String symbol = temp.peek();
                    if (symbol.equals(""+"") || symbol.equals(""-""))
                     temp.push(""*"");
                    else if (symbol.equals(""*"") || symbol.equals(""/""))
                    {
                     postfix.add(temp.pop());    
                     temp.push(""*"");
                    }
                }
                else
                    temp.push(""*"");
                    continue;
                    
            }
            
            else if(inputs[i].equals(""/""))  
            {
                if(!temp.isEmpty())
                {
                    String symbol = temp.peek();
                    if (symbol.equals(""+"") || symbol.equals(""-""))
                    temp.push(""/"");
                   else if (symbol.equals(""*"") || symbol.equals(""/""))
                   {
                    postfix.add(temp.pop());    
                    temp.push(""/"");
                   }
                }
                else 
                    temp.push(""/"");
                    continue;
            }
            
            else if(isNumeric(inputs[i]))
            {
                   postfix.add(inputs[i]);
                   continue;
            }
            
            else 
            {
               System.out.print(""error"");
                break;
            }
        }
     while (!temp.isEmpty() )   
     {
         postfix.add(temp.pop());
     }
    
            for (int i=0; i<postfix.size();i++)
            {
            //    System.out.print(postfix.get(i)+""\t"");
            }

    }


    
    public Double ans( String inputString)
    {
        toPostfix(inputString);
        Stack <Double>values = new Stack<Double>();
        for(int i=0; i<postfix.size(); i++)
        {
            if(isNumeric(postfix.get(i)))
            {
                values.push(Double.parseDouble(postfix.get(i)));
                continue;
            }
            
            
            else if(postfix.get(i).equals(""+""))  
            {
                    double val1=values.pop();
                    double val2=values.pop();
                    values.push(val1+val2);
                   continue;
            }
        
    
            
            
            else if(postfix.get(i).equals(""-""))  
            {
                    double val1=values.pop();
                    double val2=values.pop();
                    values.push(val2-val1);
                   continue;
            }
                       
            else if(postfix.get(i).equals(""*""))  
            {
                    double val1=values.pop();
                    double val2=values.pop();
                    values.push(val1*val2);
                   continue;
            }
            
            else if(postfix.get(i).equals(""/""))  
            {
                    double val1=values.pop();
                    double val2=values.pop();
                    values.push(val2/val1);
                   continue;
            } 
        }
        return values.get(0);
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public static void main(String[] args) 
    {
       Calculator x= new Calculator();
      double z=  x.ans(""4 * ( 1 - 2 ) * 3"");
       System.out.print(z);

    }
    


    
    
    
    
    
}

