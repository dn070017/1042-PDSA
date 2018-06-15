import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class Expression{
  
   private Node root;    // DO NOT MODIFY THIS
   private int size;
   
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
    //public String[] Infix2BT(String infix){
        root=new Node(null,null,null);
        //System.out.println(root.getValue()==null);     
        size=0;
        String[] Parts = infix.split(""((?<=[+\\*\\-\\/\\(\\})])|(?=[+\\*\\-\\/\\(\\})]))"");
        int N = Parts.length;
        if(Parts.length<3){throw new NullPointerException();}
        int key=1;
        Node[] all = new Node[N];
        for(int i = 0; i<N; i++){
            all[i]=new Node(null,null,Parts[i]);
            if(!Parts[i].equals(""("")&!Parts[i].equals("")"")){size++;}
        }
        if (Parts.length==3){root.setValue(Parts[1]);return root;}
        int i = N-2; // N<2
        int ri = 1;//right
        int le = 0;
        while(Parts[i].equals("")"")){ // to find how many right )
             ri++;i--;}
        //System.out.println(ri);
        //System.out.println(root==null);
        if(ri==1){root=all[i-1];key=i-1;i--;}
        //if(ri==1){root.setValue(all[i-1].getValue());key=i-1;i--;}
        else{
          while(root.getValue()==null){//to find root
              if(Parts[i].equals(""("")){le++;}
              i--;
              if(le==(ri-1)){
                 while(Parts[i].equals(""("")){i--;}
                 //System.out.println(i);
                 root=all[i];key=i;
                 //root.setValue(all[i].getValue());key=i;
                // break;
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
        //System.out.println(size);
        
        if(root.getValue()==null|root==null){throw new NullPointerException();}
        //ArrayList<Node> pre = new ArrayList<Node>();
       // size=1;
       // Node test = root;
       // while(test.getLeft()!=null|test.getRight()!=null){
       //    while(test.getLeft().getLeft()!=null){test=test.getLeft(); size++;}
       //   size++;//the depest left
       //    while(test.getRight().getRight()!=null){test=test.getRight(); size++;}
       //    size++;
       // }
        Node[] prefix=new Node[size];
        int[] check = new int[size];
        //pre.add(root);
        prefix[0]=root;
        int i=1;
        int key=0;
        while(true){
          if(prefix[key].getLeft()!=null&(i-key)==1){
             prefix[i]=prefix[key].getLeft();
             if(prefix[key].getRight()!=null){check[key]=1;}
             key=i;i++;//2 3
          }//no left
          else if (prefix[key].getRight()!=null&&(i-key)>=2&&check[key]==1){
             prefix[i]=prefix[key].getRight();
             check[key]=0;
             key=i;i++;
          }
//          else if (prefix[key].getRight()!=null&&(i-key)>=3&&check[key]==1){
//             prefix[i]=prefix[key].getRight();
//             check[key]=0;
//             key=i;i++;
//          }
          else{key=key-1;}//1 3
          if(i==size){break;}
        }
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        if(root.getValue()==null|root==null){throw new NullPointerException();}
        Node[] postfix=new Node[size];
        postfix[size-1]=root;
        int[] check2 = new int[size];
        int i=size-2;
        int key=size-1;
        while(true){
            
          if(postfix[key].getRight()!=null&(key-i)==1){
             postfix[i]=postfix[key].getRight();
             if(postfix[key].getLeft()!=null){check2[key]=1;}
             key=i;i--;}//no left
  
          else if (postfix[key].getLeft()!=null&&(key-i)>=2&&check2[key]==1){
             postfix[i]=postfix[key].getLeft();
             check2[key]=0;
             key=i;i--;
          }
          else{key=key+1;}//1 3
          if(i==-1){break;}
        }
        return postfix;
    }

    public double Evaluation(){
        if(root.getValue()==null|root==null){throw new NullPointerException();}
        Node[] postf= new Node[PrintPostfix().length];
        for (int i=0;i<postf.length;i++){postf[i]=new Node(null,null,PrintPostfix()[i].getValue());}
        //System.out.println(postfix.length);//to find +-*/
        for (int i =0;i<(postf.length);i++){
            if(postf[i].getValue().equals(""+"")|postf[i].getValue().equals(""-"")|postf[i].getValue().equals(""*"")|postf[i].getValue().equals(""/"")){
                int j = i-1;
                while(postf[j]==null&j>0){j--;}
                Double a = Double.parseDouble(postf[j].getValue());
                postf[j]=null;
                j--;
                while(postf[j]==null&j>0){j--;}
                Double b = Double.parseDouble(postf[j].getValue());
                postf[j]=null;
               if(postf[i].getValue().equals(""+"")){postf[i].setValue(Double.toString(b+a)); }
               if(postf[i].getValue().equals(""-"")){postf[i].setValue(Double.toString(b-a)); }
               if(postf[i].getValue().equals(""*"")){postf[i].setValue(Double.toString(b*a)); }
               if(postf[i].getValue().equals(""/"")){postf[i].setValue(Double.toString(b/a)); }
            }}
        double answer = Double.parseDouble(postf[postf.length-1].getValue());
       
        return answer;
    }
    
    
    	public static void main(String[] args) throws Exception{
		 try(BufferedReader br = new BufferedReader(new FileReader(""input9.txt""))){
			 String temp;
			 while((temp = br.readLine())!=null){
				 String[] split_temp=temp.split("" "");
				 for(String input:split_temp){
//                                         String[] Parts = input.split(""((?<=[+\\*\\-\\/\\(\\})])|(?=[+\\*\\-\\/\\(\\})]))"");
//                                         System.out.println(""Input: "");
//					 for(int i = 0 ; i<Parts.length;i++){
//						 System.out.println(Parts[i]);
//					 }
					 Expression e = new Expression();
					 Node n = e.Infix2BT(input);			 
					 Node[] pre = e.PrintPrefix();
					 Node[] post = e.PrintPostfix();
					 double answer = e.Evaluation();
					 System.out.println(""PrintPrefix: "");
					 for(int i = 0 ; i<pre.length;i++){
						 System.out.println(pre[i].getValue());
					 }
					 System.out.println("""");
					 System.out.println(""PrintPostfix: "");
					 for(int i = 0 ; i<post.length;i++){
						 System.out.println(post[i].getValue());
					 }
					 System.out.println("""");
					 System.out.println(""Evaluation(): "");
					 System.out.println(answer);
					 System.out.println("""");
                                         System.out.println(Double.parseDouble(""8210000000000000000""));
                                         
				 }
			 }
		 }	
	}
    
    
//    public static void main(String[] args) throws Exception {
//       //System.out.println(Arrays.toString(""(4+(((4*2)/2)/3))"".split(""[+\\*\\-\\/\\(\\})]"")));
//       
//       //String infix = ""((20120224791*(121259+797912))+(211544771212/2))"";
//       //String[] Parts = infix.split(""((?<=[+\\*\\-\\/\\(\\})])|(?=[+\\*\\-\\/\\(\\})]))"");
//       //Node[] all = new Node[Parts.length];
//       String[] infix = new String[]{""((20120224791*(121259+797912))+(211544771212/2))"",""(((1.23-(121259+797912))*(21212+213135498))/32349)"",""((1312.1+((5.132*323.13)/(131313.12+464588)))""};
//       String infix2 = ""(4+(((4*2)/2)/3))"";
//// for (int j =0;j<3;j++){
//       Expression e = new Expression();
//       Node n = e.Infix2BT(infix2);			 
//       Node[] pre = e.PrintPrefix();
//       Node[] post = e.PrintPostfix();
//					 double answer = e.Evaluation();
//					 System.out.println(""PrintPrefix: "");
//					 for(int i = 0 ; i<pre.length;i++){
//						 System.out.println(pre[i].getValue());
//					 }
//					 System.out.println("""");
//					 System.out.println(""PrintPostfix: "");
//					 for(int i = 0 ; i<post.length;i++){
//						 System.out.println(post[i].getValue());
//					 }
//


//       Expression x1 = new Expression();  
//       Node r = x1.Infix2BT(infix2); 
//       Node[] pre = x1.PrintPrefix();
//       Node[] pos = x1.PrintPostfix();
//       for(int i = 0; i < pre.length ; i++){
//         System.out.println(pre[i].getValue());
//        }
//       
//       for(int i = 0; i < pos.length ; i++){
//         System.out.println(pos[i].getValue());
//        }
//      System.out.println(x1.Evaluation());
       
      //  }
}




