

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
public class Bingo {

    public static void main(String[] args) throws Exception{
        
        InputStream is = null; 
        InputStreamReader isr = null;
        BufferedReader br = null;
        
        try{
            is = new FileInputStream(args[0]);
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            
            Scanner inputdata = new Scanner(br);
            String[] data;
            data = inputdata.nextLine().split("","");
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            System.out.printf(""number of announced strings: %d%n""
                    + ""dimension of matrix: %d x %d%n"", stringCount, num, num);

            /*  now you can write your own solution to hw0
             *  you can follow the instruction described below:
             
             *  1. read the rest content of the file
             *  2. store the announce strings (2nd line of the file) in variable announce
             *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
             *  4. compare the matrix and announce strings (this is the tricky part)
             *  5. output how many 'straight line' are there in the matrix
             * 
             *  [note]
             *  you can use every data structure in standard Java packages (Java 8 supported)
             *  the packages in stdlib.jar and algs4.jar are also available for you to use
             *
             *  [hint]
.
             *  2. some data structure such as HashSet, HashMap, Arrays, ArrayList, Vector are very
             *     useful for solving problems. 
             */
            // using Scanner (Step.1)
            // read announce data by nextLine (Step.2)
            String[] announce = inputdata.nextLine().split("","");

            // read dimension of matrix (Step.3)
            for (int i = 0; i < num; i++) {
                matrix[i] = inputdata.nextLine().split("","");
            }
            // label zeros(3*3)
            int[][] label = new int[num][num];
            for (int[] row : label) {
                java.util.Arrays.fill(row, 0);
            }
            // if match 0 being 1
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    for (String announce1 : announce) {
                        if (matrix[i][j].equals(announce1)) {
                            label[i][j] = 1;
                        }
                    }
                }
            }

            // caculate the number of bingo line
            int countline = 0;
            int diag_sum = 0;
            int antidiag_sum = 0;
            // bingo count total line
            for (int i = 0; i < num; i++) {
                // diag bingo
                diag_sum += label[i][i];
                antidiag_sum += label[i][2 - i];
                // row_col bingo
                int row_sum = 0;
                int col_sum = 0;
                for (int j = 0; j < num; j++) {
                    row_sum += label[i][j];
                    col_sum += label[j][i];
                }
                if (row_sum == num)
                    countline++;
                if (col_sum == num)
                    countline++;
            }
            if (diag_sum == num)
                countline++;
            if (antidiag_sum == num)
                countline++;
            System.out.printf(""outputline: %d%n"", countline);
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


