import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Steven
 */
public class Bingo {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
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
            
            announce = br.readLine().split("","");
            
            for(int i=0;i<num;i++){
            matrix[i] = br.readLine().split("","");
            }
            
            for(int j=0;j<stringCount;j++){
                for(int x=0;x<num;x++){
                    for(int y=0;y<num;y++){
                        if(matrix[x][y].equals(announce[j])){
                            matrix[x][y] = ""0"";
                        }
                   }    
                }
            }
            int count=0;
            for(int i=0;i<num;i++){
                if(""0"".equals(matrix[i][0]))
                    count++;
            }
            for(int i=0;i<num;i++){
                if(""0"".equals(matrix[0][i]))
                    count++;
            }
            for(int i=0;i<num;i++){
                if(""0"".equals(matrix[i][i]))
                    count++;
            }
            for(int i=0;i<num;i++)
            {
                if(""0"".equals(matrix[num-i][i]))
                    count++;
            }
            
            // printf in Java (you should comment out or delete this in your final submission)
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
