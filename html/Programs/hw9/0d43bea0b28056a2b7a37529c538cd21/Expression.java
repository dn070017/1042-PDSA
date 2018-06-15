import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class Expression{
  
    private Node root    // DO NOT MODIFY THIS
;
   private int size;
   
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
    //public String[] Infix2BT(String infix){
        String[] Parts = infix.split(""((?<=[+\\*\\-\\/\\(\\})])|(?=[+\\*\\-\\/\\(\\})]))"");
        int N = Parts.length;
        int key=1;
        size=0;
        Node[] all = new Node[N];
        for(int i = 0; i<N; i++){
            all[i]=new Node(null,null,Parts[i]);
            if(!Parts[i].equals(""("")&!Parts[i].equals("")"")){size++;}
        }
        int i = N-2; // N<2
        int ri = 1;//right
        int le = 0;
        while(Parts[i].equals("")"")){ // to find how many right )
             ri++;i--;}
        //System.out.println(ri);
        //System.out.println(root==null);
        if(ri==1){;root=all[i-1];key=i-1;i--;}
        else{
          while(root==null){//to find root
              if(Parts[i].equals(""("")){le++;}
              i--;
              if(le==(ri-1)){
                 while(Parts[i].equals(""("")){i--;}
                 //System.out.println(i);
                 root = all[i];key=i;
                 //System.out.println(all[key].getValue());
              }
          }}
        
        while(true){
            //System.out.println(all[key].getLeft()==null);
            //System.out.println(key);
            //System.out.println(all[key].getRight()==null);
            if(all[key].getLeft()==null){//i=key-1
               //System.out.println(all[i].getValue());
               ri=0;
               le=0;
               i=key-1;
               while(all[i].getValue().equals("")"")){ri++;i--;}//end i at ) -1 or key-1
               //System.out.println(ri==0);
               if(ri==0){all[key].setLeft(all[i]);}//end i at the key-1(left hand)
                 else if(ri==1){all[key].setLeft(all[i-1]);i--;}//end i at left hand
                 else if(ri>1){
                   while(ri>le+1){
                     if(all[i].getValue().equals(""("")){
                         le++;i--;}
                     else {i--;}//end i at the left)
                   }
                   all[key].setLeft(all[i]);//end i at the left hand
                 }
               String xl =all[key].getLeft().getValue();
               if(xl.equals(""+"")|xl.equals(""*"")|xl.equals(""-"")|xl.equals(""/"")){key=i;}
               //System.out.println(key);
            }
            
            else if(all[key].getRight()==null){//after having left but no right(左樹優先)
                ri=0;le=0;i=key+1;//i is the right position
                while(all[i].getValue().equals(""("")){le++;i++;}//end i at ( +1 or key+1
                //System.out.println(le);
                if(le==0){all[key].setRight(all[i]);}//end i at the key-1(R hand)
                 else if(le==1){all[key].setRight(all[i+1]);key=i+1;i++;}//end i at R hand
                 else if(le>1){
                   while(le>ri+1){
                     if(all[i].getValue().equals("")"")){ri++;i++;}//end i at the R h)
                     else{i++;} // go right
                   }
                   all[key].setRight(all[i]);
                   key=i;//end i at the R hand
                 }
            }
            else {
                //System.out.println(all[key].getValue());
                //System.out.println(all[key].getRight().getValue());
                //System.out.println(""else"");
                key=key+1;
                //System.out.println((!(xv.equals(""+"")|xv.equals(""*"")|xv.equals(""-"")|xv.equals(""/"")))&(i<(N-1)));
                while((!(all[key].getValue().equals(""+"")|all[key].getValue().equals(""*"")|all[key].getValue().equals(""-"")|all[key].getValue().equals(""/"")))&(key<(N-1)))
                {key=key+1;}
                //System.out.println(key);}//to find +-*/
            if(key>=(N-1)){break;}
            //System.out.println(key);
            }}
        return root;
      }

    public Node[] PrintPrefix(){
        Node[] prefix = new Node[size];
        int[] check = new int[size];
        Node x = root;
        prefix[0]=x;
        int i=1;
        int key=0;
        while(true){
          if(prefix[key].getLeft()!=null&(i-key)==1){
             prefix[i]=prefix[key].getLeft();
             if(prefix[key].getRight()!=null){check[key]=1;}
             key=i;i++;//2 3
          }//no left
          else if (prefix[key].getRight()!=null&&(i-key)==2){
             prefix[i]=prefix[key].getRight();
             check[key]=0;
             key=i;i++;
          }
          else if (prefix[key].getRight()!=null&&(i-key)>=3&&check[key]==1){
             prefix[i]=prefix[key].getRight();
             check[key]=0;
             key=i;i++;
          }
          else{key=key-1;}//1 3
          if(i==size){break;}
        }
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        Node[] postfix = null;
        Node x =root;
        int i=0;
        int key=0;
        int p=0;
        while(true){
          while(x.getLeft()!=null){x=x.getLeft();}
          postfix[i]=x;i++;
          
          if (postfix[key].getRight()!=null&(i-key)==2){
             postfix[i]=postfix[key].getRight();
             key=i;i++;
          }
          else if (postfix[key].getRight()!=null&key==0){//root right
             postfix[i]=postfix[key].getRight();
             key=i;i++;p=1;
          }
          else{key=key-1;}//1 3
          if(p==1&key==0){break;}
        }
        return postfix;
    }

    public double Evaluation(){
        double answer = 0;
        return answer;
    }
    
    public static void main(String[] args) throws Exception {
       //System.out.println(Arrays.toString(""(4+(((4*2)/2)/3))"".split(""[+\\*\\-\\/\\(\\})]"")));
       Expression x1 = new Expression();
       String infix = ""(4+(((4*2)/2)/3))"";
       String[] Parts = infix.split(""((?<=[+\\*\\-\\/\\(\\})])|(?=[+\\*\\-\\/\\(\\})]))"");
       //Node[] all = new Node[Parts.length];

       Node r = x1.Infix2BT(infix); 
       //System.out.println(r.getValue());
       Node[] pre = x1.PrintPrefix();
       for(int i = 0; i < pre.length ; i++){
         System.out.println(pre[i].getValue());
        }

        }
}



