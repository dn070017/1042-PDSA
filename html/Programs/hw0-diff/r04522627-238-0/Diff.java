import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {
       
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] matrix_bingo = new int[num][num];

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
            data = br.readLine().split("","");
            System.out.printf(""announced strings:\n"");
            for(int i = 0; i < stringCount; i++)
            {
                announce[i] = data[i];
                System.out.printf(announce[i] + "" "");
            }
            
            System.out.printf(""\nmatrix:\n"");
            for(int i = 0; i < num; i++)
            {
                data = br.readLine().split("","");
                for(int j = 0; j < num; j++)
                {
                    matrix[i][j] = data[j];
                    //標記與announce相同的
                    for(int n = 0; n < announce.length; n++)
                    {
                        if(matrix[i][j].equals(announce[n]))
                        {
                             matrix_bingo[i][j] = 1;
                             break;
                        }
                        else
                        {
                             matrix_bingo[i][j] = 0;
                        }
                    }
                    System.out.printf(matrix[i][j] + "" "");
                }
                System.out.printf(""\n"");
            }
            
            
            //算出直線數
            int numberOfLines = 0;
            //直線與橫線
            int[] temp = new int[3];
            for(int i = 0; i < num; i++)
            {
                temp[0] = temp[1] = 1;
                for(int j = 0; j < num; j++)
                {
                    temp[0] = temp[0] * matrix_bingo[i][j];
                    temp[1] = temp[1] * matrix_bingo[j][i];
                }
                if(temp[0] == 1)
                {
                    numberOfLines++;
                }
                if(temp[1] == 1)
                {
                    numberOfLines++;
                }
            }
            
            //對角線
            for(int n = 0; n < 2; n++)
            {
                temp[2] = 1;
                for(int i = 0; i < num; i++)
                {
                    temp[2] = temp[2] * matrix_bingo[(num - 1 - 2*i) * n + i][i];
                }
            }
            
            
            numberOfLines = temp[0] + temp[1] + temp[2];
            
            System.out.printf(""number of straight lines: %d\n"",numberOfLines);
             
        }
    }
}

