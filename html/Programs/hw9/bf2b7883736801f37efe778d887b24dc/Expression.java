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
        int j=0;
        int k=0;
        int l=0;
        for(int i=0;i<outData.length;i++)
        {
            j=i;
            char isANumber=outData[i].charAt(0);
            if(outData[i].equals(""(""))
            {
                Node a=new Node(null,null,outData[i]);
                prefix[i-k-l]=a;
                //check[i-k-l]=outData[i];
            }
            else if(outData[i].equals("")""))
            {
                l++;
            }
            else if(Character.isDigit(isANumber)==true)
            {
                Node a=new Node(null,null,outData[i]);
                prefix[i-k-l]=a;
                //check[i-k-l]=outData[i];
            }
            else if(Character.isDigit(isANumber)!=true && outData[i].equals(""("")!=true && outData[i].equals("")"")!=true)
            {
                do
                {                    
                    if(outData[j].equals(""("")!=true)
                    {
                        j--;
                    }
                    else if(outData[j].equals(""("")==true)
                    {
                        Node a=new Node(null,null,outData[i]);
                        prefix[j-k]=a;
                        //check[j-k]=outData[i];
                        break;
                    }
                }
                while(j>=0);
                k++;
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
    
//private static void preorderPrintTree(Node root) {
//        if (root.getLeft()==null && root.getRight()==null)
//        {
//            System.out.print(root.getValue());
//            
//        }
//        else 
//        {
//            System.out.print(root.getValue());
//            
//            preorderPrintTree(root.getLeft());
//            
//            preorderPrintTree(root.getRight());            
//        }
//    }
  
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

