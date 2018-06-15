
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NoSuchElementException;
public class Expression{
  
    private Node root;
    
     Node[] a=new Node[1000] ;
     Node[] b=new Node[1000] ;
     String[] ee;
    // DO NOT MODIFY THIS
    public Expression(){}
    int n=0 ;
    int count=0;
    int count1=0;
    // Build a Binary and Return the Root
    public Node  Infix2BT(String infix){
        n=0;
      //  this.root=root;
        Stack<Node> vals = new Stack<Node>();
        //Stack<Node> cal = new Stack<Node>();
       // Stack<String> ops = new Stack<String>();
        String num=null;
        ee=infix.split("""");
       // System.out.println(ee);
        int eat=0;
        int next=0;
        //if(ee==null){throw new NullPointerException();}
       // n=ee.length-1;
        //System.out.println(n);
        for (int i=1; i < ee.length; i++) {
            if (ee[i].equals(""("")) { //if(ee[i+1].equals("")"")){throw new NullPointerException();}
                if(num==null){next=1;}
            
            } 
            else if (ee[i].equals(""+"")) { if(num==null){vals.push(new Node(null,null,ee[i]));}
            else{Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                vals.push(new Node(null,null,ee[i]));
                
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals(""-"")) {if(num==null){vals.push(new Node(null,null,ee[i]));}
                     else{
                Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                vals.push(new Node(null,null,ee[i]));
              
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals(""*"")) {if(num==null){vals.push(new Node(null,null,ee[i]));}
                     else{
                Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                vals.push(new Node(null,null,ee[i]));
                
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals(""/"")) {if(num==null){vals.push(new Node(null,null,ee[i]));}
                     else{
                Node temp_node = new Node(null, null, num);
                vals.push(temp_node); 
                num=null;
                vals.push(new Node(null,null,ee[i]));
                
                //System.out.println(vals.peek().getValue());
            }
            n++;
            } 
            else if (ee[i].equals("")"")) {
                if(vals.isEmpty())
                {//throw new NoSuchElementException(""Stack underflow"");
                    if(num!=null){
                    Node temp_node = new Node(null, null, num);
                //vals.push(temp_node); 
                //Node opt_node =vals.pop();
                num=null;
                 //Node temp=vals.pop();
                 //Node temp_node1=new Node(vals.pop(),temp_node,opt_node.toString());
                 vals.push(temp_node);
                 eat=0;
                 next=0;}
                   else {}
                // System.out.println(vals.peek().getValue());
                }
                else if(eat==1&&next==0)
                {Node temp_node = new Node(null, null, num);
               // vals.push(temp_node); 
               Node opt_node =vals.pop();
                num=null;
                Node temp_node1=new Node(vals.pop(),temp_node, opt_node.getValue() );
                vals.push(temp_node1);
                eat=0;
                next=0;
          //      System.out.println(vals.peek().getValue());
                }
                else if(eat==0&&next==0){
                Node temp_node=vals.pop();
                Node opt_node =vals.pop();
                   //System.out.println(vals.peek().getValue()); 
                Node temp_node1=new Node(vals.pop(),temp_node,opt_node.getValue());
                
               vals.push(temp_node1);
                eat=0;
                next=0;
               
                }
                else if(eat==1&&next==1)
                {Node temp_node = new Node(null, null, num);
                //vals.push(temp_node); 
                 Node opt_node =vals.pop();
                num=null;
                Node temp_node1=new Node(vals.pop(),temp_node,opt_node.getValue());
                vals.push(temp_node1);
                eat=0;
                next=0;
                //System.out.println(vals.peek().getValue());
                }
           /*     else if(eat==0&&next==1){
                  // System.out.println(vals.peek().getValue()); 
                  
                  Node temp_node=vals.pop();
                  if(!cal.isEmpty())
                  {Node temp_node1=new Node(cal.pop(), temp_node,ops.pop());
                
                cal.push(temp_node1);
                eat=0;
                next=0;}
                  else{Node temp_node1=new Node(vals.pop(), temp_node,ops.pop());
                
                cal.push(temp_node1);
                eat=0;
                next=0;}
                }*/
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
        if(vals.isEmpty()){ }
        else{root=vals.pop();}
        
        return root;
    }
    
    private void printPreOrderRec(Node currRoot ) {
       if (currRoot == null) {
    return ;
     }
      
       
       //int i=0;
        a[count]=currRoot;
       
      //System.out.print(a[i]);
       count=count+1;
      printPreOrderRec(currRoot.getLeft());
      printPreOrderRec(currRoot.getRight());
      
     }

    public Node[] PrintPrefix(){
        //if(root.getValue()==null){throw new NullPointerException();}
        if(root.getValue()==null){throw new NullPointerException();}
       //System.out.println(root.getValue());
    //   System.out.println(n);
        Node[] prefix =new Node[n];
        //Node testroot=root;
       // prefix[i]=new Node(null,null,testroot.getValue());
        //System.out.println(prefix[i].getValue());
      //  i=i+1;
        
       printPreOrderRec(root);
        for(int j=0;j<n;j++)
        {prefix[j]=a[j];
         //System.out.println(prefix[j].getValue());
        }
        if(n==0){throw new NullPointerException();}
    
        count=0;
        
        return prefix;
    }
  
  private void printPostOrderRec(Node currRoot) {
  if (currRoot == null) {
    return;
  }
  printPostOrderRec(currRoot.getLeft());
  printPostOrderRec(currRoot.getRight());
  //System.out.print(currRoot.getValue());
  b[count1]=currRoot;
  count1=count1+1;
}
    public Node[] PrintPostfix(){
        Node[] postfix = new Node[n];
        printPostOrderRec(root);
        for(int j=0;j<n;j++)
        {postfix[j]=b[j];
         //System.out.println(prefix[j].getValue());
        }
         count1=0;
          
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
               if(vals.isEmpty()){vals.push(num);}
                else if(eat==1)
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
      
         if(vals.isEmpty()){throw new NullPointerException();}
       // System.out.printf(""%.2f"",s_d)
        Double total=Double.parseDouble(vals.peek());
       //total=(Math.floor(total*1000000000))/1000000000 ;
     answer=total;
        return answer;
    }
    public static void main(String[] args) throws Exception {
        String test=""((20120224791*(121259+797912))+(211544771212/2))"";
        
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
        System.out.println(u);
       // tempp[]=tem.PrintPostfix();
        test=""()"";
        tem.Infix2BT(test);
        tempp = tem.PrintPrefix();
        for(int i=0;i<tempp.length;i++)
        { System.out.print(tempp[i].getValue());}
          System.out.println();
        temppp = tem.PrintPostfix();
        for(int i=0;i<tempp.length;i++)
        { System.out.print(temppp[i].getValue());}
         u=tem.Evaluation();
        System.out.println(u);
    }
}



