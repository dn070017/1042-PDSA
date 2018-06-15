//import edu.princeton.cs.algs4.Stack;
public class Expression{
  
    private Node root;
    String[] outData;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        String str=infix;
        outData=str.split(""(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])"");
        Stack<String> stk=new Stack<String>();
        Node tempRoot=null;
        Node tempRoot2=null;
        
        int i=0;
        int j=0;
          do
          {
              if(outData[i].equals("")""))
              {
                  if(j==0)
                  {
                      String rightString = stk.pop();
                      String midString = stk.pop();
                      String leftString = stk.pop();
                      
                      Node b = new Node(null, null, rightString); 
                      Node a = new Node(null, null, leftString);
                      
                      tempRoot=new Node(a, b, midString);
                      stk.pop();                     
                      
                      j++;
                  }
                  else
                  {
                      String rightString=stk.pop();
                      String midString = stk.pop();
                      String leftString = stk.pop();
                      char rightIsANumber=rightString.charAt(0);
                      char midIsANumber=midString.charAt(0);
                      char leftIsANumber=leftString.charAt(0);
                      if(Character.isDigit(rightIsANumber)==true && Character.isDigit(midIsANumber)!=true && Character.isDigit(leftIsANumber)!=true)
                      {
                          stk.push(leftString);
                          Node b = new Node(null, null, rightString);
                          tempRoot=new Node(tempRoot, b, midString);
                          stk.pop();
                      }
                      else if(Character.isDigit(rightIsANumber)!=true && Character.isDigit(midIsANumber)==true && Character.isDigit(leftIsANumber)!=true)
                      {
                          stk.push(leftString);
                          Node a = new Node(null, null, midString);
                          tempRoot=new Node(a, tempRoot, rightString);
                          stk.pop();
                      }
                      else if(Character.isDigit(rightIsANumber)==true && Character.isDigit(midIsANumber)!=true && Character.isDigit(leftIsANumber)==true)
                      {
                          stk.push(leftString);
                          Node b = new Node(null, null, rightString);
                          Node a = new Node(null, null, leftString);
                          tempRoot2=new Node(a, b, midString);
                          stk.pop();
                      }
                      else if(Character.isDigit(rightIsANumber)!=true && Character.isDigit(midIsANumber)!=true && Character.isDigit(leftIsANumber)!=true)
                      {
                          stk.push(leftString);
                          tempRoot=new Node(tempRoot, tempRoot2, midString);
                          stk.pop();
                      }
                  }
              }
              else
              {
                  stk.push(outData[i]);
              }
              i++;
          }
          while(i!=outData.length);
          
          root=tempRoot;
        return root;
    }

    public Node[] PrintPrefix(){
        Node[] prefix  = new Node[outData.length];
        //String[] check=new String[outData.length];
        String[] copyOutData=new String[outData.length];
        copyOutData=outData.clone();
        for(int i=0;i<outData.length;i++)
        {
            char isANumber=copyOutData[i].charAt(0);
            if(Character.isDigit(isANumber)!=true && copyOutData[i].equals(""("")!=true && copyOutData[i].equals("")"")!=true)
            {
                for(int j=i-1;j>=0;j--)
                {
                    if(copyOutData[j].equals(""(""))
                    {
                        copyOutData[j]=copyOutData[i];
                        copyOutData[i]="")"";
                        break;
                    }
                }
            }
        }
        String out = """";
        int a=0;
        for(int i=0;i<outData.length;i++)
        {
            if(copyOutData[i].equals("")"")!=true)
            {
                out = copyOutData[i];
                Node b=new Node(null,null,out);
                prefix[i-a]=b;
//                check[i-a]=out;
            }
            else if(copyOutData[i].equals("")"")==true)
            {
                a++;
            }
        }

//        int i=0;
//        do
//        {
//            System.out.print(check[i]);
//            i++;
//        }
//        while(i!=((outData.length+1)/2));
        

        return prefix;
    }
    

  
    public Node[] PrintPostfix(){
        
        Node[] postfix = new Node[outData.length];
        //String[] check=new String[outData.length];
        
        Stack<String> stk=new Stack<String>();
        int j=0;
        for(int i=1;i<outData.length;i++)
        {            
            char isANumber=outData[i].charAt(0);
            if(outData[i].equals(""(""))
            {
                j++;
            }
            else if(outData[i].equals("")""))
            {
                String symbol=stk.pop();
                postfix[i-j-1]=new Node(null, null, symbol);
                //check[i-j-1]=symbol;
            }
            else if(Character.isDigit(isANumber)!=true && outData[i].equals(""("")!=true && outData[i].equals("")"")!=true)
            {
                j++;
                stk.push(outData[i]);
            }
            else
            {
                Node a=new Node(null, null, outData[i]);
                postfix[i-j-1]=a;
                //check[i-j-1]=outData[i];
            }
        }
//        int i=0;
//        do
//        {
//            System.out.print(check[i]);
//            i++;
//        }
//        while(i!=((outData.length+1)/2));
//        System.out.print(""\n"");
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        Stack<String> stk=new Stack<String>();
        int i=0;
          do
          {
              if(outData[i].equals("")""))
              {
                  String num2 = stk.pop();
                  double numTwo=Double.parseDouble(num2);

                  String label = stk.pop();             

                  String num1 = stk.pop();
                  double numOne=Double.parseDouble(num1);
                  if(label.equals(""+""))
                  {
                      answer = numOne+numTwo;
                  }
                  if(label.equals(""-""))
                  {
                      answer = numOne-numTwo;
                  }
                  if(label.equals(""*""))
                  {
                      answer = numOne*numTwo;
                  }
                  if(label.equals(""/""))
                  {
                      answer = numOne/numTwo;
                  }
                  stk.pop();
                  stk.push(String.valueOf(answer));
              }
              else
              {
                  stk.push(outData[i]);
              }
              i++;
          }
          while(i!=outData.length);
        return answer;
    }
}

