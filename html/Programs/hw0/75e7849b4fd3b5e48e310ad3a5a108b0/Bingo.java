import java.io.FileReader;
import java.io.BufferedReader;


public class Bingo  {

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
            int[][] match = new int[num][num];
            int temp1 = 0;
            int temp2 = 0;
            int count = 0;
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            String[] m = br.readLine().split("" "");
           
            String[] bg = m[0].split("","");
            
            for(int i=0; i<num; i++){
                String[] bg2 = m[i+1].split("","");
                for(int j=0;j<num;j++){
                    matrix[i][j] = bg2[j];
                    //System.out.println(matrix[i][j]);
                }
            }
            for(int i=0; i<stringCount; i++){
                announce[i] = bg[i];
                //System.out.println(announce[i]);
            }

            for(int i=0; i<num; i++){
                for(int j=0; j<num; j++){
                    match[i][j] = 0;
                    for(int k=0; k<stringCount; k++){
                        if(matrix[i][j].equals(announce[k])) match[i][j]=1;
                    }
                   // System.out.println(match[i][j]);
                }
            }
            for(int i=0; i<num; i++){
                temp1 = 1;
                temp2 = 1;
                for(int j=0; j<num;j++){
                    if(match[i][j]==0) temp1 =0;
                    if(match[j][i]==0) temp2 =0;
                }
                count = count + temp1 + temp2;
            }
            temp1 = 1;
            temp2 = 1;
            for(int i=0; i<num; i++){
                if(match[i][i]==0) temp1 = 0;
                if(match[i][num-1-i]==0) temp2 = 0;
            }
            count = count  + temp1 + temp2;
            
            System.out.println(count);
            
            
            
            
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

