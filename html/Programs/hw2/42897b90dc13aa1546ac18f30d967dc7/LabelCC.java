
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
            String[] temp = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] check = new int[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            announce = br.readLine().split("","");
//            System.out.printf(""announce[0]=%s\n"", announce[0]);
//            System.out.printf(""announce[1]=%s\n"", announce[1]);

            for (int i = 0; i < num; i++) {
                temp = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = temp[j];
//                    System.out.printf(""matrix[%d][%d]=%s\n"", i, j, matrix[i][j]);
                }
            }
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    check[i][j] = 0;
//                    System.out.printf(""check[%d][%d]=%s\n"",i,j,check[i][j]);
                }
            }
            int count = 0;
            int output = 0;
//            System.out.printf("" %s , %s \n"", announce[0], announce[1]);
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
//                    System.out.printf(""matrix[%d][%d]=%s\n"",i,j,matrix[i][j]);
                    for(int k=0;k<stringCount;k++)
                    if (matrix[i][j].equals(announce[k]))
                    {
                        check[i][j] = 1;
                    }
//                    System.out.printf(""check[%d][%d]=%s\n"", i, j, check[i][j]);
                }
            }
            int row = 0;
            for (int i = 0; i < num; i++) {
                row = 0;
                for (int j = 0; j < num; j++) {
                    row += check[i][j];
                    if (row == num) {
                        output++;
                    }
                }
//                System.out.printf(""row=%s\n"", row);
            }
            int col = 0;
            for (int i = 0; i < num; i++) {
                col = 0;
                for (int j = 0; j < num; j++) {
                    col += check[j][i];
                    if (col == num) {
                        output++;
                    }
                }
//                System.out.printf(""col=%s\n"", col);
            }
            int dia = 0;
            int invdia = 0;
            for (int i = 0; i < num; i++) {
                dia += check[i][i];
                if (dia == num) {
                    output++;
                }
            }
            for (int i = 0; i < num; i++) {
                invdia += check[num - 1 - i][i];
                if (invdia == num) {
                    output++;
                }
            }
//            System.out.printf(""output=%s\n"", output);
            System.out.printf(""%d\n"", output);
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

