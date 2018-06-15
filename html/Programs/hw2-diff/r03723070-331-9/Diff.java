
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */

public class LabelCC {
    
    public static void main(String[] args) throws Exception{
       BufferedReader br = new BufferedReader(new FileReader(args[0]));
       String [] input = br.readLine().split("","");//read txt
       int N = Integer.parseInt(input[0]);
       int [] ans = new int [2];
       ans[0] = Integer.parseInt(input[1]);
       ans[1] = Integer.parseInt(input[2]);
       //System.out.printf(""%s,%d,%d\n"",input[0],ans[0],ans[1]);
       //set all input data for the first line, check ans[0][1]
       
       //open the metrix and set all entry to -1
       int [][] data = new int [N+1][N+1];
      /* for (int i = 0;i<N+1;i++ ){
           for (int j = 0;j<N+1;j++ ){
               //System.out.printf(""%d"", data[i][j]);
               data[i][j] = -1;
           }
       }*/
       //read all data into metrix
       String data0 = br.readLine();
       while(data0!=null){
          //set the site block
          String [] data1 = data0.split("","");
          int x = Integer.parseInt(data1[0]);
          int y = Integer.parseInt(data1[1]);
              data[x][y] = -1;
              //System.out.printf(""%d\n"",data[x][y]);
          data0 = br.readLine();
       }   
       //now assigne the number to all site but blocked site
       int count = 1;
       int []connect = new int[N*N/2+1];
       for(int i =1;i<N+1;i++){
           for(int j =1;j<N+1;j++){
               //if the site is not block
               if(data[i][j]!=-1){
                   //check the left and top, if it is all not blocked
                   if((data[i][j-1]<=0 )&& (data[i-1][j] <= 0)){
                       // opent the site
                       data[i][j] = count;
                       count += 1;
                   }
                   //check the left and top, if it open site
                   if(data[i][j-1]>0 || data[i-1][j] > 0 ){
                       //System.out.printf("" carry data[%d][%d] is %d\n"",i,j,data[i][j]);             
                       if(data[i][j-1]>0 && data[i-1][j] > 0){
                           data[i][j] = data[i][j-1];
                           if(data[i-1][j]!=data[i][j-1]){
                           int p = Math.max(data[i][j-1],data[i-1][j]);
                           int q = Math.min(data[i][j-1],data[i-1][j]);
                           
                           if(connect[q]!=0)
                           connect[p] = connect[q];
                           else
                               connect[p] = q;
                           
                           if(connect[q]!=0)
                           connect[q] = Math.min(q,connect[q]);
                           else
                               connect[q] = q;
                       }
                           
                       }
                       else{
                           data[i][j] = Math.max(data[i][j-1],data[i-1][j]);
                           //System.out.printf(""to carry data[%d][%d] is %d\n\n"",i,j,data[i][j]);     
                       }
                   }
               }
               //record it to p,q
              
           }
       }
        //System.out.printf(""\n""); 
       //show second step the primitve metrix
       /*for (int i = 0;i<N+1;i++ ){
           for (int j = 0;j<N+1;j++ ){
               if(data[i][j] != -1)
                   System.out.printf("" %d"", data[i][j]); 
               else
               System.out.printf(""%d"", data[i][j]);
               if(j==N)
               System.out.printf(""\n"", data[i][j]);  
           }
       }*/
       //print all connect
       /*for (int i =0;i<N*N/2+1;i++){
            if(connect[i]!=0)
            System.out.printf(""connect[%d] is %d\n"",i,connect[i]);
       }*/   
       int finalans =data[ans[0]][ans[1]] ;
       
       if(connect[data[ans[0]][ans[1]]]!=0)
       finalans = connect[data[ans[0]][ans[1]]];
       if(data[ans[0]][ans[1]]==-1)
       finalans = 0;
       
       System.out.printf(""%d\n"",finalans);
        
       }
       }
    

