
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
       for (int i = 0;i<N+1;i++ ){
           for (int j = 0;j<N+1;j++ ){
               data[i][j] = -1;
           }
       }
       //read all data into metrix
       String data0 = br.readLine();
       while(data0!=null){
          //set the site block
          String [] data1 = data0.split("","");
          int x = Integer.parseInt(data1[0]);
          int y = Integer.parseInt(data1[1]);
              data[x][y] = 0;
          data0 = br.readLine();
       }
       // show first step the metric
       /*for (int i = 0;i<N+1;i++ ){
           for (int j = 0;j<N+1;j++ ){
               if(data[i][j] != -1)
                   System.out.printf("" %d"", data[i][j]); 
               else
               System.out.printf(""%d"", data[i][j]);
               if(j==N){
               System.out.printf(""\n"", data[i][j]);
                 
               }
           }
       }*/
       
       //now assigne the number to all site but blocked site
       int count = 1;
       int []connect = new int[N*N/2+2];
       for(int i =1;i<N+1;i++){
           for(int j =1;j<N+1;j++ ){
               //if the site is not block
               if(data[i][j]!=0){
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
                           connect[q] = Math.min(q,connect[q]);
                           else
                               connect[q] = q;
                           if(connect[p]!=0)
                           connect[p] = Math.min(q,connect[p]);
                           else
                               connect[p] = q;
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
       // print the connect
       /*for (int i =1;i<=count;i++){
           //System.out.printf(""connect[%d] is %d\n"",i,connect[i]);         
           //change the root to the smallest
           for(int j=1;j<=count;j++ ){
               if(connect[j]==i && connect[i]!=0){                  
                   connect[j] = connect[i];
                   //System.out.printf(""!!!connect[%d] is %d\n"",i,connect[i]);
               }
           }
       }*/ 
       //print all connect
       /*for (int i =0;i<N*N/2+1;i++){
            if(connect[i]!=0)
            System.out.printf(""connect[%d] is %d\n"",i,connect[i]);
       }*/
       // modify it into the correct group
       //int [] fff = new int [2];
       /*for(int i =1;i<=count;i++){
           //BinarySearch BS =  new BinarySearch;
           
        for(int j =1;j<N+1;j++ ){
               for(int k =1; k<N+1 ;k++){
                if(data[j][k] == i &&connect[i]!=0) {
                //match data[j][k] min to others max
                //System.out.printf("" data[%d][%d] equal to connect[%d]\n"",i,j,data[i][j],i-1);
                data[j][k] = connect[i];
                
                // change it into the less one
                 //System.out.printf("" data[%d][%d] chage to %d\n"",i,j,data[i][j]); 
               }  
            }   
           }  
       }*/ 
        //System.out.printf(""ans[0] is %d ans[1] is %d\n"",ans[0],ans[1]);
       //print the result metrix
       
        //System.out.printf(""data[%d][%d] is %d\n"",ans[0],ans[1],data[ans[0]][ans[1]]);
       /*for(int i =1;i<=count;i++){
           if(data[ans[0]][ans[1]] == i && connect[i] !=0)
           data[ans[0]][ans[1]] = connect[i];
           if(data[ans[0]][ans[1]] == i && connect[i] ==0)
           data[ans[0]][ans[1]] = data[ans[0]][ans[1]] ;
       }*/
       int finalans =data[ans[0]][ans[1]] ;
       if(connect[data[ans[0]][ans[1]]]!=0)
       finalans = connect[data[ans[0]][ans[1]]];
       
        System.out.printf(""%d\n"",finalans);
        

//print the result metrix
        /*for (int i = 0;i<N+1;i++ ){
           for (int j = 0;j<N+1;j++ ){
               if(data[i][j] != -1)
               System.out.printf("" %d"", data[i][j]); 
               else
               System.out.printf(""%d"", data[i][j]);
               if(j==N)
               System.out.printf(""\n"", data[i][j]);  
           }*/
       }
       }
    
