import java.io.FileReader;
import java.io.BufferedReader;
//import java.util.Arrays;


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
//           String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] bin = new int[num][num];

            // printf in Java (you should comment out or delete this in your final submission)

            String[] announce = br.readLine().split("","");

            int k = 0;
            int a = 0;
            for (int i = 0; i < num; i++) {
                String[] coun = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = coun[j];
                    for (int m = 0; m < stringCount; m++) {
                        if (matrix[i][j].equals(announce[m])) {
                            bin[i][j] = 1;
                        }
                    }
                }
            }

           
            for (int i = 0; i < num; i++) {
                if (i == 0 && bin[0][0] == 1) {
                    for (int j = 0; j < (num - 1); j++) {
                        if (bin[j][j] == bin[j + 1][j + 1] ) {
                            k = k + 1;
                        }
                        if (k == num - 1) {
                            a = a + 1;
                        }
                    }
                    k = 0;
                } else if (i == 0 && bin[0][num - 1] == 1) {
                    for (int j = 0; j < (num - 1); j++) {
                        if (bin[j][num - 1 - j] == bin[j + 1][num - 2 - j]) {
                            k = k + 1;
                        }
                        if (k == num - 1) {
                            a = a + 1;
                        }
                        
                    }
                    k = 0;
                }
                for (int j = 0; j < (num - 1); j++) {
                    if (bin[i][j] == bin[i][j + 1] && bin[i][j] == 1) {
                        k = k + 1;
                    }
                    if (k == num - 1) {
                        a = a + 1;
                    }  
                }
                k = 0;

                for (int j = 0; j < (num - 1); j++) {
                    if (bin[j][i] == bin[j+1][i] && bin[j][i] == 1) {
                        k = k + 1;          
                    }
                    if (k == num - 1) {
                        a = a + 1; 
                    }
                }
                k = 0;
            }
            System.out.printf(""%d "", a);
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

