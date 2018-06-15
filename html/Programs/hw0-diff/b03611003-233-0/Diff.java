package bingo;

import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

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
//            ========================================================================================
            announce = br.readLine().split("","");
            for (int i = 0; i < num; i++) {
                matrix[i] = br.readLine().split("","");
            }
            int[][] crossedout = new int[num][num];

//            store to-be-crossedout into String[] announce, bingo into String[][] matrix. creat a counter int[][] crossedout
//            System.out.print(matrix[2][0]);
            for (int j = 0; j < stringCount; j++) {

//                check every announce
                for (int row = 0; row < num; row++) {
                    for (int col = 0; col < num; col++) {
                        if (matrix[row][col].equals(announce[j])) {
                            crossedout[row][col] = 1;
                        }
                    }
                }
            }

//            for (int row = 0; row < matrix.length; row++) {
//                for (int column = 0; column < matrix[row].length; column++) {
//                    System.out.println(crossedout[row][column] + "" "");
//                }
//                System.out.println();
//            }
//       
//              show the crossedout counter      
            int counter = 0;

            for (int row = 0; row < num; row++) {
                int thisrow = 0;
                for (int col = 0; col < num; col++) {
                    thisrow += crossedout[row][col];
                }
                if (thisrow == num) {
                    counter++;
                }
            }
//            check rows

            for (int col = 0; col < num; col++) {
                int thiscol = 0;
                for (int row = 0; row < num; row++) {
                    thiscol += crossedout[row][col];
                }
                if (thiscol == num) {
                    counter++;
                }
            }
//            check cols
            int cross1 = 0;
            int cross2 = 0;

            for (int i = 0; i < num; i++) {
                cross1 += crossedout[i][i];   
                cross2 += crossedout[i][num - 1 - i];
            }
            
            if(cross1 == num){
                counter++;
            }
            if(cross2 == num){
                counter++;
            }
//            check cross
            
            
            System.out.print(counter);
            

        }
    }
}

