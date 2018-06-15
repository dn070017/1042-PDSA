
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

            announce = br.readLine().split("","");

            for (int i = 0; i < num; i++) {
                matrix[i] = br.readLine().split("","");
            }

            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(data[0]+"" ""+data[1]+""\n"");
//            System.out.printf(announce[0]+"" ""+announce[1]+""\n"");
//            System.out.println(matrix[0][0]+"" ""+matrix[0][1]+"" ""+matrix[0][2]);
//            System.out.println(matrix[1][0]+"" ""+matrix[1][1]+"" ""+matrix[1][2]);
//            System.out.println(matrix[2][0]+"" ""+matrix[2][1]+"" ""+matrix[2][2]);
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            //fill in compare matrix
            int[][] mat_compare = new int[num][num];
            for (int y = 0; y < num; y++) {
                for (int x = 0; x < num; x++) {
                    for (int j = 0; j < stringCount; j++) {
                        if (matrix[x][y].equals(announce[j])) {
                            mat_compare[x][y] = 1;
                        }
                    }
                }
            }
//            System.out.println(mat_compare[0][0]+"" ""+mat_compare[0][1]+"" ""+mat_compare[0][2]);
//            System.out.println(mat_compare[1][0]+"" ""+mat_compare[1][1]+"" ""+mat_compare[1][2]);
//            System.out.println(mat_compare[2][0]+"" ""+mat_compare[2][1]+"" ""+mat_compare[2][2]);
            int[] row = new int[num];
            int[] column = new int[num];
            int[] diagonal = new int[2];
            int line_count = 0;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    row[i] += mat_compare[i][j];
                    column[i] += mat_compare[j][i];
                }
                //check diagonal
                diagonal[0] += mat_compare[i][i];
                diagonal[1] += mat_compare[i][num - i - 1];
                if (row[i] == num) {
                    line_count++;
                }
                if (column[i] == num) {
                    line_count++;
                }
            }
            if (diagonal[0] == num) {
                line_count++;
            }
            if (diagonal[1] == num) {
                line_count++;
            }
//            System.out.println(row[0]+"" ""+row[1]+"" ""+row[2]);
//            System.out.println(column[0]+"" ""+column[1]+"" ""+column[2]);
            System.out.println(line_count);
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

