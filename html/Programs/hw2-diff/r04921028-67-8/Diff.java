import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
 */
public class LabelCC {
     public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        String buf[] = br.readLine().split("","");
//        System.out.println(buf[0]);
        int n = Integer.valueOf(buf[0]);
        int targetx = Integer.valueOf(buf[1]);
        int targety = Integer.valueOf(buf[2]);
//        System.out.println(buf[1]+"",""+buf[2]);
        int [][] M;
        M = new int[n][n];
       
        while (br.ready())
        {
            String buf1[] = br.readLine().split("","");
//            System.out.println(buf1[0]+"",""+buf1[1]);
            int x = Integer.valueOf(buf1[0]);
            int y = Integer.valueOf(buf1[1]);
            M[x-1][y-1] = -1;
        }
        
        int count = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(M[i][j]==0){
                    if(j==0&&j==0){
                        M[i][j]=1;
//                        System.out.println(""1"");
                    } 
                    if(i!=0&&j==0){
//                        System.out.println(""2"");
                        if(M[i-1][j]>0){
                          M[i][j] = M[i-1][j];   
                        } 
                        if(M[i-1][j]==-1){
                            count++;
                          M[i][j]=count;  
                        }
                    }
                    if(i==0&&j!=0){
//                        System.out.println(""3"");
                        if(M[i][j-1]>0){
                            M[i][j] = M[i][j-1]; 
                        }
                        if(M[i][j-1]==-1){
                            count++;
                            M[i][j] = count; 
                        }
                    }
                    if(i!=0&&j!=0){
//                        System.out.println(""3"");
                        if(M[i][j-1]>0){
                            M[i][j] = M[i][j-1]; 
                        }
                        if(M[i-1][j]>0){
                            M[i][j] = M[i-1][j]; 
                        }
                        if(M[i][j-1]==-1&&M[i-1][j]==-1){
                            count++;
                            M[i][j] = count; 
                        }
                        
                        
                    }
                
                }
                
            }
        }
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                if(M[i][j]==0){System.out.println(""hello"");}
//            }
//        }
//        System.out.println(count); 
        System.out.println(M[targetx-1][targety-1]); 
        if(M[targetx-1][targety-1]==-1){
            System.out.println(""0"");
        }
        }
       
    }
    
}

