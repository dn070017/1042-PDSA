
import java.io.FileReader;
import java.io.BufferedReader;
import java.sql.Array;
import java.util.Arrays;

public class Bingo {

    public static int amount = 0;

    private static void sumrow(int[][] d) {
        int order = 0;
        int column = 0;
        int sumrow = 0;
        int sumcolumn = 0;
        int l = d.length;

        while (order < l) {
            //sum of row
            while (column < l) {
                sumrow = d[order][column] + sumrow;
                sumcolumn = d[column][order] + sumcolumn;

                column++;
            }
            if (sumrow == l || sumcolumn == l) {
                amount++;
            }
            sumrow = 0;
            sumcolumn = 0;
            column = 0;
            order++;
        }
        order=0;
        sumrow=0;
        sumcolumn=0;
        while (order < (l + 1) / 2) {
            sumrow = d[(l - 1) / 2 + order][(l - 1) / 2 - order]+d[(l - 1) / 2 - order][(l - 1) / 2 + order] + sumrow;
            sumcolumn = d[(l - 1) / 2 + order][(l - 1) / 2 + order] +d[(l - 1) / 2 - order][(l - 1) / 2 - order]+ sumcolumn;
            order++;
        }
        if(d[(l - 1) / 2][(l - 1) / 2]==1){
        sumrow--;
        sumcolumn--;
        }
            if (sumrow == l) {
                amount++;
            }
            if ( sumcolumn == l) {
                amount++;
            }

    }

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            //read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            String[] data1 = br.readLine().split("","");
            int i = 0;
            while (i < stringCount) {
                announce[i] = data1[i];
                i++;
            }
            int k = 0;
            int x = 0;

            while (k < num) {
                String[] data2 = br.readLine().split("","");
                while (x < num) {
                    matrix[k][x] = data2[x];
                    x++;
                }
                x = 0;
                k++;
            }
            int[][] judge = new int[num][num];
            int y=0;
            int z;
            search:

            while (y < num) {
                z = 0;
                while (z < num) {
                    x = 0;
                    while (x < stringCount) {
                        if (matrix[y][z].equals(announce[x])) {
                            judge[y][z] = 1;
                        }
                        x++;
                        if (x > stringCount) {
                            x = 0;
                        }
                    }
                    z++;
                }
                y++;
            }

            sumrow(judge);
        }

        System.out.println(""there are ""+amount+"" lines in the matrix"");
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

