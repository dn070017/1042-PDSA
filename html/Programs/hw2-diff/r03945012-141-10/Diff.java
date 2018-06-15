import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 1042 PDSA
 * hw02_LabelCC
 * @author Robert
 */


public class LabelCC {

    public static void main(String[] args) throws Exception{
        // read file (.txt)
        InputStream is = null; 
        InputStreamReader isr = null;
        BufferedReader br = null;
        try{
            is = new FileInputStream(args[0]);
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            Scanner inputdata = new Scanner(br);
            
            // num = data[0] = matrix size
            // data[1,2] = label(x,y)
            // initialize matrix == 1
            String[] data;
            data = inputdata.nextLine().split("","");
            int num = Integer.parseInt(data[0]);
            int x = Integer.parseInt(data[1]) -1;
            int y = Integer.parseInt(data[2]) -1;
            int[][] matrix = new int[num][num];
            for (int[] row : matrix) {
                java.util.Arrays.fill(row, 1);
            }
            // setup uf for(0-63) as label number
            QuickUnionUF uf = new QuickUnionUF(num*num);
            
            
            // read main data
            // set blocks area = 0
            while (inputdata.hasNextLine()) {
                String[] tmp = inputdata.nextLine().split("","");
                int row = Integer.parseInt(tmp[0])-1;
                int col = Integer.parseInt(tmp[1])-1;
                matrix[row][col] = 0;
            }
            
            // Labelling connected components            
            // the first pass
            int count = 1;
            for (int i=0; i< matrix.length; i++){
                for (int j=0; j< matrix[0].length; j++){
                    if (matrix[i][j]==1){
                        if (i == 0 && j==0){
                            matrix[i][j] =count;
                            count++;
                        }
                        else if (i == 0){
                            if (matrix[i][j-1]>0)
                                matrix[i][j] = matrix[i][j-1];
                            else{
                                matrix[i][j] = count;
                                count++;
                            }
                        }
                        else if (j == 0){
                            if (matrix[i-1][j]>0)
                                matrix[i][j] = matrix[i-1][j];
                            else{
                                matrix[i][j] = count;
                                count++;
                            }
                        }
                        else {
                            if (matrix[i-1][j] == 0 && matrix[i][j-1] == 0){
                                matrix[i][j] = count;
                                count++;
                            }
                            else if (matrix[i-1][j] == 0)
                                matrix[i][j] = matrix[i][j-1]; 
                            else if (matrix[i][j-1] == 0)
                                matrix[i][j] = matrix[i-1][j];
                            else{
                                if (matrix[i-1][j] > matrix[i][j-1]){
                                    uf.union(matrix[i-1][j], matrix[i][j-1]);
                                    matrix[i][j] = matrix[i][j-1];
                                }
                                else if (matrix[i-1][j] < matrix[i][j-1]) {
                                    uf.union(matrix[i][j-1], matrix[i-1][j]);
                                    matrix[i][j] = matrix[i-1][j];
                                }
                                else  
                                    matrix[i][j] = matrix[i-1][j];
                            }             
                        }
                    }
                }
            }
            
            // the second pass
            for (int i=0; i< matrix.length; i++){
                for (int j=0; j< matrix[0].length; j++){
                    matrix[i][j] = uf.find(matrix[i][j]);
                }
            }
            
            System.out.print(matrix[x][y]);
            
            
        }
        catch(FileNotFoundException | NumberFormatException e){
        }
        finally{
        // releases resources associated with the streams
            if(is!=null)
                is.close();
            if(isr!=null)
                isr.close();
            if(br!=null)
                br.close();
        } 
    }
    
}

