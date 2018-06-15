
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static int count = 0;

    public static int calculate(boolean[][] check, int num) {

        //calculate the colum
        for (int i = 0; i < num; i++) {

            boolean key = check[0][i];
            for (int j = 1; j < num; j++) {
                key = (check[j][i] && key);
            }

            if (key) {
                count++;
            }
        }

        //calculate the row
        for (int i = 0; i < num; i++) {

            boolean key = check[i][0];
            for (int j = 1; j < num; j++) {
                key = (check[i][j] && key);
            }
            if (key) {
                count++;
            }
        }

//calculate the diagonal
        {
            boolean key = check[0][0];
            for (int i = 1; i < num; i++) {
                key = (check[i][i] && key);
            }

            if (key) {
                count++;
            }
        }

        {
            boolean key = check[num - 1][0];
            for (int i = 1; i < num; i++) {
                key = (check[num - 1 - i][i] && key);
            }
            if (key) {
                count++;
            }
        }

        return count;

    }

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
           // System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            //   System.out.print(announce.length+""\n"");
            announce = br.readLine().split("","");
            boolean[][] check = new boolean[num][num];

            for (int i = 0; i < num; i++) {
                data = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = data[j];
                    check[i][j] = false;
                    for (int k = 0; k < announce.length; k++) {
                        if (matrix[i][j].equals(announce[k])) {
                            check[i][j] = true;
                            //System.out.print(check[i][j]+""\t"");
                        }
                    }

               //     System.out.print(check[i][j] + ""\t"");
                }
     //           System.out.print(""\n"");
            }

            System.out.print(calculate(check, num) + ""\n"");

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

