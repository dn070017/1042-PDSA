
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("","");

            int stringCount = Integer.parseInt(data[0]);
            
            int num = Integer.parseInt(data[1]);

            String[] cont = new String[stringCount];
            String[][] matrix = new String[num][num];

            cont = br.readLine().split("","");
            int i;
            int j;
            for (i = 0; i < stringCount; i++) {
            }
            i = 0;
            while (i < num) {
                matrix[i] = br.readLine().split("","");
                i++;
            }
            int[][] one = new int[num][num];

            for (i = 0; i < num; i++) {
                for (j = 0; j < num; j++) {
                    int bin;
                    for (bin = 0; bin < stringCount; bin++) {
                        if (matrix[i][j].compareTo(cont[bin]) == 0) {
                            one[i][j] = 1;
                            break;
                        } else {
                            one[i][j] = 0;
                        }
                    }
                }
            }
            
            int count = 0;
            int k;
            for (i = 0; i < num; i++) {
                k = 0;
                for (j = 0; j < num; j++) {
                    k += one[i][j];
                }
                if (k == num) {
                    count++;
                }
            }
            for (j = 0; j < num; j++) {
                k = 0;
                for (i = 0; i < num; i++) {
                    k += one[i][j];
                }
                if (k == num) {
                    count++;
                }
            }
            k = 0;
            for (i = 0; i < num; i++) {
                k += one[i][i];
            }
            if (k == num) {
                count++;
            }
            k = 0;
            for (i = 0; i < num; i++) {
                k += one[num -1- i][i];
            }
            if (k == num) {
                count++;
            }
System.out.print(count);
            
               
               
            
            
            

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

