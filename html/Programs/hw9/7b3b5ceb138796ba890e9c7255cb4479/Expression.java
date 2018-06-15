
import java.util.ArrayList;

public class Expression{
    
    public static boolean isOperator(char x)
    {
        if(x =='+' || x=='-' || x=='*' || x=='/'  )
        {
            return true;
        }
        
        else return false;
    }
    
    
        public static boolean isBracket(char x)
    {
        if(x =='(' || x==')'  )
        {
            return true;
        }
        
        else return false;
    }
    
    public ArrayList<String> seperate(String x)
    {
        int count =0;
        ArrayList<String> list = new ArrayList<String>();
        while(!x.isEmpty())
        {
            while(! isOperator(x.charAt(count)) && x.length()>count+1)
            {
                count++;
            }
            if(x.length()==count+1)
            {
                if(!isOperator(x.charAt(x.length()-1)) )
                {
                list.add(x);
                x="""";
                break;
                }
                
                else
                {
                     String value = x.substring(0, count);  
                     list.add(value);
                    list.add(x.substring(x.length()-1, x.length()));
                    x="""";
                }
            }

            else
            {
            if(count==0)
             list.add(x.substring(count,count+1));
            else
            {
            String value = x.substring(0, count);
             list.add(value);
            }
            x = x.substring(count+1, x.length());
            
            count =0;
            }
        }
 
        return list;
       
        
    }

  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix)
    { 
        if(!infix.contains(""(""))
        {
           root = new Node(null,null,infix);
        //    System.out.print(infix+""\n"");
            return root;
        }

       infix =infix.substring(1, infix.length()-1);
       int count = 0;
       int index =0;
       if(infix.contains(""(""))
        {
            for(int i=0; i<infix.length();i++)
            {
              //   System.out.print(i+""shit""+""\n"");
                if( infix.charAt(i) == '(')
                {
                    count++;

                }
                if( infix.charAt(i) == ')')
                {
                    count --;

                }

                if( isOperator(infix.charAt(i)) )
                {
                    if(count ==0)
                    {
                     //   System.out.print(i+""shit"");
                        index =i;
                        break;
                    }
                }

            }
            String left = infix.substring(0,index);
            String right = infix.substring(index+1, infix.length());
            String value = String.valueOf(infix.charAt(index));  
    //        System.out.print(value + ""\n"" );

            root = new Node(Infix2BT(left), Infix2BT(right),value);     
             return root;
       }
       
       else
       {

           for(int i = 0; i<infix.length(); i++)
           {
               if(isOperator(infix.charAt(i)))
               {
                   index = i;
                   break;
               }
           }
       //    System.out.print(infix.charAt(index)+""\n"");
           
           if(index != 0)
           {
            String left = infix.substring(0,index);
            String right = infix.substring(index+1, infix.length());
            String value = String.valueOf(infix.charAt(index)); 
            root = new Node(Infix2BT(left), Infix2BT(right), value);
             return root;
           }
           else
           {
               Node newNode = new Node(null, null,infix);
               return root;
           }
       } 
    }
private ArrayList<Node>prefixArray = new ArrayList<Node>();
    public void prefixInsert(Node a)
    {
       if(a!=null)
       {
        prefixArray.add(a);
        System.out.print(a.getValue());
        prefixInsert(a.getLeft());
        prefixInsert(a.getRight());
       }
    }
    public Node[] PrintPrefix(){
        Node[] prefix = null;
        
        prefixInsert(root);
        prefix = new Node[prefixArray.size()];
       // System.out
        for(int i =0; i<prefixArray.size();i++)
        {
            prefix[i] = prefixArray.get(i);
        }

        return prefix;
    }
    
    private ArrayList<Node>postfixArray = new ArrayList<Node>();
    public void postfixInsert(Node a)
    {
       if(a!=null)
       {
        postfixInsert(a.getLeft());
        postfixInsert(a.getRight());
        postfixArray.add(a);
       }
    }
  
    public Node[] PrintPostfix(){
        Node[] postfix = null;       
       postfixInsert(root);
       postfix= new Node[postfixArray.size()];
        for(int i =0; i<postfixArray.size();i++)
        {
            System.out.print(postfixArray.get(i).getValue());
            postfix[i] = postfixArray.get(i);
        }

        return postfix;
    }

    public double Evaluation(){
         postfixInsert(root);
        double answer = 0;
       ArrayList<String> array =new ArrayList<String>();
        for(int i=0; i<postfixArray.size(); i++)
        {
            array.add(postfixArray.get(i).getValue());
        }
        
        while(array.size()!=1)
        {
            
            for(int i =0; i<array.size(); i++)
            {
                if(isOperator(array.get(i).charAt(0)))
                {
                    char operator = array.get(i).charAt(0);
                    double val1 = Double.parseDouble(array.get(i-2));
                    double val2 = Double.parseDouble(array.get(i-1));
                    double temp=0;
                    if(operator == '+')
                        temp = val1+val2;
                     if(operator == '-')
                        temp = val1-val2;
                     if(operator == '*')
                        temp = val1*val2;
                     if(operator == '/')
                        temp = val1/val2;
                     
                     array.set(i, Double.toString(temp));
                     array.remove(i-1);
                     array.remove(i-2);
                     break;
                }
            }
        }
        answer = Double.parseDouble(array.get(0));
        System.out.print(answer);
        return answer;
       
        
        
    }
    
    public static void main(String[] args)
    {
        String str = ""((20120224791*(121259+797912))+(211544771212/2))"";
        Expression test =new Expression();
        test.Infix2BT(str);
        //test.PrintPostfix();
      //  System.out.print(""\n"");
        //test.PrintPrefix();
       // System.out.print(""\n"");
       test.Evaluation();

    }
}

