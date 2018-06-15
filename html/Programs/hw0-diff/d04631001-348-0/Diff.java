
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {

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
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            announce = br.readLine().split("","");
            //System.out.printf(""announced: %s %s \n"", announce[0], announce[1]);

            String[] temp = new String[num];
            for (int i = 0; i < matrix[0].length; i++) {
                temp = br.readLine().split("","");
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = temp[j];
                }
            }
            //System.out.printf(""matrix:\n%s %s %s\n%s %s %s\n%s %s %s\n"", matrix[0][0], matrix[0][1], matrix[0][2], matrix[1][0], matrix[1][1], matrix[1][2], matrix[2][0], matrix[2][1], matrix[2][2]);

            int[][] matrix_a = new int[matrix[0].length][matrix.length];
            int[][] matrix_s = new int[matrix[0].length][matrix.length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    for (int k = 0; k < announce.length; k++) {
                        if (matrix[i][j].equals(announce[k])) {
                            matrix_a[i][j] = 1;
                        } //else if (announce[1].equals(matrix[i][j])) matrix_a[i][j]=""1"";                    
                        else {
                            matrix_a[i][j] = 0;
                        }
                        matrix_s[i][j] = matrix_s[i][j] + matrix_a[i][j];
                    }
                    //System.out.print(matrix_s[i][j] + ""\t"");
                }
                //System.out.println();
            }
            int[] sum_h = new int[matrix.length]; int bingo_h = 0;
            int[] sum_v = new int[matrix.length]; int bingo_v = 0;
            int[] sum_l = new int[matrix.length]; int bingo_l = 0;
            int[] sum_r = new int[matrix.length]; int bingo_r = 0;
            
            // horizontal addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                for (int j = 0; j < matrix_s.length; j++) {
                    sum_h[i] = sum_h[i] + matrix_s[i][j];
                    {
                        if (sum_h[i]==matrix_s[0].length)
                            bingo_h = bingo_h + 1;
                    }
                }
            }
            // vertical addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                for (int j = 0; j < matrix_s.length; j++) {
                    sum_v[i] = sum_v[i] + matrix_s[j][i];
                    {
                        if (sum_v[i]==matrix_s[0].length)
                            bingo_v = bingo_v + 1;
                    }
                }
            }
            // 45 degree-cross addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                for (int j = 0; j<matrix_s.length ; j++) {
                    sum_l[i] = sum_l[i] + matrix_s[i][matrix_s.length-j-1];
                    {
                        if (sum_l[i]==matrix_s[0].length)
                            bingo_l = bingo_l + 1;
                    }
                }
            }
            // -45 degree-cross addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                sum_r[i] = sum_r[i] + matrix_s[i][i];
                {
                    if (sum_r[i]==matrix_s[0].length)
                            bingo_l = bingo_l + 1;
                }
            }
            int bingo = 0;
            bingo = bingo_h + bingo_v + bingo_l + bingo_r;
            //System.out.printf(""bingo h:\n%d\n"", bingo_h);
            //System.out.printf(""bingo v:\n%d\n"", bingo_v);
            //System.out.printf(""bingo l:\n%d\n"", bingo_l);
            //System.out.printf(""bingo r:\n%d\n"", bingo_r);
            //System.out.printf(""bingo:\n%d\n"", bingo);
            System.out.printf(""%d\n"", bingo);
            //System.out.printf(""matrix hit:\n%d %d %d\n%d %d %d\n%d %d %d\n"", matrix_s[0][0], matrix_s[0][1], matrix_s[0][2], matrix_s[1][0],matrix_s[1][1],matrix_s[1][2], matrix_s[2][0],matrix_s[2][1], matrix_s[2][2]);

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

