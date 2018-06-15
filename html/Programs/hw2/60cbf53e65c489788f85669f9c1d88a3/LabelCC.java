
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
       //open the metrix and set all entry to -1
       int [][] data = new int [N+1][N+1];
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
       int countforconnect = 0;
       int [][]connect = new int[N*N][2];
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
                       //System.out.printf("" carry data[%d][%d] is %d\n"",i,j,data[i][j]);             
                     else if(data[i][j-1]>0 && data[i-1][j] > 0){                                                   
                               data[i][j] = Math.min(data[i][j-1],data[i-1][j]);
                               int p = Math.max(data[i][j-1],data[i-1][j]);
                               int q = Math.min(data[i][j-1],data[i-1][j]);
                           //max's root to min
                           //both not connect
                               connect[countforconnect][0] = p;
                               connect[countforconnect][1] = q;
                               //System.out.printf(""countforconnect is %d\n"",countforconnect);
                               countforconnect++;
                               //System.out.printf(""countforconnect is %d\n"",countforconnect);
                               /*for(int k =0;k<N*N/2+1;k++){
                                        if(connect[k] == op)
                                            connect[k]= oq;
                               }*/
                        }
                   else
                           data[i][j] =Math.max(data[i-1][j],data[i][j-1]);
                       }
                   
            }
        }       
           
       //equal to site
       int finalans =data[ans[0]][ans[1]];
       //connect have value
       //if(data[ans[0]][ans[1]]!=-1){
           for(int i = 0; i<countforconnect;i++){
               if(connect[i][0] == data[ans[0]][ans[1]])
                   finalans = Math.min(connect[i][1],finalans);
           }
       //}
       //block
       if(data[ans[0]][ans[1]]==-1)
       finalans = 0;
       System.out.printf(""%d\n"",finalans);
        
       }
    }
   

