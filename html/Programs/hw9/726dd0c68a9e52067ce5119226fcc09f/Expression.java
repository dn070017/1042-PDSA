
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NoSuchElementException;
public class Expression{
  
    private Node root;
    
     String[] a=new String[100] ;
     String[] b=new String[100] ;
     String[] ee;
    // DO NOT MODIFY THIS
    public Expression(){}
    int n=0 ;
    int count=0;
    int count1=0;
    // Build a Binary and Return the Root
    public Node  Infix2BT(String infix){
      //  this.root=root;
        Stack<Node> vals = new Stack<Node>();
        Stack<Node> cal = new Stack<Node>();
        Stack<String> ops = new Stack<String>();
        String num=null;
        ee=infix.split("""");
        int eat=0;
       // n=ee.length-1;
        //System.out.println(n);
        for (int i=0; i < ee.length; i++) {
            if (ee[i].equals(""("")) { if(num==null){}
            } 
            else if (ee[i].equals(""+"")) { if(num==null){ops.push(ee[i]);}
            else{Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                ops.push(ee[i]);
                
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals(""-"")) {if(num==null){ops.push(ee[i]);}
                     else{
                Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                ops.push(ee[i]);
              
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals(""*"")) {if(num==null){ops.push(ee[i]);}
                     else{
                Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                ops.push(ee[i]);
                
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals(""/"")) {if(num==null){ops.push(ee[i]);}
                     else{
                Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                ops.push(ee[i]);
                
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals("")"")) {
                if(cal.isEmpty())
                {Node temp_node = new Node(null, null, num);
                //vals.push(temp_node); 
                num=null;
                 //Node temp=vals.pop();
                 Node temp_node1=new Node(vals.pop(),temp_node,ops.pop());
                 cal.push(temp_node1);
                 eat=0;
                // System.out.println(vals.peek().getValue());
                }
                else if(eat==1)
                {Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                Node temp_node1=new Node(cal.pop(),vals.pop(),ops.pop());
                cal.push(temp_node1);
                eat=0;
                //System.out.println(vals.peek().getValue());
                }
                else if(eat==0){
                 //System.out.println(vals.peek().getValue());   
                Node temp_node=new Node(vals.pop(),cal.pop(),ops.pop());
                cal.push(temp_node);
                eat=0;
                }
            } 
            else{if(num==null)
                  {num=ee[i];n++;}
            else{num=num+ee[i];}
                //Node temp_node = new Node(null, null, ee[i]);
                //vals.push(temp_node); 
            //n++;
                eat=1;
               //System.out.println(num);
            }
        }
        root=cal.pop();
        return root;
    }
    
    private void printPreOrderRec(Node currRoot ) {
       if (currRoot == null) {
    return ;
     }
      
       
       //int i=0;
        a[count]=currRoot.getValue();
       
      //System.out.print(a[i]);
       count=count+1;
      printPreOrderRec(currRoot.getLeft());
      printPreOrderRec(currRoot.getRight());
      
     }

    public Node[] PrintPrefix(){
        //System.out.println(1);
    //   System.out.println(n);
        Node[] prefix =new Node[n];
        Node testroot=root;
       // prefix[i]=new Node(null,null,testroot.getValue());
        //System.out.println(prefix[i].getValue());
      //  i=i+1;
        
       printPreOrderRec(root);
        for(int j=0;j<n;j++)
        {prefix[j]=new Node(null,null,a[j]);
         //System.out.println(prefix[j].getValue());
        }
        // System.out.println(a);
  
  

        //System.out.println(prefix[0].getValue());
        /*while(i!=5){
            Node testroot1=testroot;
        while(testroot.getLeft()!=null)
        { testroot1=testroot;
            testroot=testroot.getLeft();
        prefix[i]=new Node(null,null,testroot.getValue());
        System.out.println(prefix[i].getValue());
        i=i+1;
        }
        testroot=testroot1.getRight();
        prefix[i]=new Node(null,null,testroot.getValue());
        System.out.println(prefix[i].getValue());
        i=i+1;*/
    
        
        return prefix;
    }
  
  private void printPostOrderRec(Node currRoot) {
  if (currRoot == null) {
    return;
  }
  printPostOrderRec(currRoot.getLeft());
  printPostOrderRec(currRoot.getRight());
  //System.out.print(currRoot.getValue());
  b[count1]=currRoot.getValue();
  count1=count1+1;
}
    public Node[] PrintPostfix(){
        Node[] postfix = new Node[n];
        printPostOrderRec(root);
        for(int j=0;j<n;j++)
        {postfix[j]=new Node(null,null,b[j]);
         //System.out.println(prefix[j].getValue());
        }
        return postfix;
    }

    public double Evaluation(){
        Stack<String> vals = new Stack<String>();
        
        Stack<String> ops = new Stack<String>();
         double answer = 0;
         double ans=0;
        String num=null;
        //String[] ee=infix.split("""");
        int eat=0;
       // n=ee.length-1;
        //System.out.println(n);
        for (int i=0; i < ee.length; i++) {
            if (ee[i].equals(""("")) { if(num==null){}
            } 
            else if (ee[i].equals(""+"")) { if(num==null){ops.push(ee[i]);}
            else{
                vals.push(num); 
                num=null;
                ops.push(ee[i]);
                
                //System.out.println(vals.peek().getValue());
            }
            
            } 
            else if (ee[i].equals(""-"")) {if(num==null){ops.push(ee[i]);}
                     else{
               
                vals.push(num); 
                num=null;
                ops.push(ee[i]);
              
                //System.out.println(vals.peek().getValue());
            }
            
            } 
            else if (ee[i].equals(""*"")) {if(num==null){ops.push(ee[i]);}
                     else{
                
                vals.push(num); 
                num=null;
                ops.push(ee[i]);
                
                //System.out.println(vals.peek().getValue());
            }
            
            } 
            else if (ee[i].equals(""/"")) {if(num==null){ops.push(ee[i]);}
                     else{
                
                vals.push(num); 
                num=null;
                ops.push(ee[i]);
                
                //System.out.println(vals.peek().getValue());
            }
            
            } 
            else if (ee[i].equals("")"")) {
               
                 if(eat==1)
                {
                vals.push(num); 
                num=null;
                eat=0;
                if(ops.peek().equals(""*"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y*x;}
                else if(ops.peek().equals(""+"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y+x;}
                else if(ops.peek().equals(""-"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y-x;}
                else if(ops.peek().equals(""/"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y/x;}
                ops.pop();
                vals.push(String.valueOf(ans));
                
                //System.out.println(vals.peek().getValue());
                }
                else if(eat==0){if(ops.peek().equals(""*"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y*x;}
                else if(ops.peek().equals(""+"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y+x;}
                else if(ops.peek().equals(""-"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y-x;}
                else if(ops.peek().equals(""/"")){
                Double x=Double.parseDouble(vals.pop());
                Double y=Double.parseDouble(vals.pop());
                ans=y/x;}
                vals.push(String.valueOf(ans));
                ops.pop();
                 //System.out.println(vals.peek().getValue());   
                num=null;
                eat=0;
                }
            } 
            else{if(num==null)
                  {num=ee[i];}
            else{num=num+ee[i];}
                //Node temp_node = new Node(null, null, ee[i]);
                //vals.push(temp_node); 
            
                eat=1;
               //System.out.println(num);
            }
        }
      

       // System.out.printf(""%.2f"",s_d)
        Double total=Double.parseDouble(vals.peek());
       total=(Math.floor(total*1000000000))/1000000000 ;
     answer=total;
        return answer;
    }
    public static void main(String[] args) throws Exception {
        String test=""(12/12)"";
        Expression tem=new Expression();
       // tem.Infix2BT(test);
        
       // temp=tem.Infix2BT(test);
        String abc = tem.Infix2BT(test).getValue();
       // System.out.println(abc);
        
       // Node temp = tem.Infix2BT(test);
       // System.out.println(temp.getValue());
        Node[] tempp = tem.PrintPrefix();
        for(int i=0;i<tempp.length;i++)
        { System.out.print(tempp[i].getValue());}
       // tempp[]=tem.PrintPostfix();
    System.out.println();
    Node[] temppp = tem.PrintPostfix();
        for(int i=0;i<tempp.length;i++)
        { System.out.print(temppp[i].getValue());}
        double u=tem.Evaluation();
        System.out.print(u);
       // tempp[]=tem.PrintPostfix();
    }
}


