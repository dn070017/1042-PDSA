/*
.
 * To change this template file, choose Tools | Templates
.
 */
package labelcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Steven
 */
public class LabelCC {
    
    private static int[] id;
    private static int[][] matrix;

    static int N;
    static int a;
    
    
    public LabelCC(int N){
        id = new int[N * N];
        matrix = new int[N][N];

       
        
    }
    public static void blocked(int row, int col){
        matrix[row][col]=0;
      }
    public static void label(int row, int col){
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(matrix[i][j]!=0)
                    a=matrix[i][j]+1;
                    
                    
                    
            }
           
                
        }
    }
    
    

    
    public static void main(String[] args) throws IOException {
         try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("","");
            N = Integer.parseInt(data[0]);
            int targ1 = Integer.parseInt(data[1]);
            int targ2 = Integer.parseInt(data[2]);
            
            int row = 0;
            int col = 0;
            String[] randc = new String[2];
            
            for(int i=0;i<N;i++){
            randc = br.readLine().split("","");
            row = Integer.parseInt(randc[0]);
            col = Integer.parseInt(randc[1]);
            blocked(row,col);
            }
    }
    
}
}

