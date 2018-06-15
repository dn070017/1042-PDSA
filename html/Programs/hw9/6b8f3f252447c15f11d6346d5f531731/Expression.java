//import edu.princeton.cs.algs4.Stack;
public class Expression{
  
    private Node root;
    String[] outData;
    Queue<Node> qPreNode=new Queue<Node>();
    Queue<Node> qPostNode=new Queue<Node>();
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
        int length=((outData.length+1)/2);

        Node[] prefix=new Node[length];
//            int countNull=0;
//            int length=((outData.length+1)/2);
//            prefix = new Node[length];
//            String[] copyOutData=new String[outData.length];
//            for(int i=0;i<outData.length;i++)
//            {
//                if(outData[i]==null)
//                {
//                    countNull++;
//                }            
//            }
//            for(int i=0;i<outData.length;i++)
//            {
//                copyOutData[i]=outData[countNull+i];
//            }
//  
//            for(int i=0;i<outData.length;i++)
//            {
//                char isANumber=copyOutData[i].charAt(0);
//                if(Character.isDigit(isANumber)!=true && copyOutData[i].equals(""("")!=true && copyOutData[i].equals("")"")!=true)
//                {
//                    for(int j=i-1;j>=0;j--)
//                    {
//                        if(copyOutData[j].equals(""(""))
//                        {
//                            copyOutData[j]=copyOutData[i];
//                            copyOutData[i]="")"";
//                            break;
//                        }
//                    }
//                }
//            }
//            String out = """";
//            int a=0;
//            for(int i=0;i<outData.length;i++)
//            {
//                if(copyOutData[i].equals("")"")!=true)
//                {
//                    out = copyOutData[i];
//                    Node b=new Node(null,null,out);
//                    prefix[i-a]=b;
//                    //check[i-a]=out;
//                }
//                else if(copyOutData[i].equals("")"")==true)
//                {
//                    a++;
//                }
//            }

        preorderPrintTree(root);
        for(int i=0;i<((outData.length+1)/2);i++)
        {
            prefix[i]=qPreNode.dequeue();
        }
        return prefix;
    }
    
    private void preorderPrintTree(Node root) {

        if (root.getLeft()==null&&root.getRight()==null)
        {
            qPreNode.enqueue(root);
            //System.out.print(root.getValue());
        }            
        else{
            qPreNode.enqueue(root);
            //System.out.print(root.getValue());

            preorderPrintTree(root.getLeft());
            preorderPrintTree(root.getRight());

        }
    }

  
    public Node[] PrintPostfix(){
        int length=((outData.length+1)/2);

        Node[] postfix=new Node[length];

//            int length=((outData.length+1)/2);
//            postfix = new Node[length];
//
//            int countNull=0;
//            String[] copyOutData=new String[outData.length];
//            for(int i=0;i<outData.length;i++)
//            {
//                if(outData[i]==null)
//                {
//                    countNull++;
//                }
//            }
//            for(int i=0;i<outData.length;i++)
//            {
//                copyOutData[i]=outData[countNull+i];
//            }
//
//            Stack<String> stk=new Stack<String>();
//            int j=0;
//            for(int i=0;i<outData.length;i++)
//            {            
//                char isANumber=copyOutData[i].charAt(0);
//                if(copyOutData[i].equals(""(""))
//                {
//                    j++;
//                }
//                else if(copyOutData[i].equals("")""))
//                {
//                    String symbol=stk.pop();
//                    postfix[i-j]=new Node(null, null, symbol);
//                }
//                else if(Character.isDigit(isANumber)!=true && copyOutData[i].equals(""("")!=true && copyOutData[i].equals("")"")!=true)
//                {
//                    j++;
//                    stk.push(copyOutData[i]);
//                }
//                else
//                {
//                    Node a=new Node(null, null, copyOutData[i]);
//                    postfix[i-j]=a;
//                }
//            }

        postorderPrintTree(root);
        for(int i=0;i<((outData.length+1)/2);i++)
        {
            postfix[i]=qPostNode.dequeue();
        }
        return postfix;
    }
    
    private void postorderPrintTree(Node root) {
        if (root.getLeft()==null&&root.getRight()==null) {
            qPostNode.enqueue(root);
            //System.out.print(root.getValue());
        } else {
            postorderPrintTree(root.getLeft());
            postorderPrintTree(root.getRight());
            qPostNode.enqueue(root);
            //System.out.print(root.getValue());            
        }
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

