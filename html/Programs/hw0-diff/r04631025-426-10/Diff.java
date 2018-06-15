/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Tim
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] matrixCheck = new int[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
          //  System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            announce = br.readLine().split("","");
            for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                    matrixCheck[i][j]=0;
                }
            }

            for (int i = 0; i < num; i++) {
                matrix[i] = br.readLine().split("","");
            }
            
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    for (int k = 0; k < stringCount; k++) 
                        if (matrix[i][j].equals(announce[k])) {
                            matrixCheck[i][j] = 1;
                        } 

                }
            }

            int sum;
            int straightline=0;
            //count the row
            for (int i = 0; i < num; i++) {
                sum = 0;
                for (int j = 0; j < num; j++) {
                    sum = sum+matrixCheck[i][j];
                }
                if(sum==num){
                    straightline++;
                }
            }

            //count the column
            for (int j = 0; j < num; j++) {
                sum = 0;
                for (int i = 0; i < num; i++) {
                    sum =sum+matrixCheck[i][j];
                }
                if(sum==num){
                    straightline++;
                }
            }
            //count the diagonal
            int dia=0;
            for(int i=0;i<num;i++){
                dia+=matrixCheck[i][i];
            }
            if(dia==num){
                straightline++;
            }
            
            int dia2=0;
            for(int i=0;i<num;i++){                
                dia2+=matrixCheck[i][num-i-1];
            }
            if(dia2==num){
                straightline++;
            }
            
            System.out.printf(""%d"", straightline);

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
    
}

