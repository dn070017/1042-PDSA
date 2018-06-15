
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            // strings announced for Bingo game         
            String[] announcedata = br.readLine().split("","");
            announce=Arrays.copyOf(announcedata,stringCount);

            for (int n = 0; n < num; n++) {
                String[] matrixdata = br.readLine().split("","");
                matrix[n]=Arrays.copyOf(matrixdata,num);
            }

            int StraightLine = 0;
            int SameCountrow = 0;
            int SameCountcol = 0;
           

            for (int n = 0; n < num; n++) {
                for (int m = 0; m < num; m++) {
              
                    for (int i = 0; i < stringCount; i++) {
                        if (matrix[n][m].equals(announce[i])) {
                            SameCountrow++;
                        }
                        if (matrix[m][n].equals(announce[i])) {
                            SameCountcol++;                                                    
                        }    
                        if (SameCountrow!=0 ||SameCountcol!=0)
                            break;
                    }
                }
                if (SameCountrow == num) {
                    StraightLine++;
                }                
                if (SameCountcol == num) {
                    StraightLine++;
                }
                       SameCountrow = 0;
                       SameCountcol = 0;
            }
           int SameCountdia1 = 0;
           int SameCountdia2 = 0;
            int Num = num-1;
            for (int n = 0; n < num; n++) {
                for (int i = 0; i < stringCount; i++) {
                    if (matrix[n][Num].equals(announce[i])) {
                        SameCountdia2++;
                    }
                    if (matrix[n][n].equals(announce[i])) {
                        SameCountdia1++;
                    }
                    if (SameCountdia2!=0 ||SameCountdia1!=0)
                            break;
                }
                Num--;
  
            }
            if (SameCountdia1 == num) {
                StraightLine++;
            }
              if (SameCountdia2== num) {
                StraightLine++;
            }
 
            System.out.printf(""%d\n"", StraightLine);
        }

        /*  now you can write your own solution to hw0
         *  you can follow the instruction described below:
         * 
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
    }
}
    


