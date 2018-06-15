import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class LabelCC {
    private static int[] id;
    private static int[] sz;
.
    public static void QuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for(int i =0; i<N ; i++){
            id[i]=i; 
            sz[i]=1;
        }
    }
    
        private static int root(int i)
    {
        while (i != id[i]) 
            i = id[id[i]];
            return i;
        }
 //       public static boolean connected(int p, int q)
 //       {
 //           return root(p) == root(q);
 //       }
        
        public static void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if(i == j) return;
            if (sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i];}
            else               {id[j] = i; sz[i] += sz[j];} 
        }
        
   public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style args[0]
        //args[0] is  just for juged system, ""input6.txt""~8
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read first line number as vector
            String [] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            //System.out.println(data);
            QuickUnionUF(num*num);

            int [] label = new int[num*num];
            Arrays.fill(label, num*num-1);
            int [] announce = {Integer.parseInt(data[1]),Integer.parseInt(data[2])} ;
            String temp = null;
            int flag=0;
            while ((temp = br.readLine())!=null) {
                    String[] temp2 = temp.split("","");
                    int t0 = Integer.parseInt(temp2[0]);
                    int t1 = Integer.parseInt(temp2[1]);
                    //System.out.println(t0+"",""+t1);
                    label[(t0-1)*num+t1-1]= 0;
                    flag=flag+1;
                    }
            //for (int i = 0; i < num; i++) {
            //    for (int j = 0; j < num; j++){
            //    System.out.println(label[i*num+j]);  
            //   }
            //System.out.println();
            //}
            int index=0;
            for(int i = 0; i<num*num; i++){
              //自己沒有堵塞才值得看
              if(label[i]>0){
               //if邊界
               if(i<num|(i%num)==0){
                  //兩邊界 
                  if(i<num&(i%num)==0){
                    label[i]=index+1;
                    index=index+1;}
                  //僅上邊界看左
                  else if(i<num){
                      if(label[i-1]>0){label[i]=label[i-1];}
                      else {                    
                          label[i]=index+1;
                          index=index+1;}
                  }
                  //僅左邊界看上
                  else {
                      if(label[i-num]>0){label[i]=label[i-num];}
                      else {                    
                          label[i]=index+1;
                          index=index+1;}
                  }}
               //if 非邊界
               else{
                   //都不塞
                   if(label[i-num]>0&label[i-1]>0){
                       if(label[i-num]==label[i-1]){
                           label[i]=label[i-1];}
                       else{
                           union(label[i-num],label[i-1]);
                           label[i]=label[i-1];}
                   }
                   //單塞
                   else if(label[i-num]>0|label[i-1]>0){
                       if(label[i-num]>0){
                           label[i]=label[i-num];}
                       else{
                           label[i]=label[i-1];}
                   }
                   //都塞
                   else{
                       label[i]=index+1;
                       index=index+1;}
               }}
            //System.out.println();
            //System.out.println(index);
            //System.out.println();
            //for(int i = 0; i<num*num;i++){
            //    label[i]=root(label[i]);
            //}    
            //for (int k = 0; k < num; k++) {
            //    for (int j = 0; j < num; j++){
            //    System.out.println(label[k*num+j]);  
            //   }
            //System.out.println();
            //}
            }
            if(flag==0){
                Arrays.fill(id,1);
            }
            System.out.println(root(label[num*(announce[0]-1)+announce[1]-1]));
            //System.out.println(80%7!=0);
        }
}}


